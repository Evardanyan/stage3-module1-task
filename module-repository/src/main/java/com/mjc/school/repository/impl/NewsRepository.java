package com.mjc.school.repository.impl;

import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.repository.model.data.NewsModel;
import com.mjc.school.repository.utils.DataSource;

import java.util.*;

public class NewsRepository implements Repository<NewsModel> {

    private final DataSource dataSource;

    public NewsRepository() {
        this.dataSource = DataSource.getInstance();
    }

    @Override
    public List<NewsModel> readAll() {
        return this.dataSource.getNews();
    }


    @Override
    public NewsModel readById(Long newsId) {
        return this.dataSource.getNews().stream()
                .filter(news -> Objects.equals(newsId, news.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("News with ID " + newsId + " not found"));
    }


    @Override
    public NewsModel create(NewsModel model) {
        List<NewsModel> newsList = this.dataSource.getNews();
        Long maxId = newsList.stream().mapToLong(NewsModel::getId).max().orElse(0L);
        Long nextId = maxId + 1L;
        model.setId(nextId);
        newsList.add(model);
        return model;
    }



    @Override
    public NewsModel update(NewsModel model) {
        List<NewsModel> newsList = this.dataSource.getNews();
        Optional<NewsModel> newsModelOptional = newsList.stream().filter(news -> news.getId().equals(model.getId())).findFirst();

        if (newsModelOptional.isPresent()) {
            NewsModel newsModel = newsModelOptional.get();
            newsModel.setTitle(model.getTitle());
            newsModel.setContent(model.getContent());
            newsModel.setLastUpdatedDate(model.getLastUpdatedDate());
            newsModel.setAuthorId(model.getAuthorId());
            return newsModel;
        } else {
            throw new IllegalArgumentException("NewsModel with id " + model.getId() + " does not exist.");
        }
    }


    @Override
    public Boolean deleteById(Long newsId) {
        List<NewsModel> newsList = this.dataSource.getNews();
        int originalSize = newsList.size();
        newsList.removeIf(news -> newsId.equals(news.getId()));
        return originalSize != newsList.size();
    }


    @Override
    public Boolean isExistById(Long newsId) {
        return this.dataSource.getNews().stream()
                .map(NewsModel::getId)
                .findFirst()
                .orElse(null) != null;
    }

}
