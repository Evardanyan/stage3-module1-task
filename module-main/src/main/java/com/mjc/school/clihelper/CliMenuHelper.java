package com.mjc.school.clihelper;

import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.exception.ValidatorException;

import java.util.Arrays;
import java.util.Scanner;

public class CliMenuHelper {
    public void printMainMenu() {
        System.out.println("Enter the number of operation:");
        Arrays.stream(Operations.values())
                .map(Operations::getOperationWithNumber)
                .forEach(System.out::println);
    }

    public void getNews(NewsController newsController) {
        System.out.println(Operations.GET_ALL_NEWS.getOperation());
        newsController.readAll().forEach(System.out::println);
    }


    public void getNewsById(NewsController newsController, Scanner keyboard) {
        System.out.println(Operations.GET_NEWS_BY_ID.getOperation());
        Long newsId = getKeyboardNumber("News Id", keyboard);
        NewsDtoResponse news = newsController.readById(newsId);
        System.out.println(news.toString());
    }


    public void createNews(NewsController newsController, Scanner keyboard) {
        System.out.println(Operations.CREATE_NEWS.getOperation());
        NewsDtoRequest dtoRequest = readNewsDtoRequestFromKeyboard(null, keyboard);
        System.out.println(newsController.create(dtoRequest));
    }

    public void updateNews(NewsController newsController, Scanner keyboard) {
        System.out.println(Operations.UPDATE_NEWS.getOperation());
        Long newsId = getKeyboardNumber("News Id", keyboard);
        NewsDtoRequest dtoRequest = readNewsDtoRequestFromKeyboard(newsId, keyboard);
        System.out.println(newsController.update(dtoRequest));
    }

    private NewsDtoRequest readNewsDtoRequestFromKeyboard(Long newsId, Scanner keyboard) {
        String title = getKeyboardString("News title", keyboard);
        String content = getKeyboardString("News content", keyboard);
        Long authorId = getKeyboardNumber("Author Id", keyboard);
        return new NewsDtoRequest(newsId, title, content, authorId);
    }

    private String getKeyboardString(String paramName, Scanner keyboard) {
        System.out.println(String.format("Enter %s:", paramName));
        String input = keyboard.nextLine().trim();
        if (input.isEmpty()) {
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_NULL_STRING.getCodeMsg(), paramName, paramName));
        }
        return input;
    }

    private Long getKeyboardNumber(String paramName, Scanner keyboard) {
        System.out.println(String.format("Enter %s:", paramName));
        try {
            Long input = keyboard.nextLong();
            keyboard.nextLine();
            return input;
        } catch (Exception ex) {
            keyboard.nextLine();
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_INT_VALUE.getCodeMsg(), paramName));
        }
    }

    public void deleteNews(NewsController newsController, Scanner keyboard) {
        System.out.println(Operations.REMOVE_NEWS_BY_ID.getOperation());
        System.out.println(newsController.deleteById(Long.valueOf(this.getKeyboardNumber("News Id", keyboard))));
    }
}
