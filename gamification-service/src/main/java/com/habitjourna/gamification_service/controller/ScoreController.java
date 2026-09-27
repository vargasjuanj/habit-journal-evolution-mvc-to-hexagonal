package com.habitjourna.gamification_service.controller;

import com.habitjourna.gamification_service.controller.dto.HabitCompletionRequestDTO;
import com.habitjourna.gamification_service.controller.dto.HabitCompletionResponseDTO;
import com.habitjourna.gamification_service.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gamification")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService service;

    @PostMapping("/habit-completed")
    public ResponseEntity<String> registerHabitCompletion(@RequestBody HabitCompletionRequestDTO dto) {

        service.processHabitCompletion(dto.getUserId(), dto.getHabitId());

        return ResponseEntity.ok("Puntos asignados correctamente");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HabitCompletionResponseDTO>> getUserScores(@PathVariable Long userId) {
        List<HabitCompletionResponseDTO> scores = service.getScoresByUserId(userId);
        return ResponseEntity.ok(scores);
    }
}
