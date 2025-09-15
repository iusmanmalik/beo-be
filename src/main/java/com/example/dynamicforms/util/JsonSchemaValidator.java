package com.example.dynamicforms.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class JsonSchemaValidator {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void validate(String schemaJson, String dataJson) {
        try {
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            JsonNode schemaNode = objectMapper.readTree(schemaJson);
            JsonSchema schema = factory.getSchema(schemaNode);
            JsonNode dataNode = objectMapper.readTree(dataJson);

            Set<ValidationMessage> errors = schema.validate(dataNode);
            if (!errors.isEmpty()) {
                throw new IllegalArgumentException("Validation failed: " + errors.toString());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON or schema: " + e.getMessage(), e);
        }
    }


}