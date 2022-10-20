# Java

---

## 1. `Final`

<img src="https://media.geeksforgeeks.org/wp-content/uploads/20220225104709/FinalkeywordinJava-768x384.jpg" />

```java
// Java Program to demonstrate Different
// Ways of Initializing a final Variable

// Main class
class GFG {

    // a final variable
    // direct initialize
    final int THRESHOLD = 5;

    // a blank final variable
    final int CAPACITY;

    // another blank final variable
    final int  MINIMUM;

    // a final static variable PI
    // direct initialize
    static final double PI = 3.141592653589793;

    // a  blank final static  variable
    static final double EULERCONSTANT;

    // instance initializer block for
    // initializing CAPACITY
    {
        CAPACITY = 25;
    }

    // static initializer block for
    // initializing EULERCONSTANT
    static{
        EULERCONSTANT = 2.3;
    }

    // constructor for initializing MINIMUM
    // Note that if there are more than one
    // constructor, you must initialize MINIMUM
    // in them also
    public GFG()
    {
        MINIMUM = -1;
    }

}
```

## 2. `Static`

-   Chỉ phụ thuộc vào class, không phụ thuộc objects
-   Khởi tạo 1 lần khi chương trình thực thi
-   Có thể truy cập trực tiếp bằng tên class không cần object

## 3. `interface` vs `abstract class`

<img src="https://media.geeksforgeeks.org/wp-content/uploads/Abstract-Class-vs-Interface.png" />

-   **Abstract Class**

```java
// Class 1
// Helper abstract class
abstract class Shape {

    // Declare fields
    String objectName = " ";

    // Constructor of this class
    Shape(String name) { this.objectName = name; }

    // Method
    // Non-abstract methods
    // Having as default implementation
    public void moveTo(int x, int y)
    {
        System.out.println(this.objectName + " "
                           + "has been moved to"
                           + " x = " + x + " and y = " + y);
    }

    // Method 2
    // Abstract methods which will be
    // implemented by its subclass(es)
    abstract public double area();
    abstract public void draw();
}

// Class 2
// Helper class extending Class 1
class Rectangle extends Shape {

    // Attributes of rectangle
    int length, width;

    // Constructor
    Rectangle(int length, int width, String name)
    {

        // Super keyword refers to current instance itself
        super(name);

        // this keyword refers to current instance itself
        this.length = length;
        this.width = width;
    }

    // Method 1
    // To draw rectangle
    @Override public void draw()
    {
        System.out.println("Rectangle has been drawn ");
    }

    // Method 2
    // To compute rectangle area
    @Override public double area()
    {
        // Length * Breadth
        return (double)(length * width);
    }
}

// Class 3
// Helper class extending Class 1
class Circle extends Shape {

    // Attributes of a Circle
    double pi = 3.14;
    int radius;

    // Constructor
    Circle(int radius, String name)
    {
        // Super keyword refers to parent class
        super(name);
        // This keyword refers to current instance itself
        this.radius = radius;
    }

    // Method 1
    // To draw circle
    @Override public void draw()
    {
        // Print statement
        System.out.println("Circle has been drawn ");
    }

    // Method 2
    // To compute circle area
    @Override public double area()
    {
        return (double)((pi * radius * radius));
    }
}

// Class 4
// Main class
class GFG {

    // Main driver method
    public static void main(String[] args)
    {
        // Creating the Object of Rectangle class
        // and using shape class reference.
        Shape rect = new Rectangle(2, 3, "Rectangle");

        System.out.println("Area of rectangle: "
                           + rect.area());

        rect.moveTo(1, 2);

        System.out.println(" ");

        // Creating the Objects of circle class
        Shape circle = new Circle(2, "Circle");

        System.out.println("Area of circle: "
                           + circle.area());

        circle.moveTo(2, 4);
    }
}
```

```
<!-- ------------------------------- OUTPUT -------------------------------- -->
Area of rectangle: 6.0
Rectangle has been moved to x = 1 and y = 2

Area of circle: 12.56
Circle has been moved to x = 2 and y = 4
```

-   **Interface**

```java
// Interface
interface Shape {

    // Abstract method
    void draw();
    double area();
}

// Class 1
// Helper class
class Rectangle implements Shape {

    int length, width;

    // constructor
    Rectangle(int length, int width)
    {
        this.length = length;
        this.width = width;
    }

    @Override public void draw()
    {
        System.out.println("Rectangle has been drawn ");
    }

    @Override public double area()
    {
        return (double)(length * width);
    }
}

// Class 2
// Helper class
class Circle implements Shape {

    double pi = 3.14;
    int radius;

    // constructor
    Circle(int radius) { this.radius = radius; }

    @Override public void draw()
    {
        System.out.println("Circle has been drawn ");
    }

    @Override public double area()
    {

        return (double)((pi * radius * radius));
    }
}

// Class 3
// Main class
class GFG {

    // Main driver method
    public static void main(String[] args)
    {
        // Creating the Object of Rectangle class
        // and using shape interface reference.
        Shape rect = new Rectangle(2, 3);

        System.out.println("Area of rectangle: "
                           + rect.area());

        // Creating the Objects of circle class
        Shape circle = new Circle(2);

        System.out.println("Area of circle: "
                           + circle.area());
    }
}
```

```
<!-- ------------------------------- OUTPUT -------------------------------- -->
Area of rectangle: 6.0
Area of circle: 12.56
```

## 4. `Super`

```java
class Animal { // Superclass (parent)
  public void animalSound() {
    System.out.println("The animal makes a sound");
  }
}

class Dog extends Animal { // Subclass (child)
  public void animalSound() {
    super.animalSound(); // Call the superclass method
    System.out.println("The dog says: bow wow");
  }
}

public class Main {
  public static void main(String args[]) {
    Animal myDog = new Dog(); // Create a Dog object
    myDog.animalSound(); // Call the method on the Dog object
  }
}
```

```
<!-- ------------------------------- OUTPUT -------------------------------- -->
The animal makes a sound
The dog says: bow wow
```
