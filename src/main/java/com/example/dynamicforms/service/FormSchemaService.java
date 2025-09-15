package com.example.dynamicforms.service;

import com.example.dynamicforms.dto.FormSchemaDTO;
import com.example.dynamicforms.entity.FormSchema;
import com.example.dynamicforms.exception.ResourceNotFoundException;
import com.example.dynamicforms.repository.FormSchemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormSchemaService {

    private final FormSchemaRepository formSchemaRepository;

    public FormSchema createSchema(FormSchemaDTO dto) {
        if (formSchemaRepository.existsBySlugAndDeletedAtIsNull(dto.getSlug())) {
            throw new IllegalArgumentException("Slug already exists");
        }
        FormSchema schema = FormSchema.builder()
                .name(dto.getName())
                .slug(dto.getSlug())
                .description(dto.getDescription())
                .schemaJson(dto.getSchemaJson())
                .createdAt(Instant.now())
                .build();
        return formSchemaRepository.save(schema);
    }

    public Page<FormSchema> listSchemas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return formSchemaRepository.findAllByDeletedAtIsNull(pageable);
    }

    public FormSchema getSchemaBySlug(String slug) {
        return formSchemaRepository.findBySlugAndDeletedAtIsNull(slug)
                .orElseThrow(() -> new ResourceNotFoundException("FormSchema not found"));
    }

    @Transactional
    public FormSchema updateSchema(String slug, FormSchemaDTO dto) {
        FormSchema schema = getSchemaBySlug(slug);
        schema.setName(dto.getName());
        schema.setDescription(dto.getDescription());
        schema.setSchemaJson(dto.getSchemaJson());
        schema.setUpdatedAt(Instant.now());
        return formSchemaRepository.save(schema);
    }

    @Transactional
    public void softDeleteSchema(String slug) {
        FormSchema schema = getSchemaBySlug(slug);
        schema.setDeletedAt(Instant.now());
        formSchemaRepository.save(schema);
    }
}