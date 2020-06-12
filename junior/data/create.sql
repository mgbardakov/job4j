--creating new data base
CREATE DATABASE orderDB

--creating tables
CREATE TABLE role
(
	role_id serial PRIMARY KEY,		
	role_title varchar(200)
);

CREATE TABLE rule
(
	rule_id serial PRIMARY KEY,	
	rule_title varchar(200)
);

CREATE TABLE users
(
	user_id serial PRIMARY KEY,
	role_id int,
	user_name varchar(200)
);

CREATE TABLE item
(
	item_id serial PRIMARY KEY,
	user_id int,
	state_id int,
	category_id int,
	item_title varchar(200)
);

CREATE TABLE attach
(
	attach_id serial PRIMARY KEY,
	item_id int,
	file_name varchar(200)
);

CREATE TABLE state
(
	state_id serial PRIMARY KEY,	
	state_title varchar(200)
);

CREATE TABLE category
(
	category_id serial PRIMARY KEY,	
	category_name varchar(200)
);

CREATE TABLE comment
(
	comment_id serial PRIMARY KEY,
	item_id int,
	comment_text text
);

CREATE TABLE role_rule
(
	role_rule_id serial PRIMARY KEY,
	role_id int REFERENCES role(role_id),
	rule_id int REFERENCES rule(rule_id)
);
 
--connecting tables
ALTER TABLE users
	ADD CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES role;


ALTER TABLE item
	ADD CONSTRAINT fk_item_users FOREIGN KEY (user_id) REFERENCES users;
	
ALTER TABLE item	
	ADD CONSTRAINT fk_item_state FOREIGN KEY (state_id) REFERENCES state;
	
ALTER TABLE item	
	ADD CONSTRAINT fk_item_category FOREIGN KEY (category_id) REFERENCES category;


ALTER TABLE comment
	ADD CONSTRAINT fk_comment_item FOREIGN KEY (item_id) REFERENCES item;	


ALTER TABLE attach
	ADD CONSTRAINT fk_attach_item FOREIGN KEY (item_id) REFERENCES item;

--filling tables
INSERT INTO state
VALUES
(1, 'accepted'),
(2, 'in progress'),
(3, 'completed');

INSERT INTO category
VALUES
(1, 'high priority'),
(2, 'medium priority'),
(3, 'low priority');

INSERT INTO role
VALUES
(1,'employee'),
(2, 'manager'),
(3, 'chief');

INSERT INTO rule
VALUES
(1, 'complete order'),
(2, 'look at all orders'),
(3, 'look at all information');

INSERT INTO role_rule
VALUES
(1, 1, 1),
(2, 2, 1),
(3, 2, 2),
(4, 3, 1),
(5, 3, 2),
(6, 3, 3);

INSERT INTO users
VALUES
(1, 1, 'Fedor'),
(2, 2, 'Stepan'),
(3, 3, 'Michail');

INSERT INTO item
VALUES
(1, 1, 1, 3, 'first order'),
(2, 1, 1, 2, 'second order'),
(3, 2, 2, 1, 'third order'),
(4, 3, 1, 1, 'forth order');

INSERT INTO attach
VALUES
(1, 1, 'file 1'),
(2, 1, 'file 2'),
(3, 1, 'file 3'),
(4, 2, 'file 4'),
(5, 2, 'file 5'),
(6, 3, 'file 6');

INSERT INTO comment
VALUES
(1, 1, 'comment 1'),
(2, 1, 'comment 2'),
(3, 1, 'comment 3'),
(4, 2, 'comment 4'),
(5, 2, 'comment 5'),
(6, 3, 'comment 6');

--creating car_catalog
CREATE DATABASE car_catalog;

--creating tables
CREATE TABLE body
(
	body_id serial PRIMARY KEY,
	body_name varchar(100)
);

CREATE TABLE engine
(
	engine_id serial PRIMARY KEY,
	engine_name varchar(100)
);

CREATE TABLE gearbox
(
	gearbox_id serial PRIMARY KEY,
	gearbox_name varchar(100)
);

CREATE TABLE car
(
	car_id serial PRIMARY KEY,
	car_name varchar(150),
	body_id int REFERENCES body(body_id) NOT NULL,
	engine_id int REFERENCES engine(engine_id) NOT NULL,
	gearbox_id int REFERENCES gearbox(gearbox_id) NOT NULL
);

--filling tables
INSERT INTO engine (engine_name)
VALUES
('Бензиновый'),
('Дизельный'),
('Ракетный');

INSERT INTO gearbox (gearbox_name)
VALUES
('4-ступенчатая'),
('5-ступенчатая'),
('6-ступенчатая');

INSERT INTO body (body_name)
VALUES
('Седан'),
('Купе'),
('Минивен');

INSERT INTO car (car_name, body_id, engine_id, gearbox_id)
VALUES
('Nissan Almera', 1, 2, 2),
('Ferrari F50', 2, 1, 3)

--gets all cars with parts attached to them
SELECT car_name, body_name, engine_name, gearbox_name FROM car
JOIN body ON car.body_id = body.body_id
JOIN engine ON car.engine_id = engine.engine_id
JOIN gearbox ON car.gearbox_id = gearbox.gearbox_id;

--gets all bodies not attached to cars
SELECT body_name FROM body b
LEFT JOIN car c ON b.body_id = c.body_id
WHERE car_id IS NULL;

--gets all engines not attached to cars
SELECT engine_name FROM engine e
LEFT JOIN car c ON c.engine_id = e.engine_id
WHERE car_id IS NULL;

--gets all gearboxes not attached to cars
SELECT gearbox_name FROM gearbox g
LEFT JOIN car c ON c.gearbox_id = g.gearbox_id
WHERE car_id IS NULL;

