package com.example.habit_journal_api_hex.common;

import java.util.UUID;

public class IdGenerator {
    public static String nextId(){
        return UUID.randomUUID().toString();
    }
}
