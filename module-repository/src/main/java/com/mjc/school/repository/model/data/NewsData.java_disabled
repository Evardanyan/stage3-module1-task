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
    private List<NewsModel> newsList;

    private NewsData(final List<AuthorModel> authorModelList) {
        this.init(authorModelList);
    }

    public static NewsData getNewsData(final List<AuthorModel> authorModelList) {
        if (NewsData.newsData == null) {
            NewsData.newsData = new NewsData(authorModelList);
        }
        return NewsData.newsData;
    }

    private void init(final List<AuthorModel> authorModelList) {
        this.newsList = new ArrayList<NewsModel>();
        final Random random = new Random();
        for (long i = 1L; i <= 20L; ++i) {
            final LocalDateTime date = Utils.getRandomDate();
            this.newsList.add(new NewsModel(i, Utils.getRandomContentByFilePath("news"), Utils.getRandomContentByFilePath("content"), date, date, authorModelList.get(random.nextInt(authorModelList.size())).getId()));
        }
    }

    public List<NewsModel> getNewsList() {
        return this.newsList;
    }

}
