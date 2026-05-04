package org.steffim.vehiclemanagerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  // TODO: Prüfen, ob alle Exceptions sinnvoll gefangen sind.

  @ExceptionHandler(FailedToSaveVehicleException.class)
  public ResponseEntity<String> handleFailedToSaveVehicle(FailedToSaveVehicleException ex) {
    //        TODO: Bessere Fehlermeldung und Status Code optimieren
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }

  // TODO: ex.getMessage ausgeben ist für das Frontend nicht optimal
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("Validation failed: " + ex.getMessage());
  }

  @ExceptionHandler(InvalidVehicleTypeCategoryException.class)
  public ResponseEntity<String> handleInvalidVehicleTypeCategory(
      InvalidVehicleTypeCategoryException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }
}
