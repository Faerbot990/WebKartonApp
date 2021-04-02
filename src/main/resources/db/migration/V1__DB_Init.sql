create sequence hibernate_sequence start 1 increment 1;
create table category
(
    id         int8 not null,
    local_date date,
    name       varchar(255),
    slug       varchar(255),
    primary key (id)
);
create table news
(
    id          int8 not null,
    filename    varchar(2000000),
    information varchar(255),
    local_date  date,
    title       varchar(255),
    primary key (id)
);
create table product
(
    id                  int8   not null,
    filename            varchar(999999),
    local_date          date,
    price               float8 not null,
    product_color       varchar(255),
    product_description varchar(2048),
    product_name        varchar(255),
    quantity            varchar(255),
    slug                varchar(255),
    subcategory_id      int8,
    primary key (id)
);
create table subcategory
(
    id                     int8 not null,
    image                  varchar(999999),
    local_date             date,
    sub_category_name      varchar(255),
    sub_category_name_slug varchar(255),
    category_id            int8,
    primary key (id)
);
create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);
create table usr
(
    id       int8 not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
alter table if exists product
    add constraint FKku369nri8u3s17uom8or57trs foreign key (subcategory_id) references subcategory;
alter table if exists subcategory
    add constraint FKe4hdbsmrx9bs9gpj1fh4mg0ku foreign key (category_id) references category;
alter table if exists user_role
    add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr