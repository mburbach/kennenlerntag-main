package org.steffim.vehiclemanagerservice.model.factory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.steffim.vehiclemanagerservice.exception.InvalidVehicleTypeCategoryException;
import org.steffim.vehiclemanagerservice.model.dto.VehicleTypeResult;
import org.steffim.vehiclemanagerservice.model.entity.VehicleType;
import org.steffim.vehiclemanagerservice.model.shared.VehicleTypeCategory;

@AllArgsConstructor
@Component
public class VehicleTypeFactory {

  private final PersonFactory personFactory;

  public @NotEmpty VehicleTypeResult toResult(@NotBlank VehicleType vehicleType) {
    return VehicleTypeResult.builder()
        .id(vehicleType.getId())
        .name(vehicleType.getName())
        .type(vehicleType.getType())
        .personResponsible(personFactory.toResult(vehicleType.getPersonResponsible()))
        .build();
  }

  //                TODO: Add person responsible when implemented
  public @NotBlank VehicleType toEntity(@NotNull String vehicleType) {
    VehicleTypeCategory category = getVehicleTypeCategory(vehicleType);
    return VehicleType.builder().name(vehicleType).type(category).build();
  }

  private static VehicleTypeCategory getVehicleTypeCategory(String vehicleType) {
    VehicleTypeCategory category;
    try {
      category = VehicleTypeCategory.valueOf(vehicleType.trim().toUpperCase(Locale.ROOT));
    } catch (IllegalArgumentException ex) {
      String allowedValues = Stream.of(VehicleTypeCategory.values())
          .map(Enum::name)
          .collect(Collectors.joining(", "));
      throw new InvalidVehicleTypeCategoryException(
          "Invalid vehicle type category '" + vehicleType + "'. Allowed values: " + allowedValues + ".");
    }
    return category;
  }
}