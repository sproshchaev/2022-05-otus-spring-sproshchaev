-- liquibase formatted sql
-- changeset sproshchaev: 2022-08-28-0003-book
create table book
(
    id        bigint auto_increment primary key,
    title     varchar(255),
    author_id bigint,
    genre_id  bigint,
    foreign key (author_id) references author (id),
    foreign key (genre_id) references genre (id)
);