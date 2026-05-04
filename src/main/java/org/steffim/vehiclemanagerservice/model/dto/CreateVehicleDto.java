package org.steffim.vehiclemanagerservice.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CreateVehicleDto {

    @NotEmpty
    private String name;

    @NotNull
    private Long vehicleTypeId;

    @NotNull
    private OffsetDateTime registrationDate;

    @Positive
    private int weight;

    @Builder.Default
    private boolean readyForService = false;

    @Positive
    private int numberOfAxes;

    @Positive
    private int maxSpeed;
}