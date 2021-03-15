Hibernate: create sequence hibernate_sequence start 1 increment 1
Hibernate: create table category (
    id int8 not null,
     image varchar(200000),
      name varchar(255),
       parent_category_name varchar(255),
        parent_category_slug varchar(255),
         slug varchar(255), primary key (id))
    Hibernate: create table news (
        id int8 not null,
         filename varchar(255),
          information varchar(255),
           title varchar(255),
            primary key (id))
    Hibernate: create table product (
        id int8 not null,
         filename varchar(200000),
          price float8 not null,
           product_color varchar(255),
            product_description varchar(2048),
             product_name varchar(255),
              quantity varchar(255),
               product_category_id int8,
                primary key (id))
    Hibernate: create table user_role (
        user_id int8 not null,
     roles varchar(255))
    Hibernate: create table usr
    (id int8 not null,
     password varchar(255),
      username varchar(255),
       primary key (id))
    Hibernate: create table usr_product_list (
        user_id int8 not null,
         product_list_id int8 not null)
    Hibernate: alter table
        if exists product
        add constraint FKid4vcfgj211k2uqjuex1x7xq0
        foreign key (product_category_id) references category
    Hibernate: alter table
        if exists user_role
        add constraint FKfpm8swft53ulq2hl11yplpr5
        foreign key (user_id) references usr
    Hibernate: alter table
        if exists usr_product_list
        add constraint FKpos401lmvyqngapg69w1tw4ss
        foreign key (product_list_id) references product
    Hibernate: alter table
        if exists usr_product_list
        add constraint FKp5vrlq5f8efd6ecfxmhcm0px5
        foreign key (user_id) references usr