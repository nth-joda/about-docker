# DOCKER

---

## 1. Các lệnh docker cơ bản

- `docker ps` : xem container đang chạy, chạy port nào.
- `docker run <tên_container>` : pull/run image
- `docker build` : build ra 1 image
- `docker push` : đưa 1 img publish (quản lý ở docker hub)
- `docker image ls` : liệt kê các images.
- `docker inspect image <tên_docker_image>` : thông tin chi tiết của 1 image
- `docker container ls` : liệt kê các container đang chạy
- `docker container ls -a`: liệt kê tất cả container (đang chạy / dừng)
- `docker container start <tên_container>`: chạy 1 container đang dừng

## 2. Các thành phần chính của docker:

a. **Docker file**:
<img src="./assets/docker_file.png">

Best practices:

- `RUN <command>` : Mỗi lệnh `RUN` sẽ tạo ra 1 image layer --> Nên gộp các lệnh lại thành 1 lệnh `RUN` để tối ưu thời gian build image, size của image: `RUN <command_1> \ <command_2> \ <command_3> ...`
- `COPY <src_dir> <dst_dir>` : copy file/folder từ host machine vào container
- `ADD <src_dir_OR_url> <dst_dir>` : tương tự `COPY` nhưng có thể tải từ 1 url
- `VOLUME <absolute_path>` : chỉ định 1 directory để lưu trữ dữ liệu trong container
- `EXPOSE <port>` : chỉ định cổng port mà container sẽ dùng

- `CMD [ <executable> , <param_1> , <param_2> ]` : chỉ định command mà container sẽ chạy khi bắt đầu

- `ENTRYPOINT [ <executable> , <param_1> , <param_2> ]` : như `CMD`. Nếu cả `CMD` và `ENTRYOINT` cùng xuất hiện trong **docker file** thì `ENTRYPOINT` là command, `CMD` là params.

b. **Docker image**: được build ra từ **docker file**:
`docker image build -t <tên_image> <vị_trí_lưu_file_image_sau_khi_build> `

c. **Docker container**:

- Cần phải có biến môi trường, cách tạo biếng mtr:
  `export <TÊN_BIẾN>=<GIÁ_TRỊ>`
- Cách chạy container: `docker container run -it -e <BIẾN_ĐẦU_VÀO>=$<BIẾN MÔI TRƯỜNG> -e <BIẾN_ĐẦU_VÀO2>=$<BIẾN MÔI TRƯỜNG2> --name <tên_caontainer>`

# 3. Infrastructure

- Immuatable infrastructure: Đóng gói môi trường thành image, deploy image đó ở môi trường production
  <img src="./assets/Infrastructure.png">

---

# DOCKER-COMPOSE (Multi-container application)

## 1. Docker-compose.yaml

- file mặc định để **docker-compose** định nghĩa application

- Ví dụ sử dụng **docker-compose**: Application gồm:
  - 1 container wordpress: https://hub.docker.com/_/wordpress
  - 1 container mysql db: https://hub.docker.com/_/mysql

Vì 2 containers này có public trên **docker hub**, nên không cần viết **docker file**. Sẽ chỉ định nghĩa chúng trong **docker-compose.yaml**

```yaml
version: "3" # Version của docker-compose, khuyến khích dùng 3

services: # Khai báo các service
  wordpress:
    image: wordpress:5.6-php7.4 # Image của service, kèm version. (được public trên docker hub)
    environment: # Khai báo các biến môi trường của service
      WORDPRESS_DB_HOST: db
      WORDPRESS_DB_USER: wordpress
      WORDPRESS_DB_PASSWORD: 123456
      WORDPRESS_DB_NAME: wordpress
    volumes: # Khai báo các volume của service, được mount vào container; sẽ không mất đi khi container bị xóa
      - wordpress:/var/www/html
    ports:
      - "8080:80" # Khai báo port của service, sẽ được map sang port của host
    restart: always # Khai báo cách restart container khi container bị down
    depends_on: # Khai báo thứ tự chạy các service, ví dụ ở đây: sẽ chỉ chạy 'wordpress' khi 'db' đã chạy xong
      - db

  db:
    image: mysql:5.7
    environment:
      MYSQL_RANDOM_ROOT_PASSWORD: "1"
      MYSQL_DATABASE: wordpress
      MYSQL_USER: wordpress
      MYSQL_PASSWORD: 123456
    volumes:
      - db:/var/lib/mysql
    restart: always # Khai báo cách restart container khi container bị down

volumes:
  wordpress:
  db:
```

# 2. Các lệnh với Docker-compose:

- `docker-compose up` : chạy file `docker-compose.yaml`

```apache
D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker-compose up
Creating network "docker-compose-example-app_default" with the default driver
Creating volume "docker-compose-example-app_wordpress" with default driver
5363b603851d: Pull complete
93fcdb189245: Pull complete
c0025acb1755: Pull complete
bcdfe24e9c2c: Pull complete
ee894f64a1fa: Pull complete
47ebdf2b72a5: Pull complete
53fab7d97aa4: Pull complete
wordpress_1  | AH00558: apache2: Could not reliably determine the server's fully qualified domain name, using 172.18.0.3. Set the 'ServerName' directive globally to suppress this message
wordpress_1  | [Sun Sep 18 04:59:32.787744 2022] [mpm_prefork:notice] [pid 1] AH00163: Apache/2.4.38 (Debian) PHP/7.4.16 configured -- resuming normal operations
wordpress_1  | [Sun Sep 18 04:59:32.787783 2022] [core:notice] [pid 1] AH00094: Command line: 'apache2 -D FOREGROUND'
```

- Sau khi `docker-compose up` chạy xong, `docker-compose ps` sẽ liệt kê các container đang chạy:

```apache
D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker-compose ps
                 Name                               Command               State          Ports
------------------------------------------------------------------------------------------------------
docker-compose-example-app_db_1          docker-entrypoint.sh mysqld      Up      3306/tcp, 33060/tcp
docker-compose-example-app_wordpress_1   docker-entrypoint.sh apach ...   Up      0.0.0.0:8080->80/tcp
```

- `docker-compose up -d` sẽ chạy các container ở background (không liên tục ghi log ở terminal console):

```apache
D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker-compose up -d
Starting docker-compose-example-app_db_1 ... done
Starting docker-compose-example-app_wordpress_1 ... done

D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>
```

- `docker-compose stop` dừng các container chạy ở background bởi `docker-compose up -d`

- `docker-compose config` kiểm tra file `docker-compose.yaml` có setup hợp lệ không, nếu có lỗi sẽ báo dòng bị lỗi.

- `docker-compose start` khởi động lại các container đang dừng (bởi `docker-compose stop`)

- `docker volume ls` liệt kê các volume tạo bởi `docker-compose.yaml`:

```apache
D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker volume ls
DRIVER    VOLUME NAME
local     docker-compose-example-app_db
local     docker-compose-example-app_wordpress
```

- `docker inspect volume <tên_volume>` chi tiết 1 volume:

```apache
D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker inspect volume docker-compose-example-app_db
[
    {
        "CreatedAt": "2022-09-18T05:10:37Z",
        "Driver": "local",
        "Labels": {
            "com.docker.compose.project": "docker-compose-example-app",
            "com.docker.compose.version": "1.29.2",
            "com.docker.compose.volume": "db"
        },
        "Mountpoint": "/var/lib/docker/volumes/docker-compose-example-app_db/_data",
        "Name": "docker-compose-example-app_db",
        "Options": null,
        "Scope": "local"
    }
]
Error: No such object: volume
```

- `sudo ls -la <địa_chỉ_mountpoint>` hiển thị nội dung của mountpoint trên host machine (linux command)
- `docker-compose top [<tên_container>]` liệt kê các process đang chạy trong các container:

```apache
D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker-compose top
docker-compose-example-app_db_1
UID    PID    PPID   C   STIME   TTY     TIME       CMD
---------------------------------------------------------
999    2994   2975   0   05:10   ?     00:00:00   mysqld
root   3370   2975   0   05:24   ?     00:00:00   /bin/sh
root   3388   2975   0   05:24   ?     00:00:00   /bin/sh

docker-compose-example-app_wordpress_1
  UID      PID    PPID   C   STIME   TTY     TIME             CMD
--------------------------------------------------------------------------
root       3188   3157   0   05:10   ?     00:00:00   apache2 -DFOREGROUND
www-data   3338   3188   0   05:10   ?     00:00:00   apache2 -DFOREGROUND
www-data   3339   3188   0   05:10   ?     00:00:00   apache2 -DFOREGROUND
www-data   3340   3188   0   05:10   ?     00:00:00   apache2 -DFOREGROUND
www-data   3341   3188   0   05:10   ?     00:00:00   apache2 -DFOREGROUND
www-data   3342   3188   0   05:10   ?     00:00:00   apache2 -DFOREGROUND
```

- `docker-compose logs` kiểm tra các log chạy trong application:

```apache
D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker-compose logs
Attaching to docker-compose-example-app_wordpress_1, docker-compose-example-app_db_1
db_1         | 2022-09-18 04:59:24+00:00 [Note] [Entrypoint]: Entrypoint script for MySQL Server 5.7.39-1.el7 started.
db_1         | 2022-09-18 04:59:25+00:00 [Note] [Entrypoint]: Switching to dedicated user 'mysql'
db_1         | 2022-09-18 04:59:25+00:00 [Note] [Entrypoint]: Entrypoint script for MySQL Server 5.7.39-1.el7 started.
db_1         | 2022-09-18T05:10:37.654642Z 0 [Note] Skipping generation of SSL certificates as certificate files are
db_1         | 2022-09-18T05:10:37.655330Z 0 [Note] Server socket created on IP: '::'.
db_1         | 2022-09-18T05:10:37.656651Z 0 [Warning] Insecure configuration for --pid-file: Location '/var/run/mysqld' in the path is accessible to all OS users. Consider choosing a different directory.
db_1         | 2022-09-18T05:10:37.661379Z 0 [Note] Event Scheduler: Loaded 0 events
db_1         | 2022-09-18T05:10:37.661543Z 0 [Note] mysqld: ready for connections.
db_1         | Version: '5.7.39'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server (GPL)

```

- `docker container exec -it <id_của_container> <shell_của_container>` truy cập vào 1 container:

```apache
D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker container ps
CONTAINER ID   IMAGE                  COMMAND                  CREATED          STATUS          PORTS                  NAMES
fcdfb158510a   wordpress:5.6-php7.4   "docker-entrypoint.s…"   31 minutes ago   Up 20 minutes   0.0.0.0:8080->80/tcp   docker-compose-example-app_wordpress_1
14be452956d0   mysql:5.7              "docker-entrypoint.s…"   31 minutes ago   Up 20 minutes   3306/tcp, 33060/tcp    docker-compose-example-app_db_1

D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker container exec -it fcdfb158510a /bin/bash
root@fcdfb158510a:/var/www/html#
```

- `docker-compose stop`: dừng các container
- `docker-compose rm`: xóa các container đã dừng:

```apache
D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker-compose stop
Stopping docker-compose-example-app_wordpress_1 ... done
Stopping docker-compose-example-app_db_1        ... done

D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker-compose rm
Going to remove docker-compose-example-app_wordpress_1, docker-compose-example-app_db_1
Are you sure? [yN] y
Removing docker-compose-example-app_wordpress_1 ... done
Removing docker-compose-example-app_db_1        ... done

D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>docker container ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES

D:\Documents\PK's_Workplace\__FIS__\Fis_Projects\TestDocker\docker-compose-example-app>
```
