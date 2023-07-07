function enterChatRoom(item) {
    // 선택한 채팅방 ID를 가져옵니다.
    const chatRoomId = item.dataset.chatRoomId;

    // 채팅방 페이지로 이동합니다.
    window.location.href = '/chatroom/' + chatRoomId;
}