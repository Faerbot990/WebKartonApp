create sequence hibernate_sequence start 1 increment 1;

create table category
(
    slug          varchar(255) not null,
    image         varchar(200000),
    name          varchar(255),
    name_sub      varchar(255),
    name_sub_slug varchar(255),
    category_slug varchar(255),
    primary key (slug)
);
create table news
(
    id          int8 not null,
    filename    varchar(200000),
    information varchar(255),
    title       varchar(255),
    primary key (id)
);
create table product
(
    slug                varchar(255) not null,
    filename            varchar(999999),
    price               float8       not null,
    product_color       varchar(255),
    product_description varchar(2048),
    product_name        varchar(255),
    quantity            varchar(255),
    category_slug       varchar(255),
    primary key (slug)
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
create table usr_product_list
(
    user_id           int8         not null,
    product_list_slug varchar(255) not null
);
alter table if exists category add constraint FK5h87fxn3a4hqktk64keodev71 foreign key (category_slug) references category;

alter table if exists product add constraint FKgxuoidemfiurovm8ktg84hett foreign key (category_slug) references category;

alter table if exists user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr;

alter table if exists usr_product_list add constraint FKonawyj3mbv57ribnkg3nd13g2 foreign key (product_list_slug) references product;

alter table if exists usr_product_list add constraint FKp5vrlq5f8efd6ecfxmhcm0px5 foreign key (user_id) references usr;