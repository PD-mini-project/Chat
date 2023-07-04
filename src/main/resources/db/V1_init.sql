show databases;
create database chattingroom;
use chattingroom;

DROP TABLE IF EXISTS chat_message;
DROP TABLE IF EXISTS chatroom_categories;
DROP TABLE IF EXISTS chat_room;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS user;

create table user (
    id bigint auto_increment primary key,
    username varchar(20) not null unique,
    password varchar(255) not null,
    description varchar(255) default "",
    create_at datetime default current_timestamp,
    modify_at datetime default current_timestamp
) engine=InnoDB;

create table chatroom (
    id bigint auto_increment primary key,
    title varchar(255) not null,
    description varchar(255) default "",
    create_at datetime default current_timestamp,
    modify_at datetime default current_timestamp,
    user_id bigint not null,
    foreign key (user_id) references user (id)
) engine=InnoDB;

create table categories (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(20) NOT NULL
) engine=InnoDB;

create table chatroom_categories (
    chatroom_id bigint,
    category_id bigint,
    FOREIGN KEY (chatroom_id) REFERENCES chatroom(id),
    FOREIGN KEY (category_id) REFERENCES categories(id),
    PRIMARY KEY (chatroom_id, category_id)
) engine=InnoDB;


create table chat_message (
    id bigint auto_increment primary key,
    message text,
    create_at datetime default current_timestamp,
    chatroom_id bigint not null,
    user_id bigint not null,
    foreign key (chatroom_id) references chatroom(id),
    foreign key (user_id) references user(id)
) engine=InnoDB;
