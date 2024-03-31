package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Getter
public class AuthorDtoResponse {

    private Long id;
    private String name;
}
