package com.mjc.school.repository.model.data;

import com.mjc.school.repository.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsData {

    private static final String CONTENT_FILE_NAME = "content";
    private static final String NEWS_FILE_NAME = "news";
    private static NewsData newsData;
    private final List<NewsModel> news;

    private NewsData(List<AuthorModel> authorModelList) {
        news = init(authorModelList);
    }

    public static NewsData getNewsData(List<AuthorModel> authorModelList) {
        if (newsData == null) {
            newsData = new NewsData(authorModelList);
        }
        return newsData;
    }

    private List<NewsModel> init(List<AuthorModel> authorModelList) {
        List<NewsModel> newsList = new ArrayList<>(20);
        Random random = new Random();
        for (long i = 1L; i <= 20L; i++) {
            LocalDateTime date = Utils.getRandomDate();
            newsList.add(new NewsModel(i,
                    Utils.getRandomContentByFilePath(NEWS_FILE_NAME),
                    Utils.getRandomContentByFilePath(CONTENT_FILE_NAME),
                    date,
                    date,
                    authorModelList.get(random.nextInt(authorModelList.size())).getId()));
        }
        return newsList;
    }

    public List<NewsModel> getNews() {
        return news;
    }
}

