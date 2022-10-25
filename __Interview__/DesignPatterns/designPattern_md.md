# Design Patterns

---

## 0. REFs:

-   [The Catalog of Design Patterns](https://refactoring.guru/design-patterns/catalog)

-   [Tổng hợp các bài hướng dẫn về Design Pattern - 23 mẫu cơ bản của GoF](https://viblo.asia/p/tong-hop-cac-bai-huong-dan-ve-design-pattern-23-mau-co-ban-cua-gof-3P0lPQPG5ox)

## 1. Creational Design Patterns

### 1.1 Factory Method

-   Context: More Classes appears when developing the system. Ex, when a logistic company develop more to transport by ships from its original method of truck.

    <img src="https://refactoring.guru/images/patterns/diagrams/factory-method/solution1.png" />

-   Design:
    <img src="https://refactoring.guru/images/patterns/diagrams/factory-method/structure.png" />

    ```java
    // The creator class declares the factory method that must
    // return an object of a product class. The creator's subclasses
    // usually provide the implementation of this method.
    class Dialog
        // The creator may also provide some default implementation
        // of the factory method.
        abstract method createButton():Button

        // Note that, despite its name, the creator's primary
        // responsibility isn't creating products. It usually
        // contains some core business logic that relies on product
        // objects returned by the factory method. Subclasses can
        // indirectly change that business logic by overriding the
        // factory method and returning a different type of product
        // from it.
        method render() is
            // Call the factory method to create a product object.
            Button okButton = createButton()
            // Now use the product.
            okButton.onClick(closeDialog)
            okButton.render()


    // Concrete creators override the factory method to change the
    // resulting product's type.
    class WindowsDialog extends Dialog is
        method createButton():Button is
            return new WindowsButton()

    class WebDialog extends Dialog is
        method createButton():Button is
            return new HTMLButton()


    // The product interface declares the operations that all
    // concrete products must implement.
    interface Button is
        method render()
        method onClick(f)

    // Concrete products provide various implementations of the
    // product interface.
    class WindowsButton implements Button is
        method render(a, b) is
            // Render a button in Windows style.
        method onClick(f) is
            // Bind a native OS click event.

    class HTMLButton implements Button is
        method render(a, b) is
            // Return an HTML representation of a button.
        method onClick(f) is
            // Bind a web browser click event.


    class Application is
        field dialog: Dialog

        // The application picks a creator's type depending on the
        // current configuration or environment settings.
        method initialize() is
            config = readApplicationConfigFile()

            if (config.OS == "Windows") then
                dialog = new WindowsDialog()
            else if (config.OS == "Web") then
                dialog = new WebDialog()
            else
                throw new Exception("Error! Unknown operating system.")

        // The client code works with an instance of a concrete
        // creator, albeit through its base interface. As long as
        // the client keeps working with the creator via the base
        // interface, you can pass it any creator's subclass.
        method main() is
            this.initialize()
            dialog.render()
    ```

### 1.2 Abstract Factory

-   Context
-   Design

### 1.3 Builder

-   Context
-   Design

### 1.4 Prototype

-   Context
-   Design

### 1.5 Singleton

-   Context:

    -   Đảm bảo rằng chỉ có một instance của lớp.
    -   Việc quản lý việc truy cập tốt hơn vì chỉ có một thể hiện duy nhất.
    -   Có thể quản lý số lượng thể hiện của một lớp trong giới hạn chỉ định.

-   Design

    -   `private constructor` để hạn chế truy cập từ class bên ngoài.
    -   Đặt `private static final` variable đảm bảo biến chỉ được khởi tạo trong class.
    -   Có `một method public static` để `return instance` được khởi tạo ở trên.

    ```java
    package com.gpcoder.patterns.creational.singleton;

    public class LazyInitializedSingleton {

        private LazyInitializedSingleton() {
            // 1) private constructor
        }

        private static LazyInitializedSingleton instance; // 2) private static final

        public static LazyInitializedSingleton getInstance() {
            if (instance == null) {
                instance = new LazyInitializedSingleton();
            }
            return instance;
            // 3) public static method => instance
        }
    }
    ```

## 2. Structural Patterns

### 2.1 Adapter

-   Context
-   Design

### 2.2 Bridge

-   Context
-   Design

### 2.3 Composite

-   Context
-   Design

### 2.4 Destructor

-   Context
-   Design

### 2.5 Facade

-   Context
-   Design

### 2.6 Flyweight

-   Context
-   Design

### 2.7 Proxy

-   Context
-   Design

## 3. Behavioral Patterns

### 3.1 Chain of Responsibility

-   Context
-   Design

### 3.2 Command

-   Context
-   Design

### 3.3 Iterator

-   Context
-   Design

### 3.4 Mediator

-   Context
-   Design

### 3.5 Memento

-   Context
-   Design

### 3.6 Observer

-   Context
-   Design

### 3.7 State

-   Context
-   Design

### 3.8 Strategy

-   Context:
    <img src="https://gpcoder.com/wp-content/uploads/2019/01/design-patterns-strategy-diagram.png" />

    -   Định nghĩa **tập hợp các thuật toán**, đóng gói từng thuật toán lại, và dễ dàng **thay đổi linh hoạt các thuật toán** bên trong object.
    -   Strategy cho phép thuật toán **biến đổi độc lập** khi người dùng sử dụng chúng.

    ---> Giúp **tách rời phần xử lý một chức năng cụ thể** ra khỏi đối tượng.

-   Design:

    -   `Strategy` : định nghĩa các hành vi có thể có của một Strategy.
    -   `ConcreteStrategy` : cài đặt các hành vi cụ thể của Strategy.
    -   `Context` : chứa một tham chiếu đến đối tượng Strategy và nhận các yêu cầu từ Client, các yêu cầu này sau đó được ủy quyền cho Strategy thực hiện.

    Ví dụ: Ta có nhiều algorithms để sắp xếp 1 `list`. Dựa vào đặc tính của list, các algorithms sẽ có performance khác nhau. Để quản lý chung, thì mỗi List ta tạo thành 1 object `sortedList` với các private properties như `sortStrategy`, `items`, và public phương thức `sort()`. Do đó khi khởi tạo object, ta có thể định nghĩa các **chiến thuật** sort khác nhau.

    <img src="https://gpcoder.com/wp-content/uploads/2019/01/design-patterns-strategy-example.png" />

    ```java
    // File `SortStrategy.java`
    package com.gpcoder.patterns.behavioral.strategy.sort;

    import java.util.List;

    public interface SortStrategy {

        <T> void sort(List<T> items);
    }
    ```

    ```java
    // File `QuickSort.java`
    package com.gpcoder.patterns.behavioral.strategy.sort;

    import java.util.List;

    public class QuickSort implements SortStrategy {

        @Override
        public <T> void sort(List<T> items) {
            System.out.println("Quick sort algorithms");
        }
    }
    ```

    ```java
    // File `MergeSort.java`
    package com.gpcoder.patterns.behavioral.strategy.sort;

    import java.util.List;

    public class MergeSort implements SortStrategy {

        @Override
        public <T> void sort(List<T> items) {
            System.out.println("Merge sort algorithms");
        }
    }
    ```

    ```java
    // File `SortStrategy.java`
    package com.gpcoder.patterns.behavioral.strategy.sort;

    import java.util.ArrayList;
    import java.util.List;

    public class SortedList {

        private SortStrategy strategy;
        private List<String> items = new ArrayList<>();

        public void setSortStrategy(SortStrategy strategy) {
            this.strategy = strategy;
        }

        public void add(String name) {
            items.add(name);
        }

        public void sort() {
            strategy.sort(items);
        }
    }
    ```

    ```java
    // File `StrategyPatternExample.java`
    package com.gpcoder.patterns.behavioral.strategy.sort;

    public class StrategyPatternExample {

        public static void main(String[] args) {

            SortedList sortedList = new SortedList();
            sortedList.add("Java Core");
            sortedList.add("Java Design Pattern");
            sortedList.add("Java Library");
            sortedList.add("Java Framework");

            sortedList.setSortStrategy(new QuickSort());
            sortedList.sort();

            sortedList.setSortStrategy(new MergeSort());
            sortedList.sort();
        }
    }
    ```

    ```java
    // Output
        Quick sort
        Merge sort
    ```

### 3.9 Template Method

-   Context:
    -   Tạo ra một bộ khung (template) cho một vấn đề đang cần giải quyết.
    -   Trong đó các đối tượng cụ thể sẽ có **cùng các bước** thực hiện, nhưng trong **mỗi bước thực hiện đó có thể khác nhau**.
    -   Điều này sẽ tạo nên một cách thức **truy cập giống nhau** nhưng có **hành động và kết quả khác nhau**.
-   Design:
    <img src="https://gpcoder.com/wp-content/uploads/2019/01/design-patterns-template-method-diagram.png"/>

    `Template medthod` gồm 2 components chính:

    -   **AbstractClass** :

        -   Định nghĩa các phương thức trừu tượng cho từng bước có thể được điều chỉnh bởi các lớp con.
        -   Cài đặt một phương thức duy nhất điều khiển thuật toán và gọi các bước riêng lẻ đã được cài đặt ở các lớp con.

    -   **ConcreteClass** : là một thuật toán cụ thể, cài đặt các phương thức của AbstractClass. Các thuật toán này ghi đè lên các phương thức trừu tượng để cung cấp các triển khai thực sự. Nó không thể ghi đè phương thức duy nhất đã được cài đặt ở AbstractClass (templateMethod).

    Ví dụ: 1 website gồm header, footer, navigation (menu), body. Riêng phần body thường xuyên thay đổi, sẽ hiển thị riêng theo từng trang. Thay vì phải viết tất cả các phần ở mỗi trang, chúng ta có thể gom chúng lại và đặt trong một template để tái sử dụng mà không duplicate code ở nhiều nơi.
    <img src="https://gpcoder.com/wp-content/uploads/2019/01/design-patterns-template-method-example.png" />

    ```java
    // File `PageTemplate.java`
    package com.gpcoder.patterns.behavioral.templatemethod.template;

    public abstract class PageTemplate {

        protected void showHeader() {
            // Định nghĩa showHeader
            System.out.println("<header />");
        }

        protected void showNavigation() {
            System.out.println("<nav />");
        }

        protected void showFooter() {
            System.out.println("<footer />");
        }

        protected abstract void showBody();

        public final void showPage() {
            // Phương thức showPage: tuần tự show Header, Nav, Body, Footer
            showHeader();
            showNavigation();
            showBody(); // Phương thức này có tính thay đổi lớn -->abstract và định nghĩa bởi các con của PageTemplate
            showFooter();
        }
    }
    ```

    ```java
    // File `HomePage.java`
    package com.gpcoder.patterns.behavioral.templatemethod.template;

    public class HomePage extends PageTemplate {

        // Kế thừa các thuộc tính/phương thức của PageTemplate: showHeader(), showNavigation(), showFooter().

        // Định nghĩa abstract method showBody()
        @Override
        protected void showBody() {
            System.out.println("Content of Body, defined by HomePage");
        }
    }
    ```

    ```java
    // File `DetailPage.java`
    package com.gpcoder.patterns.behavioral.templatemethod.template;

    public class DetailPage extends PageTemplate {

        @Override
        protected void showBody() {
            System.out.println("Content of Body, defined by DetailPage");
        }
    }
    ```

    ```java
    // File `ContactPage.java`
    package com.gpcoder.patterns.behavioral.templatemethod.template;

    public class ContactPage extends PageTemplate {

        @Override
        protected void showNavigation() {
            // Overide lại phương thức showNavigation của PageTemplate.
            // Just do nothing
            // Because we don't want to show navigation bar on contact page
        }

        @Override
        protected void showBody() {
            System.out.println("Content of Body, defined by ContactPage");
        }
    }
    ```

    ```java
    // File `TemplateMethodPatternExample.java`
    package com.gpcoder.patterns.behavioral.templatemethod.template;

    public class TemplateMethodPatternExample {

        public static void main(String[] args) {

            // Dùng gọi PageTemplate tạo ra 3 page : Home, Detail, Contact:

            PageTemplate homePage = new HomePage();
            homePage.showPage();

            System.out.println();
            PageTemplate detailPage = new DetailPage();
            detailPage.showPage();

            System.out.println();
            PageTemplate contactPage = new ContactPage();
            contactPage.showPage();
        }
    }
    ```

    ```html
    <!-- Output: -->

    <!-- Start HomePage v -->
    <header />

    <nav />
    Content of Body, defined by HomePage

    <footer />
    <!-- End HomePage ^ -->

    <!-- Start DetailPage v -->
    <header />

    <nav />
    Content of Body, defined by DetailPage

    <footer />
    <!-- End DetailPage ^ -->

    <!-- Start ContactPage v -->
    <header />
    Content of Body, defined by ContactPage

    <footer />
    <!-- End ContactPage ^ -->
    ```

### 3.10 Visitor

-   Context
-   Design

## Cheatsheet

<img src="https://substackcdn.com/image/fetch/w_1456,c_limit,f_webp,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F4371b692-437f-4b0f-ad92-3c8ee578b75a_1252x1606.png" alt=""/>

<img src="https://substackcdn.com/image/fetch/w_1456,c_limit,f_webp,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2Fd70dd65e-c070-44e8-87aa-91b3c91b7cd1_1226x1610.png" alt="" />
