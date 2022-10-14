--date: 2022-09-15
--author: sproshchaev

drop table if exists author;
create table author
(
    id bigserial primary key,
    fullname varchar(255)
);