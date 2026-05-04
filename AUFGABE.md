# Technische Aufgabe – Vehicle Manager Service

**Zeitrahmen:** ca. 30–45 Minuten

---

## Kontext

Vor dir liegt ein laufender Spring-Boot-Service zur Verwaltung von Fahrzeugen.
Der Service ist bereits funktionsfähig und mit Testdaten befüllt (20 Fahrzeuge, 5 Typen, 5 Personen).

Starte die Anwendung lokal (Anleitung in `README.md`) und ruf anschließend den folgenden Endpunkt auf:

```
GET http://localhost:8080/vehicles
```

Die Swagger-UI ist unter `http://localhost:8080/swagger-ui.html` erreichbar.

---

## Aufgabe

### 1. Analyse

Schau dir die **Anwendungslogs** an, während du `GET /vehicles` aufrufst.

- Was fällt dir auf?
- Worin siehst du ein potenzielles Problem – insbesondere im Hinblick auf Skalierbarkeit?
- Erkläre das Problem in eigenen Worten.

### 2. Umsetzung

Behebe das identifizierte Problem im Code.

Deine Lösung sollte:
- das Problem grundsätzlich lösen, nicht nur für diesen einen Endpunkt
- keine bestehenden Tests brechen
- den Stil und die Struktur des vorhandenen Codes respektieren

### 3. Nachweis (optional, wenn Zeit bleibt)

Zeige anhand der Logs oder eines Tests, dass deine Lösung das Problem tatsächlich behebt.

---

## Hinweise

- Die Anwendung loggt alle SQL-Queries und die Anzahl der Datenbankzugriffe pro Request in die Konsole.
- Du darfst bestehenden Code anpassen, aber keine externen Bibliotheken hinzufügen.
- Fragen zum Setup oder zur Aufgabenstellung sind ausdrücklich erlaubt.
