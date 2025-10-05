package com.mc.citizen.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Identification {
    @NonNull
    @Enumerated(EnumType.STRING)
    private IDType idType;
    @NonNull
    private String idNumber;
    @NonNull
    private LocalDate issuedDate;
    @NonNull
    private LocalDate expirationDate;
}
