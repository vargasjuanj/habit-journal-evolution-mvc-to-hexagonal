package com.example.habit_journal_api_hex.infrastructure.external.gamification.dto;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "gamification-service", url = "http://localhost:8082")
public interface GamificationFeignClient {

    @PostMapping("/api/v1/gamification/habit-completed")
    void notifyHabitCompletion(@RequestBody HabitCompletionRequest request);

}
