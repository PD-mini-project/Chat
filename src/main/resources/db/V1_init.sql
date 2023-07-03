show databases;
create database chattingroom;
use chattingroom;

DROP TABLE IF EXISTS chat_message;
DROP TABLE IF EXISTS chatroom_categories;
DROP TABLE IF EXISTS chat_room;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS user;

create table user
(
    id bigint auto_increment primary key,
    username varchar(20) not null unique,
    password varchar(255) not null,
    description varchar(255) default "",
    create_at datetime(6) default now(),
    modify_at datetime(6) default now()
) engine=InnoDB;

create table chat_room
(
    id bigint auto_increment primary key,
    title varchar(255) not null,
    description varchar(255) default "",
    create_at datetime(6) default now(),
    modify_at datetime(6) default now(),
    user_id bigint not null,
    -- on delete cascade은 부모 테이블이 삭제되면 자식 테이블 값도 삭제되는 것이다.
    foreign key (user_id) references user (id) on delete cascade
) engine=InnoDB;

create table categories
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(20) NOT NULL
) engine=InnoDB;

create table chatroom_categories (
    chatroom_id bigint,
    category_id bigint,
    FOREIGN KEY (chat_room_id) REFERENCES chat_room(id) on delete cascade,
    FOREIGN KEY (category_id) REFERENCES categories(id) on delete cascade,
    PRIMARY KEY (chat_room_id, category_id)
) engine=InnoDB;

create table chat_message (
    id bigint auto_increment primary key,
    message text,
    create_at datetime(6) default now(),
    chat_room_id bigint not null,
    user_id bigint not null,
    foreign key (chat_room_id) references chat_room(id) on delete cascade,
    foreign key (user_id) references user(id) on delete cascade
) engine=InnoDB;
