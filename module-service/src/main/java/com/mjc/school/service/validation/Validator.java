package com.mjc.school.service.validation;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.enums.Errors;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final int NEWS_TITLE_MIN = 5;
    private static final int NEWS_TITLE_MAX = 30;
    private static final int NEWS_CONTENT_MIN = 5;
    private static final int NEWS_CONTENT_MAX = 255;
    private static final int AUTHOR_NAME_MIN = 3;
    private static final int AUTHOR_NAME_MAX = 15;

    public void validateNewsDtoRequest(NewsDtoRequest newsDtoRequest) {
        validateNewsTitle(newsDtoRequest.getTitle());
        validateNewsContent(newsDtoRequest.getContent());
        validateNewsAuthorId(newsDtoRequest.getAuthorId());
    }

    public void validateAuthorDtoRequest(AuthorDtoRequest authorDtoRequest) {
        validateAuthorName(authorDtoRequest.getName());
    }

    public void validateNewsId(Long newsId) {
        if (newsId == null || newsId < 1) {
            throw new ValidatorException(Errors.INVALID_NEWS_ID.getMessage());
        }
    }

    private void validateNewsTitle(String title) {
        if (title.length() < NEWS_TITLE_MIN || title.length() > NEWS_TITLE_MAX) {
            throw new ValidatorException(Errors.INVALID_NEWS_TITLE_LENGTH.getMessage());
        }
    }

    private void validateNewsContent(String content) {
        if (content.length() < NEWS_CONTENT_MIN || content.length() > NEWS_CONTENT_MAX) {
            throw new ValidatorException(Errors.INVALID_NEWS_CONTENT_LENGTH.getMessage());
        }
    }

    private void validateNewsAuthorId(Long newsAuthorId) {
        if (newsAuthorId == null || newsAuthorId < 1) {
            throw new ValidatorException(Errors.INVALID_NEWS_AUTHOR_ID.getMessage());
        }
    }

    public void validateAuthorId(Long authorId) {
        if (authorId == null || authorId < 1) {
            throw new ValidatorException(Errors.INVALID_AUTHOR_ID.getMessage());
        }
    }

    private void validateAuthorName(String name) {
        if (name.length() < AUTHOR_NAME_MIN || name.length() > AUTHOR_NAME_MAX) {
            throw new ValidatorException(Errors.INVALID_AUTHOR_NAME_LENGTH.getMessage());
        }
    }

    public boolean validateId(String id) {
        char[] chars = id.toCharArray();
        int counter = 0;
        if (chars[0] == '-') {
            counter++;
        }
        for (char character : chars) {
            if (Character.isDigit(character)) {
                counter++;
            }
        }
        return counter == chars.length;
    }
}
