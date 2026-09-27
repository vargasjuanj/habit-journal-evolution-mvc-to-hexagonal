package com.example.habit_journal_api_hex.aplication.exceptions;

public class DuplicateHabitException extends RuntimeException {
    public DuplicateHabitException(String name) {
        super("El hábito '" + name + "' ya existe. No se permiten duplicados.");
    }
}
