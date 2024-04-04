package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Data
@NoArgsConstructor
public class NewsDtoRequest {

    private Long id;
    private String title;
    private String content;
    private Long authorId;
}
