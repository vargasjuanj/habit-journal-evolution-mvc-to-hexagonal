package com.example.habit_journal_api_hex.infrastructure.external.gamification;


import com.example.habit_journal_api_hex.domain.port.out.GamificationPort;
import com.example.habit_journal_api_hex.infrastructure.external.gamification.dto.GamificationFeignClient;
import com.example.habit_journal_api_hex.infrastructure.external.gamification.dto.HabitCompletionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GamificationAdapter implements GamificationPort {

    private final GamificationFeignClient feignClient;


    @Override
    public void notifyHabitCreation(Long userId, String habitId) {
        HabitCompletionRequest request = HabitCompletionRequest.builder()
                .userId(userId)
                .habitId(habitId)
                .build();
        try{
            feignClient.notifyHabitCompletion(request);
            System.out.println("✅ Notificación enviada a Gamification para el hábito: " + habitId);
        }catch (Exception e){
            System.err.println("⚠️ Error al notificar a Gamification: " + e.getMessage());
        }

    }
}
