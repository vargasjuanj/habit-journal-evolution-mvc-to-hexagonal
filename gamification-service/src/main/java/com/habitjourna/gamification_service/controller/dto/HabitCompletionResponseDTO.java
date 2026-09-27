package com.habitjourna.gamification_service.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HabitCompletionResponseDTO {
    private Long id;
    private Long userId;
    private String habitId;
    private int points;
    private LocalDateTime awardedAt;

    public HabitCompletionResponseDTO(Long userId, String habitId, int points, LocalDateTime awardedAt) {
        this.userId = userId;
        this.habitId = habitId;
        this.points = points;
        this.awardedAt = awardedAt;
    }
}
