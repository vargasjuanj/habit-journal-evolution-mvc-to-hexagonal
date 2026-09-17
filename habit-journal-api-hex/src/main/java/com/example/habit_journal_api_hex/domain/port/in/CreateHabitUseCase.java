package com.example.habit_journal_api_hex.domain.port.in;

import com.example.habit_journal_api_hex.domain.Habit;

public interface CreateHabitUseCase {
    Habit createHabit(Habit habit);
}
