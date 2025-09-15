package com.example.dynamicforms.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FormSchemaDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Slug must be lowercase alphanumeric and hyphens only")
    private String slug;

    private String description;

    @NotBlank
    private String schemaJson;
}

