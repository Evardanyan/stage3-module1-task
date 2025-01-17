package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.data.AuthorData;
import com.mjc.school.repository.model.data.AuthorModel;
import com.mjc.school.repository.model.data.NewsData;
import com.mjc.school.repository.model.data.NewsModel;

import java.util.List;

public class DataSource {
    private final List<NewsModel> news;

    private DataSource() {
        List<AuthorModel> authorList = AuthorData.getInstance().getAuthors();
        this.news = NewsData.getNewsData(authorList).getNews();
    }

    private static class LazyDataSourceHolder {
        static final DataSource INSTANCE = new DataSource();
    }

    public static DataSource getInstance() {
        return LazyDataSourceHolder.INSTANCE;
    }

    public List<NewsModel> getNews() {
        return news;
    }
}

