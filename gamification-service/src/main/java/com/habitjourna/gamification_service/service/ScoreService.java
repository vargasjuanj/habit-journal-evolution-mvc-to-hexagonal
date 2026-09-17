package com.habitjourna.gamification_service.service;

import com.habitjourna.gamification_service.controller.dto.HabitCompletionResponseDTO;

import java.util.List;

public interface ScoreService {
    void processHabitCompletion(Long userId, String habitId);
    List<HabitCompletionResponseDTO> getScoresByUserId(Long userId);
}
