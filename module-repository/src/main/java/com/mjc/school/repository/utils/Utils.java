package com.mjc.school.repository.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class Utils {


    public static String getRandomContentByFilePath(String fileName) {
        final Random random = new Random();
        final int numLines = 30;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
            return reader.lines().skip(random.nextInt(numLines)).findFirst().orElse("");
        } catch (IOException e) {
            e.printStackTrace();
            return "We have an issue reading the file";
        }
    }



    public static LocalDateTime getRandomDate() {
        Random random = new Random();
        int endDay = 30;
        LocalDate day = LocalDate.now().plusDays(random.nextInt(endDay));
        LocalTime time = LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(60));
        return LocalDateTime.of(day, time);
    }

}
