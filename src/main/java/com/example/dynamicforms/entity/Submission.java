package com.example.dynamicforms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "submission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {
    @Id
    @GeneratedValue
    private UUID id;

//    @Type(value = org.hibernate.type.JsonType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "data_json", columnDefinition = "jsonb", nullable = false)
    private String dataJson;

    @Column(name = "form_id")
    private String formId;

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

}
