package com.mc.citizen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ElementCollection
    @Builder.Default
    @CollectionTable(name = "Citizen_titles", joinColumns = @JoinColumn(name = "citizen_id"))
    private Set<Title> titles = new LinkedHashSet<>();

    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private LocalDate birthDate;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @ElementCollection
    @Builder.Default
    @CollectionTable(name = "Citizen_phoneNumbers", joinColumns = @JoinColumn(name = "citizen_id"))
    private Set<PhoneNumber> phoneNumbers = new LinkedHashSet<>();

    @NonNull
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @ElementCollection
    @Builder.Default
    @CollectionTable(name = "Citizen_addresses", joinColumns = @JoinColumn(name = "citizen_id"))
    private Set<Address> addresses = new LinkedHashSet<>();

    @ElementCollection
    @Builder.Default
    @CollectionTable(name = "Citizen_identyDocuments", joinColumns = @JoinColumn(name = "citizen_id"))
    private Set<Identification> identyDocuments = new LinkedHashSet<>();

    @Column(updatable = false, unique = true)
    private String socialSecurityNumber;

    @Column(updatable = false, unique = true)
    private String taxIdentificationNumber;
}
