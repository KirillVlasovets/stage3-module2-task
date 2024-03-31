package com.mjc.school.repository.domain;

import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.repository.model.implementation.NewsModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private final List<String> contentList;
    @Getter
    @Setter
    private List<AuthorModel> authorList;
    @Getter
    @Setter
    private List<NewsModel> newsList;
    private static final String AUTHOR_FILE_NAME = "authors";
    private static final String NEWS_FILE_NAME = "news";
    private static final String CONTENT_FILE_NAME = "content";
    private static DataSource instance;

    private DataSource() {
        this.contentList = initContentList();
        this.authorList = initAuthorList();
        this.newsList = initNewsList();
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public void updateContentList(String newContent) {
        contentList.add(newContent);
        new Utils().addLineInFile(CONTENT_FILE_NAME, newContent);
    }

    public void updateAuthorList(AuthorModel newAuthor) {
        authorList.add(newAuthor);
        new Utils().addLineInFile(AUTHOR_FILE_NAME, newAuthor.getName());
    }

    public void updateNewsList(NewsModel newNews) {
        newsList.add(newNews);
        new Utils().addLineInFile(NEWS_FILE_NAME, newNews.getTitle());
    }

    private List<String> initContentList() {
        return new ArrayList<>(new Utils().readFile(CONTENT_FILE_NAME));
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
