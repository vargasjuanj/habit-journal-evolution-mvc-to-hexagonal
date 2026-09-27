package com.habitjourna.gamification_service.controller.dto;

import lombok.Data;

@Data
public class HabitCompletionRequestDTO {
    private Long userId;
    private String habitId;
}
