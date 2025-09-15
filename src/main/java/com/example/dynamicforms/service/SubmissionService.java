package com.example.dynamicforms.service;

import com.example.dynamicforms.dto.FormSubmissionDTO;
import com.example.dynamicforms.entity.FormSchema;
import com.example.dynamicforms.entity.Submission;
import com.example.dynamicforms.exception.ResourceNotFoundException;
import com.example.dynamicforms.repository.SubmissionRepository;
import com.example.dynamicforms.util.JsonSchemaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final JsonSchemaValidator jsonSchemaValidator;

    public Submission submitForm(FormSubmissionDTO dto, String ipAddress, String userAgent) {
        // Validate dataJson against schemaJson
//        jsonSchemaValidator.validate( dto.getDataJson(), dto.getDataJson());
        String json = "{ \"id\": 101, \"name\": \"Usman\", \"active\": true, \"roles\": [\"admin\", \"user\"] }";
        Submission submission = Submission.builder()
                .dataJson(json)
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .createdAt(Instant.now())
                .formId(dto.getFormId())
                .build();
        return submissionRepository.save(submission);
    }

    public Page<Submission> listSubmissions(FormSchema formSchema, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return submissionRepository.findAllByDeletedAtIsNull(pageable);
    }

    public Page<Submission> listSubmissions(int page, int size) {

        if (page == 1) {page = 0;}
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return submissionRepository.findAllByDeletedAtIsNull(pageable);
    }

    public Submission getSubmission( UUID submissionId) {
        return submissionRepository.findByIdAndDeletedAtIsNull(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found"));
    }

    @Transactional
    public void softDeleteSubmission( UUID submissionId) {
        Submission submission = getSubmission(submissionId);
        submission.setDeletedAt(Instant.now());
        submissionRepository.save(submission);
    }
}