package org.steffim.vehiclemanagerservice.model.dto;

import jakarta.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class VehicleResult {
    @NotEmpty
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private VehicleTypeResult vehicleType;

    @NotEmpty
    private OffsetDateTime registrationDate;

    private int weight;

    private boolean readyForService;

    private int numberOfAxes;

    private int maxSpeed;

}