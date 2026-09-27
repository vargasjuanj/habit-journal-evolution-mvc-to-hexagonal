package com.example.habit_journal_api_hex.infrastructure.persistence.mongo;

import com.example.habit_journal_api_hex.common.IdGenerator;
import com.example.habit_journal_api_hex.domain.Habit;
import com.example.habit_journal_api_hex.domain.port.out.HabitRepositoryPort;
import com.example.habit_journal_api_hex.infrastructure.persistence.mongo.document.HabitDocument;
import com.example.habit_journal_api_hex.infrastructure.persistence.mongo.repository.HabitMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HabitMongoAdapter implements HabitRepositoryPort {
    private final HabitMongoRepository mongoRepository;
    private final HabitMongoMapper mapper;

    @Override
    public Habit save(Habit habit) {
        HabitDocument document = mapper.toDocument(habit);

        if(document.getId()==null){
            document.setId(IdGenerator.nextId());
        }

        HabitDocument savedDoc = mongoRepository.save(document);
        return mapper.toDomain(savedDoc);
    }

    @Override
    public Optional<Habit> findByName(String name) {
        return mongoRepository.findByName(name).map(mapper::toDomain);
    }

    @Override
    public List<Habit> findAll() {
        return mongoRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Habit> findById(String id) {
        return mongoRepository.findById(id).map(mapper::toDomain);
    }
}
