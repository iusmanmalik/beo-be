package com.example.dynamicforms.repository;

import com.example.dynamicforms.entity.FormSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormSchemaRepository extends JpaRepository<FormSchema, UUID> {
    Optional<FormSchema> findBySlugAndDeletedAtIsNull(String slug);

    Page<FormSchema> findAllByDeletedAtIsNull(Pageable pageable);

    boolean existsBySlugAndDeletedAtIsNull(String slug);
}
