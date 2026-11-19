package com.musterfirma.vehiclemanagerservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.musterfirma.vehiclemanagerservice.model.dto.CreateVehicleDto;
import com.musterfirma.vehiclemanagerservice.model.dto.VehicleResult;
import com.musterfirma.vehiclemanagerservice.model.entity.WorkshopStay;
import com.musterfirma.vehiclemanagerservice.service.VehicleService;

@Validated
@RestController
@RequiredArgsConstructor
public class VehicleController {

  public static final String VEHICLE_DTO_EXAMPLE = """
      {
        "name": "City Bus 42",
        "vehicleTypeId": 1,
        "registrationDate": "2026-04-20T10:15:30+02:00",
        "weight": 1500,
        "readyForService": true,
        "numberOfAxes": 2,
        "maxSpeed": 120
      }
      """;
  private final VehicleService vehicleService;

  // TODO: https://github.com/tkaczmarzyk/specification-arg-resolver/tree/master einführen
  @PostMapping("/vehicle")
  @Operation(
      summary = "Add a vehicle",
      description =
          "This endpoint adds a new vehicle. Check vehicle_type table for generated testdata. One type was generated on start up.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Vehicle successfully created"),
        @ApiResponse(responseCode = "400", description = "Validation error"),
        @ApiResponse(responseCode = "404", description = "Vehicle type not found"),
        @ApiResponse(responseCode = "409", description = "Vehicle could not be saved")
      })
  public ResponseEntity<VehicleResult> addVehicle(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Vehicle to create",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      examples = @ExampleObject(value = VEHICLE_DTO_EXAMPLE)))
          @RequestBody
          @Valid
          CreateVehicleDto dto) {
    VehicleResult vehicleResult = vehicleService.addVehicle(dto);
    return ResponseEntity.ok(vehicleResult);
  }

  @DeleteMapping("/vehicle/{id}")
  @Operation(summary = "Delete vehicle", description = "This endpoint deletes a vehicle by id.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Vehicle successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Vehicle not found")
      })
  public ResponseEntity<VehicleResult> deleteVehicle(
      @Parameter(description = "ID of the vehicle to delete", example = "3") @PathVariable
          Long id) {
    VehicleResult vehicleResult = vehicleService.deleteVehicle(id);
    return ResponseEntity.ok(vehicleResult);
  }

  @GetMapping("/vehicles")
  @Operation(summary = "Get all vehicles", description = "This endpoint returns all vehicles.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "List of vehicles")
      })
  public ResponseEntity<List<VehicleResult>> getAllVehicle() {
    List<VehicleResult> vehicles = vehicleService.getAllVehicles();
    return ResponseEntity.ok(vehicles);
  }

  @PostMapping("/vehicle/{id}/workshop")
  @Operation(
      summary = "Mark vehicle as in workshop",
      description = "This endpoint marks a vehicle as in workshop with a reason and planned return.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Vehicle marked as in workshop"),
        @ApiResponse(responseCode = "404", description = "Vehicle not found")
      })
  public ResponseEntity<VehicleResult> markInWorkshop(
      @Parameter(description = "ID of the vehicle", example = "3") @PathVariable Long id,
      @RequestBody WorkshopStay workshopStay) {
    return ResponseEntity.ok(vehicleService.markInWorkshop(id, workshopStay));
  }

  @PutMapping("/vehicle/{id}/workshop/release")
  @Operation(
      summary = "Release vehicle from workshop",
      description = "This endpoint ends the open workshop stay of a vehicle.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Vehicle released"),
        @ApiResponse(responseCode = "404", description = "Vehicle not found")
      })
  public ResponseEntity<VehicleResult> releaseFromWorkshop(
      @Parameter(description = "ID of the vehicle", example = "3") @PathVariable Long id) {
    return ResponseEntity.ok(vehicleService.releaseFromWorkshop(id));
  }
}
