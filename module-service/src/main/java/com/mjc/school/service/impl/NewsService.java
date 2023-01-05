package com.mjc.school.service.impl;

import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.repository.model.data.NewsModel;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.interfaces.ModelMapper;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.validator.NewsValidator;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NewsService implements Service<NewsDtoRequest, NewsDtoResponse> {
    private final Repository<NewsModel> newsRepository;
    private final NewsValidator newsValidator;
    private ModelMapper mapper;

    public NewsService() {
        this.mapper = (ModelMapper)Mappers.getMapper((Class)ModelMapper.class);
        this.newsRepository = RepositoryFactory.getInstance().getNewsRepository();
        this.newsValidator = NewsValidator.getNewsValidator();
    }

    public List<NewsDtoResponse> readAll() {
        return (List<NewsDtoResponse>)this.mapper.modelListToDtoList(this.newsRepository.readAll());
    }

    public NewsDtoResponse readById(Long newsId) {
        this.newsValidator.validateNewsId(newsId);
        if (this.newsRepository.isExistById(newsId)) {
            NewsModel newsModel = this.newsRepository.readById(newsId);
            return this.mapper.modelToDto(newsModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), newsId));
    }

    public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
        this.newsValidator.validateNewsDto(dtoRequest);
        NewsModel model = this.mapper.dtoToModel(dtoRequest);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        model.setCreateDate(date);
        model.setLastUpdatedDate(date);
        NewsModel newsModel = this.newsRepository.create(model);
        return this.mapper.modelToDto(newsModel);
    }


    public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
        this.newsValidator.validateNewsId(dtoRequest.id());
        this.newsValidator.validateNewsDto(dtoRequest);
        if (this.newsRepository.isExistById(dtoRequest.id())) {
            NewsModel model = this.mapper.dtoToModel(dtoRequest);
            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            model.setLastUpdatedDate(date);
            NewsModel newsModel = this.newsRepository.update(model);
            return this.mapper.modelToDto(newsModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), dtoRequest.id()));
    }

    public Boolean deleteById(Long newsId) {
        this.newsValidator.validateNewsId(newsId);
        if (this.newsRepository.isExistById(newsId)) {
            return this.newsRepository.deleteById(newsId);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), newsId));
    }

}
