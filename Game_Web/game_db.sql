CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;
/*Tao cac o du lieu lien quan den database*/
CREATE TABLE if NOT EXISTS cart (
cart_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	total DECIMAL(20,2) DEFAULT 0,
	quantity BIGINT DEFAULT 0,
	created_ar DATETIME DEFAULT NOW(),
	modified_at DATETIME,
	DELETE BOOLEAN DEFAULT false
)


CREATE TABLE if NOT EXISTS cart_item(
cart_id BIGINT,
product_id BIGINT,
own_price DECIMAL(20,2) DEFAULT 0,
own_quantity BIGINT DEFAULT 0,
created_at DATETIME DEFAULT NOW(),
modified_at DATETIME 
deleted BOOLEAN DEFAULT false
)
ALTER TABLE cart_item ADD CONSTRAINT PK_cart_item PRIMARY KEY(cart_id, product_id);
CREATE TABLE if NOT EXISTS game(
product_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
game_type BIGINT NULL,
game_name VARCHAR(100) NOT NULL,
game_price DECIMAL(20,2),
game_image NVARCHAR(MAX),
game_rating FLOAT,
game_status BIT NULL,
platform_id BIGINT NULL
)
CREATE TABLE if NOT EXISTS platform(
platform_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
platform_name VARCHAR(100)
)

/*Them vao cac foreign key*/
 