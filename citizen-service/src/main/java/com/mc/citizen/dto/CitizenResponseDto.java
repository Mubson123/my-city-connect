package com.mc.citizen.dto;

import com.mc.citizen.model.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link Citizen}
 */
@Builder
public record CitizenResponseDto(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, Set<Title> titles,
                                 Gender gender, String firstName, String lastName, LocalDate birthDate,
                                 @Email String email, Set<PhoneNumber> phoneNumbers, MaritalStatus maritalStatus,
                                 Set<Address> addresses, Set<Identification> identyDocuments, String socialSecurityNumber,
                                 String taxIdentificationNumber) implements Serializable {
}