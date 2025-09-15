package com.example.dynamicforms.controller;

import com.example.dynamicforms.dto.*;
import com.example.dynamicforms.entity.FormSchema;
import com.example.dynamicforms.service.FormSchemaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forms/schema")
@RequiredArgsConstructor
public class FormSchemaController {

    private final FormSchemaService formSchemaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createSchema(@Valid @RequestBody FormSchemaDTO dto) {
        FormSchema created = formSchemaService.createSchema(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<FormSchema>> listSchemas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(formSchemaService.listSchemas(page, size));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<FormSchema> getSchema(@PathVariable String slug) {
        return ResponseEntity.ok(formSchemaService.getSchemaBySlug(slug));
    }

    @PutMapping("/{slug}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FormSchema> updateSchema(@PathVariable String slug, @Valid @RequestBody FormSchemaDTO dto) {
        return ResponseEntity.ok(formSchemaService.updateSchema(slug, dto));
    }

    @DeleteMapping("/{slug}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSchema(@PathVariable String slug) {
        formSchemaService.softDeleteSchema(slug);
        return ResponseEntity.noContent().build();
    }
}