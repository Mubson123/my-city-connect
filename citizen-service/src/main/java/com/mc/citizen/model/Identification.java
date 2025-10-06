package com.mc.citizen.model;

import jakarta.persistence.Column;
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
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private IDType idType;
    @Column(unique = true)
    private String idNumber;
    private LocalDate issuedDate;
    private LocalDate expirationDate;
}
