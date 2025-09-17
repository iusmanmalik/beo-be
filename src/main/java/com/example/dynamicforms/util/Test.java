package com.example.dynamicforms.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        System.out.println("args = " + encoder.encode("admin123"));

                System.out.println("args = " + "$2a$10$zdQKrndBSK1hYfrIdFZn/OchX2/MmV2eTpUwanunkNzui9gisOFZy");

        try {
            // JSON String (can also be from file, API, etc.)
            String json = "{ \"id\": 101, \"eventtime\": \"12:00 AM\", \"active\": true, \"roles\": [\"admin\", \"user\"] }";

            // Create ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // Parse JSON into JsonNode
            JsonNode rootNode = mapper.readTree(json);

            // Access fields
            int id = rootNode.get("id").asInt();
            boolean active = rootNode.get("active").asBoolean();

            // Access array
            JsonNode roles = rootNode.get("roles");
            for (JsonNode role : roles) {
                System.out.println("Role: " + role.asText());
            }

            // Print extracted values
            System.out.println("ID: " + id);
            String eventTimeStr = rootNode.get("eventtime").asText();
            System.out.println("Raw eventtime string: " + eventTimeStr);

            // Convert string to LocalTime (handle 12-hour format with AM/PM)

            // Use Locale.ENGLISH so "AM/PM" is recognized
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

            LocalTime eventTime = LocalTime.parse(eventTimeStr, formatter);

            System.out.println("Parsed LocalTime: " + eventTime);
            System.out.println("Active: " + active);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
