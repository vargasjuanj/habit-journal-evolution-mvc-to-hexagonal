package com.example.habit_journal_api_hex.infrastructure.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class HabitResponseDTO {
    private String id;
    private String name;
    private List<LocalDateTime> logs;
}
