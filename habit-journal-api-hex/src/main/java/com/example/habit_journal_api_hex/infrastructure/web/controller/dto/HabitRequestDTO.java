package com.example.habit_journal_api_hex.infrastructure.web.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HabitRequestDTO {
    @NotBlank(message = "El nombre del hábito no puede estar vacío.")
    private String name;
    private List<LocalDateTime> logs;
}
