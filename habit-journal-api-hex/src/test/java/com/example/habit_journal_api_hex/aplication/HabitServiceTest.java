package com.example.habit_journal_api_hex.aplication;


import com.example.habit_journal_api_hex.aplication.exceptions.DuplicateHabitException;
import com.example.habit_journal_api_hex.domain.Habit;
import com.example.habit_journal_api_hex.domain.LogEntry;
import com.example.habit_journal_api_hex.domain.port.out.HabitRepositoryPort;
import com.example.habit_journal_api_hex.infrastructure.persistence.memory.FakeHabitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HabitServiceTest {

    private HabitService habitService;
    private HabitRepositoryPort habitRepository;

    private com.example.habit_journal_api_hex.domain.port.out.GamificationPort gamificationPort;

    @BeforeEach
    void setUp() {
        habitRepository = new FakeHabitRepository();
        gamificationPort = org.mockito.Mockito.mock(com.example.habit_journal_api_hex.domain.port.out.GamificationPort.class);
        habitService = new HabitService(habitRepository, gamificationPort);
    }

    @Test
    @DisplayName("Debe crear y recuperar un hábito correctamente (Happy Path)")
    void shouldCreateAndRetrieveHabit() {
        Habit newHabit = new Habit();
        newHabit.setName("Leer Clean Code");
        newHabit.setLogEntries(new ArrayList<>());
        Habit savedHabit = habitService.createHabit(newHabit);
        assertNotNull(savedHabit.getId(), "El ID no debería ser nulo tras guardar");
        assertFalse(savedHabit.getId().isEmpty());
        assertEquals("Leer Clean Code", savedHabit.getName());
        Habit foundHabit = habitService.getHabit(savedHabit.getId());
        assertEquals(savedHabit.getId(), foundHabit.getId());
    }

    @Test
    @DisplayName("Debe lanzar excepción si el nombre está duplicado")
    void shouldThrowExceptionForDuplicateName() {
        Habit habit1 = new Habit();
        habit1.setName("Correr");
        habitService.createHabit(habit1);

        Habit habit2 = new Habit();
        habit2.setName("Correr");
        assertThrows(DuplicateHabitException.class, () -> {
            habitService.createHabit(habit2);
        });
    }

    @Test
    @DisplayName("Debe persistir los LogEntries correctamente")
    void shouldPersistLogEntries() {
        Habit habit = new Habit();
        habit.setName("Meditar");

        LogEntry log1 = new LogEntry();
        log1.setEntryDate(LocalDateTime.now());

        habit.setLogEntries(List.of(log1));
        Habit savedHabit = habitService.createHabit(habit);
        Habit retrievedHabit = habitService.getHabit(savedHabit.getId());
        assertEquals(1, retrievedHabit.getLogEntries().size());
    }
}
