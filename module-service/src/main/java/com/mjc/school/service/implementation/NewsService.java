package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.repository.model.implementation.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidatingNews;
import com.mjc.school.service.annotation.ValidatingNewsId;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.enums.Errors;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseRepository<NewsModel, Long> newsRepository;
    private final BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository, BaseRepository<AuthorModel, Long> authorRepository) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsRepository.readAll().stream().map(NewsMapper.INSTANCE::newsModelToResponse).toList();
    }

    @Override
    @ValidatingNewsId
    public NewsDtoResponse readById(Long id) {
        NewsDtoResponse response = NewsMapper.INSTANCE.newsModelToResponse(newsRepository.readById(id).get());
        checkNewsIdExists(response, id);
        return response;
    }

    @Override
    @ValidatingNews
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        checkAuthorIdExists(createRequest);
        return NewsMapper.INSTANCE.newsModelToResponse(newsRepository.create(NewsMapper.INSTANCE.newsRequestToModel(createRequest)));
    }

    @Override
    @ValidatingNews
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        NewsDtoResponse response = NewsMapper.INSTANCE.newsModelToResponse(newsRepository.update(NewsMapper.INSTANCE.newsRequestToModel(updateRequest)));
        checkNewsIdExists(response, updateRequest.getId());
        return response;
    }

    @Override
    @ValidatingNewsId
    public boolean deleteById(Long id) {
        if (!newsRepository.deleteById(id)) {
            Errors error = Errors.NEWS_NOT_FOUND_EXCEPTION;
            throw new NotFoundException(String.format("ERROR_CODE: %d ERROR_MESSAGE: %s id = %d",
                    error.getCode(), error.getMessage(), id));
        }
        return true;
    }

    private void checkNewsIdExists(NewsDtoResponse response, Long id) {
        if (response == null) {
            Errors error = Errors.NEWS_NOT_FOUND_EXCEPTION;
            throw new NotFoundException(
                    String.format("ERROR_CODE: %d ERROR_MESSAGE: %s id = %d", error.getCode(), error.getMessage(), id));
        }
    }

    private void checkAuthorIdExists(NewsDtoRequest request) {
        if (!authorRepository.readAll().stream().map(AuthorModel::getId).toList().contains(request.getAuthorId())) {
            Errors error = Errors.AUTHOR_NOT_FOUND_EXCEPTION;
            throw new NotFoundException(
                    String.format("ERROR_CODE: %d ERROR_MESSAGE: %s id = %d",
                            error.getCode(), error.getMessage(), request.getAuthorId()));
        }
    }
}
