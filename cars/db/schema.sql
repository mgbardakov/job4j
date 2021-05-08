DROP TABLE IF EXISTS engine CASCADE;
DROP TABLE IF EXISTS car CASCADE;
DROP TABLE IF EXISTS driver CASCADE;
DROP TABLE IF EXISTS history_owner CASCADE;

CREATE TABLE engine (
  id serial PRIMARY KEY,
  name text
);

CREATE TABLE car (
  id serial PRIMARY KEY,
  name text,
  engine_id int NOT null REFERENCES engine(id)
);

CREATE TABLE driver (
  id serial PRIMARY KEY,
  name text
);

CREATE TABLE history_owner (
   id serial PRIMARY KEY,
   driver_id int NOT null REFERENCES driver(id),
   car_id int NOT null REFERENCES car(id)
);