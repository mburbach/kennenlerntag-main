package org.steffim.vehiclemanagerservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.steffim.vehiclemanagerservice.model.shared.VehicleTypeCategory;

@Entity
@Table(name = "vehicle_type")
@Builder(toBuilder = true)
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    TODO: not blank
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    //    TODO: not blank
    // TODO: Enum einführen und auch nur diese Werte in DB erlauben
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VehicleTypeCategory type;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "person_responsible", referencedColumnName = "id", nullable = false)
    Person personResponsible;

}