package com.mjc.school.service.validator;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.exception.ValidatorException;

public class NewsValidator {

    private static final NewsValidator instance = new NewsValidator();
    private static final String NEWS_ID = "News id";
    private static final String NEWS_CONTENT = "News content";
    private static final String AUTHOR_ID = "Author id";
    private static final String NEWS_TITLE = "News title";
    private static final int NEWS_CONTENT_MIN_LENGTH = 5;
    private static final int NEWS_CONTENT_MAX_LENGTH = 255;
    private static final int NEWS_TITLE_MIN_LENGTH = 5;
    private static final int NEWS_TITLE_MAX_LENGTH = 30;
    private static final int MAX_AUTHOR_ID = 20;

    private NewsValidator() {
    }

    public static NewsValidator getInstance() {
        return instance;
    }

    public void validateNewsId(Long newsId) {
        validateNumber(newsId, NEWS_ID);
    }

    public void validateAuthorId(Long authorId) {
        validateNumber(authorId, AUTHOR_ID);
        if (authorId > MAX_AUTHOR_ID) {
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.AUTHOR_ID_DOES_NOT_EXIST.getCodeMsg(), authorId));
        }
    }

    public void validateNewsDto(NewsDtoRequest dtoRequest) {
        validateString(dtoRequest.title(), NEWS_TITLE, NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
        validateString(dtoRequest.content(), NEWS_CONTENT, NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
        validateAuthorId(dtoRequest.authorId());
    }

    private void validateNumber(Long id, String parameter) {
        if (id == null || id < 1L) {
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_NEGATIVE_OR_NULL_NUMBER.getCodeMsg(), parameter, parameter, id));
        }
    }

    private void validateString(String value, String parameter, int minLength, int maxLength) {
        if (value == null) {
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_NULL_STRING.getCodeMsg(), parameter, parameter));
        }
        int length = value.trim().length();
        if (length < minLength || length > maxLength) {
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_STRING_LENGTH.getCodeMsg(), parameter, minLength, maxLength, parameter, value));
        }
    }
}

