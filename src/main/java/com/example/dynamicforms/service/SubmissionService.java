package com.example.dynamicforms.service;

import com.example.dynamicforms.dto.FormSubmissionDTO;
import com.example.dynamicforms.entity.Submission;
import com.example.dynamicforms.exception.ResourceNotFoundException;
import com.example.dynamicforms.repository.SubmissionRepository;
import com.example.dynamicforms.util.Utils;
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

    public Submission submitForm(FormSubmissionDTO dto, String ipAddress, String userAgent) {

        Submission submission = getSubmission(dto, ipAddress, userAgent);
        return submissionRepository.save(submission);
    }

    private static Submission getSubmission(FormSubmissionDTO dto, String ipAddress, String userAgent) {
        Submission submission = Submission.builder()
                .eventName(dto.getEventName())
                .eventId(dto.getEventId())
                .email(dto.getEmail())
                .clientName(dto.getClientName())
                .venueRoom(dto.getVenueRoom())
                .eventStartingTime(dto.getEventStartingTime())
                .eventEndingTime(dto.getEventEndingTime())
                .formDate(dto.getFormDate())
                .companyName(dto.getCompanyName())
                .eventLocation(dto.getEventLocation())
                .eventPurpose(dto.getEventPurpose())
                .phoneNumber(dto.getPhoneNumber())
                .setupStyle(dto.getSetupStyle())
                .servingTime(dto.getServingTime())
                .guestCount(dto.getGuestCount())
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .createdAt(Instant.now())
                .build();

        if(Utils.isNotNullOrEmpty(dto.getAudioVisualEquipment())) {
            dto.getAudioVisualEquipment().forEach(submission::addAudioVisualEquipment);
        }
        if(Utils.isNotNullOrEmpty(dto.getMenu())) {
            dto.getMenu().forEach(submission::addMenuItem);
        }
        if(Utils.isNotNullOrEmpty(dto.getBeverages())) {
            dto.getBeverages().forEach(submission::addBeverage);
        }
        return submission;
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