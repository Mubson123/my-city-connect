package com.mc.citizen.model.util;

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
public class Address {
    @NonNull
    @Enumerated(EnumType.STRING)
    private AddressKind type;
    @NonNull
    private String street;
    @NonNull
    private String zip;
    @NonNull
    private String city;
}
