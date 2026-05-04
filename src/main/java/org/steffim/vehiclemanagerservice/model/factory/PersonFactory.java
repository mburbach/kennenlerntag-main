package org.steffim.vehiclemanagerservice.model.factory;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.steffim.vehiclemanagerservice.model.dto.PersonResult;
import org.steffim.vehiclemanagerservice.model.entity.Person;

@AllArgsConstructor
@Component
public class PersonFactory {


  public @NotEmpty PersonResult toResult(@NotNull Person personResponsible) {
    return PersonResult.builder()
        .id(personResponsible.getId())
        .firstName(personResponsible.getFirstName())
        .lastName(personResponsible.getLastName())
        .build();
  }
}