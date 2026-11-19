package com.musterfirma.vehiclemanagerservice.model.factory;

import java.time.LocalDateTime;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.musterfirma.vehiclemanagerservice.model.dto.CreateVehicleDto;
import com.musterfirma.vehiclemanagerservice.model.dto.VehicleResult;
import com.musterfirma.vehiclemanagerservice.model.dto.WorkshopStayResult;
import com.musterfirma.vehiclemanagerservice.model.entity.Vehicle;
import com.musterfirma.vehiclemanagerservice.model.entity.VehicleType;
import com.musterfirma.vehiclemanagerservice.model.entity.WorkshopStay;

@AllArgsConstructor
@Component
public class VehicleFactory {

    private final VehicleTypeFactory vehicleTypeFactory;

    public VehicleResult toResult(Vehicle entity) {
        return VehicleResult.builder()
                .id(entity.getId())
                .name(entity.getName())
                .vehicleType(vehicleTypeFactory.toResult(entity.getVehicleType()))
                .registrationDate(entity.getRegistrationDate())
                .weight(entity.getWeight())
                .readyForService(entity.isReadyForService())
                .numberOfAxes(entity.getNumberOfAxes())
                .maxSpeed(entity.getMaxSpeed())
                .inWorkshop(entity.getWorkshopStays().stream().anyMatch(s -> s.getEndDate() == null))
                .lastWorkshopStay(
                        entity.getWorkshopStays().stream()
                                .max(Comparator.comparing(WorkshopStay::getStartDate))
                                .map(this::toWorkshopStayResult)
                                .orElse(null))
                .build();
    }

    private WorkshopStayResult toWorkshopStayResult(WorkshopStay stay) {
        return WorkshopStayResult.builder()
                .reason(stay.getReason())
                .startDate(stay.getStartDate())
                .plannedReturn(stay.getPlannedReturn())
                .endDate(stay.getEndDate())
                .build();
    }

  public Vehicle toEntity(CreateVehicleDto dto, VehicleType vehicleType) {
        Vehicle entity = new Vehicle();
    entity.setName(dto.getName());
        entity.setVehicleType(vehicleType);
    entity.setRegistrationDate(dto.getRegistrationDate());
    entity.setWeight(dto.getWeight());
    entity.setReadyForService(dto.isReadyForService());
    entity.setNumberOfAxes(dto.getNumberOfAxes());
    entity.setMaxSpeed(dto.getMaxSpeed());

        return entity;
    }

}