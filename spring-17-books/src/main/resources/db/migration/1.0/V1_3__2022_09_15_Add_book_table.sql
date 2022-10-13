--date: 2022-09-15
--author: sproshchaev

drop table if exists book;
create table book
(
    id bigserial primary key,
    title varchar(255),
    author_id bigint,
    genre_id bigint,
    foreign key (author_id) references author (id),
    foreign key (genre_id) references genre (id)
);