package org.steffim.vehiclemanagerservice.model.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.steffim.vehiclemanagerservice.exception.InvalidVehicleTypeCategoryException;
import org.steffim.vehiclemanagerservice.model.entity.VehicleType;
import org.steffim.vehiclemanagerservice.model.shared.VehicleTypeCategory;

@ExtendWith(MockitoExtension.class)
class VehicleTypeFactoryTest {
  @InjectMocks private VehicleTypeFactory vehicleTypeFactory;

  @Test
  void toEntity_shouldMapValidEnumValue() {
    VehicleType result = vehicleTypeFactory.toEntity("CARGO");

    assertEquals("CARGO", result.getName());
    assertEquals(VehicleTypeCategory.CARGO, result.getType());
  }

  @Test
  void toEntity_shouldMapTrimmedCaseInsensitiveValue() {
    VehicleType result = vehicleTypeFactory.toEntity("  service  ");

    assertEquals("  service  ", result.getName());
    assertEquals(VehicleTypeCategory.SERVICE, result.getType());
  }

  @Test
  void toEntity_shouldThrowExceptionForInvalidEnumValue() {
    InvalidVehicleTypeCategoryException exception =
        assertThrows(
            InvalidVehicleTypeCategoryException.class, () -> vehicleTypeFactory.toEntity("plane"));

    assertTrue(exception.getMessage().contains("Invalid vehicle type category 'plane'"));
    assertTrue(exception.getMessage().contains("PUBLIC_TRANSPORT"));
    assertTrue(exception.getMessage().contains("CARGO"));
    assertTrue(exception.getMessage().contains("SERVICE"));
  }
}
