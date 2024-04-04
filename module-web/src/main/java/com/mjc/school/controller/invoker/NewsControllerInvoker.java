package com.mjc.school.controller.invoker;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.controller.utils.ScanUtils;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class NewsControllerInvoker {

    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;
    private final Scanner scanner;

    public void createNews() {
        System.out.println(MenuConstants.CREATE_NEWS_TEXT);
        System.out.println(controller.create(createRequestWithoutId()));
    }

    public void getAllNews() {
        System.out.println(MenuConstants.GET_ALL_NEWS_TEXT);
        System.out.println(controller.readAll());
    }

    public void getNewsById() {
        System.out.println(MenuConstants.GET_NEWS_BY_ID_TEXT);
        System.out.println(MenuConstants.ENTER_NEWS_ID_TEXT);
        System.out.println(controller.readById(ScanUtils.getNextLong(scanner)));
    }

    public void updateNews() {
        System.out.println(MenuConstants.UPDATE_NEWS_TEXT);
        System.out.println(MenuConstants.ENTER_NEWS_ID_TEXT);
        Long id = ScanUtils.getNextLong(scanner);
        NewsDtoRequest request = createRequestWithoutId();
        request.setId(id);
        System.out.println(controller.update(request));
    }

    public void deleteNews() {
        System.out.println(MenuConstants.DELETE_NEWS_TEXT);
        System.out.println(MenuConstants.ENTER_NEWS_ID_TEXT);
        Long id = ScanUtils.getNextLong(scanner);
        System.out.println(controller.deleteById(id));
    }

    private NewsDtoRequest createRequestWithoutId() {
        System.out.println(MenuConstants.ENTER_TITLE_TEXT);
        String title = scanner.nextLine();
        System.out.println(MenuConstants.ENTER_CONTENT_TEXT);
        String content = scanner.nextLine();
        System.out.println(MenuConstants.ENTER_AUTHOR_ID_TEXT);
        Long authorId = ScanUtils.getNextLong(scanner);
        NewsDtoRequest request = new NewsDtoRequest();
        request.setTitle(title);
        request.setContent(content);
        request.setAuthorId(authorId);
        return request;
    }
}
