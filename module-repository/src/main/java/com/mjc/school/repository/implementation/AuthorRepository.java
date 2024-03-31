package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.domain.DataSource;
import com.mjc.school.repository.model.implementation.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    private final DataSource dataSource;

    @Autowired
    public AuthorRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthorList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return readAll().stream().filter(x -> Objects.equals(x.getId(), id)).findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel newAuthor) {
        dataSource.updateAuthorList(newAuthor);
        return newAuthor;
    }

    @Override
    public AuthorModel update(AuthorModel author) {
        List<AuthorModel> allAuthors = readAll();
        allAuthors.set(allAuthors.indexOf(readById(author.getId()).get()), author);
        dataSource.setAuthorList(allAuthors);
        return author;
    }

    @Override
    public boolean deleteById(Long id) {
        List<AuthorModel> allAuthors = readAll();
        boolean isAuthorDeleted = allAuthors.remove(readAll().get(id.intValue()));
        dataSource.setAuthorList(allAuthors);
        return isAuthorDeleted;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
