package com.example.habit_journal_api_hex.infrastructure.web.controller;

import com.example.habit_journal_api_hex.domain.Habit;
import com.example.habit_journal_api_hex.domain.port.in.CreateHabitUseCase;
import com.example.habit_journal_api_hex.domain.port.in.RetrieveHabitsUseCase;
import com.example.habit_journal_api_hex.infrastructure.web.controller.dto.HabitDtoMapper;
import com.example.habit_journal_api_hex.infrastructure.web.controller.dto.HabitRequestDTO;
import com.example.habit_journal_api_hex.infrastructure.web.controller.dto.HabitResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitController {
    private final CreateHabitUseCase createHabitUseCase;
    private final RetrieveHabitsUseCase retrieveHabitsUseCase;
    private final HabitDtoMapper habitDtoMapper;

    @PostMapping
    public ResponseEntity<HabitResponseDTO> createNewHabit(@Valid @RequestBody HabitRequestDTO requestDTO){
        Habit domainHabit = habitDtoMapper.toDomain(requestDTO);
        Habit createdHabit = createHabitUseCase.createHabit(domainHabit);

        return ResponseEntity.status(HttpStatus.CREATED).body(habitDtoMapper.toResponse(createdHabit));
    }

    @GetMapping
    public ResponseEntity<List<HabitResponseDTO>> getAllHabits(){
        List<Habit> habits = retrieveHabitsUseCase.getHabits();
        List<HabitResponseDTO> responseDTOS = habits.stream().map(habitDtoMapper::toResponse).toList();

        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponseDTO> getHabitById(@PathVariable String id){
        Habit habit = retrieveHabitsUseCase.getHabit(id);

        return ResponseEntity.ok(habitDtoMapper.toResponse(habit));
    }

}
