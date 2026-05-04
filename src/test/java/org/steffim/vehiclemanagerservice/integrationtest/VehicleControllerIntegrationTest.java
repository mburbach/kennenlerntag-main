package org.steffim.vehiclemanagerservice.integrationtest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.steffim.vehiclemanagerservice.integrationtest.utils.TestModelUtil.VEHICLE_TYPE;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.steffim.vehiclemanagerservice.integrationtest.configuration.TestcontainersConfiguration;
import org.steffim.vehiclemanagerservice.integrationtest.utils.TestModelUtil;
import org.steffim.vehiclemanagerservice.model.dto.CreateVehicleDto;
import org.steffim.vehiclemanagerservice.model.dto.VehicleResult;
import org.steffim.vehiclemanagerservice.model.entity.Vehicle;
import org.steffim.vehiclemanagerservice.repository.VehicleRepository;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Import(TestcontainersConfiguration.class)
@SqlGroup({
  @Sql(scripts = "/sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
  @Sql(scripts = "/sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class VehicleControllerIntegrationTest {
  // TODO: Int Tests in extra Package, um Ausführung von Unit- und Int. Tests zu trennen für z.B.
  // SonarQube
  private static final String VEHICLE_NAME1 = "Vehicle Name 1";
  private static final String VEHICLE_NAME2 = "Vehicle Name 2";
  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Autowired private VehicleRepository vehicleRepository;

  @Autowired private TestModelUtil testModelUtil;

  @Test
  void shouldSaveVehicle() throws Exception {
    //        given
    CreateVehicleDto vehicle = testModelUtil.createVehicleDto();

    //        when
    MvcResult result =
        mockMvc
            .perform(
                post("/vehicle")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(vehicle)))
            .andExpect(status().isOk())
            .andReturn();

    assertThat(result).isNotNull();

    VehicleResult vehicleResult = testModelUtil.getResult(result, VehicleResult.class);

    //        then
    // Check if the response contains the expected vehicle details
    assertThat(vehicleResult).isNotNull();
    assertThat(vehicleResult.getName()).isEqualTo(vehicle.getName());
    assertThat(vehicleResult.getVehicleType().getId()).isEqualTo(VEHICLE_TYPE);
    assertThat(vehicle.getRegistrationDate().truncatedTo(ChronoUnit.MINUTES)).isEqualTo(vehicleResult.getRegistrationDate().truncatedTo(ChronoUnit.MINUTES));
    assertThat(vehicleResult.getWeight()).isEqualTo(vehicle.getWeight());
    assertThat(vehicleResult.isReadyForService()).isEqualTo(vehicle.isReadyForService());
    assertThat(vehicleResult.getNumberOfAxes()).isEqualTo(vehicle.getNumberOfAxes());
    assertThat(vehicleResult.getMaxSpeed()).isEqualTo(vehicle.getMaxSpeed());

    // Check if the vehicle was saved correctly
    Optional<Vehicle> savedVehicle = vehicleRepository.findById(vehicleResult.getId());
    assertThat(savedVehicle).isPresent();

    Vehicle vehicleEntity = savedVehicle.orElseThrow();
    assertThat(vehicleEntity.getName()).isEqualTo(vehicle.getName());
    assertThat(vehicleEntity.getRegistrationDate().truncatedTo(ChronoUnit.MINUTES))
        .isEqualTo(vehicle.getRegistrationDate().truncatedTo(ChronoUnit.MINUTES));
    assertThat(vehicleEntity.getWeight()).isEqualTo(vehicle.getWeight());
    assertThat(vehicleEntity.isReadyForService()).isEqualTo(vehicle.isReadyForService());
    assertThat(vehicleEntity.getNumberOfAxes()).isEqualTo(vehicle.getNumberOfAxes());
    assertThat(vehicleEntity.getMaxSpeed()).isEqualTo(vehicle.getMaxSpeed());
  }

  @Test
  void shouldGetAllVehicle() throws Exception {
    Vehicle vehicle1 = testModelUtil.createVehicleEntity();
    Vehicle vehicle2 = testModelUtil.createVehicleEntity();

    vehicle1.setName(VEHICLE_NAME1);
    vehicle2.setName(VEHICLE_NAME2);

    vehicle1 = testModelUtil.saveEntity(vehicle1);
    vehicle2 = testModelUtil.saveEntity(vehicle2);

    //        when
    MvcResult result = mockMvc.perform(get("/vehicles")).andExpect(status().isOk()).andReturn();

    List<VehicleResult> vehicleResults = testModelUtil.getResultList(result, VehicleResult.class);

    //        then
    assertThat(vehicleResults).isNotNull();
    assertThat(vehicleResults).hasSize(2);

    assertThat(vehicleResults)
        .extracting(VehicleResult::getName,
            resultVehicle -> resultVehicle.getVehicleType().getId())
        .containsExactlyInAnyOrder(
            tuple(vehicle1.getName(), vehicle1.getVehicleType().getId()),
            tuple(vehicle2.getName(), vehicle2.getVehicleType().getId()));
  }
}
