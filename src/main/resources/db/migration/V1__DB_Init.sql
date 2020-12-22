
create sequence hibernate_sequence start 1 increment 1;

create table news
(id int8 not null,
 information varchar(255),
  title varchar(255),
   filename varchar(255),
    primary key (id)
);
create table product
(id int8 not null,
 description varchar(255),
 filename varchar(255),
 product_category varchar(255),
 product_name varchar(255),
 price int4 not null,
 quantity varchar(255),
 primary key (id)
);

 create table orders
 (id int8 not null,
  date date,
   email varchar(255),
    first_name varchar(255),
     last_name varchar(255),
      phone_number varchar(255),
       total_price float8,
        filename varchar(255),
         price int4 not null,
          product_category varchar(255),
           product_description varchar(255),
            product_name varchar(255),
             quantity varchar(255),
              user_id int8,
               primary key (id));
 create table orders_product_list
 (order_id int8 not null,
  product_list_id int8 not null,
   product_list_order int4 not null,
    primary key (order_id, product_list_order));

 create table user_role
 (user_id int8 not null,
  roles varchar(255));

 create table usr
 (id int8 not null,
  password varchar(255) not null ,
   username varchar(255) not null ,
    primary key (id));
 create table usr_product_list
 (user_id int8 not null,
  product_list_id int8 not null);



 alter table if exists orders
     add constraint order_id_user_fk
         foreign key (user_id) references usr;
 alter table if exists orders_product_list
     add constraint product_list_order_fk
         foreign key (product_list_id) references orders;
 alter table if exists orders_product_list
     add constraint order_id_fk
         foreign key (order_id) references orders;
 alter table if exists user_role
     add constraint user_role_id_fk
         foreign key (user_id) references usr;
 alter table if exists usr_product_list
     add constraint user_order_list_fk
         foreign key (product_list_id) references orders;
 alter table if exists usr_product_list
     add constraint user_product_list_fk
         foreign key (user_id) references usr;