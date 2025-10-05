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
public class PhoneNumber {
    private String number;
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
}
