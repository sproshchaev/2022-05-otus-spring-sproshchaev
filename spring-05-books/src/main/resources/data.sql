insert into author(`fullname`)
values ('John Bunyan');
insert into author (`fullname`)
values ('Daniel Defoe');
insert into genre (`name`)
values ('History');
insert into genre (`name`)
values ('Classic');
insert into book (`title`, `author_id`, `genre_id`)
values ('The Pilgrim’s Progress', 1, 1);
insert into book (`title`, `author_id`, `genre_id`)
values ('Robinson Crusoe', 2, 2);
insert into book (`title`, `author_id`, `genre_id`)
values ('The Holy War', 1, 1);
insert into book (`title`, `author_id`, `genre_id`)
values ('The Farther Adventures of Robinson Crusoe', 2, 2);