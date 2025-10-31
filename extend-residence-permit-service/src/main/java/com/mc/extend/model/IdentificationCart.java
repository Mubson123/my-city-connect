package com.mc.extend.model;

import com.mc.extend.model.utils.Address;
import com.mc.extend.model.utils.Gender;
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
public class IdentificationCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @NotBlank(message = "ID Cart number required")
    private String iDCartNumber;
    @Column(nullable = false)
    private LocalDate issuedAt;
    @Column(nullable = false)
    private LocalDate expiresAt;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotBlank(message = "Firstname required")
    private String firstname;
    @NotBlank(message = "Lastname required")
    private String lastname;
    @NotBlank(message = "Birthdate required")
    private LocalDate birthdate;
    @NotBlank(message = "Birthplace required")
    private String birthplace;
    @NotBlank(message = "Nationality required")
    private String nationality;
    @Embedded
    private Address address;
}
