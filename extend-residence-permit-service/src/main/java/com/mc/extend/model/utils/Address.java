package com.mc.extend.model.utils;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
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
    @Size(min = 3, message = "street must have at least 3 characters")
    private String street;
    @Pattern(regexp = "\\d{5}", message = "zip must be a 5-digit number")
    private String zip;
    @NotBlank(message = "city required")
    private String city;
    @NotBlank(message = "country required")
    private String country;
}
