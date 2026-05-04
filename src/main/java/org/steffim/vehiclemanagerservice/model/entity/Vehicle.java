package org.steffim.vehiclemanagerservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.OffsetDateTime;
import lombok.*;

@Entity
@Table(name = "vehicle")
@Builder(toBuilder = true)
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "vehicle_type", referencedColumnName = "id", nullable = false)
    private VehicleType vehicleType;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private OffsetDateTime registrationDate;

    @NotNull
    @Positive
    @Column(name = "weight", nullable = false)
    private int weight; // in Tonnen

    @NotNull
    @Column(name = "ready_for_service", nullable = false)
    private boolean readyForService = false;

    @NotNull
    @Positive
    @Column(name = "number_of_axes", nullable = false)
    private int numberOfAxes;

    @NotNull
    @Positive
    @Column(name = "max_speed", nullable = false)
    private int maxSpeed; // in km/h

}