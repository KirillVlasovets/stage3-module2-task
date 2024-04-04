package com.mjc.school.controller.view;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.constants.ActionCodes;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.controller.invoker.AuthorControllerInvoker;
import com.mjc.school.controller.invoker.NewsControllerInvoker;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public Menu(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController,
                BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController) {
        this.authorController = authorController;
        this.newsController = newsController;
    }

    public void start() {
        NewsControllerInvoker newsInvoker = new NewsControllerInvoker(newsController, scanner);
        AuthorControllerInvoker authorInvoker = new AuthorControllerInvoker(authorController, scanner);
        while (true) {
            System.out.println(MenuConstants.START_TEXT);
            switch (scanner.nextLine()) {
                case ActionCodes.CREATE_NEWS -> newsInvoker.createNews();
                case ActionCodes.CREATE_AUTHOR -> authorInvoker.createAuthor();
                case ActionCodes.GET_ALL_NEWS -> newsInvoker.getAllNews();
                case ActionCodes.GET_ALL_AUTHORS -> authorInvoker.getAllAuthors();
                case ActionCodes.GET_NEWS_BY_ID -> newsInvoker.getNewsById();
                case ActionCodes.GET_AUTHOR_BY_ID -> authorInvoker.getAuthorById();
                case ActionCodes.UPDATE_NEWS -> newsInvoker.updateNews();
                case ActionCodes.UPDATE_AUTHOR -> authorInvoker.updateAuthor();
                case ActionCodes.DELETE_NEWS -> newsInvoker.deleteNews();
                case ActionCodes.DELETE_AUTHOR -> authorInvoker.deleteAuthor();
                case ActionCodes.EXIT -> exit();
            }
        }
    }

    private void exit() {
        System.out.println(MenuConstants.EXIT_TEXT);
        System.exit(0);
    }
}
