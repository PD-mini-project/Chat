package com.chatting.room.chatroom.repository;

import com.chatting.room.chatroom.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    // 추가적인 메서드 선언이 필요한 경우 여기에 작성
}