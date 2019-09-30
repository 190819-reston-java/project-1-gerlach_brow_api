CREATE DATABASE test;

CREATE TABLE "user" (
	id serial PRIMARY KEY,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	is_admin boolean NOT NULL,
	email varchar(50) NOT NULL UNIQUE,
	"password" varchar(50) NOT NULL,
	address varchar(100),
	address_2 varchar(100),
	phone_number varchar(15),
	"position" varchar(50)
);

CREATE TABLE Transactions (
	id serial PRIMARY KEY,
	user_id int REFERENCES "user"(id) NOT NULL,
	status varchar(50),
	manager_name varchar(50),
	trans_date timestamp,
	imgUrl varchar(1000),
	"comment" varchar (250)
);

CREATE TABLE Trimg (
	id serial PRIMARY KEY,
	user_id int not null,
	receipt_img bytea
);

DROP TABLE "user";
DROP TABLE Transactions;
DROP TABLE trimg;

INSERT INTO "user" (first_name, last_name, is_admin, email, "password", address, address_2, phone_number, position)
VALUES ('Jan', 'Jansen', 'NO', 'jelly@jelly.roll', 'wasspord', '777', '888', '3211231111', 'Mom of Sales');
INSERT INTO "user" (first_name, last_name, is_admin, email, "password", address, address_2, phone_number, position)
VALUES ('Jiggy', 'Jergins', 'YES', 'taco@taco.tuesday', 'wasspord', '787', '898', '123-321-3333', 'Dad of Sales');
INSERT INTO "user" (first_name, last_name, is_admin, email, "password", address, address_2, phone_number, position)
VALUES ('Tom', 'Selleck', 'NO', 'freshlyfired@whoa.ree', 'wasspord', '808', '333r3', '321-123-1111', 'Plumber');
INSERT INTO "user" (first_name, last_name, is_admin, email, "password", address, address_2, phone_number, position)
VALUES ('Leslie', 'Wentz', 'YES', 'Hello@world.foobar', 'wasspord', 're132 s Java st', '88438', '843-043-2323', 'Manager of Sales');
INSERT INTO "user" (first_name, last_name, is_admin, email, "password", address, address_2, phone_number, position)
VALUES ('Stacy', 'Stefanovic', 'NO', 'S@ss.lard', 'wasspord', '12423 S Washing Avenue North', '', '7832133234', 'Local Transient');

INSERT INTO transactions (user_id, status, manager_name, imgUrl, "comment", trans_date)
VALUES ('1', 'pending', NULL, NULL, 'Charging for emotional damages', current_timestamp);
INSERT INTO transactions (user_id, status, manager_name, imgUrl, "comment", trans_date)
VALUES ('1', 'resolved', 'Leslie', NULL, 'Someone stole my stapler', current_timestamp);
INSERT INTO transactions (user_id, status, manager_name, imgUrl, "comment", trans_date)
VALUES ('1', 'resolved', 'Leslie', NULL, '', current_timestamp);
INSERT INTO transactions (user_id, status, manager_name, imgUrl, "comment", trans_date)
VALUES ('3', 'resolved', 'Jiggy', NULL, 'Charging for emotional damages', current_timestamp);
INSERT INTO transactions (user_id, status, manager_name, imgUrl, "comment", trans_date)
VALUES ('5', 'resolved', 'Jiggy', NULL, 'Someone licked all my envelopes! I am very upset about this because I can no longer use them!', current_timestamp);
INSERT INTO transactions (user_id, status, manager_name, imgUrl, "comment", trans_date)
VALUES ('1', 'pending', NULL, NULL, 'Tom farted on my sandwich', current_timestamp);
INSERT INTO transactions (user_id, status, manager_name, imgUrl, "comment", trans_date)
VALUES ('3', 'pending', NULL, NULL, 'Leslie is slandering my good name', current_timestamp);
INSERT INTO transactions (user_id, status, manager_name, imgUrl, "comment", trans_date)
VALUES ('3', 'resolved', 'Leslie', NULL, 'Bought lunch for a client', current_timestamp);
INSERT INTO transactions (user_id, status, manager_name, imgUrl, "comment", trans_date)
VALUES ('5', 'resolved', 'Jiggy', NULL, 'Donuts for morning meeting 08-12-2019', current_timestamp);

SELECT * FROM Transactions;
SELECT * FROM "user";
select * from trimg;
