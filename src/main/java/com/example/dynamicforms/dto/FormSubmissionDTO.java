package com.example.dynamicforms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormSubmissionDTO {
    @NotBlank
    private String dataJson;

    @NotBlank
    private String name;

    @NotBlank
    private String formId;


}
