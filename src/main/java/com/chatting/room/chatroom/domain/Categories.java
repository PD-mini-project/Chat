package com.chatting.room.chatroom.domain;

import com.chatting.room.chatroom.execption.CategoryNullException;
import com.chatting.room.chatroom.execption.CategoryTitleLengthException;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class Categories {

    private final List<Category> categories;

    private static final String DELIMITER = ",";
    private static final int TITLE_LENGTH = 20;

    private Categories(List<Category> categories) {
        validateNotNull(categories);
        this.categories = categories;
    }

    private void validateNotNull(List<Category> categories) {
        if (Objects.isNull(categories)) {
            throw new CategoryNullException();
        }
    }

    public static Categories toCategory(String categoryVal) {
        List<String> titleList = Arrays.stream(categoryVal.split(DELIMITER)).collect(Collectors.toList());

        validateCategoryTitle(titleList);

        List<Category> categories = titleList.stream()
                .map(Category::new)
                .collect(Collectors.toList());


        return new Categories(categories);
    }

    private static void validateCategoryTitle(List<String> titleList) {
        titleList.stream()
                .filter(title -> title.length() > TITLE_LENGTH)
                .findAny()
                .ifPresent(
                        t -> {throw new CategoryTitleLengthException();
                });
    }
}
