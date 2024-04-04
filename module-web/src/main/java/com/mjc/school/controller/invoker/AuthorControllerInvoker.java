package com.mjc.school.controller.invoker;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.controller.utils.ScanUtils;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class AuthorControllerInvoker {

    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller;
    private final Scanner scanner;

    public void createAuthor() {
        System.out.println(MenuConstants.CREATE_AUTHOR_TEXT);
        controller.create(createRequestWithoutId());
    }

    public void getAllAuthors() {
        System.out.println(MenuConstants.GET_ALL_AUTHORS_TEXT);
        System.out.println(controller.readAll());
    }

    public void getAuthorById() {
        System.out.println(MenuConstants.GET_AUTHOR_BY_ID_TEXT);
        System.out.println(MenuConstants.ENTER_AUTHOR_ID_TEXT);
        System.out.println(controller.readById(ScanUtils.getNextLong(scanner)));
    }

    public void updateAuthor() {
        System.out.println(MenuConstants.UPDATE_AUTHOR_TEXT);
        System.out.println(MenuConstants.ENTER_AUTHOR_ID_TEXT);
        Long id = ScanUtils.getNextLong(scanner);
        AuthorDtoRequest request = createRequestWithoutId();
        request.setId(id);
        System.out.println(controller.update(request));
    }

    public void deleteAuthor() {
        System.out.println(MenuConstants.DELETE_AUTHOR_TEXT);
        System.out.println(MenuConstants.ENTER_AUTHOR_ID_TEXT);
        Long id = ScanUtils.getNextLong(scanner);
        System.out.println(controller.deleteById(id));
    }

    private AuthorDtoRequest createRequestWithoutId() {
        System.out.println(MenuConstants.ENTER_NAME_TEXT);
        String name = scanner.nextLine();
        AuthorDtoRequest request = new AuthorDtoRequest();
        request.setName(name);
        return request;
    }
}
