package com.example.habit_journal_api_hex.infrastructure.persistence.mongo;

import com.example.habit_journal_api_hex.domain.Habit;
import com.example.habit_journal_api_hex.domain.LogEntry;
import com.example.habit_journal_api_hex.infrastructure.persistence.mongo.document.HabitDocument;
import com.example.habit_journal_api_hex.infrastructure.persistence.mongo.document.LogEntryDocument;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HabitMongoMapper {

    public HabitDocument toDocument(Habit domain) {
        HabitDocument doc = new HabitDocument();
        doc.setId(domain.getId());
        doc.setName(domain.getName());

        if (domain.getLogEntries() != null) {
            List<LogEntryDocument> logs = domain.getLogEntries().stream().map(log -> {
                LogEntryDocument logDoc = new LogEntryDocument();
                logDoc.setEntryDate(log.getEntryDate());
                return logDoc;
            }).collect(Collectors.toList());

            doc.setLogEntries(logs);
        }


        return doc;
    }

    public Habit toDomain(HabitDocument doc) {
        Habit domain = new Habit();
        domain.setId(doc.getId());
        domain.setName(doc.getName());

        if (doc.getLogEntries() != null) {
            domain.setLogEntries(doc.getLogEntries().stream().map( logDoc -> {
                LogEntry log = new LogEntry();
                log.setEntryDate(logDoc.getEntryDate());
                return log;
            }).collect(Collectors.toList()));
        } else {
            domain.setLogEntries(new ArrayList<>());
        }

        return domain;
    }


}

