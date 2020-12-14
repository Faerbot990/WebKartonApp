
insert into usr (id, password, username, active)
    values (1, 'admin', 'admin', true);

insert into user_role (user_id, roles)
    values (1, 'ROLE_ADMIN');