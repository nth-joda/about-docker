# DATABASE

---

## 1. `___ JOIN` ... `ON`

-   Kết nối các cột từ nhiều bảng

<img  style="width:80%;" src="https://ugoproto.github.io/ugodoc/img/introduction_to_sql_joins/Visual_SQL_JOINS_V2.png" />

-   Ví dụ có 2 bảng như sau:

```sql
TABLE_A
  PK Value
---- ----------
   1 FOX
   2 COP
   3 TAXI
   6 WASHINGTON
   7 DELL
   5 ARIZONA
   4 LINCOLN
  10 LUCENT

TABLE_B
  PK Value
---- ----------
   1 TROT
   2 CAR
   3 CAB
   6 MONUMENT
   7 PC
   8 MICROSOFT
   9 APPLE
  11 SCOTCH
```

### 1.1 `inner join`

-   Lấy các **most common** giữa A và B

```sql
SELECT <select_list>
FROM Table_A A
INNER JOIN Table_B B
ON A.Key = B.Key
```

-   Ví dụ:

```sql
--INNER JOIN--
SELECT A.PK AS A_PK, A.Value AS A_Value,
       B.Value AS B_Value, B.PK AS B_PK
FROM Table_A A
INNER JOIN Table_B B
ON A.PK = B.PK
```

```sql
-- RESULT:
A_PK A_Value    B_Value    B_PK
---- ---------- ---------- ----
   1 FOX        TROT          1
   2 COP        CAR           2
   3 TAXI       CAB           3
   6 WASHINGTON MONUMENT      6
   7 DELL       PC            7

(5 row(s) affected)
```

### 1.2 `left join`

-   Lấy tất cả phẩn tử của A và vài phần tử của B thỏa điều kiện join, ko thỏa thì `null`

```sql
SELECT <select_list>
FROM Table_A A
LEFT JOIN Table_B B
ON A.Key = B.Key
```

-   Ví dụ:

```sql
--LEFT JOIN
SELECT A.PK AS A_PK, A.Value AS A_Value,
B.Value AS B_Value, B.PK AS B_PK
FROM Table_A A
LEFT JOIN Table_B B
ON A.PK = B.PK
```

```sql
-- RESULT:
A_PK A_Value    B_Value    B_PK
---- ---------- ---------- ----
   1 FOX        TROT          1
   2 COP        CAR           2
   3 TAXI       CAB           3
   4 LINCOLN    NULL       NULL
   5 ARIZONA    NULL       NULL
   6 WASHINGTON MONUMENT      6
   7 DELL       PC            7
  10 LUCENT     NULL       NULL

(8 row(s) affected)
```

### 1.3 `outer join`

-   Là FULL JOIN / FULL OUTER JOIN: lấy tất cả.

```sql
SELECT <select_list>
FROM Table_A A
FULL OUTER JOIN Table_B B
ON A.Key = B.Key
```

<img style="width:50%" src="https://ugoproto.github.io/ugodoc/img/introduction_to_sql_joins/FULL_OUTER_JOIN.png" />

-   Ví dụ:

```sql
--OUTER JOIN
SELECT A.PK AS A_PK, A.Value AS A_Value,
B.Value AS B_Value, B.PK AS B_PK
FROM Table_A A
FULL OUTER JOIN Table_B B
ON A.PK = B.PK
```

```sql
-- RESULT:
A_PK A_Value    B_Value    B_PK
---- ---------- ---------- ----
   1 FOX        TROT          1
   2 COP        CAR           2
   3 TAXI       CAB           3
   6 WASHINGTON MONUMENT      6
   7 DELL       PC            7
NULL NULL       MICROSOFT     8
NULL NULL       APPLE         9
NULL NULL       SCOTCH       11
   5 ARIZONA    NULL       NULL
   4 LINCOLN    NULL       NULL
  10 LUCENT     NULL       NULL

(11 row(s) affected)
```

## 2. DML vs DDL

-   **DML - Data Manipulation Language** : Ngôn ngữ thao tác dữ liệu (`INSERT`, `UPDATE`, `DELETE`, ...)
-   **DDL - Data Definition Language**: Ngôn ngữ định nghĩa dữ liệu (`CREATE`, `ALTER`, `DROP`, ...)

## 3. Thứ tự các mệnh đề trong SQL:

**`SELECT`** > **`FROM`** > `WHERE` > `GROUP BY` > `HAVING` > `ORDER BY`

-   Trình tự thực thi:
    -   FROM
        -   ON
        -   JOIN
    -   WHERE
    -   GROUP BY: sử dụng khi có aggregation function
    -   HAVING : aggregation conditions, ex: `COUNT(items) > 5`
    -   SELECT
    -   DISTINCT
    -   ORDER BY
    -   TOP

## 4. Relationships
