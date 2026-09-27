package com.example.habit_journal_api_hex.aplication;

import com.example.habit_journal_api_hex.aplication.exceptions.DuplicateHabitException;
import com.example.habit_journal_api_hex.aplication.exceptions.HabitNotFoundException;
import com.example.habit_journal_api_hex.domain.Habit;
import com.example.habit_journal_api_hex.domain.port.in.CreateHabitUseCase;
import com.example.habit_journal_api_hex.domain.port.in.RetrieveHabitsUseCase;
import com.example.habit_journal_api_hex.domain.port.out.GamificationPort;
import com.example.habit_journal_api_hex.domain.port.out.HabitRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class HabitService implements CreateHabitUseCase, RetrieveHabitsUseCase {
    private final HabitRepositoryPort habitRepositoryPort;
    private final GamificationPort gamificationPort;

    @Override
    public Habit createHabit(Habit habit) {

        habitRepositoryPort.findByName(habit.getName()).ifPresent(
                existing -> {
                    throw new DuplicateHabitException(habit.getName());
                }
        );

        if(!habit.hasValidName()){
            throw new IllegalArgumentException("El nombre del hábito no cumple con las reglas de negocio.");
        }
        Habit savedHabit = habitRepositoryPort.save(habit);

        gamificationPort.notifyHabitCreation(1l, savedHabit.getId());

        return savedHabit;
    }

    @Override
    public List<Habit> getHabits() {
        return habitRepositoryPort.findAll();
    }

    @Override
    public Habit getHabit(String id) {
        return habitRepositoryPort.findById(id).orElseThrow(
                () -> new HabitNotFoundException(id)
        );
    }
}
