
insert into usr (id, password, username, active)
    values (1, '$2y$12$5j.v7udq4rBRZtt4vgRtyOvDv6YLdt4uUiZrVHEwEL1P6sf.pbqIO ', 'admin', true);

insert into user_role (user_id, roles)
    values (1, 'ROLE_ADMIN');