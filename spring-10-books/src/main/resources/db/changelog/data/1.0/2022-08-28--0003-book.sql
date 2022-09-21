--liquibase formatted sql
--changeset sproshchaev:2022-08-28-0001-genre
insert into book (title, author_id, genre_id)
values ('The Pilgrim’s Progress', 1, 1),
       ('Robinson Crusoe', 2, 2),
       ('The Holy War', 1, 1),
       ('The Farther Adventures of Robinson Crusoe', 2, 2);