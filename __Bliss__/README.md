Learn
1. #Java
    -  thread
    -  collection
    -  kiểu dữ liệu
    -  data
    -  design pattern
2. #Git flow
tên branch : featureA -> merged to Dev to test
4. #Redis 
what, Use case
6. #Rabbit MQ

7. #Gearman 

3. #Linux - ubuntu
create GG Cloud compute
Basic comands

8. #Elk stack 

5. #MySql 
9. #Angular 
B1: 1-4. 3/11
B2: 5-7. 8/11
B3: 8-9. 11/11
-----------------

# Java
1. Collections:

<img src="https://images.viblo.asia/363a4ddb-6a25-44e7-b1e7-7e4f81f1b17a.gif" />

    - Collection ~> Iterator: Base, cac interface khac ke thua tu Collection
    - Set : unique values
        - TreeSet: sorted values already
        - HashSet: using Hash Table
        - EnumSet: elements are ENUM
    - List:
        - ArrayList: changable size array

        ```java
            import java.util.*;  
            class TestJavaCollection1{  
                public static void main(String args[]){  
                    ArrayList<String> list=new ArrayList<String>();//Creating arraylist  
                    list.add("Ravi");//Adding object in arraylist  
                    list.add("Vijay");  
                    list.add("Ravi");  
                    list.add("Ajay");  
                    //Traversing list through Iterator  
                    Iterator itr=list.iterator();  
                    while(itr.hasNext()){  
                        System.out.println(itr.next());  
                    }  
                }  
            }  
        ```

        - LinkedList: double-linked list
        - Vector: ~ ArrayList, synchronized, dynamic array
        - Stack: based onb stack = LIFO (last-in-first-out)

    - Queue
        - LinkedList
        - PriorityQueue
        - ArrayDeque: queue 2 directions

    - Map
        - TreeMap: cây đỏ đen (Red-Black tree) trong đó các key đã được sắp xếp
        - HashMap: using HashTable --> effective search (O(1))
        - EnumMap: 
        - WeakHashMap

2. kiểu dữ liệu. bytes…
    - Primitives
        - Boolean (1 bit)
        - Char (2 bytes)
        - Byte (1 byte) : [-128, 127]
        - Short (2 bytes) : [-32000, 32000]
        - Int (4 bytes): [-2ty, 2ty]
        - Long (8 bytes): 
        - Float (4 bytes)
        - Double (8 bytes)
    - None Primitives
        - String
        - Array
        - Class
