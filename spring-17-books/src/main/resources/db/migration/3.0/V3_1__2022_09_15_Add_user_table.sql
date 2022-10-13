--date: 2022-09-15
--author: sproshchaev

drop table if exists user_access;
create table user_access
(
    id bigserial primary key,
    login varchar(255),
    password varchar(255),
    fullname varchar(255)
);