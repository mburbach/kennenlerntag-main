package org.steffim.vehiclemanagerservice.integrationtest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.steffim.vehiclemanagerservice.model.dto.CreateVehicleDto;
import org.steffim.vehiclemanagerservice.model.entity.Person;
import org.steffim.vehiclemanagerservice.model.entity.Vehicle;
import org.steffim.vehiclemanagerservice.model.entity.VehicleType;
import org.steffim.vehiclemanagerservice.model.shared.VehicleTypeCategory;
import org.steffim.vehiclemanagerservice.repository.VehicleRepository;
import org.steffim.vehiclemanagerservice.repository.VehicleTypeRepository;

@Component
public class TestModelUtil {
  private static final OffsetDateTime REGISTRATION_DATE = OffsetDateTime.now();
  private static final int WEIGHT = 1500;
  private static final boolean READY_FOR_SERVICE = true;
  private static final int NUMBER_OF_AXES = 10;
  private static final int MAX_SPEED = 120;
  public static Long VEHICLE_TYPE = 1L;

  @Autowired private VehicleRepository vehicleRepository;
  @Autowired private VehicleTypeRepository vehicleTypeRepository;

  @Autowired private ObjectMapper objectMapper;

  public <T> T getResult(MvcResult result, Class<T> clazz)
      throws JsonProcessingException, UnsupportedEncodingException {
    String responseBody = result.getResponse().getContentAsString();
    return objectMapper.readValue(responseBody, clazz);
  }

  public <T> List<T> getResultList(MvcResult result, Class<T> elementType)
      throws JsonProcessingException, UnsupportedEncodingException {
    String responseBody = result.getResponse().getContentAsString();
    return objectMapper.readValue(
        responseBody,
        objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
  }

  private String createRandomVehicleName() {
    return "Test Vehicle " + UUID.randomUUID();
  }

  private String createRandomVehicleTypeName() {
    return "Test Vehicle Type " + UUID.randomUUID();
  }

  public CreateVehicleDto createVehicleDto() {
    VehicleType vehicleType = createVehicleTypeEntity();
    vehicleType = vehicleTypeRepository.save(vehicleType);
    return CreateVehicleDto.builder()
        .name(createRandomVehicleName())
        .vehicleTypeId(vehicleType.getId())
        .registrationDate(REGISTRATION_DATE)
        .weight(WEIGHT)
        .readyForService(READY_FOR_SERVICE)
        .numberOfAxes(NUMBER_OF_AXES)
        .maxSpeed(MAX_SPEED)
        .build();
  }

  private Person createPerson() {
    return Person.builder().firstName("Test").lastName("Person").build();
  }

  public Vehicle createVehicleEntity() {
    return Vehicle.builder()
        .name(createRandomVehicleName())
        .registrationDate(REGISTRATION_DATE)
        .weight(WEIGHT)
        .readyForService(READY_FOR_SERVICE)
        .numberOfAxes(NUMBER_OF_AXES)
        .maxSpeed(MAX_SPEED)
        .vehicleType(createVehicleTypeEntity())
        .build();
  }

  public @NotNull VehicleType createVehicleTypeEntity() {
    Person person = createPerson();
    return VehicleType.builder()
        .name(createRandomVehicleTypeName())
        .type(VehicleTypeCategory.CARGO)
        .personResponsible(person)
        .build();
  }

  @Transactional
  public Vehicle saveEntity(Vehicle vehicle) {
    return vehicleRepository.save(vehicle);
  }
}
