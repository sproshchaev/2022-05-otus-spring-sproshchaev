--liquibase formatted sql
--changeset sproshchaev:2022-08-28-0001-author
insert into author(fullname)
values ('John Bunyan'),
       ('Daniel Defoe'),
       ('Gianni Rodari');