drop table if exists pizza;
create table pizza
(
    id SERIAL PRIMARY KEY,
    pizza_name varchar(255) NOT NULL,
    ingredients varchar(255) NOT NULL,
    price int NOT NULL,
    photo_path text not null
);
drop table if exists pizza_user;
create table pizza_user
(
    id SERIAL PRIMARY KEY,
    email varchar(255) NOT NULL,
    password varchar(32) NOT NULL,
    role varchar(20) default 'simpleUser'
);
-- create table file_information (
--                                   id serial not null primary key,
--                                   original_file_name varchar(100),
--                                   storage_file_name varchar(100) not null,
--                                   size bigint not null,
--                                   type varchar(100)
-- );


create table user_token
(
    user_id int primary key references pizza_user,
    token varchar(200) not null
);






drop table if exists pizza_order;
create table pizza_order
(
    order_id SERIAL PRIMARY KEY ,
    user_id int,
    quantity int,
    list_of_ordered_pizza text,
    order_cost int,
    date_of_order varchar(255)

);
