package com.example.habit_journal_api_hex.aplication.exceptions;

public class HabitNotFoundException extends RuntimeException {
    public HabitNotFoundException(String id) {
        super("No se encontró el habito con el ID: " + id);
    }
}
