package com.example.habit_journal_api_hex.infrastructure.external.gamification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class HabitCompletionRequest {
    private Long userId;
    private String habitId;
}
