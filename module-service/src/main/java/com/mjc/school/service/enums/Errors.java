package com.mjc.school.service.enums;

import lombok.Getter;

@Getter
public enum Errors {

    NEWS_NOT_FOUND_EXCEPTION("News with this id does not exist.", 404),
    AUTHOR_NOT_FOUND_EXCEPTION("Author with this id does not exist.", 404),
    INCORRECT_DATA_FORMAT("%s should be a number", 404),
    INVALID_NEWS_ID("News id should be >= 1.", 501),
    INVALID_NEWS_TITLE_LENGTH("News title should be >= 5 and <= 30.", 502),
    INVALID_NEWS_CONTENT_LENGTH("News content should be >= 5 and <= 255.", 503),
    INVALID_AUTHOR_ID("Author id should be >= 1.", 504),
    INVALID_NEWS_AUTHOR_ID("News author id should be >= 1.", 505),
    INVALID_AUTHOR_NAME_LENGTH("Author name length should be >= 3 and <= 15.", 506);

    private final String message;
    private final int code;

    Errors(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
