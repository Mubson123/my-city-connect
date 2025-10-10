package com.mc.citizen.model.util;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NotBlank
    @Enumerated(EnumType.STRING)
    private AddressTyp type;
    @NotBlank
    private String street;
    @NotBlank
    private String zip;
    @NotBlank
    private String city;
}
