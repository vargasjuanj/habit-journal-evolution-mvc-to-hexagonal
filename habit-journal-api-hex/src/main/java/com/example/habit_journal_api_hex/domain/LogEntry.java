package com.example.habit_journal_api_hex.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogEntry {
    private LocalDateTime entryDate;
}
