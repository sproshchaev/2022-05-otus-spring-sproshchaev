update user_access set role = 'USER' where login = 'reader';
update user_access set role = 'ADMIN' where login = 'librarian';
update user_access set role = 'ADMIN' where login = 'administrator';