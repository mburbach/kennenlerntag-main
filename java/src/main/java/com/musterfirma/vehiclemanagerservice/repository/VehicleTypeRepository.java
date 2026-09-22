package com.musterfirma.vehiclemanagerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.musterfirma.vehiclemanagerservice.model.entity.VehicleType;

@Repository
public interface VehicleTypeRepository
    extends JpaRepository<VehicleType, Long>, JpaSpecificationExecutor<VehicleType> {}
