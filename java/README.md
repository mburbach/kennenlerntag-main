# Vehicle Manager Service (Java / Spring Boot)

Die Anwendung besteht aus drei Teilen:

- einer PostgreSQL-Datenbank, die per Docker Compose läuft
- dem Spring-Boot-Service (Backend) auf Port `8080`
- der Fahrzeug-Übersicht als React-Anwendung (Frontend) auf Port `5173`, sie liegt in `../frontend`

Für die Aufgabe brauchst du alle drei. Die Anleitung unten führt dich der Reihe nach durch. Die
`docker compose`-Befehle führst du im Wurzelverzeichnis des Repositories aus, alle anderen Befehle
im Verzeichnis `java/`.

## Schnellstart: alles im Container

Ohne lokales Java und Node startest du Datenbank, Backend und Frontend zusammen (im
Wurzelverzeichnis):

```bash
docker compose --profile java up -d --build
```

Das Backend läuft dann auf http://localhost:8080, das Frontend auf http://localhost:5173. Für die
Arbeit am Code ist der Weg unten bequemer, weil das Backend dann direkt aus der IDE läuft.

## Voraussetzungen

- **Java 25.** Prüfe mit `java -version`. Ältere Versionen reichen nicht, das Projekt ist auf
  `java.version=25` festgelegt. Über [SDKMAN](https://sdkman.io/) installierst du es mit
  `sdk install java 25-tem`.
- **Docker** mit Docker Compose, laufend.
- **Node.js 20 oder neuer** für das Frontend. Alternativ startest du das Frontend im Container,
  siehe Schritt 3.
- Die Ports `5432` (Datenbank), `8080` (Backend) und `5173` (Frontend) müssen frei sein.

Maven brauchst du nicht zu installieren. Das Projekt bringt den Maven Wrapper mit, `./mvnw` lädt
die passende Version beim ersten Aufruf selbst herunter.

## 1) Datenbank starten

Im Wurzelverzeichnis:

```bash
docker compose up -d postgres-java
```

Die Anwendung erwartet diese Werte aus `src/main/resources/application.properties`:

- URL: `jdbc:postgresql://localhost:5432/postgres?currentSchema=vehicle_manager`
- User: `postgres`
- Passwort: `admin`

Tabellen und Testdaten legt Flyway beim Start des Backends automatisch an.

## 2) Backend starten

Auf der Kommandozeile:

```bash
./mvnw spring-boot:run
```

Unter Windows `mvnw.cmd spring-boot:run`.

In IntelliJ öffnest du das Verzeichnis `java/` als Projekt. Unter
`.run/VehicleManagerServiceApplication.run.xml` liegt eine fertige Run-Konfiguration. Das aktive
Profil ist leer, da es noch keine Unterscheidung zwischen Dev und Prod gibt.

Das Backend läuft, sobald `http://localhost:8080/vehicles` eine Liste im JSON-Format liefert.

## 3) Frontend starten

Mit lokalem Node:

```bash
cd ../frontend
npm install
npm run dev
```

Ohne lokales Node nutzt du den Schnellstart oben, dort läuft das Frontend im Container mit.

Die Fahrzeug-Übersicht ist danach unter http://localhost:5173 erreichbar. Der
Dev-Server leitet alle Aufrufe unter `/api` an das Backend weiter, deshalb muss Schritt 2 laufen.
Details stehen in `../frontend/README.md`.

## 4) Optional: Tests ausführen

```bash
./mvnw test
```

Die Integrationstests bringen über Testcontainers automatisch eine temporäre PostgreSQL-Instanz mit,
die unabhängig von der lokalen Datenbank läuft. Dafür wird Docker auf dem Entwicklungsrechner
benötigt.

## Manuelles Testen

Die Endpunkte kannst du unter http://localhost:8080/swagger-ui.html ansehen und ausprobieren.

Da es für VehicleType noch keinen Controller gibt, er aber zum Anlegen eines Vehicle benötigt wird,
fügt `afterMigrate.sql` beim Start automatisch einen VehicleType ein. Sofern er nicht manuell
gelöscht wurde, hat er die Id 1.

## Wenn etwas nicht startet

- `Port 5432 already in use`: Es läuft bereits eine PostgreSQL-Instanz. Beende sie oder ändere den
  Port in `../docker-compose.yml` (Service `postgres-java`) und in `application.properties`.
- Flyway meldet einen Checksum-Fehler: Eine Migration wurde nach dem ersten Start verändert. Setze
  die lokale Datenbank zurück mit `docker compose --profile java down -v` (im Wurzelverzeichnis)
  und starte bei Schritt 1 neu.
- Das Frontend zeigt "Fahrzeuge konnten nicht geladen werden": Das Backend läuft nicht oder nicht
  auf Port 8080. Prüfe Schritt 2.
