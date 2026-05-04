package org.steffim.vehiclemanagerservice.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.steffim.vehiclemanagerservice.exception.FailedToSaveVehicleException;
import org.steffim.vehiclemanagerservice.exception.ResourceNotFoundException;
import org.steffim.vehiclemanagerservice.model.dto.CreateVehicleDto;
import org.steffim.vehiclemanagerservice.model.dto.VehicleResult;
import org.steffim.vehiclemanagerservice.model.entity.Vehicle;
import org.steffim.vehiclemanagerservice.model.entity.VehicleType;
import org.steffim.vehiclemanagerservice.model.factory.VehicleFactory;
import org.steffim.vehiclemanagerservice.repository.VehicleRepository;
import org.steffim.vehiclemanagerservice.repository.VehicleTypeRepository;

@RequiredArgsConstructor
@Service
public class VehicleService {

  private final VehicleRepository vehicleRepository;
  private final VehicleTypeRepository vehicleTypeRepository;
  private final VehicleFactory vehicleFactory;

  public List<VehicleResult> getAllVehicles() {

    List<Vehicle> vehicleEntities = vehicleRepository.findAll();
    return vehicleEntities.stream().map(vehicleFactory::toResult).toList();
  }

  public VehicleResult addVehicle(CreateVehicleDto dto) {
    VehicleType vehicleType =
        vehicleTypeRepository
            .findById(dto.getVehicleTypeId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Vehicle type with ID " + dto.getVehicleTypeId() + " not found."));
    Vehicle entity = vehicleFactory.toEntity(dto, vehicleType);

    try {
      Vehicle savedEntity = vehicleRepository.save(entity);
      return vehicleFactory.toResult(savedEntity);
    } catch (DataAccessException ex) {
      throw new FailedToSaveVehicleException(entity);
    }
  }

  public VehicleResult deleteVehicle(Long id) {
    Vehicle vehicle =
        vehicleRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Vehicle with ID " + id + " not found."));
    vehicleRepository.delete(vehicle);
    return vehicleFactory.toResult(vehicle);
  }
}
