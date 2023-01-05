package com.mjc.school;

import com.mjc.school.clihelper.CliMenuHelper;
import com.mjc.school.controller.NewsController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);
        CliMenuHelper helper = new CliMenuHelper();
        NewsController newsController = new NewsController();
        while (true) {
            try {
                while (true) {
                    String key;
                    helper.printMainMenu();
                    switch (key = keyboardInput.nextLine()) {
                        case "1" -> {
                            helper.getNews(newsController);
                            continue;
                        }
                        case "2" -> {
                            helper.getNewsById(newsController, keyboardInput);
                            continue;
                        }
                        case "3" -> {
                            helper.createNews(newsController, keyboardInput);
                            continue;
                        }
                        case "4" -> {
                            helper.updateNews(newsController, keyboardInput);
                            continue;
                        }
                        case "5" -> {
                            helper.deleteNews(newsController, keyboardInput);
                            continue;
                        }
                        case "0" -> {
                            System.exit(0);
                            continue;
                        }
                    }
                    System.out.println("Command not found.");
                }
            }
            catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        }
    }
}
