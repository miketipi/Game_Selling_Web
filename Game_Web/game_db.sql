CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;
/*Tao cac o du lieu lien quan den database*/
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
CREATE TABLE if NOT EXISTS cart (
cart_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT null,
	total DECIMAL(20,2) DEFAULT 0,
	quantity BIGINT DEFAULT 0,
	created_at DATETIME DEFAULT NOW(),
	modified_at DATETIME,
	deleted BOOLEAN DEFAULT FALSE,
	user_id BIGINT NOT NULL 
);
CREATE TABLE if NOT EXISTS favourite (
	fav_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT null,
	user_id BIGINT NOT NULL,
	created_at DATETIME DEFAULT NOW(),
	modified_at DATETIME,
	deleted BOOLEAN DEFAULT false
);

CREATE TABLE if NOT EXISTS favourite_item (
	fav_id BIGINT NOT null,
	product_id BIGINT NOT null,
	created_at DATETIME DEFAULT NOW(),
	modified_at DATETIME,
	deleted BOOLEAN DEFAULT false
);
ALTER TABLE favourite_item ADD CONSTRAINT PK_fav_item PRIMARY KEY(fav_id, product_id);
CREATE TABLE if NOT EXISTS cart_item(
cart_id BIGINT NOT null,
product_id BIGINT NOT null,
own_price DECIMAL(20,2) DEFAULT 0,
own_quantity BIGINT DEFAULT 0,
created_at DATETIME DEFAULT NOW(),
modified_at DATETIME ,
deleted BOOLEAN DEFAULT false
);
ALTER TABLE cart_item ADD CONSTRAINT PK_cart_item PRIMARY KEY(cart_id, product_id);
CREATE TABLE if NOT EXISTS game(
product_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
game_type_id BIGINT NULL,
game_name VARCHAR(100) NOT NULL,
game_price DECIMAL(20,2),
game_image NVARCHAR(400),
game_rating FLOAT,
game_status VARCHAR(100) DEFAULT "available",
platform_id BIGINT NULL,
game_version NVARCHAR(100),
game_downloaded BIGINT DEFAULT 0,
publisher_id BIGINT,
deleted BOOLEAN DEFAULT FALSE,
created_at DATETIME DEFAULT NOW(),
modified_at DATETIME 
);
CREATE TABLE if NOT EXISTS platform(
platform_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
platform_name nVARCHAR(100)
);
CREATE TABLE if NOT EXISTS gametype(
game_type_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
game_type_name nVARCHAR(100)
);
CREATE TABLE if NOT EXISTS users(
user_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
user_name NVARCHAR(100) NOT NULL unique,
real_name NVARCHAR(100),
pass_word NVARCHAR(100) NOT NULL,
adresss NVARCHAR(100),
phone NVARCHAR(10),
ROLE VARCHAR(5) NOT NULL DEFAULT 'USER',
created_at DATETIME DEFAULT NOW(),
modified_at DATETIME ,
deleted BOOLEAN DEFAULT false
);

CREATE TABLE if NOT EXISTS publisher(
publisher_id bigINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
publisher_name NVARCHAR(100));

/*Them vao cac foreign key*/
/*Foreign key cua game*/
ALTER TABLE game ADD CONSTRAINT FK_game_publisher FOREIGN KEY (`publisher_id`) REFERENCES publisher(`publisher_id`);
ALTER TABLE game ADD CONSTRAINT FK_game_platform FOREIGN KEY (`platform_id`) REFERENCES platform(`platform_id`);
ALTER TABLE game ADD CONSTRAINT FK_game_type FOREIGN KEY(`game_type_id`) REFERENCES gametype(`game_type_id`);
/*Foreign key cua cart*/
ALTER TABLE cart ADD CONSTRAINT FK_cart_user FOREIGN KEY (`user_id`) REFERENCES users(`user_id`);
/*Foreign key cua cart_item*/
ALTER TABLE cart_item ADD CONSTRAINT FK_cart_item_game FOREIGN KEY(`product_id`) REFERENCES game(`product_id`);
ALTER TABLE cart_item ADD CONSTRAINT FK_cart_item_cart FOREIGN KEY(`cart_id`) REFERENCES cart(`cart_id`);
/*Foreign key cua favorite*/
ALTER TABLE favourite ADD CONSTRAINT FK_fav_user FOREIGN KEY (`user_id`) REFERENCES users(`user_id`);
/*Foreign key cua favorite_item*/
ALTER TABLE favourite_item ADD CONSTRAINT FK_fav_item_user FOREIGN KEY (`product_id`) REFERENCES game(`product_id`);
ALTER TABLE favourite_item ADD CONSTRAINT FK_fav_item_fav FOREIGN KEY(`fav_id`) REFERENCES favourite(`fav_id`);
/*Cac trigger cua game*/
delimiter $$
CREATE TRIGGER `TRG_game_created_at` BEFORE INSERT ON `game`
 FOR EACH ROW BEGIN
	SET NEW.created_at = NOW();
END $$
delimiter;

delimiter $$
CREATE TRIGGER `TRG_game_modified_at` BEFORE UPDATE ON `game`
 FOR EACH ROW BEGIN
	SET NEW.modified_at = NOW();
END $$
delimiter;

delimiter $$
CREATE TRIGGER `TRG_cart_created_at` BEFORE INSERT ON `cart`
 FOR EACH ROW BEGIN
	SET NEW.created_at = NOW();
END $$
delimiter;

delimiter $$
CREATE TRIGGER `TRG_cart_modified_at` BEFORE UPDATE ON `cart`
 FOR EACH ROW BEGIN
	SET NEW.modified_at = NOW();
END $$
delimiter;
delimiter $$
CREATE TRIGGER `TRG_favourite_created_at` BEFORE INSERT ON `favourite`
 FOR EACH ROW BEGIN
	SET NEW.created_at = NOW();
END $$
delimiter;

delimiter $$
CREATE TRIGGER `TRG_favourite_modified_at` BEFORE UPDATE ON `favourite`
 FOR EACH ROW BEGIN
	SET NEW.modified_at = NOW();
END $$
delimiter;
delimiter $$
CREATE TRIGGER `TRG_cart_item_created_at` BEFORE INSERT ON `cart_item`
 FOR EACH ROW BEGIN
	SET NEW.created_at = NOW();
END $$
delimiter;

delimiter $$
CREATE TRIGGER `TRG_cart_item_modified_at` BEFORE UPDATE ON `cart_item`
 FOR EACH ROW BEGIN
	SET NEW.modified_at = NOW();
END $$
delimiter;
delimiter $$
CREATE TRIGGER `TRG_favourite_item_created_at` BEFORE INSERT ON `favourite_item`
 FOR EACH ROW BEGIN
	SET NEW.created_at = NOW();
END $$
delimiter;

delimiter $$
CREATE TRIGGER `TRG_favourite_item_modified_at` BEFORE UPDATE ON `favourite_item`
 FOR EACH ROW BEGIN
	SET NEW.modified_at = NOW();
END $$
delimiter;
delimiter $$

CREATE TRIGGER `TRG_user_created_at` BEFORE INSERT ON `users`
 FOR EACH ROW BEGIN
	SET NEW.created_at = NOW();
END $$
delimiter;

delimiter $$
CREATE TRIGGER `TRG_user_modified_at` BEFORE UPDATE ON `users`
 FOR EACH ROW BEGIN
	SET NEW.modified_at = NOW();
END $$
delimiter ;
/*Them du lieu nen tang */
INSERT INTO platform (platform_id, platform_name)
VALUES (1, 'Steam');

INSERT INTO platform (platform_id, platform_name)
VALUES (2, 'Sony PlayStation');

INSERT INTO platform (platform_id, platform_name)
VALUES (3, 'Microsoft Xbox');

INSERT INTO platform (platform_id, platform_name)
VALUES (4, 'Nintendo Switch');

INSERT INTO platform (platform_id, platform_name)
VALUES (5, 'PC');

INSERT INTO platform (platform_id, platform_name)
VALUES (6, 'Mac');

INSERT INTO platform (platform_id, platform_name)
VALUES (7, 'Linux');

INSERT INTO platform (platform_id, platform_name)
VALUES (8, 'iOS');

INSERT INTO platform (platform_id, platform_name)
VALUES (9, 'Android');

INSERT INTO platform (platform_id, platform_name)
VALUES (10, 'VR');

/*Them du lieu loai game*/
insert into gametype(game_type_id,game_type_name)  
values ('1','fps');
insert into gametype(game_type_id,game_type_name)
values ('2','Third-Person shooter');
insert into gametype(game_type_id,game_type_name)
values ('3','Simulation');
insert into gametype(game_type_id,game_type_name)
values ('4','Racing');
insert into gametype(game_type_id,game_type_name)
values ('5','PVP');
insert into gametype(game_type_id,game_type_name)
values ('6','Action');
insert into gametype(game_type_id,game_type_name)
values ('7','Puzzle');
insert into gametype(game_type_id,game_type_name)
values ('8','Sport');
insert into gametype(game_type_id,game_type_name)
values ('9','Casual');
insert into gametype(game_type_id,game_type_name)
values ('10', 'Horror');
/*Them du lieu publisher*/
INSERT INTO publisher (publisher_id, publisher_name)
VALUES (1, 'Tencent Games');

INSERT INTO publisher (publisher_id, publisher_name)
VALUES (2, 'Sony Interactive Entertainment');

INSERT INTO publisher (publisher_id, publisher_name)
VALUES (3, 'Microsoft');

INSERT INTO publisher (publisher_id, publisher_name)
VALUES (4, 'Activision Blizzard');

INSERT INTO publisher (publisher_id, publisher_name)
VALUES (5, 'Electronic Arts (EA)');

INSERT INTO publisher (publisher_id, publisher_name)
VALUES (6, 'Nintendo');

INSERT INTO publisher (publisher_id, publisher_name)
VALUES (7, 'Bandai Namco');

INSERT INTO publisher (publisher_id, publisher_name)
VALUES (8, 'Take-Two Interactive');

INSERT INTO publisher (publisher_id, publisher_name)
VALUES (9, 'Ubisoft');

INSERT INTO publisher (publisher_id, publisher_name)
VALUES (10, 'Square Enix');
/*Them du lieu game*/
INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1000', '1', 'Left 4 dead 2', '120', 'https://cdn.cloudflare.steamstatic.com/steam/apps/550/header.jpg?t=1666824129', '3.5', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1001', '1', 'Counter-strike Global Offensive', '350', 'https://didongviet.vn/dchannel/wp-content/uploads/2022/10/csgo-di-dong-viet-14.jpg', '4.5', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1002', '2', 'Risk of rain 2', '180', 'https://upload.wikimedia.org/wikipedia/en/c/c1/Risk_of_Rain_2.jpg', '4.0', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1003', '3', 'GTA V', '500', 'https://gamedva.com/wp-content/uploads/GTA-5-Grand-Theft-Auto-V.jpg', '3.8', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1004', '3', 'Hero Siege', '120', 'https://cdn.cloudflare.steamstatic.com/steam/apps/269210/header.jpg?t=1669637809', '3.2', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1005', '1', 'Rainbow Six Siege', '300', 'https://image.api.playstation.com/vulcan/ap/rnd/202209/2121/UlfMBx2yUHge8Vlz7eszqw13.png', '3.5', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1006', '4', 'Mario Kart Wii', '90', 'https://m.media-amazon.com/images/M/MV5BNzQ1ZTZmMjctZjk0MS00YTdiLWE5MjEtYjVkZmY3Y2I5MTllXkEyXkFqcGdeQXVyMTAyNzc0MDkz._V1_FMjpg_UX1000_.jpg', '5', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1007', '3', 'Pokémon Unite', '12', 'https://image.thanhnien.vn/w2048/Uploaded/2023/fsmym/2021_08_21/moba-pokemon-unite-ra-mat-tren-android-ios02_eclv.jpg', '4', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1008', '6', 'Frontier Hunter: Erza’s Wheel of Fortune', '198', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1429500/header.jpg?t=1671299332', '4.8', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1009', '6', 'Overcooked! 2', '300', 'https://cdn.cloudflare.steamstatic.com/steam/apps/728880/header.jpg?t=1670442579', '3.8', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');
INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1010', '1', 'Call of Duty®: Modern Warfare® II', '800', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1938090/header.jpg?t=1671472823', '3', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1011', '6', 'Borderlands 2', '83', 'https://cdn.cloudflare.steamstatic.com/steam/apps/49520/header.jpg?t=1645058069', '4.2', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1012', '5', 'Pummel Party', '165', 'https://cdn.cloudflare.steamstatic.com/steam/apps/880940/header.jpg?t=1670489597', '3.9', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1013', '6', 'PICO PARK', '70', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1509960/header.jpg?t=1627200665', '4.2', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1014', '3', 'Vampire Survivors', '70', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1794680/header.jpg?t=1671207503', '3.5', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1015', '2', 'Dying Light 2 Stay Human', '990', 'https://cdn.cloudflare.steamstatic.com/steam/apps/534380/header.jpg?t=1672330001', '4', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1016', '3', 'Tiny Civilization', '30', 'https://cdn.cloudflare.steamstatic.com/steam/apps/2230280/header.jpg?t=1672557857', '4.6', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1017', '9', 'Melatonin', '205', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1585220/header.jpg?t=1671915401', '4.7', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1018', '9', 'Chicken Invaders Universe', '100', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1510460/header.jpg?t=1671113704', '3.5', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1019', '3', 'X-Plane 12', '480', 'https://cdn.cloudflare.steamstatic.com/steam/apps/2014780/header.jpg?t=1671633662', '3.5', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1020', '5', 'Brawlhalla', '330', 'https://cdn.cloudflare.steamstatic.com/steam/apps/291550/header.jpg?t=1668629907', '3.9', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1021', '6', 'Dota 2', '10', 'https://cdn.cloudflare.steamstatic.com/steam/apps/570/header.jpg?t=1666237243', '3.5', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1022', '2', 'SCUM', '280', 'https://cdn.cloudflare.steamstatic.com/steam/apps/513710/header.jpg?t=1671208269', '3', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1023', '3', 'Terraria', '120', 'https://cdn.cloudflare.steamstatic.com/steam/apps/105600/header.jpg?t=1666290860', '4.9', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1024', '10', 'Phasmophobia', '160', 'https://cdn.cloudflare.steamstatic.com/steam/apps/739630/header.jpg?t=1672281192', '4.4', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1025', '9', 'Human: Fall Flat', '198', 'https://cdn.cloudflare.steamstatic.com/steam/apps/477160/header.jpg?t=1668685016', '3.9', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1026', '6', 'Castle Crashers', '165', 'https://cdn.cloudflare.steamstatic.com/steam/apps/204360/header.jpg?t=1671827960', '4.9', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1027', '2', 'METAL SLUG', '100', 'https://cdn.cloudflare.steamstatic.com/steam/apps/366250/header.jpg?t=1584641206', '4.2', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1028', '9', 'Beat Saber', '250', 'https://cdn.cloudflare.steamstatic.com/steam/apps/620980/header.jpg?t=1622461922', '4.8', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1029', '1', 'Blood Trail', '220', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1032430/header.jpg?t=1657139427', '4.1', 'not available', RAND()*10, '', FLOOR(RAND()*10000), '1');

INSERT INTO game (product_id, game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id)
VALUES ('1030', '6', 'Return to abyss', '56', 'https://cdn.cloudflare.steamstatic.com/steam/apps/2185780/header.jpg?t=1673082640', '3.2', 'available', RAND()*10, '', FLOOR(RAND()*10000), '1');

/*Insert thông tin vào user*/
INSERT INTO users (user_name, real_name, pass_word, adresss, phone, ROLE)
VALUES ('phuc', 'Real Name 1', '123456', 'Address 1', '1234567890', 'USER');

INSERT INTO users (user_name, real_name, pass_word, adresss, phone, ROLE)
VALUES ('User2', 'Real Name 2', 'password2', 'Address 2', '1234567891', 'USER');

INSERT INTO users (user_name, real_name, pass_word, adresss, phone, ROLE)
VALUES ('User3', 'Real Name 3', 'password3', 'Address 3', '1234567892', 'USER');

INSERT INTO users (user_name, real_name, pass_word, adresss, phone, ROLE)
VALUES ('User4', 'Real Name 4', 'password4', 'Address 4', '1234567893', 'USER');

INSERT INTO users (user_name, real_name, pass_word, adresss, phone, ROLE)
VALUES ('tphuc', 'Real Name 5', '12345', 'Address 5', '1234567894', 'ADMIN');

COMMIT;

