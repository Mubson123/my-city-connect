package com.mc.oauth.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @Size(min = 3, max = 255, message = "Firstname must have at least 3 characters")
    @NotBlank(message = "Firstname is required")
    private String firstname;
    @Size(min = 3, max = 255, message = "Lastname must have at least 3 characters")
    @NotBlank(message = "Lastname is required")
    private String lastname;
    @NotBlank(message = "Email is required")
    @Email(message = "Email format not correct", regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Column(unique = true, nullable = false)
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    private String password;
}
