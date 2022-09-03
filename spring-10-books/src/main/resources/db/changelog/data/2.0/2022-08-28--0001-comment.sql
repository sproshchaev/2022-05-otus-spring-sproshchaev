--liquibase formatted sql
--changeset sproshchaev:2022-08-28-0001-comment
insert into comment (comment_text, book_id)
values ('The Pilgrims Progress — is a very interesting book!', 1),
       ('Robinson Crusoe — is a very interesting book!', 2),
       ('The Holy War — is a very interesting book!', 3),
       ('The Farther Adventures of Robinson Crusoe — is a very interesting book!', 4);