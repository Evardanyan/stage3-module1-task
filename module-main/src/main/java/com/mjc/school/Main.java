package com.mjc.school;

import com.mjc.school.clihelper.CliMenuHelper;
import com.mjc.school.controller.impl.NewsController;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Runnable> commands = Map.of(
            "1", () -> new CliMenuHelper().getNews(new NewsController()),
            "2", () -> new CliMenuHelper().getNewsById(new NewsController(), new Scanner(System.in)),
            "3", () -> new CliMenuHelper().createNews(new NewsController(), new Scanner(System.in)),
            "4", () -> new CliMenuHelper().updateNews(new NewsController(), new Scanner(System.in)),
            "5", () -> new CliMenuHelper().deleteNews(new NewsController(), new Scanner(System.in)),
            "0", () -> System.exit(0)
    );

    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);
        CliMenuHelper helper = new CliMenuHelper();
        while (true) {
            try {
                helper.printMainMenu();
                String key = keyboardInput.nextLine();
                if (commands.containsKey(key)) {
                    commands.get(key).run();
                } else {
                    System.out.println("Command not found.");
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}




