package com.mjc.school.service.factroy;

import com.mjc.school.service.impl.NewsService;

public class ServiceFactory {
    private static ServiceFactory instance;
    private final NewsService newsService = new NewsService();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public NewsService getNewsService() {
        return this.newsService;
    }
}
