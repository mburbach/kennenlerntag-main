package com.musterfirma.vehiclemanagerservice.exception;

import com.musterfirma.vehiclemanagerservice.model.entity.Vehicle;

public class FailedToSaveVehicleException extends RuntimeException {
    public FailedToSaveVehicleException(Vehicle vehicle) {
        super("Failed to save vehicle: " +
               vehicle.toString());
    }
}
