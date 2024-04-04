package com.mjc.school.repository.domain;

import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.repository.model.implementation.NewsModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSource {

    @Getter
    @Setter
    private List<AuthorModel> authorList;
    @Getter
    @Setter
    private List<NewsModel> newsList;
    private static final String AUTHOR_FILE_NAME = "authors";
    private static final String NEWS_FILE_NAME = "news";
    private static final String CONTENT_FILE_NAME = "content";

    public DataSource() {
        this.authorList = initAuthorList();
        this.newsList = initNewsList();
    }

    public void updateAuthorList(AuthorModel newAuthor) {
        authorList.add(newAuthor);
        new Utils().addLineInFile(AUTHOR_FILE_NAME, newAuthor.getName());
    }

    public void updateNewsList(NewsModel newNews) {
        newsList.add(newNews);
        new Utils().addLineInFile(NEWS_FILE_NAME, newNews.getTitle());
    }

    private List<AuthorModel> initAuthorList() {
        List<AuthorModel> authors = new ArrayList<>();
        Long id = 1L;
        for (String authorName : new Utils().readFile(AUTHOR_FILE_NAME)) {
            authors.add(new AuthorModel(id, authorName));
            id++;
        }
        return authors;
    }

    private List<NewsModel> initNewsList() {
        List<NewsModel> news = new ArrayList<>();
        int contentNumber = 0;
        Long id = 1L;
        List<String> contentList = new Utils().readFile(CONTENT_FILE_NAME);
        for (String title : new Utils().readFile(NEWS_FILE_NAME)) {
            news.add(NewsModel.builder()
                    .id(id)
                    .title(title)
                    .content(contentList.get(contentNumber))
                    .createDate(new Utils().getTime())
                    .lastUpdateDate(new Utils().getTime())
                    .authorId(id)
                    .build());
            id++;
            contentNumber++;
        }
        return news;
    }
}
