--liquibase formatted sql
--changeset sproshchaev:2022-08-28-0001-genre
insert into genre (name)
values ('History'),
       ('Classic'),
       ('Fantasy'),
       ('Autobiography'),
       ('Fiction');