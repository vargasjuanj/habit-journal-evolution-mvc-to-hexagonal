package com.example.habit_journal_api_hex.domain;

import lombok.Data;

import java.util.List;

@Data
public class Habit {
    private String id;
    private String name;
    private List<LogEntry> logEntries;

    public boolean hasValidName(){
        return this.name!=null && this.name.trim().length()>=5;
    }
}
