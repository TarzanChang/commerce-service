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
### 資料表設計
- users table

| **column_name**  	| **data_type** 	| **nullable** 	 | **descrption**             	|
|------------------	|---------------	|----------------|----------------------------	|
| user_id          	| int           	| n            	 | primary key,auto_increment 	|
| first_name       	| string        	| n            	 | varchar(20),名             	|
| last_name        	| string        	| n            	 | varchar(20),姓             	|
| email            	| string        	| n            	 | varchar(50),電子信箱       	|
| birthday         	| date          	| y            	 | 生日                       	|
| address          	| string        	| n            	 | varchar(200),地址          	|
| city             	| string        	| n            	 | varchar(50),城市           	|
| state            	| string        	| n            	 | varchar(50),區域           	|
| zipcode          	| string        	| n            	 | varchar(50),郵遞區號       	|
| password         	| string        	| n            	 | varchar(10),密碼           	|
| role             	| string        	| y            	 | varchar(10),角色           	|
| has_newsletter   	| boolen        	| y            	 | 有無新訊息                 	|
| delete_at        	| date_time     	| y            	 |                            	|
| creation_date    	| date_time     	| n            	 |                            	|
| created_by       	| string        	| n            	 |                            	|
| last_update_date 	| date_time     	| n            	 |                            	|
| last_updated_by  	| string        	| n            	 |                            	|

- segments table

| **column_name**  	| **data_type** 	| **nullable** 	| **descrption**             	|
|------------------	|---------------	|--------------	|----------------------------	|
| segment_id       	| int           	| n            	| primary key,auto_increment 	|
| segment_name     	| string        	| n            	| varchar(20)                	|
| delete_at        	| date_time     	| y            	|                            	|
| creation_date    	| date_time     	| n            	|                            	|
| created_by       	| string        	| n            	|                            	|
| last_update_date 	| date_time     	| n            	|                            	|
| last_updated_by  	| string        	| n            	|                            	|

- users_segments table

| **column_name**  	| **data_type** 	| **nullable** 	| **descrption**                     	|
|------------------	|---------------	|--------------	|------------------------------------	|
| user_segment_id  	| int           	| n            	| primary key,auto_increment         	|
| user_id          	| int           	| n            	| FK1,reference users(user_id)       	|
| segment_id       	| int           	| n            	| FK1,reference segments(segment_id) 	|
| delete_at        	| date_time     	| y            	|                                    	|
| creation_date    	| date_time     	| n            	|                                    	|
| created_by       	| string        	| n            	|                                    	|
| last_update_date 	| date_time     	| n            	|                                    	|
| last_updated_by  	| string        	| n            	|                                    	|

- orders table

| **column_name**  	 | **data_type** 	| **nullable** 	| **descrption**                                    	|
|--------------------|---------------	|--------------	|---------------------------------------------------	|
| order_id         	 | int           	| n            	| primary key,auto_increment                        	|
| order_status     	 | string        	| n            	| varchar(10),訂單狀態, ordered,delivered,cancelled 	|
| order_date       	 | date          	| n            	| 訂單日期                                          	|
| user_id          	 | int           	| n            	| FK,reference users(user_id)                       	|
| delete_at        	 | date_time     	| y            	|                                                   	|
| creation_date    	 | date_time     	| n            	|                                                   	|
| created_by       	 | string        	| n            	|                                                   	|
| last_update_date 	 | date_time     	| n            	|                                                   	|
| last_updated_by  	 | string        	| n            	|                                                   	|

- orders_detail table

| **column_name**  	| **data_type** 	| **nullable** 	| **descrption**                     	|
|------------------	|---------------	|--------------	|------------------------------------	|
| od_detail_id     	| int           	| n            	| primary key,auto_increment         	|
| order_id         	| int           	| n            	| FK1,reference orders(order_id)     	|
| product_id       	| int           	| n            	| FK1,reference products(product_id) 	|
| quantity         	| int           	| y            	| 數量                               	|
| creation_date    	| date_time     	| n            	|                                    	|
| created_by       	| string        	| n            	|                                    	|
| last_update_date 	| date_time     	| n            	|                                    	|
| last_updated_by  	| string        	| n            	|                                    	|

- invoices table

| **column_name**  	| **data_type** 	| **nullable** 	| **descrption**                	|
|------------------	|---------------	|--------------	|-------------------------------	|
| invoices_id      	| int           	| n            	| primary key,auto_increment    	|
| invoices_date    	| date          	| n            	| 訂單日期                      	|
| order_id         	| int           	| n            	| FK,reference orders(order_id) 	|
| creation_date    	| date_time     	| n            	|                               	|
| created_by       	| string        	| n            	|                               	|
| last_update_date 	| date_time     	| n            	|                               	|
| last_updated_by  	| string        	| n            	|                               	|

- categories table

| **column_name**  	| **data_type** 	| **nullable** 	| **descrption**             	|
|------------------	|---------------	|--------------	|----------------------------	|
| category_id      	| int           	| n            	| primary key,auto_increment 	|
| category_name    	| string        	| n            	| varchar(20)                	|
| delete_at        	| date_time     	| y            	|                            	|
| creation_date    	| date_time     	| n            	|                            	|
| created_by       	| string        	| n            	|                            	|
| last_update_date 	| date_time     	| n            	|                            	|
| last_updated_by  	| string        	| n            	|                            	|

- products table

| **column_name**  	| **data_type** 	| **nullable** 	| **descrption**                      	|
|------------------	|---------------	|--------------	|-------------------------------------	|
| product_id       	| int           	| n            	| primary key,auto_increment          	|
| product_name     	| string        	| n            	| varchar(50),產品名稱                	|
| category_id      	| int           	| n            	| FK,reference categorys(category_id) 	|
| width            	| decimal(10,2) 	| y            	| 寬度                                	|
| height           	| decimal(10,2) 	| y            	| 長度                                	|
| price            	| decimal(10,2) 	| y            	| 價格                                	|
| stock            	| int           	| y            	| 庫存                                	|
| description      	| string        	| y            	| varchar(500)                        	|
| creation_date    	| date_time     	| n            	|                                     	|
| created_by       	| string        	| n            	|                                     	|
| last_update_date 	| date_time     	| n            	|                                     	|
| last_updated_by  	| string        	| n            	|                                     	|

- product_reviews table

| **column_name**  	 | **data_type** 	| **nullable** 	| **descrption**                     	|
|--------------------|---------------	|--------------	|------------------------------------	|
| pt_review_id     	 | int           	| n            	| primary key,auto_increment         	|
| review_date      	 | date_time     	| n            	|                                    	|
| product_id       	 | int           	| n            	| FK1,reference products(product_id) 	|
| user_id          	 | int           	| n            	| FK2,reference users(user_id)       	|
| rating           	 | int           	| y            	|                                    	|
| comment          	 | string        	| y            	| varchar(300)                       	|
| review_status    	 | string        	| n            	| accepted,rejected,pending          	|
| delete_at        	 | date_time     	| y            	|                                    	|
| creation_date    	 | date_time     	| n            	|                                    	|
| created_by       	 | string        	| n            	|                                    	|
| last_update_date 	 | date_time     	| n            	|                                    	|
| last_updated_by  	 | string        	| n            	|                                    	|