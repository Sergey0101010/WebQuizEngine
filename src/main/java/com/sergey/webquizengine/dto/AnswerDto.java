package com.sergey.webquizengine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerDto {
    private boolean success;
    private String feedback;
}
