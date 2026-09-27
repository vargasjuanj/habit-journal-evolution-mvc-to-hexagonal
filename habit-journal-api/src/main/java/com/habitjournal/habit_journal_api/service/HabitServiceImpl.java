package com.habitjournal.habit_journal_api.service;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.controller.dto.HabitResponseDTO;
import com.habitjournal.habit_journal_api.model.Habit;
import com.habitjournal.habit_journal_api.model.LogEntry;
import com.habitjournal.habit_journal_api.repository.HabitRepository;
import com.habitjournal.habit_journal_api.service.exceptions.DuplicateHabitException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService{
    private final HabitRepository habitRepository;

    @Override
    @Transactional
    public HabitResponseDTO createNewHabit(HabitRequestDTO requestDTO){

        habitRepository.findByName(requestDTO.getName()).ifPresent(habit ->{
            throw new DuplicateHabitException(requestDTO.getName());
        });

        Habit newHabit = new Habit();
        newHabit.setName(requestDTO.getName());

        if(requestDTO.getLogs() != null && !requestDTO.getLogs().isEmpty()){

            List<LogEntry> logs = requestDTO.getLogs().stream()
                    .map(date -> {
                        LogEntry log = new LogEntry();
                        log.setEntryDate(date);
                        return log;
                    }).toList();

            newHabit.setLogEntries(logs);
        }

        Habit habit = habitRepository.save(newHabit);

        return mapToResponseDTO(habit);

    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitResponseDTO> findAllHabits() {
        List<Habit> habits = habitRepository.findAll();

        return habits.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public List<HabitResponseDTO> findHabitsLoggedSince(int days) {
        LocalDateTime sinceDate = LocalDateTime.now().minusDays(days);
        List<Habit> activeHabits = habitRepository.findHabitsLoggedSince(sinceDate);
        return activeHabits.stream().map(this::mapToResponseDTO).toList();
    }

    private HabitResponseDTO mapToResponseDTO(Habit habit) {
        List<LocalDateTime> logDates = habit.getLogEntries().stream()
                .map(LogEntry::getEntryDate).toList();

        return new HabitResponseDTO(habit.getId(), habit.getName(), logDates);
    }


}
