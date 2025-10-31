package com.mc.extend.constraint;

import com.mc.extend.fixtures.VisaFixtures;
import com.mc.extend.model.Visa;
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
        assertThat(violations).hasSize(2);
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        "Visa number required",
                        "Country of issue required"
                );
    }
}
