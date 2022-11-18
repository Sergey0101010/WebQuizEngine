package com.sergey.webquizengine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CompletedQuizDto {
    private int id;
    private LocalDateTime completedAt;
}
