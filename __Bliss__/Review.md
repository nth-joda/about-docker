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
#### 1.4.1 Factory Method
- Why? 
    - Do not care the specific class of an instance --> Easier for scability
    <img src="https://refactoring.guru/images/patterns/diagrams/factory-method/structure.png" />
    - Chỉ cần quann tâm nó là `Product`
    ```java
    Product Creator.createProduct(String productType){
        if(productType == "a") return new A();
        else if (productType == "b") return new B();
        return null;
    }

    Product a = Creator.createProduct("a");
    Product b = Creator.createProduct("b");
    ```

#### 1.4.2 Builder

- Giảm nhẹ việc tạo instance với nhiều params.
- Tách biệt param bắt buộc và param optional: `ComputerBuilder(
            "500 GB", "2 GB").setBluetoothEnabled(true)
            .setGraphicsCardEnabled(true).build(); `

```java
package com.journaldev.design.builder;

public class Computer {
	
	//required parameters
	private String HDD;
	private String RAM;
	
	//optional parameters
	private boolean isGraphicsCardEnabled;
	private boolean isBluetoothEnabled;
	

	public String getHDD() {
		return HDD;
	}

	public String getRAM() {
		return RAM;
	}

	public boolean isGraphicsCardEnabled() {
		return isGraphicsCardEnabled;
	}

	public boolean isBluetoothEnabled() {
		return isBluetoothEnabled;
	}
	
	private Computer(ComputerBuilder builder) {
		this.HDD=builder.HDD;
		this.RAM=builder.RAM;
		this.isGraphicsCardEnabled=builder.isGraphicsCardEnabled;
		this.isBluetoothEnabled=builder.isBluetoothEnabled;
	}
	
	//Builder Class
	public static class ComputerBuilder{

		// required parameters
		private String HDD;
		private String RAM;

		// optional parameters
		private boolean isGraphicsCardEnabled;
		private boolean isBluetoothEnabled;
		
		public ComputerBuilder(String hdd, String ram){
			this.HDD = hdd;
			this.RAM = ram;
		}

		public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
			this.isGraphicsCardEnabled = isGraphicsCardEnabled;
			return this;
		}

		public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
			this.isBluetoothEnabled = isBluetoothEnabled;
			return this;
		}
		
		public Computer build(){
			return new Computer(this);
		}

	}

}
```

```java
package com.journaldev.design.test;

import com.journaldev.design.builder.Computer;

public class TestBuilderPattern {

	public static void main(String[] args) {
		//Using builder to get the object in a single line of code and 
                //without any inconsistent state or arguments management issues		
		Computer comp = new Computer.ComputerBuilder(
				"500 GB", "2 GB").setBluetoothEnabled(true)
				.setGraphicsCardEnabled(true).build();
	}

}
```

#### 1.4.3 Prototype

- Tạo (copy) object dựa trên 1 object mẫu với low cost
- Class có các phương thức (shadow/deep)copy:
- Khi dùng:
    ```c#
        // Thực thi shallow copy của P1 và truyền nó cho P1
        Person p2 = p1.ShallowCopy();
        // Thực thi deep copy của P1 và truyền cho P3, no reflection
        Person p3 = p1.DeepCopy();
    ```

```c#
public class Person
    {
        public int Age;
        public DateTime BirthDate;
        public string Name;
        public IdInfo IdInfo;

        //Các object con bên trong chỉ được copy reference. Nghĩa là chỉ nhân bản được value type.
        public Person ShallowCopy()
        {
            return (Person)this.MemberwiseClone();
        }

        //Các object con bên trong cũng được copy lại toàn bộ các thuộc tính.
        public Person DeepCopy()
        {
            Person clone = (Person)this.MemberwiseClone();
            clone.IdInfo = new IdInfo(IdInfo.IdNumber);
            clone.Name = String.Copy(Name);
            return clone;
        }
    }

    public class IdInfo
    {
        public int IdNumber;

        public IdInfo(int idNumber)
        {
            this.IdNumber = idNumber;
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Person p1 = new Person();
            p1.Age = 42;
            p1.BirthDate = Convert.ToDateTime("1977-01-01");
            p1.Name = "Jack Daniels";
            p1.IdInfo = new IdInfo(666);

            // Thực thi shallow copy của P1 và truyền nó cho P1
            Person p2 = p1.ShallowCopy();
            // Thực thi deep copy của P1 và truyền cho P3
            Person p3 = p1.DeepCopy();

            // Hiển thị giá trị P1, P2, P3
            Console.WriteLine("Original values of p1, p2, p3:");
            Console.WriteLine("   p1 instance values: ");
            DisplayValues(p1);
            Console.WriteLine("   p2 instance values:");
            DisplayValues(p2);
            Console.WriteLine("   p3 instance values:");
            DisplayValues(p3);

            // Thay đổi giá trị của thuộc tính P1 và hiển thị giá trị P1, P2, P3
            p1.Age = 32;
            p1.BirthDate = Convert.ToDateTime("1900-01-01");
            p1.Name = "Frank";
            p1.IdInfo.IdNumber = 7878;
            Console.WriteLine("\nValues of p1, p2 and p3 after changes to p1:");
            Console.WriteLine("   p1 instance values: ");
            DisplayValues(p1);
            Console.WriteLine("   p2 instance values (reference values have changed):");
            DisplayValues(p2);
            Console.WriteLine("   p3 instance values (everything was kept the same):");
            DisplayValues(p3);
        }

        public static void DisplayValues(Person p)
        {
            Console.WriteLine("      Name: {0:s}, Age: {1:d}, BirthDate: {2:MM/dd/yy}",
                p.Name, p.Age, p.BirthDate);
            Console.WriteLine("      ID#: {0:d}", p.IdInfo.IdNumber);
        }
    }   
```