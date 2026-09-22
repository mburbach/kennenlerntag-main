package com.musterfirma.vehiclemanagerservice.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.musterfirma.vehiclemanagerservice.model.shared.VehicleTypeCategory;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class VehicleTypeResult {
    @NotEmpty
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private VehicleTypeCategory type;

    @NotEmpty
    private PersonResult personResponsible;

}
