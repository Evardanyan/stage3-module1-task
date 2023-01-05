package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.factroy.ServiceFactory;
import com.mjc.school.service.interfaces.Service;

import java.util.List;

public class NewsController {

    private final Service<NewsDtoRequest, NewsDtoResponse> newsService = ServiceFactory.getInstance().getNewsService();

    public List<NewsDtoResponse> findAll() {
        return this.newsService.findAll();
    }

    public NewsDtoResponse findById(Long newsId) {
        return (NewsDtoResponse)this.newsService.findById(newsId);
    }

    public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
        return (NewsDtoResponse)this.newsService.create(dtoRequest);
    }

    public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
        return (NewsDtoResponse)this.newsService.update(dtoRequest);
    }

    public Boolean deleteById(Long newsId) {
        return this.newsService.deleteById(newsId);
    }

}
