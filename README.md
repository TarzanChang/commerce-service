# E-Commerce Admin Dashboard API 系統開發
根據 https://marmelab.com/react-admin-demo/#/categories 系統，使用 Springboot+MySQL 開發管理後台 API 系統，並以 Swagger 呈現文件。

## 模組規劃：
- 銷售模組
    - 訂單管理
    - 發票管理

- 產品模組
  - 產品管理
  - 產品類別管理  
- 用戶模組
  - 用戶管理
  - 用戶標籤管理  
- 評論模組


# 初始化專案
## SQL連線設定

### 預先於本地建立資料庫
````
CREATE DATABASE commerce_db;
````
### DB連線資訊
````
# application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/commerce_db
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
````