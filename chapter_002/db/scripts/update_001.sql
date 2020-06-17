DROP TABLE IF EXISTS item;

CREATE TABLE item
(
    id serial PRIMARY KEY,
    name varchar(200),
    description text
);