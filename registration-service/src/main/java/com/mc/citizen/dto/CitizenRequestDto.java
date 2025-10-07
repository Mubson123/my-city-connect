package com.mc.citizen.dto;

import com.mc.citizen.model.*;
import com.mc.citizen.model.util.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link Citizen}
 */
@Builder
public record CitizenRequestDto(Set<Title> titles, @NotNull Gender gender,
                                @NotNull(message = "firstname required") String firstName,
                                @NotNull(message = "lastname required") String lastName,
                                @NotNull(message = "birthdate required") LocalDate birthDate,
                                @NotNull(message = "birthplace required") String birthPlace,
                                @Email(message = "e-mail required") String email, Set<PhoneNumber> phoneNumbers,
                                @NotNull(message = "marital status required") MaritalStatus maritalStatus,
                                @NotNull(message = "address required") Set<Address> addresses) implements Serializable {
}