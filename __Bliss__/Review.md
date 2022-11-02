Review
---------------------------
# Buoi 1 (3/11)
1. Java
    - Thread
    - Collection
    - Datatype
    - Design patterns
2. Git flow
3. Ubuntu
4. Redis
-----------------------------
## 1. Java
### 1.1 Thread 

- Xử lý đa luồng
- Có 2 cách tạo 1 thread trong java:
    - **entends class `Thread`**: override `run()`, tạo object, `object.start();` để chạy thread.
    ```java
    public class MyThread extends Thread {
        public void run() {System.out.println("MyThread running");}
    }

    public class MainProject {

        public static void main(String[] args) {
            System.out.println("Hello World!");
            MyThread myThread = new MyThread();
            myThread.start();
            System.out.println("Hello World222!");
        }
    }
    ```
    - **implement interface `Runnable`**: override `run()`, tạo object myThread, tạo object Thread(myThread).start() 
    ```java
    public class MyThread  implements Runnable {
        public void run() {System.out.println("MyThread running");}
    }

    public class MainProject {
        public static void main(String[] args) {
            System.out.println("Hello World!");

            MyThread myThread = new MyThread();
            Thread thread = new Thread(myThread);
            thread.start();

            System.out.println("Hello World222!");
        }
    }
    ```
(?) Khi dùng multithread với các biến `static` (singleton, etc.) "Conccurrency Problem"
- Trong hàm gọi khởi tạo thread, `while (myThread.isAlive()) { ...do somethings ... }`
- Safe Thread with singleton:
    - `synchronized`  khởi tạo instance trong hàm `getInstance` method kèm với doubled-checking:
    ```java
    public static Singleton getInstance() {
        if(singleton == null) {
            synchronized(Singleton.class) {
                if(singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
    ```
    (!) Nhưng ko hoàn toàn tối ưu. --> Khởi tạo instance khi định nghĩa class
    ```java
    public class MySingleton (){
        private static final MySingleton instance = new MySingleton()
        private MySingleton(){}
        public static MySingleton getInstance(){
            return instance;
        }
    }
    ```
### 1.2 Collection
<img src="https://images.viblo.asia/6080e672-c9c9-49c9-852c-6b0552b8ea08.png" />

`Collections` - class cung cấp các static methods, hỗ trợ thao tác tìm, sắp xếp, chèn, xoá ...

```
   - addAll(Collection<? super T> c, T…elements): Thêm tất cả các phần tử được chỉ định vào colletion được chỉ định.

    - reverse(List<?> list): Xáo trộn các phần tử có trong List được chỉ định.

    - sort(List<?> list): Sắp xếp các phần tử của List. Với điều kiện các phần tử của List phải là kiểu Comparable. Nghĩa là lớp các phần tử phải được implements giao diện Comparable

    - sort(List<?> list, Comparator<? super T> c): Sắp xếp các phần tử theo comparator đã chỉ định.

    - swap(List<?> list, int i, int j): Hoán đổi các phần tử tại vị trí i và j.
```

`collection` là 1 interface **được** implemented bởi các collection khác.

#### 1.2.1 Các lớp trong collection framework:

- 1. List: lưu els dưới dạng danh sách
    - `ArrayList`: allows duplicates, order-keep, async, access-by-index, slow speed when adding el --> Dùng cho mảng cần nhiều truy xuất.
    - `LinkedList`: allows dup, order-insert, async, slow retrieve than ArrayList, fast insert/del.
- 2. Set: 
    - `HashSet`: no dup, Hashing technique, allow `null` el, async, not order --> Searching
    - `TreeSet`: no dup, fast "GET", no `null` el, sort els.
- 3. Queue

- 4. Map
    - `HashMap`: key based, no dup keys, async, allow 1 `null` key, multi `null` values
    - `TreeMap`: no allow `null` key, key in order

### 1.3 Datatype

#### 1.3.1 Primitives
    - 1. Boolean (1 bit): `true | false`
    - 2. Char (2 bytes): 1 byte for char 1 byte for ascii

    - 3. Byte (1 byte) : [-128, 127]
    - 4. Short (2 bytes) : [-32000, 32000]
    - 5. Int (4 bytes): [-2ty, 2ty]
    - 6. Long (8 bytes): 
    
    - 7. Float (4 bytes)
    - 8. Double (8 bytes)

### 1.3.2 None Primitives
    - 1. String
    - 2. Array
    - 3. Class

#### 1.3.2 Non-Primitives

### 1.4 Design patterns
