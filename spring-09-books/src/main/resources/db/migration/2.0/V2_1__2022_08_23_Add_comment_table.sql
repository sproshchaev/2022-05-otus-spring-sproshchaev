--date: 2022-08-23
--author: sproshchaev

drop table if exists comment;
create table comment
(
    id bigserial primary key,
    comment_text varchar(255),
    book_id bigint references book(id) on delete cascade
);