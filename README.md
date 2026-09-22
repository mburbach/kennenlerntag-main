# Vehicle Manager Service

Der Service zur Verwaltung von Fahrzeugen liegt in zwei gleichwertigen Backend-Varianten vor. Beide
bieten dieselbe REST-API auf Port `8080` und teilen sich das Frontend.

| Verzeichnis | Inhalt                                                        | Anleitung                                    |
| ----------- | ------------------------------------------------------------- | -------------------------------------------- |
| `java/`     | Backend mit Java 25, Spring Boot, JPA/Hibernate und Flyway    | [java/README.md](java/README.md)             |
| `php/`      | Backend mit PHP 8.5, Symfony, Doctrine ORM und Migrations     | [php/README.md](php/README.md)               |
| `frontend/` | Fahrzeug-Übersicht als React-Anwendung auf Port `5173`        | [frontend/README.md](frontend/README.md)     |

## Starten mit Docker Compose

Die `docker-compose.yml` im Wurzelverzeichnis startet Datenbank, Backend und Frontend. Welches
Backend läuft, wählst du über das Profil:

```bash
docker compose --profile java up -d --build   # Java / Spring Boot
docker compose --profile php  up -d --build   # PHP / Symfony
```

Danach liefert http://localhost:8080/vehicles die Fahrzeuge als JSON, die Fahrzeug-Übersicht läuft
unter http://localhost:5173.

Beide Backends belegen Port `8080`, es läuft also immer nur eines. Vor dem Wechsel stoppst du die
laufende Variante mit demselben Profil, z. B. `docker compose --profile java down`. Die Datenbanken
der beiden Varianten sind getrennt (Java auf Port `5432`, PHP auf Port `5433`).

Wie du die Backends lokal ohne Container entwickelst und testest, steht in den jeweiligen
Anleitungen.

Die Aufgabenstellung steht in [AUFGABE.md](AUFGABE.md), die Tickets in `FLT-1042.md` und
`FLT-1087.md`.
