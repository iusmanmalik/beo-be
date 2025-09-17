package com.example.dynamicforms.controller;

import com.example.dynamicforms.dto.*;
import com.example.dynamicforms.entity.Submission;
import com.example.dynamicforms.service.SubmissionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/forms")
@RequiredArgsConstructor
public class FormSubmissionController {

    private final SubmissionService submissionService;

    // Simple in-memory rate limiter map
    private final ConcurrentHashMap<String, Long> rateLimiter = new ConcurrentHashMap<>();
    private final long RATE_LIMIT_INTERVAL_MS = 10_000; // 10 seconds per IP

    @PostMapping("/submit")
    public ResponseEntity<?> submitForm(
                                        @Valid @RequestBody FormSubmissionDTO dto,
                                        HttpServletRequest request) {
        String ip = request.getRemoteAddr();

        Long lastRequestTime = rateLimiter.get(ip);
        long now = System.currentTimeMillis();
        if (lastRequestTime != null && (now - lastRequestTime) < RATE_LIMIT_INTERVAL_MS) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(new ApiResponse<>(429, "Too many requests", null));
        }
        rateLimiter.put(ip, now);
//        FormSchemaDTO formSchemaDTO = FormSchemaDTO.builder().slug(slug).name(dto.getName()).schemaJson(dto.getDataJson()).build();
//        FormSchema schema = formSchemaService.createSchema(formSchemaDTO);

        Submission submission = submissionService.submitForm(dto, ip, request.getHeader("User-Agent"));
        return ResponseEntity.status(HttpStatus.CREATED).body(submission);
    }

    @GetMapping("/submissions")
    public ResponseEntity<Page<Submission>> listSubmissions(
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
//        FormSchema schema = formSchemaService.listSchemas(page,size);
        return ResponseEntity.ok(submissionService.listSubmissions(page,size));
    }

    @GetMapping("/submissions/{id}")
    public ResponseEntity<Submission> getSubmission(@PathVariable UUID id) {
//        FormSchema schema = formSchemaService.getSchemaBySlug(slug);
        return ResponseEntity.ok(submissionService.getSubmission(id));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/submissions/{id}")
    public ResponseEntity<?> deleteSubmission(@PathVariable UUID id) {
//        FormSchema schema = formSchemaService.getSchemaBySlug(slug);
        submissionService.softDeleteSubmission(id);
        return ResponseEntity.noContent().build();
    }
}