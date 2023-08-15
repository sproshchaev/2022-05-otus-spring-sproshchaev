--date: 2022-09-15
--author: sproshchaev

drop table if exists author;
create table author
(
    id bigint identity (1,1) primary key,
    fullname varchar(255)
);