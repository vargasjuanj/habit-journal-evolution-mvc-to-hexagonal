package com.example.habit_journal_api_hex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HabitJournalApiHexApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitJournalApiHexApplication.class, args);
	}

}
