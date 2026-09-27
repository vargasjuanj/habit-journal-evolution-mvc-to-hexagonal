package com.example.habit_journal_api_hex.domain.port.in;

import com.example.habit_journal_api_hex.domain.Habit;

import java.util.List;

public interface RetrieveHabitsUseCase {
    List<Habit> getHabits();
    Habit getHabit(String id);
}
