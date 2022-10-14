--date: 2022-09-15
--author: sproshchaev

drop table if exists genre;
create table genre
(
    id bigserial primary key,
    name varchar(255)
);