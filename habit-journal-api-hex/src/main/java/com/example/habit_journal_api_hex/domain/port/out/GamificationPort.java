package com.example.habit_journal_api_hex.domain.port.out;

public interface GamificationPort {
    void notifyHabitCreation(Long userId, String habitId);
}
