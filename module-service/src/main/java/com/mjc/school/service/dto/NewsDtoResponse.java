package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Getter
public class NewsDtoResponse {

    private Long id;
    private String title;
    private String content;
    private String createDate;
    private String lastUpdateDate;
    private Long authorId;
}
