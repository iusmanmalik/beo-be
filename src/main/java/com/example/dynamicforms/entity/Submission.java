package com.example.dynamicforms.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "submission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Submission {
    @Id
    @GeneratedValue
    private UUID id;

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

//    @ElementCollection
//    @CollectionTable(name = "menu_items", joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "menu_item")
    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menu = new ArrayList<>();;

//    @ElementCollection
//    @CollectionTable(name = "beverages", joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "beverage")
    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Beverage> beverages = new ArrayList<>();;
    private LocalTime servingTime;
    private String dietaryRequirementsAllergies;
    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AudioVisualEquipment> audioVisualEquipment = new ArrayList<>();
    private String specialInstructions;

    /*
    eventId
    eventName
    guestCount
    date
    eventStartingTime
    eventEndingTime
    eventPurpose
    eventLocation
    clientName
    companyName
    phoneNumber
    email
    venueRoom
    setupStyle
    menu  == list type
    beverages == list type
    servingTime
    dietaryRequirementsAllergies
    audioVisualEquipment == list type
    specialInstructions

     */

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "updated_at")
    private Instant updatedAt;


    public void addAudioVisualEquipment(AudioVisualEquipment equipment) {
        if (audioVisualEquipment == null) audioVisualEquipment = new ArrayList<>();
        audioVisualEquipment.add(equipment);
        equipment.setSubmission(this); // 🔥 important!
    }

    public void removeAudioVisualEquipment(AudioVisualEquipment equipment) {
        audioVisualEquipment.remove(equipment);
        equipment.setSubmission(null);
    }

    public void addMenuItem(MenuItem item) {
        if (menu == null) menu = new ArrayList<>();
        menu.add(item);
        item.setSubmission(this);
    }

    public void addBeverage(Beverage beverage) {
        if (beverages == null) beverages = new ArrayList<>();
        beverages.add(beverage);
        beverage.setSubmission(this);
    }
}
