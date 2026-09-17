package com.example.habit_journal_api_hex.domain.port.out;

import com.example.habit_journal_api_hex.domain.Habit;

import java.util.List;
import java.util.Optional;

public interface HabitRepositoryPort {
    Habit save(Habit habit);
    Optional<Habit> findByName(String name);
    List<Habit> findAll();
    Optional<Habit> findById(String id);
}
