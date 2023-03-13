package com.mjc.school.service.impl;

import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.repository.model.data.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.interfaces.ModelMapper;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.validator.NewsValidator;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NewsService implements Service<NewsDtoRequest, NewsDtoResponse> {
    private final Repository<NewsModel> newsRepository;
    private final NewsValidator newsValidator;
    private final ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    public NewsService() {
        this.newsRepository = RepositoryFactory.getInstance().getNewsRepository();
        this.newsValidator = NewsValidator.getInstance();
    }

    public List<NewsDtoResponse> readAll() {
        List<NewsModel> newsModels = newsRepository.readAll();
        return mapper.modelListToDtoList(newsModels);
    }

    public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
        newsValidator.validateNewsDto(dtoRequest);
        NewsModel model = mapper.dtoToModel(dtoRequest);
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        model.setCreateDate(now);
        model.setLastUpdatedDate(now);
        NewsModel newsModel = newsRepository.create(model);
        return mapper.modelToDto(newsModel);
    }

    public NewsDtoResponse readById(Long newsId) {
        validateNewsExists(newsId);
        NewsModel newsModel = newsRepository.readById(newsId);
        return mapper.modelToDto(newsModel);
    }

    public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
        Long newsId = dtoRequest.id();
        validateNewsExists(newsId);
        newsValidator.validateNewsDto(dtoRequest);
        NewsModel model = mapper.dtoToModel(dtoRequest);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        model.setLastUpdatedDate(date);
        NewsModel newsModel = newsRepository.update(model);
        return mapper.modelToDto(newsModel);
    }

    public Boolean deleteById(Long newsId) {
        validateNewsExists(newsId);
        return newsRepository.deleteById(newsId);
    }

    private void validateNewsExists(Long newsId) {
        if (!newsRepository.isExistById(newsId)) {
            throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), newsId));
        }
    }


}

