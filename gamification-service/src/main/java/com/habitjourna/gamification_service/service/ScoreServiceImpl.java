package com.habitjourna.gamification_service.service;

import com.habitjourna.gamification_service.controller.dto.HabitCompletionResponseDTO;
import com.habitjourna.gamification_service.model.Score;
import com.habitjourna.gamification_service.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{

    private final ScoreRepository scoreRepository;

    private static final int POINTS_PER_HABIT=10;

    @Override
    public void processHabitCompletion(Long userId, String habitId) {
        System.out.println("--- GAMIFICATION: Recibida notificación para usuario " + userId + " ---");

        Score score = Score.builder()
                .userId(userId)
                .habitId(habitId)
                .points(POINTS_PER_HABIT)
                .build();

        scoreRepository.save(score);

        System.out.println("--- GAMIFICATION: +10 Puntos guardados ---");
    }

    @Override
    public List<HabitCompletionResponseDTO> getScoresByUserId(Long userId) {

        List<Score> scores = scoreRepository.findByUserId(userId);

        return scores.stream().map(score ->
                new HabitCompletionResponseDTO(score.getUserId(),
                        score.getHabitId(), score.getPoints(),
                        score.getAwardedAt())).toList();
    }
}
