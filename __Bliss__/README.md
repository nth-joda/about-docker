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
<img src="https://images.viblo.asia/363a4ddb-6a25-44e7-b1e7-7e4f81f1b17a.gif" />

1. Collections:
    - Collection kethua Iterator: Base, cac interface khac ke thua tu Collection
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

3. Thread
    - Do multiple tasks at same time
    - 2 ways to create a thread:
        - extends the `Thread`
        - implement `Runnable`


# Linux - ubuntu
--------------


# Redis
--> (+) nhanh, da dung, open source, many data types, persistence
--> (-) cost, storage, No UI
--------------------------------
1. Kieu DL:
    - String 
    - List
    - Hash
    - Set
    - Sorted Set
    - Bit Map
    - Geospatial indexes
    - Streams
2. Use Cases:
    - Caching
3. Commands:
    ```redis
        SET <key> <value> : Them/sua gtri cua 1 key
        GET <key> : Lay gtri cua key

        EXISTS <key> : (integer) tra ve so luong gia tri cua key

        EXPIRE <key> <number_seconds_to_delete>: Set khoang t/g ton tai
        TLL <key>: number of seconds to live

        <----STRING-------->
        STRLEN <key>: length of key (of string type)

        APPEND <key> "<string_of_value_to_append>": add the value to the key's old

        SETRANGE <key> <num_last_letters> <replacing_value>: Thay doi <num> last letter bang <value>

        <----NUM-------->
        INCR <key> : increase value of <key> by 1 unit
        DECR <key> : decrease ....

        INCRBY <key> <value>: increase value of <key> by <value> unit
        DECRBY <key> <value>: decrease ....

        <----ARRAY------->
        RPUSH <key> <value>: add <value> to the bottom of the <key>
        LPUSH <key> <value>: .........top.....

        LRANGE <key> <index_1> <index_2>: Lay cac gia tri trong <key> tu vi tri <index_1> den vi tri <index_2> tu Left to Right

        LPOP <key>: (tra ve element) xoa element o vi tri dau.
        RPOP <key>: (tra ve element) xoa element o vi tri cuoi.

        LINDEX <key> <index>: tra ve element of vi tri <index>
        LLEN <key>: tra ve chieu dai cua ds <key>
        LSET <key> <index> <element>: gan gtri element o vi tri <index> bang <element>

        DEL <key> : xoa ca ds <key>

        <---HASH------>
        HSET <key> <field> <value>: set <value> into <field> of hashtable <key>
        HGET <key> <field>: get value of <field>

        HMSET <key> [<field> <value>] : set <value> into relavant <field>
        HMGET <key> [<field>]: get values of [<field>]
    ```
    
