package org.steffim.vehiclemanagerservice.model.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.OffsetDateTime;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CreateVehicleDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenNameIsEmpty_thenViolation() {
    CreateVehicleDto dto =
        CreateVehicleDto.builder()
            .name("")
            .vehicleTypeId(1L)
            .registrationDate(OffsetDateTime.now())
            .weight(1000)
            .numberOfAxes(2)
            .maxSpeed(180)
            .build();

    Set<ConstraintViolation<CreateVehicleDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void whenNameIsNull_thenViolation() {
    CreateVehicleDto dto =
        CreateVehicleDto.builder()
            .name(null)
            .vehicleTypeId(1L)
            .registrationDate(OffsetDateTime.now())
            .weight(1000)
            .numberOfAxes(2)
            .maxSpeed(180)
            .build();

    Set<ConstraintViolation<CreateVehicleDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void whenVehicleTypeIsNull_thenViolation() {
    CreateVehicleDto dto =
        CreateVehicleDto.builder()
            .name("Testfahrzeug")
            .vehicleTypeId(null)
            .registrationDate(OffsetDateTime.now())
            .weight(1000)
            .numberOfAxes(2)
            .maxSpeed(180)
            .build();

    Set<ConstraintViolation<CreateVehicleDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("vehicleTypeId")));
    }

    @Test
    void whenRegistrationDateIsNull_thenViolation() {
    CreateVehicleDto dto =
        CreateVehicleDto.builder()
            .name("Testfahrzeug")
            .vehicleTypeId(1L)
            .registrationDate(null)
            .weight(1000)
            .numberOfAxes(2)
            .maxSpeed(180)
            .build();

    Set<ConstraintViolation<CreateVehicleDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("registrationDate")));
    }

    @Test
    void whenWeightIsZero_thenViolation() {
    CreateVehicleDto dto =
        CreateVehicleDto.builder()
            .name("Testfahrzeug")
            .vehicleTypeId(1L)
            .registrationDate(OffsetDateTime.now())
            .weight(0)
            .numberOfAxes(2)
            .maxSpeed(180)
            .build();

    Set<ConstraintViolation<CreateVehicleDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("weight")));
    }

    @Test
    void whenWeightIsNegative_thenViolation() {
    CreateVehicleDto dto =
        CreateVehicleDto.builder()
            .name("Testfahrzeug")
            .vehicleTypeId(1L)
            .registrationDate(OffsetDateTime.now())
            .weight(-1)
            .numberOfAxes(2)
            .maxSpeed(180)
            .build();

    Set<ConstraintViolation<CreateVehicleDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("weight")));
    }

    @Test
    void whenNumberOfAxesIsZero_thenViolation() {
    CreateVehicleDto dto =
        CreateVehicleDto.builder()
            .name("Testfahrzeug")
            .vehicleTypeId(1L)
            .registrationDate(OffsetDateTime.now())
            .weight(1000)
            .numberOfAxes(0)
            .maxSpeed(180)
            .build();

    Set<ConstraintViolation<CreateVehicleDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("numberOfAxes")));
    }

    @Test
    void whenMaxSpeedIsZero_thenViolation() {
    CreateVehicleDto dto =
        CreateVehicleDto.builder()
            .name("Testfahrzeug")
            .vehicleTypeId(1L)
            .registrationDate(OffsetDateTime.now())
            .weight(1000)
            .numberOfAxes(2)
            .maxSpeed(0)
            .build();

    Set<ConstraintViolation<CreateVehicleDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("maxSpeed")));
    }

    @Test
    void whenReadyForServiceNotSet_thenDefaultIsFalse() {
    CreateVehicleDto dto =
        CreateVehicleDto.builder()
            .name("Testfahrzeug")
            .vehicleTypeId(1L)
            .registrationDate(OffsetDateTime.now())
            .weight(1000)
            .numberOfAxes(2)
            .maxSpeed(180)
            .build();

    assertFalse(dto.isReadyForService());
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
    CreateVehicleDto dto =
        CreateVehicleDto.builder()
            .name("Testfahrzeug")
            .vehicleTypeId(1L)
            .registrationDate(OffsetDateTime.now())
            .weight(1200)
            .readyForService(true)
            .numberOfAxes(2)
            .maxSpeed(180)
            .build();

    Set<ConstraintViolation<CreateVehicleDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }
}