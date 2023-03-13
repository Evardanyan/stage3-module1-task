package com.mjc.school.repository.factory;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.repository.model.data.NewsModel;

public class RepositoryFactory {
    private static final RepositoryFactory INSTANCE = new RepositoryFactory();
    private final Repository<NewsModel> newsRepository = new NewsRepository();

    private RepositoryFactory() {
    }

    public static RepositoryFactory getInstance() {
        return INSTANCE;
    }

    public Repository<NewsModel> getNewsRepository() {
        return newsRepository;
    }
}

