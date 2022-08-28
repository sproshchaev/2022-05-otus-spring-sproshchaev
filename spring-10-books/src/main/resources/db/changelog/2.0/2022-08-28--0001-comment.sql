-- liquibase formatted sql
-- changeset sproshchaev: 2022-08-28-0001-comment
drop table if exists comment;
create table comment
(
    id bigint auto_increment primary key,
    comment_text varchar(255),
    book_id bigint references book(id) on delete cascade
);