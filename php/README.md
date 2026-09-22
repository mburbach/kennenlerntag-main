# Vehicle Manager Service (PHP / Symfony)

PHP-Portierung des Spring-Boot-Service aus `../java`. Fachlich und in der API identisch,
inklusive der bestehenden Schwächen (siehe unten). Die Aufgabenstellung in `../AUFGABE.md` gilt
unverändert.

| Java-Variante                  | PHP-Variante                                           |
| ------------------------------ | ------------------------------------------------------ |
| Java 25, Spring Boot 3.5       | PHP 8.5, Symfony 8.1                                   |
| Spring Data JPA / Hibernate    | Doctrine ORM 3 / DBAL 4                                |
| Flyway (`db/migration`)        | Doctrine Migrations (`migrations/`, SQL in `migrations/sql`) |
| springdoc (Swagger UI)         | NelmioApiDocBundle                                     |
| JUnit, MockMvc, Testcontainers | PHPUnit, WebTestCase, Test-Datenbank im Compose-Stack  |

## Voraussetzungen

- **Docker** mit Docker Compose. PHP und Composer musst du lokal nicht installieren, alles läuft im
  Container.
- Freie Ports: `8080` (Backend), `5433` (Datenbank), `5173` (Frontend).
  Der Port 8080 ist derselbe wie beim Spring-Boot-Service, es kann also immer nur eine der beiden
  Varianten laufen.

## Starten

Alle `docker compose`-Befehle führst du im Wurzelverzeichnis des Repositories aus. Das Profil `php`
wählt die PHP-Variante:

```bash
docker compose --profile php up -d --build
```

Das startet Datenbank, Backend und Frontend. Beim ersten Start installiert der Container die
Abhängigkeiten (`composer install`), führt danach die Migrationen samt Testdaten aus (wie Flyway
beim Start des Spring-Boot-Service) und startet den Webserver. Das Backend läuft, sobald
http://localhost:8080/vehicles eine JSON-Liste liefert, die Fahrzeug-Übersicht unter
http://localhost:5173. Der Quellcode in `php/` ist in den Container gemountet, Änderungen wirken
ohne Neustart.

Stoppen: `docker compose --profile php down`.

## Tests

```bash
docker compose exec backend-php composer test
```

Das migriert die Test-Datenbank `vehicle_manager_test` und führt danach PHPUnit aus. Nur die
Unit-Tests: `docker compose exec backend-php composer test:unit`.

## Manuelles Testen und Analyse

- Swagger UI: http://localhost:8080/swagger-ui.html (OpenAPI-JSON unter `/v3/api-docs`)
- SQL-Log: `docker compose logs -f backend-php` gibt jedes Statement aus (entspricht `spring.jpa.show-sql`)
  und pro Request eine Zeile `Session Metrics: <n> SQL statements executed for GET /vehicles`
  (entspricht den Hibernate-Statistiken).
- Symfony Profiler: http://localhost:8080/_profiler zeigt pro Request die Queries.
- Datenbank direkt: `docker compose exec postgres-php psql -U postgres -d vehicle_manager`
  (von außen `localhost:5433`, User `postgres`, Passwort `admin`). Die Tabellen liegen wie beim
  Java-Service im Schema `vehicle_manager`.

Wie in der Java-Variante legt `migrations/sql/afterMigrate.sql` nach jeder Migration einen
VehicleType an (Id 1, sofern nicht gelöscht), da es noch keinen Controller für VehicleType gibt.

## Bewusst übernommene Schwächen

Die Portierung verhält sich absichtlich wie der Java-Stand, in dem das Problem aus `../FLT-1042.md`
noch nicht behoben ist:

- `GET /vehicles` lädt die Fahrzeuge mit `findAll()`. Typ und verantwortliche Person werden erst
  beim Mappen im `VehicleFactory`/`VehicleTypeFactory` einzeln nachgeladen (Doctrine lädt
  `ManyToOne`-Beziehungen standardmäßig über Proxies nach). Mit den Testdaten ergibt das
  26 Statements für 20 Fahrzeuge (1 + 20 Typen + 5 Personen), die Anzahl wächst mit der Flotte.
- Keine Indizes auf den Fremdschlüsselspalten, Zugangsdaten in `.env` im Repository, die TODOs aus
  dem Java-Code sind an den entsprechenden Stellen erhalten.

## Wenn etwas nicht startet

- `Bind for 0.0.0.0:8080 failed`: Der Spring-Boot-Service läuft noch, lokal oder mit
  `--profile java`. Beende ihn (`docker compose --profile java down`).
- Datenbank zurücksetzen: `docker compose --profile php down -v`, danach neu starten.
- Ausgaben des Backends: `docker compose logs backend-php`.
