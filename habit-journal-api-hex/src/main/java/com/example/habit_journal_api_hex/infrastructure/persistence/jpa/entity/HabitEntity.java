package com.example.habit_journal_api_hex.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "habits")
public class HabitEntity {

    @Id @UuidGenerator
    private String id;
    private String name;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    List<LogEntryEntity> logEntries = new ArrayList<>();

}
