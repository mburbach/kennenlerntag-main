package org.steffim.vehiclemanagerservice.model.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.steffim.vehiclemanagerservice.model.dto.CreateVehicleDto;
import org.steffim.vehiclemanagerservice.model.dto.VehicleResult;
import org.steffim.vehiclemanagerservice.model.entity.Vehicle;
import org.steffim.vehiclemanagerservice.model.entity.VehicleType;

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