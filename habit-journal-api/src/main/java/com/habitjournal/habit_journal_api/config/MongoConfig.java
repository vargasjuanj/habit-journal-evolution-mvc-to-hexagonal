package com.habitjournal.habit_journal_api.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MongoConfig {

    
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    
    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    
    @Bean
    public MongoClient mongoClient() {

        System.out.println("======================================");
        System.out.println("CREANDO MONGO CLIENT");
        System.out.println("DATABASE: " + databaseName);
        System.out.println("======================================");

        return MongoClients.create(mongoUri);
    }

    
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {

        System.out.println("======================================");
        System.out.println("CREANDO MONGO TEMPLATE");
        System.out.println("DATABASE: " + databaseName);
        System.out.println("======================================");

        return new MongoTemplate(mongoClient, databaseName);
    }
}
