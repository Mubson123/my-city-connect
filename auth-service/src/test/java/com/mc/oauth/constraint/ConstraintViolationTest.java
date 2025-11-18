package com.mc.oauth.constraint;


import com.mc.oauth.fixtures.AuthFixtures;
import com.mc.oauth.models.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ConstraintViolationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldDetectMissingFieldsAndWrongEmailFormat() {
        User user = AuthFixtures.wrongUser1;
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(3);
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        "Firstname is required",
                        "Firstname must have at least 3 characters",
                        "Email format not correct"
                );
    }

    @Test
    void shouldDetectMissingFieldsAndShortPassword() {
        User user = AuthFixtures.wrongUser2;
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(3);
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        "Password must be at least 8 characters long",
                        "Lastname is required",
                        "Lastname must have at least 3 characters"
                );
    }

    @Test
    void shouldDetectShortLastnameAndWrongUpdateDate() {
        User user = AuthFixtures.wrongUser3;
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).hasSize(1);
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        "Lastname must have at least 3 characters"
                );
    }
}
