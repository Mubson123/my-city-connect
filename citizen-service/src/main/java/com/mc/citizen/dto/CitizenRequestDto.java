package com.mc.citizen.dto;

import com.mc.citizen.model.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.mc.citizen.model.Citizen}
 */
@Value
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CitizenRequestDto implements Serializable {
    Set<Title> titles;
    @NotNull
    Gender gender;
    @NotNull(message = "firstname required")
    String firstName;
    @NotNull(message = "lastname required")
    String lastName;
    @NotNull(message = "birthdate required")
    LocalDate birthDate;
    @Email(message = "e-mail required")
    String email;
    Set<PhoneNumber> phoneNumbers;
    @NotNull(message = "marital status required")
    MaritalStatus maritalStatus;
    @NotNull(message = "address required")
    Set<Address> address;
    @NotNull(message = "identify yourself")
    Set<Identification> identification;
}