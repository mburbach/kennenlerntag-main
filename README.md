# Vehicle Manager Service

Kurzanleitung, um die Anwendung lokal mit PostgreSQL via Docker Compose zu starten.

## Voraussetzungen

- Java (`java.version=25`)
- laufendes Docker
- Port `5432` ist frei

## 1) PostgreSQL mit Docker Compose starten

Nutze die `docker-compose.yml` um eine PostgreSQL-Datenbank zu starten.

Die Anwendung nutzt aktuell diese DB-Werte aus `src/main/resources/application.properties`:

- URL: `jdbc:postgresql://localhost:5432/postgres?currentSchema=vehicle_manager`
- User: `postgres`
- Passwort: `admin`

## 2) Anwendung starten

In ./run ist eine VehicleManagerServiceApplication.run.xml für IntelliJ hinterlegt.
Active Profile ist aktuell noch leer, da noch keine Unterscheidungen zwischen Dev/Prod nötig sind.

## 3) Optional: Tests ausführen

Die Integration Tests bringen über Testcontainers automatisch eine temporäre PostgreSQL-Instanz mit, die unabhängig von
der lokalen DB läuft. Es wird allerdings Docker auf dem Dev-Rechner benötigt, damit die Tests erfolgreich durchlaufen
können.
Hinweis: Die Integrationstests benötigen Docker.

## Manuelles Testen

Nach dem Start der Anwendung kannst du die Endpunkte auf http://localhost:8080/swagger-ui.html sehen und testen.

`Da es für VehicleType noch keinen Controller gibt, er aber benötigt wird, um ein Vehicle zu erstellen, wird durch
```afterMigrate.sql``` automatisch ein VehicleType in die DB eingefügt, damit du direkt loslegen kannst. Sofern der Type
nicht manuell gelöscht wurde, hat er die Id 1.
