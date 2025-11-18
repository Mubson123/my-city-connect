package com.mc.citizen.model;

import com.mc.citizen.model.util.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Valid
    @ElementCollection
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "Citizen_titles", joinColumns = @JoinColumn(name = "citizen_id"))
    private Set<Title> titles = new LinkedHashSet<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank(message = "firstname required")
    private String firstName;

    @NotBlank(message = "lastname required")
    private String lastName;

    @NotNull(message = "birthdate required")
    private LocalDate birthDate;

    @NotBlank(message = "birthplace required")
    private String birthPlace;

    @NotBlank(message = "email required")
    @Email(message = "email not correct", regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Column(unique = true, nullable = false)
    private String email;

    @Valid
    @ElementCollection
    @Builder.Default
    @CollectionTable(name = "Citizen_phones", joinColumns = @JoinColumn(name = "citizen_id"))
    private Set<Phone> phones = new LinkedHashSet<>();

    @NotNull(message = "marital status required")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Valid
    @ElementCollection
    @Builder.Default
    @CollectionTable(name = "Citizen_addresses", joinColumns = @JoinColumn(name = "citizen_id"))
    private Set<Address> addresses = new LinkedHashSet<>();
}
