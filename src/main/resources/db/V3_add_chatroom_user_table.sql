create table chatroom_user (
    chatroom_id bigint,
    user_id bigint,
    FOREIGN KEY (chatroom_id) REFERENCES chatroom(id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    PRIMARY KEY (chatroom_id, user_id)
) engine=InnoDB;