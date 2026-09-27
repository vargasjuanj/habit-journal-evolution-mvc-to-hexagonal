package com.example.habit_journal_api_hex.infrastructure.persistence.jpa;

import com.example.habit_journal_api_hex.domain.Habit;
import com.example.habit_journal_api_hex.domain.port.out.HabitRepositoryPort;
import com.example.habit_journal_api_hex.infrastructure.persistence.jpa.entity.HabitEntity;
import com.example.habit_journal_api_hex.infrastructure.persistence.jpa.repository.HabitJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HabitJpaAdapter implements HabitRepositoryPort {
    private final HabitJpaRepository jpaRepository;
    private final HabitJpaMapper mapper;

    @Override
    public Habit save(Habit habit) {

        HabitEntity habitEntity = mapper.toEntity(habit);

        HabitEntity savedEntity = jpaRepository.save(habitEntity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Habit> findByName(String name) {
        return jpaRepository.findByName(name).map(mapper::toDomain);
    }

    @Override
    public List<Habit> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Habit> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
