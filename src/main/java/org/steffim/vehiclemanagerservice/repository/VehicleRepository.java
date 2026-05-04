package org.steffim.vehiclemanagerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.steffim.vehiclemanagerservice.model.entity.Vehicle;

@Repository
public interface VehicleRepository
    extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {}
