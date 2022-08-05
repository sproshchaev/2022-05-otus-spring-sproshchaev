--liquibase formatted sql

--changeset sproshchaev:2022-08-04-0001-author
insert into author (`fullname`) values ('John Bunyan');
insert into author (`fullname`) values ('Daniel Defoe');
insert into author (`fullname`) values ('Gianni Rodari');