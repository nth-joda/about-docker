# EUREKA - Spring cloud
---------------------------------

## 1. Ví dụ
<img src="https://res.cloudinary.com/deop9ytsv/image/upload/v1545143589/1_Z8HUa8vdvIrF68crSJOPTQ.png" alt="" />
Như system trên, ta cần tạo:

- Service registry (**Eureka Server**)
- Image service
- Gallery service
- Gateway - zuul
### 1.1 Eureka Server
Đây là một máy chủ dùng để quản lý, đặt tên cho các service, hay còn gọi là service registry. Quản lý tên miền - ip của các services.

Mỗi service sẽ được ***đăng ký với Eureka và sẽ ping*** cho Eureka để đảm bảo chúng vẫn hoạt động. Nếu Eureka server không nhận được bất kỳ thông báo nào từ service thì service đó sẽ bị ***gỡ khỏi Eureka một cách tự động***.

**Để tạo Eureka:**
 - File POM.xml phải có eureka dependency:
```xml
    <dependency>  
        <groupId>org.springframework.cloud</groupId>  
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>  
    </dependency>  
```
 - Cấu hình file `application.properties`:
```properties
# Give a name to the eureka server

spring.application.name=eureka-server

# default port for eureka server

server.port=8761

# eureka by default will register itself as a client. So, we need to set it to false.

# What's a client server? See other microservices (image, gallery, auth, etc).

eureka.client.register-with-eureka=false

eureka.client.fetch-registry=false
```
- Class main, application phải có `@EnableEurekaServer`:
```java
package io.github.tubean.eureka.server;  

import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;  
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;  

@SpringBootApplication  
@EnableEurekaServer  
public class ServerApplication {  

    public static void main(String[] args) {  
        SpringApplication.run(ServerApplication.class, args);  
    }  

}
```
**Để đăng ký 1 service vào Eureka:**
- Image Service
    - File `POM.XML`:
    ```xml
    <dependency>  
        <groupId>org.springframework.cloud</groupId>  
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>  
    </dependency>  
    ```
    - File `appication.properties`:

    ```properties
        # serivce name
        spring.application.name=image-service

        # port
        server.port=8200

        # eureka server url
        eureka.client.service-url.default-zone=http://localhost:8761/eureka
    ```
    - Class main, application phải có `@EnableEurekaClient`:
    ```java
    package io.github.tubean.eureka.image;  

    import org.springframework.boot.SpringApplication;  
    import org.springframework.boot.autoconfigure.SpringBootApplication;  
    import org.springframework.cloud.netflix.eureka.EnableEurekaClient;  

    @SpringBootApplication  
    @EnableEurekaClient  
    public class ImageApplication {  

        public static void main(String[] args) {  
            SpringApplication.run(ImageApplication.class, args);  
        }  

    }
    ```
- Gallery Service

    - Setup `pom.xml`, `application.properties` và `main class application` như ***Image Service***.
    - Ngoài ra các service cũng có thể giao tiếp với nhau. Ví dụ, ***Gallery service*** là 1 REST client gọi api đến ***Image service***. Để 1 REST client gọi đến 1 service ta có thể dùng:
        - `RestTemplate`: một object cho phép gửi request đến một REST API service.
        - `FeignClient`: hoạt động như một proxy, cung cấp các giải pháp khác cho RestTemplate.
    - Để setup ***Gallery service*** thành 1 REST client, gọi api đến service khác:
        - Class main application phải tạo thêm 1 Bean cho `RestTemplate`:
        ```java
        package io.github.tubean.eureka.gallery;  

        import org.springframework.boot.SpringApplication;  
        import org.springframework.boot.autoconfigure.SpringBootApplication;  
        import org.springframework.cloud.client.loadbalancer.LoadBalanced;  
        import org.springframework.cloud.netflix.eureka.EnableEurekaClient;  
        import org.springframework.context.annotation.Bean;  
        import org.springframework.context.annotation.Configuration;  
        import org.springframework.web.client.RestTemplate;  

        @SpringBootApplication  
        @EnableEurekaClient  
        public class GalleryServiceApplication {  

            public static void main(String[] args) {  
                SpringApplication.run(GalleryServiceApplication.class, args);  
            }  

        }  

        @Configuration  
        class RestTemplateConfig {  

            // Create a bean for restTemplate to call services  
        @Bean  
        @LoadBalanced // Load balance between service instances running at different ports.  
        public RestTemplate restTemplate() {  
                return new RestTemplate();  
            }  
        }
        ```
    - Ví dụ khi gọi api đến ***Image service***: ***Gallery service*** có 1 controller để load các ảnh trong 1 gallery (id):
        ```java
        @RequestMapping("/{id}")  
        public Gallery getGallery(@PathVariable final int id) {  
            // create gallery object  
            Gallery gallery = new Gallery();  
                gallery.setId(id);  

            // get list of available images   
            List<Object> images = restTemplate.getForObject("http://image-service/images/", List.class);  
            gallery.setImages(images);  

            return gallery;  
        }  
        ```
        Trong đó, `image-service` là host name của ***Image service***.

- Gateway - zuul
    - Vai trò:
        - Map giữa một prefix path (/gallery/**) và một service (gallery-service). Nó sử dụng Euraka server để định tuyến các service được request.
        - Nó giúp cân bằng tải giữa các instance của một service.
        - filter request, thêm xác thực,…
    - Setup:
        - File `pom.xml`: có dependencies `Web`, `Eureka Client` và `Zuul`.
        ```xml
        <dependency>  
            <groupId>org.springframework.boot</groupId>  
            <artifactId>spring-boot-starter-web</artifactId>  
        </dependency>  
        <dependency>  
            <groupId>org.springframework.cloud</groupId>  
            <artifactId>spring-cloud-starter-netflix-zuul</artifactId>  
        </dependency>  
        <dependency>  
            <groupId>org.springframework.cloud</groupId>  
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>  
        </dependency>  
        ```
        - Zuul hoat59 động như 1 eureka client, nên có thể định nghãi host name và port trong `application.properties`:
        ```properties
        server.port=8762

        spring.application.name=zuul-server

        eureka.client.service-url.default-zone=http://localhost:8761/eureka/

        # A prefix that can added to beginning of all requests.

        #zuul.prefix=/api

        # Disable accessing services using service name (i.e. gallery-service).

        # They should be only accessed through the path defined below.

        zuul.ignored-services=*

        # Map paths to services

        zuul.routes.gallery-service.path=/gallery/**

        zuul.routes.gallery-service.service-id=gallery-service
        ```
        - Trong main class application phải có tag `@EnableZuulProxy` và `@EnableEurekaClient`:
        ```java
        package io.github.tubean.eureka.zuulserver;  

        import org.springframework.boot.SpringApplication;  
        import org.springframework.boot.autoconfigure.SpringBootApplication;  
        import org.springframework.cloud.netflix.eureka.EnableEurekaClient;  
        import org.springframework.cloud.netflix.zuul.EnableZuulProxy;  

        @SpringBootApplication  
        @EnableEurekaClient // It acts as a eureka client  
        @EnableZuulProxy // Enable Zuul  
        public class ZuulServerApplication {  

            public static void main(String[] args) {  
                SpringApplication.run(ZuulServerApplication.class, args);  
            }  

        }
        ```


-----------------------------------------------------------------------

## Ref
- <a href="https://tubean.github.io/2018/12/microservice-springboot-eureka/">[Microservice] Dựng Microservice web bằng Spring Boot và Eureka [Phần 1]</a> 