DROP TABLE if EXISTS users;
CREATE TABLE users(
    id serial PRIMARY KEY,
    name text NOT NULL
);