package com.mc.visa.constraint;

import com.mc.visa.fixtures.VisaFixtures;
import com.mc.visa.model.Visa;
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
    void shouldDetectMissingRequiredFieldsByVisa() {
        Visa invalidVisa = VisaFixtures.invalidVisa;

        Set<ConstraintViolation<Visa>> violations = validator.validate(invalidVisa);
        assertThat(violations).hasSize(3);
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        "VisaNumber required",
                        "Country of issue required",
                        "updatedAt not nullable"
                );
    }
}
