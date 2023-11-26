# Database_backend

## Getting Started

### 连接数据库

- src/main/resources/application.properties 
- ```
  spring.datasource.url=jdbc:mysql://localhost:3306/RentalVehiclesDB
  spring.datasource.username=superAdminRV
  spring.datasource.password=12345678
- 这里需要替换成自己电脑的database


### 第一次运行程序时
- src/main/resources/application.properties
- ```
  spring.jpa.hibernate.ddl-auto = create //这一行需要使用create
  //---------------
  spring.jpa.hibernate.ddl-auto = update //第二次就换成update
 
- 执行过第一次后就换成update就可以了
- update: Hibernate changes the database according to the given entity structures.
- create: Creates the database every time but does not drop it on close.
