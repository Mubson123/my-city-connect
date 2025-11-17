package com.mc.citizen.model.util;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @NotBlank(message = "number required")
    private String number;
    @NotNull(message = "phone type required")
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
}
