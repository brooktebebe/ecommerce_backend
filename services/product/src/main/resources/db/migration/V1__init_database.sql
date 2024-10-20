create table if not exists product_category
(
    id          integer not null primary key,
    description varchar(255),
    name        varchar(255)

);
create table if not exists product
(
    id          integer not null primary key,
    description varchar(255),
    name        varchar(255),
    available_quantity double precision not null,
    price numeric (38,2),
    product_category_id integer constraint product_category_fk1 references product_category
);
create sequence if not exists product_category_seq increment by 50;
create sequence if not exists product_seq increment by 50;
