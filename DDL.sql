create schema library;

use library;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on library.* to user@'localhost';

create table if not exists authors
(
    id bigint auto_increment
        primary key,
    name varchar(255) not null
);

create table if not exists publishers
(
    id bigint auto_increment
        primary key,
    name varchar(255) not null
);

create table if not exists books
(
    id bigint auto_increment,
    author_id bigint null,
    publisher_id bigint null,
    title varchar(255) not null,
    isbn varchar(64) not null,
    description text not null,
    cover varchar(255) null,
    created_at datetime not null,
    updated_at datetime null,
    constraint books_id_uindex
        unique (id),
    constraint author
        foreign key (author_id) references authors (id)
            on update cascade,
    constraint publisher
        foreign key (publisher_id) references publishers (id)
            on update cascade
);

alter table books
    add primary key (id);