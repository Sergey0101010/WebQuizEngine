package com.sergey.webquizengine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AnswerQuizDto {
    private List<Integer> answer = new ArrayList<>();
}
