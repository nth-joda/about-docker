# SPRING BOOT
## **1. JPA / Hibernate**

### **1.1 Relationships**

#### **1.1.1 One-To-One**
- Models / Entities:
```java
@Entity
public class Library {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address physicalAddress;
    // standard constructor, getters, setters
}
```

```java
@Entity
public class Address {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String location;

    @OneToOne(mappedBy = "physicalAddress")
    private Library library;

    // standard constructor, getters, setters
}
```
- Repository:
```java
public interface LibraryRepository extends CrudRepository<Library, Long> {}
```
```java
public interface AddressRepository extends CrudRepository<Address, Long> {}
```
----------
### **1.2 Cascade Type**
- **Definition**: "
Entity relationships often depend on the existence of another entity, for example the Person–Address relationship. Without the Person, the Address entity doesn't have any meaning of its own. When we delete the Person entity, our Address entity should also get deleted.
Cascading is the way to achieve this. ***When we perform some action on the target entity, the same action will be applied to the associated entity.***
"

- **Types:**

|                                  **_JPA_**                                  	|        **_Hibernate_**        	|
|:---------------------------------------------------------------------------:	|:-----------------------------:	|
| **ALL**                                                                         	| **REPLICATE**                     	|
| **PERSIST**: M save --> S save                                                  	| **SAVE_UPDATE**                   	|
| **MERGE**: Load M and S, update đồng thời (Dễ lỗi "Multiple representation...") 	| **LOCK**: Gắn lại quan hệ M and S 	|
| **REMOVE/DELETE**: Delete M --> Delete S                                        	|                               	|
| **REFRESH**                                                                     	|                               	|
| **DETACH**: tách quan hệ M and S                                                	|                               	|
-------------------
