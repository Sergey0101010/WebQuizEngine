package com.sergey.webquizengine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuizNoAnswerDto {
    private Integer id;
    private String title;
    private String text;
    private List<String> options;
}
