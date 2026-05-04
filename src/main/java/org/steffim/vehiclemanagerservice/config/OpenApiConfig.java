package org.steffim.vehiclemanagerservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Vehicle Manager Service API")
                .description("""
                    REST API zur Verwaltung von Fahrzeugen, Fahrzeugtypen und verantwortlichen Personen.

                    **Datenmodell:**
                    - `Person` – verantwortliche Person für einen Fahrzeugtyp
                    - `VehicleType` – Fahrzeugklasse (PUBLIC_TRANSPORT, CARGO, SERVICE)
                    - `Vehicle` – einzelnes Fahrzeug mit technischen Eigenschaften

                    **Testdaten:**
                    Beim Start werden automatisch 5 Fahrzeugtypen und 20 Fahrzeuge eingespielt.
                    """)
                .version("0.0.1-SNAPSHOT")
                .contact(new Contact()
                    .name("Vehicle Manager Service")))
            .tags(List.of(
                new Tag().name("vehicle").description("Operationen auf Fahrzeugen")
            ));
    }
}
