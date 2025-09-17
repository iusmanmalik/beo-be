package com.example.dynamicforms.dto;

import com.example.dynamicforms.entity.AudioVisualEquipment;
import com.example.dynamicforms.entity.Beverage;
import com.example.dynamicforms.entity.MenuItem;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormSubmissionDTO {

    @NotBlank
    private String eventId;
    private String eventName;
    private Integer guestCount;
    private Instant formDate;
    private LocalTime eventStartingTime;
    private LocalTime eventEndingTime;
    private String eventPurpose;
    private String eventLocation;
    private String clientName;
    private String companyName;
    private String phoneNumber;
    private String email;
    private String venueRoom;
    private String setupStyle;
    private LocalTime servingTime;
    private List<MenuItem> menu = new ArrayList<>();
    private List<Beverage> beverages  = new ArrayList<>();
    private List<AudioVisualEquipment> audioVisualEquipment = new ArrayList<>();


}
