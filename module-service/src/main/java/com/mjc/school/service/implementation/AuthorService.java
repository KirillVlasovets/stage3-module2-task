package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidatingAuthor;
import com.mjc.school.service.annotation.ValidatingAuthorId;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.enums.Errors;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final BaseRepository<AuthorModel, Long> repository;

    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return repository.readAll().stream().map(AuthorMapper.INSTANCE::authorModelToResponse).toList();
    }

    @Override
    @ValidatingAuthorId
    public AuthorDtoResponse readById(Long id) {
        AuthorDtoResponse response = AuthorMapper.INSTANCE.authorModelToResponse(repository.readById(id).get());
        checkAuthorIdExists(response, id);
        return response;
    }

    @Override
    @ValidatingAuthor
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return AuthorMapper.INSTANCE.authorModelToResponse(repository.create(AuthorMapper.INSTANCE.authorRequestToModel(createRequest)));
    }

    @Override
    @ValidatingAuthor
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        AuthorDtoResponse response = AuthorMapper.INSTANCE.authorModelToResponse(repository.update(AuthorMapper.INSTANCE.authorRequestToModel(updateRequest)));
        checkAuthorIdExists(response, updateRequest.getId());
        return response;
    }

    @Override
    @ValidatingAuthorId
    public boolean deleteById(Long id) {
        if (!repository.deleteById(id)) {
            Errors error = Errors.AUTHOR_NOT_FOUND_EXCEPTION;
            throw new NotFoundException(String.format("ERROR_CODE: %d ERROR_MESSAGE: %s id = %d",
                    error.getCode(), error.getMessage(), id));
        }
        return true;
    }

    private void checkAuthorIdExists(AuthorDtoResponse response, Long id) {
        if (response == null) {
            Errors error = Errors.AUTHOR_NOT_FOUND_EXCEPTION;
            throw new NotFoundException(
                    String.format("ERROR_CODE: %d ERROR_MESSAGE: %s id = %d", error.getCode(), error.getMessage(), id));
        }
    }
}
