package com.example.habit_journal_api_hex.infrastructure.persistence.mongo.document;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LogEntryDocument {
    private LocalDateTime entryDate;
}
