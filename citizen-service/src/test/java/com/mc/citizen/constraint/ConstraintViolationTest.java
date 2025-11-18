package com.mc.citizen.constraint;


import com.mc.citizen.fixtures.CitizenFixtures;
import com.mc.citizen.model.Citizen;
import com.mc.citizen.model.util.Address;
import com.mc.citizen.model.util.AddressTyp;
import com.mc.citizen.model.util.Phone;
import com.mc.citizen.model.util.PhoneType;
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
    void shouldDetectMissingRequiredFieldsByCitizen() {
        Citizen wrongCitizen = CitizenFixtures.wrongCitizen2;

        Set<ConstraintViolation<Citizen>> violations = validator.validate(wrongCitizen);
        assertThat(violations).hasSize(8);
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        "firstname required",
                        "lastname required",
                        "email not correct",
                        "birthplace required",
                        "street must have at least 3 characters",
                        "zip must be a 5-digit number",
                        "city required",
                        "number required"
                );
    }

    @Test
    void shouldDetectMissingRequiredFieldsByPhoneNumber() {
        Phone wrongPhone = new Phone("", PhoneType.WORK);

        Set<ConstraintViolation<Phone>> violations = validator.validate(wrongPhone);
        assertThat(violations).hasSize(1);
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder("number required");
    }

    @Test
    void shouldDetectMissingRequiredFieldsByAddress() {
        Address wrongAddress = new Address(AddressTyp.MAIN, "", "", "");

        Set<ConstraintViolation<Address>> violations = validator.validate(wrongAddress);
        assertThat(violations).hasSize(3);
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        "city required",
                        "zip must be a 5-digit number",
                        "street must have at least 3 characters"
                );
    }
}
