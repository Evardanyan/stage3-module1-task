package com.mjc.school.controller.impl;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.factroy.ServiceFactory;
import com.mjc.school.service.impl.NewsService;

import java.util.List;

public class NewsController {

    private final NewsService newsService = ServiceFactory.getInstance().getNewsService();

    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    public NewsDtoResponse readById(Long newsId) {
        return newsService.readById(newsId);
    }

    public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
        return newsService.create(dtoRequest);
    }

    public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
        return newsService.update(dtoRequest);
    }

    public Boolean deleteById(Long newsId) {
        return newsService.deleteById(newsId);
    }

}

