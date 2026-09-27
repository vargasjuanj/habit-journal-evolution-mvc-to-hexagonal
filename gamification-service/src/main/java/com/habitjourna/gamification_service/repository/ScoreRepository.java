package com.habitjourna.gamification_service.repository;

import com.habitjourna.gamification_service.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUserId(Long userId);
}
