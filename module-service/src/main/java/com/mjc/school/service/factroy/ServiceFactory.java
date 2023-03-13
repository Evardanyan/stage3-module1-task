package com.mjc.school.service.factroy;

import com.mjc.school.service.impl.NewsService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final NewsService newsService;

    private ServiceFactory() {
        newsService = new NewsService();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public NewsService getNewsService() {
        return newsService;
    }
}

