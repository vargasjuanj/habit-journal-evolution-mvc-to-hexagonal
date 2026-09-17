package com.habitjournal.habit_journal_api.data;

import com.habitjournal.habit_journal_api.model.Habit;
import com.habitjournal.habit_journal_api.model.LogEntry;
import com.habitjournal.habit_journal_api.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final HabitRepository habitRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {

        mongoTemplate.dropCollection(Habit.class);
        createHabitWithLogs(
                "Aprender Arquitectura",
                List.of(
                        LocalDateTime.now().minusDays(2),
                        LocalDateTime.now()
                )
        );
        createHabitWithLogs(
                "Hacer Ejercicio (Yoga)",
                List.of(
                        LocalDateTime.now().minusDays(5),
                        LocalDateTime.now().minusDays(3)
                )
        );
        Habit habit3 = new Habit();
        habit3.setName("Beber Agua (Inactivo)");
        habitRepository.save(habit3);


        System.out.println("✅ Datos de prueba cargados: 3 Hábitos.");

    }

    private void createHabitWithLogs(String name, List<LocalDateTime> dates) {
        Habit habit = new Habit();
        habit.setName(name);

        List<LogEntry> logs = dates.stream()
                .map(date -> {
                    LogEntry log = new LogEntry();
                    log.setEntryDate(date);
                    return log;
                })
                .collect(Collectors.toList());

        habit.getLogEntries().addAll(logs);

        habitRepository.save(habit);
    }
}
