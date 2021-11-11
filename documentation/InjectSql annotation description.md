## InjectSql annotation description

### Purpose

Created in order to inject value from sql stored in files 

### Target element type

Applicable to **fields** only

### Use

1) Create SLQ file in **resources** folder of the project
2) Annotate class fields with `@InjectSql` annotation
3) Provide path value using classpath (for example: `"classpath:findAll.sql"`)  

Sample:  
```java
@InjectSql("classpath:findAll.sql")
private String FIND_ALL_SQL;
```