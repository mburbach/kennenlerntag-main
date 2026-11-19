package com.musterfirma.vehiclemanagerservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.musterfirma.vehiclemanagerservice.model.entity.WorkshopStay;

@Repository
public interface WorkshopStayRepository extends JpaRepository<WorkshopStay, Long> {

  Optional<WorkshopStay> findByVehicleIdAndEndDateIsNull(Long vehicleId);
}
