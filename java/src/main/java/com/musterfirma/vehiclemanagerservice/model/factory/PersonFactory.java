package com.musterfirma.vehiclemanagerservice.model.factory;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.musterfirma.vehiclemanagerservice.model.dto.PersonResult;
import com.musterfirma.vehiclemanagerservice.model.entity.Person;

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