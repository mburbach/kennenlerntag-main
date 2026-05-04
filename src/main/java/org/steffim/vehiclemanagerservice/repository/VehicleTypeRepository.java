package org.steffim.vehiclemanagerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.steffim.vehiclemanagerservice.model.entity.VehicleType;

@Repository
public interface VehicleTypeRepository
    extends JpaRepository<VehicleType, Long>, JpaSpecificationExecutor<VehicleType> {}
