package com.example.habit_journal_api_hex.infrastructure.persistence.mongo.repository;

import com.example.habit_journal_api_hex.infrastructure.persistence.mongo.document.HabitDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitMongoRepository extends MongoRepository<HabitDocument, String> {
    Optional<HabitDocument> findByName(String name);
}
