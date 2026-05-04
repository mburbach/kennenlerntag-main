package org.steffim.vehiclemanagerservice.exception;

public class ResourceNotFoundException extends RuntimeException {

  // TODO: Ist das optimal? Exception Konzept in Ruhe durchdenken.
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
