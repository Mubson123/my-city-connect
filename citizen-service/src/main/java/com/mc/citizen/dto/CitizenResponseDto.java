package com.mc.citizen.dto;

import com.mc.citizen.model.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.mc.citizen.model.Citizen}
 */
@Value
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CitizenResponseDto implements Serializable {
    UUID id;
    Set<Title> titles;
    Gender gender;
    String firstName;
    String lastName;
    LocalDate birthDate;
    @Email
    String email;
    Set<PhoneNumber> phoneNumbers;
    MaritalStatus maritalStatus;
    Set<Address> address;
    Set<Identification> identification;
}