create schema library;

use library;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on library.* to user@'localhost';

create table books
(
    id bigint not null AUTO_INCREMENT,
    title varchar(255) not null,
    isbn varchar(64) not null,
    description text not null,
    created_at datetime not null,
    updated_at datetime null
);

create unique index books_id_uindex
	on books (id);

alter table books
    add constraint books_pk
        primary key (id);