-- liquibase formatted sql
-- changeset sproshchaev: 2022-08-28-0001-author
drop table if exists author;
create table author
(
    id bigint auto_increment primary key,
    fullname varchar(255)
);