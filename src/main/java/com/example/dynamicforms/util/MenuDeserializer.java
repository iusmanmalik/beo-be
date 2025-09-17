package com.example.dynamicforms.util;

import com.example.dynamicforms.entity.Beverage;
import com.example.dynamicforms.entity.MenuItem;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MenuDeserializer extends JsonDeserializer<List<MenuItem>> {

    @Override
    public List<MenuItem> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String raw = p.getValueAsString();

        if (raw == null || raw.isBlank()) {
            return List.of();
        }

        // Normalize <br/> and line breaks into commas
        String normalized = raw.replaceAll("<br\\s*/?>", ",").replaceAll("\\r?\\n", ",");

        return Arrays.stream(normalized.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> MenuItem.builder().name(s).build())
                .toList();
    }
}