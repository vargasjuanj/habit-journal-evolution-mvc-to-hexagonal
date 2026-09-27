package com.example.habit_journal_api_hex.infrastructure.persistence.memory;


import com.example.habit_journal_api_hex.domain.Habit;
import com.example.habit_journal_api_hex.domain.port.out.HabitRepositoryPort;

import java.util.*;

public class FakeHabitRepository implements HabitRepositoryPort {


    private final Map<String, Habit> database = new HashMap<>();

    @Override
    public Habit save(Habit habit) {
        if (habit.getId() == null) {

            habit.setId(UUID.randomUUID().toString());
        }

        database.put(habit.getId(), habit);
        return habit;
    }

    @Override
    public Optional<Habit> findByName(String name) {
        return database.values().stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public List<Habit> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Habit> findById(String id) {

        return Optional.ofNullable(database.get(id));
    }
}
