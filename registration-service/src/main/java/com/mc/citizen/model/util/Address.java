package com.mc.citizen.model.util;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NotNull(message = "address type required")
    @Enumerated(EnumType.STRING)
    private AddressTyp type;
    @Size(min = 3, message = "street must have at least 3 characters")
    private String street;
    @Pattern(regexp = "\\d{5}", message = "zip must be a 5-digit number")
    private String zip;
    @NotBlank(message = "city required")
    private String city;
}
