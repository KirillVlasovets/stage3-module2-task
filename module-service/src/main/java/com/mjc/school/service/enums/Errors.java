package com.mjc.school.service.enums;

import lombok.Getter;

@Getter
public enum Errors {

    NEWS_NOT_FOUND_EXCEPTION("News with this id does not exist.", 404),
    AUTHOR_NOT_FOUND_EXCEPTION("Author with this id does not exist.", 404),
    INCORRECT_DATA_FORMAT("%s should be a number", 404);

    private final String message;
    private final int code;

    Errors(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
