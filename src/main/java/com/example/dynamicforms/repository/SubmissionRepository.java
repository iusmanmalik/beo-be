package com.example.dynamicforms.repository;

import com.example.dynamicforms.entity.FormSchema;
import com.example.dynamicforms.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
//    Page<Submission> findAllByFormSchemaAndDeletedAtIsNull(FormSchema formSchema, Pageable pageable);
    Page<Submission> findAllByDeletedAtIsNull(Pageable pageable);
    Optional<Submission> findByIdAndDeletedAtIsNull(UUID id);
    boolean existsByFormIdAndDeletedAtIsNull(String formId);
}
