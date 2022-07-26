drop table if exists author;
create table author
(
    id       bigint auto_increment primary key,
    fullname varchar(255)
);
drop table if exists genre;
create table genre
(
    id   bigint auto_increment primary key,
    name varchar(255)
);
drop table if exists book;
create table book
(
    id        bigint auto_increment primary key,
    title     varchar(255),
    author_id bigint,
    genre_id  bigint,
    foreign key (AUTHOR_ID) references author (ID),
    foreign key (GENRE_ID) references genre (ID)
);