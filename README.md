# 初始化專案
## SQL連線設定

### 預先於本地建立資料庫
''''
CREATE DATABASE commerce_db;
''''
### DB連線資訊
''''
# application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/commerce_db
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
''''