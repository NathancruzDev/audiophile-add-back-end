CREATE DATABASE audio;


CREATE TABLE users_entity
(
    id          integer primary key,
    name        varchar(50)        not null,
    emailadress varchar(70) unique not null,
    password    varchar(20)        not null,
    phoneNumber varchar(30),
    adress      varchar(100)       not null,
    city        varchar(50)        not null,
    country     varchar(20)        not null
);

CREATE TABLE cart
(
    id      INTEGER PRIMARY KEY UNIQUE NOT NULL,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users_entity (id)
);
CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    nameProduct VARCHAR(50)    not null,
    description VARCHAR(50),
    price       DECIMAL(10, 2) NOT NULL,
    stock       INTEGER
) INSERT INTO product(nameProduct,description,price,stock) VALUES(
'XX99 Mark I Fones de ouvido','002',1750.00,0
);
INSERT INTO product(nameProduct, description, price, stock)
VALUES ('XX59 Fones de ouvido', '003', 899.00, 0);

INSERT INTO product(nameProduct, description, price, stock)
VALUES ('ZX9 Alto-falante', '004', 4500.00, 0);
INSERT INTO product(nameProduct, description, price, stock)
VALUES ('ZX7 Alto-falante', '005', 3500.00, 0);
INSERT INTO product(nameProduct, description, price, stock)
VALUES ('Fones de ouvido sem fio YX1', '006', 599.00, 0);


