package com.mjc.school.clihelper;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.exception.ValidatorException;

import java.util.Scanner;

public class CliMenuHelper {
    public void printMainMenu() {
        System.out.println("Enter the number of operation:");
        for (Operations operation : Operations.values()) {
            System.out.println(operation.getOperationWithNumber());
        }
    }

    public void getNews(NewsController newsController) {
        System.out.println(Operations.GET_ALL_NEWS.getOperation());
        newsController.findAll().forEach(System.out::println);
    }

    public void getNewsById(NewsController newsController, Scanner keyboard) {
        System.out.println(Operations.GET_NEWS_BY_ID.getOperation());
        System.out.println("Enter news id:");
        System.out.println((Object)newsController.findById(Long.valueOf(this.getKeyboardNumber("News Id", keyboard))));
    }

    public void createNews(NewsController newsController, Scanner keyboard) {
        NewsDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operations.CREATE_NEWS.getOperation());
                System.out.println("Enter news title:");
                String title = keyboard.nextLine();
                System.out.println("Enter news content:");
                String content = keyboard.nextLine();
                System.out.println("Enter author id:");
                Long authorId = this.getKeyboardNumber("Author Id", keyboard);
                dtoRequest = new NewsDtoRequest(null, title, content, authorId);
                isValid = true;
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println((Object)newsController.create(dtoRequest));
    }

    public void updateNews(NewsController newsController, Scanner keyboard) {
        NewsDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operations.UPDATE_NEWS.getOperation());
                System.out.println("Enter news id:");
                Long newsId = this.getKeyboardNumber("News Id", keyboard);
                System.out.println("Enter news title:");
                String title = keyboard.nextLine();
                System.out.println("Enter news content:");
                String content = keyboard.nextLine();
                System.out.println("Enter author id:");
                Long authorId = this.getKeyboardNumber("Author Id", keyboard);
                dtoRequest = new NewsDtoRequest(newsId, title, content, authorId);
                isValid = true;
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println((Object)newsController.update(dtoRequest));
    }

    public void deleteNews(NewsController newsController, Scanner keyboard) {
        System.out.println(Operations.REMOVE_NEWS_BY_ID.getOperation());
        System.out.println("Enter news id:");
        System.out.println(newsController.deleteById(Long.valueOf(this.getKeyboardNumber("News Id", keyboard))));
    }

    private long getKeyboardNumber(String params, Scanner keyboard) {
        long enter;
        try {
            enter = keyboard.nextLong();
            keyboard.nextLine();
        }
        catch (Exception ex) {
            keyboard.nextLine();
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_INT_VALUE.getCodeMsg(), params));
        }
        return enter;
    }
}