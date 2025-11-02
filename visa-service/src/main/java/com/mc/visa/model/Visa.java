package com.mc.visa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @NotNull(message = "updatedAt not nullable")
    private LocalDateTime updatedAt;
    @NotBlank(message = "VisaNumber required")
    private String visaNumber;
    @NotNull(message = "issuedAt not nullable")
    private LocalDate issuedAt;
    @NotNull(message = "expiresAt not nullable")
    private LocalDate expiresAt;
    @NotNull(message = "visaType not nullable")
    @Enumerated(EnumType.STRING)
    private VisaType visaType;
    @NotBlank(message = "Country of issue required")
    private String countryOfIssue;
}
