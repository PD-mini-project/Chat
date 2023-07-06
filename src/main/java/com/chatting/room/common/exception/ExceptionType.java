package com.chatting.room.common.exception;

import com.chatting.room.chatroom.execption.CategoryNullException;
import com.chatting.room.chatroom.execption.ChatRoomNotFoundException;
import com.chatting.room.chatroom.execption.ChatRoomPasswordException;
import com.chatting.room.chatroom.execption.ChatRoomTitleNullException;
import com.chatting.room.user.exception.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    UNKNOWN_EXCEPTION("000", "알 수 없는 서버 에러가 발생했습니다."),
    METHOD_ARG_NOT_VALID_EXCEPTION("000", "요청 데이터가 잘못되었습니다."),

    USER_NOT_FOUND_EXCEPTION("001", "유저가 존재하지 않습니다.", UserNotFoundException.class),
    USERNAME_NULL_EXCEPTION("002", "유저 이름은 1자 이상이어야 합니다.", UserNameNullException.class),
    USERNAME_LENGTH_EXCEPTION("003", "유저 이름은 20자를 초과할 수 없습니다.", UserNameLengthException.class),
    USERNAME_DUPLICATE_EXCEPTION("004", "이미 존재하는 유저 이름입니다.", UserNameDuplicateException.class),
    USER_PASSWORD_NULL_EXCEPTION("005", "유저 비밀번호는 1자 이상이어야 합니다", UserPasswordNullException.class),
    USER_PASSWORD_LENGTH_EXCEPTION("006", "유저 비밀번호는 1자 이상이어야 합니다", UserPasswordLengthException.class),
    USER_DESCRIPTION_LENGTH_EXCEPTION("007", "유저 소개글은 255자 이하여야 합니다.", UserDescriptionLengthException.class),

    // 권한에 관련된 모든 예외처리 예를 들어 방 생성(로그인), 방 삭제(로그인, 주인), 방 입장(로그인), 로그인정보 없음, 유저 정보 수정, 채팅방 정보 수정
    UNAUTHORIZED_EXCEPTION("008", "권한이 없습니다.", UnAuthorizedException.class),

    CHATROOM_NOT_FOUND_EXCEPTION("009", "존재하지 않는 채팅방 입니다.", ChatRoomNotFoundException.class),
    CHATROOM_PASSWORD_EXCEPTION("010", "채팅방 비밀번호가 일치하지 않습니다.", ChatRoomPasswordException.class),
    CHATROOM_PASSWORD_LENGTH_EXCEPTION("011", "채팅방 비밀번호는 4자 이하여야 합니다.", UserPasswordLengthException.class),
    CHATROOM_TITLE_NULL_EXCEPTION("012", "채팅방 제목은 1자 이상이어야 합니다.", ChatRoomTitleNullException.class),
    CHATROOM_TITLE_LENGTH_EXCEPTION("013", "채팅방 제목은 255자 이하이어야 합니다.", ChatRoomTitleNullException.class),
    CHATROOM_DESCRIPTION_LENGTH_EXCEPTION("014", "채팅방 소개글은 255자 이하이어야 합니다.", ChatRoomTitleNullException.class),

    CATEGORY_NULL_EXCEPTION("015", "카테고리를 1개 이상 지정해야 합니다.", CategoryNullException.class),
    CATEGORY__TITLE_LENGTH_EXCEPTION("016", "카테고리는 20자 이하여야 합니다.", CategoryNullException.class),
    CATEGORY_NUMBER_EXCEPTION("017", "카테고리는 5개 이하로 지정해야 합니다.", CategoryNullException.class),
    ;

    private final String errorCode;
    private final String errorMessage;
    private Class<? extends ApplicationException> type;

    ExceptionType(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ExceptionType of(Class<?> classType) {
        return Arrays.stream(values())
                .filter(eType -> Objects.nonNull(eType.type) && eType.type.equals(classType))
                .findFirst()
                .orElse(UNKNOWN_EXCEPTION);
    }
}
