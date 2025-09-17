package com.example.dynamicforms.dto;

import com.example.dynamicforms.entity.AudioVisualEquipment;
import com.example.dynamicforms.entity.Beverage;
import com.example.dynamicforms.entity.MenuItem;
import com.example.dynamicforms.util.AudioDeserializer;
import com.example.dynamicforms.util.BeverageDeserializer;
import com.example.dynamicforms.util.MenuDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    @JsonFormat(pattern = "hh:mm a", locale = "en")
    private LocalTime eventStartingTime;
    @JsonFormat(pattern = "hh:mm a", locale = "en")
    private LocalTime eventEndingTime;
    private String eventPurpose;
    private String eventLocation;
    private String clientName;
    private String companyName;
    private String phoneNumber;
    private String email;
    private String venueRoom;
    private String setupStyle;
    @JsonFormat(pattern = "hh:mm a", locale = "en")
    private LocalTime servingTime;
    @JsonDeserialize(using = MenuDeserializer.class)
    private List<MenuItem> menu = new ArrayList<>();
    @JsonDeserialize(using = BeverageDeserializer.class)
    private List<Beverage> beverages  = new ArrayList<>();
    @JsonDeserialize(using = AudioDeserializer.class)
    private List<AudioVisualEquipment> audioVisualEquipment = new ArrayList<>();


    @Override
    public String toString() {
        return "FormSubmissionDTO{" +
                "eventId='" + eventId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", guestCount=" + guestCount +
                ", formDate=" + formDate +
                ", eventStartingTime=" + eventStartingTime +
                ", eventEndingTime=" + eventEndingTime +
                ", eventPurpose='" + eventPurpose + '\'' +
                ", eventLocation='" + eventLocation + '\'' +
                ", clientName='" + clientName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", venueRoom='" + venueRoom + '\'' +
                ", setupStyle='" + setupStyle + '\'' +
                ", servingTime=" + servingTime +
                ", menu=" + menu +
                ", beverages=" + beverages +
                ", audioVisualEquipment=" + audioVisualEquipment +
                '}';
    }
}
