package org.steffim.vehiclemanagerservice.exception;

import org.steffim.vehiclemanagerservice.model.entity.Vehicle;

public class FailedToSaveVehicleException extends RuntimeException {
    public FailedToSaveVehicleException(Vehicle vehicle) {
        super("Failed to save vehicle: " +
               vehicle.toString());
    }
}
