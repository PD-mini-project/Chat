package com.chatting.room.common.util;

public class Patterns {
    public static final String USERNAME = "[a-zA-Z0-9가-힣]{1,20}";
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{1,30}$";
}
