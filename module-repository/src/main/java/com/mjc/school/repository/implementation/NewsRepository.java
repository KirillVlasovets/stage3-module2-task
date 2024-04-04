package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotation.OnDelete;
import com.mjc.school.repository.domain.DataSource;
import com.mjc.school.repository.domain.Utils;
import com.mjc.school.repository.model.implementation.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final DataSource dataSource;

    @Autowired
    public NewsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        Optional<NewsModel> response = readAll().stream().filter(x -> Objects.equals(x.getId(), id)).findFirst();
        if (response.equals(Optional.empty())) {
            return null;
        }
        return response;
    }

    @Override
    public NewsModel create(NewsModel newNews) {
        dataSource.updateNewsList(newNews);
        return newNews;
    }

    @Override
    public NewsModel update(NewsModel news) {
        List<NewsModel> allNews = readAll();
        news.setLastUpdateDate(new Utils().getTime());
        try {
            allNews.set(allNews.indexOf(readById(news.getId()).get()), news);
        } catch (NullPointerException exception) {
            return null;
        }
        dataSource.setNewsList(allNews);
        return news;
    }

    @OnDelete
    @Override
    public boolean deleteById(Long id) {
        List<NewsModel> allNews = readAll();
        boolean isNewsDeleted = allNews.remove(readAll().get(id.intValue()));
        dataSource.setNewsList(allNews);
        return isNewsDeleted;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
