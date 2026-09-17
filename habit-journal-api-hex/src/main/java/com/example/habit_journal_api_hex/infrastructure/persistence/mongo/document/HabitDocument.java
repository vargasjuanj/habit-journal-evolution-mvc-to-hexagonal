package com.example.habit_journal_api_hex.infrastructure.persistence.mongo.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "habits")
public class HabitDocument {
    @Id
    private String id;
    private String name;

    List<LogEntryDocument> logEntries = new ArrayList<>();
}
