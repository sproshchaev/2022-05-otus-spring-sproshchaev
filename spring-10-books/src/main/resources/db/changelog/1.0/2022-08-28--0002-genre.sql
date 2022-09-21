-- liquibase formatted sql
-- changeset sproshchaev: 2022-08-28-0001-genre
drop table if exists genre;
create table genre
(
    id bigint auto_increment primary key,
    name varchar(255)
);
