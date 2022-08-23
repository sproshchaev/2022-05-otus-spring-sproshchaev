--date: 2022-08-23
--author: sproshchaev

drop table if exists genre;
create table genre
(
    id bigserial primary key,
    name varchar(255)
);