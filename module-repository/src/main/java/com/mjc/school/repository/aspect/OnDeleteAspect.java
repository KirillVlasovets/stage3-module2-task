package com.mjc.school.repository.aspect;

import com.mjc.school.repository.implementation.NewsRepository;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OnDeleteAspect {

    private final NewsRepository newsRepository;

    @Autowired
    public OnDeleteAspect(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @After("@annotation(com.mjc.school.repository.annotation.OnDelete) && args(authorId)")
    public void deleteRelatedNews(Long authorId) {
        for (int i = 0; i < newsRepository.readAll().size(); i++) {
            if (authorId.equals(newsRepository.readAll().get(i).getAuthorId())) {
                newsRepository.readAll().get(i).setAuthorId(null);
            }
        }
        newsRepository.readAll().removeIf(newsModel -> newsModel.getAuthorId() == null);
        System.out.println("All the related news has been deleted!");
    }
}
