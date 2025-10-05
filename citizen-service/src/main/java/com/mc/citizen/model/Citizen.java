package com.mc.citizen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ElementCollection
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
    @NonNull
    private String email;
    @ElementCollection
    @CollectionTable(name = "Citizen_phoneNumbers", joinColumns = @JoinColumn(name = "citizen_id"))
    private Set<PhoneNumber> phoneNumbers = new LinkedHashSet<>();
    @NonNull
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @ElementCollection
    @CollectionTable(name = "Citizen_addresses", joinColumns = @JoinColumn(name = "citizen_id"))
    private Set<Address> address = new LinkedHashSet<>();

    @ElementCollection
    @CollectionTable(name = "Citizen_identification", joinColumns = @JoinColumn(name = "citizen_id"))
    private Set<Identification> identification = new LinkedHashSet<>();

}
