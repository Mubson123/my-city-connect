package com.mc.citizen.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Identification {
    @NonNull
    private String nationality;
    @NonNull
    @Enumerated(EnumType.STRING)
    private IDType idType;
    @NonNull
    private String idNumber;
}
