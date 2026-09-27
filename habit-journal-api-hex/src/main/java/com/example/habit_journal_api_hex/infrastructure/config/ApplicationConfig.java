package com.example.habit_journal_api_hex.infrastructure.config;

import com.example.habit_journal_api_hex.aplication.HabitService;
import com.example.habit_journal_api_hex.domain.port.in.CreateHabitUseCase;
import com.example.habit_journal_api_hex.domain.port.in.RetrieveHabitsUseCase;
import com.example.habit_journal_api_hex.domain.port.out.GamificationPort;
import com.example.habit_journal_api_hex.domain.port.out.HabitRepositoryPort;
import com.example.habit_journal_api_hex.infrastructure.persistence.mongo.HabitMongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public HabitRepositoryPort habitRepositoryPort(HabitMongoAdapter adapter){
        return adapter;
    }

    @Bean
    public CreateHabitUseCase createHabitUseCase(HabitRepositoryPort habitRepositoryPort, GamificationPort gamificationPort){
        return new HabitService(habitRepositoryPort, gamificationPort);
    }

    @Bean
    public RetrieveHabitsUseCase retrieveHabitsUseCase(HabitRepositoryPort habitRepositoryPort, GamificationPort gamificationPort){
        return new HabitService(habitRepositoryPort, gamificationPort);
    }

}
