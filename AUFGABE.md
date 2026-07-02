# Technische Aufgabe – Vehicle Manager Service

**Zeitrahmen:** ca. 30–45 Minuten

---

## Kontext

Vor dir liegt ein laufender Spring-Boot-Service zur Verwaltung von Fahrzeugen.
Der Service ist bereits funktionsfähig und mit Testdaten befüllt (20 Fahrzeuge, 5 Typen, 5 Personen).

Aus dem Fachbereich (Flotten-Disposition) kam folgende Rückmeldung per Mail:

> "Seit wir einen Kunden mit einem größeren Fuhrpark angelegt haben, ist die Fahrzeug-Übersicht in der App
> spürbar langsamer geworden. Der Kunde hat mehrere hundert Fahrzeuge erfasst, und das Laden der Liste dauert
> gefühlt ewig. Bei unseren anderen, kleineren Kunden merkt man davon nichts. Könnt ihr euch das mal anschauen?
> Wir wollen in den nächsten Monaten noch weitere große Flottenkunden gewinnen, und das darf dann nicht
> schlimmer werden."

Starte die Anwendung lokal (Anleitung in `README.md`).

---

## Aufgabe

### 1. Analyse

Geh der Meldung nach und identifiziere die Ursache – mit welchen Mitteln (Code lesen, Endpunkt aufrufen,
Logs prüfen, Tools nutzen, ...) bleibt dir überlassen.

- Was ist die technische Ursache des Problems?
- Warum bemerken es nur Kunden mit vielen Fahrzeugen, andere aber nicht? Mit den lokalen Testdaten (20 Fahrzeuge)
  wird der Effekt kaum spürbar sein – überlege, wie du die Ursache trotzdem zweifelsfrei nachweisen kannst.
- Wie würdest du das Problem in 2–3 Sätzen einer Kollegin aus dem Fachbereich erklären, ohne technisches
  Vokabular vorauszusetzen?

### 2. Umsetzung

Behebe das identifizierte Problem im Code.

Deine Lösung sollte:
- das Problem grundsätzlich lösen, nicht nur an der einen Stelle, an der es dir aufgefallen ist
- keine bestehenden Tests brechen
- den Stil und die Struktur des vorhandenen Codes respektieren
- in einem angemessenen Verhältnis zu Aufwand und Risiko stehen – wir erwarten keine Überkonstruktion für
  ein Problem, das (noch) nicht in dieser Form produktiv aufgetreten ist

### 3. Nachweis (optional, wenn Zeit bleibt)

Zeige, dass deine Lösung das Problem tatsächlich behebt – z. B. anhand von Logs oder eines Tests.

### 4. Diskussion (im Gespräch)

- Wie hättest du dieses Problem im Idealfall schon vor der Meldung aus dem Fachbereich entdeckt?
- Gibt es im Code weitere Stellen mit einem ähnlichen Muster, die du (noch) nicht angefasst hast? Wie würdest
  du priorisieren, was jetzt behoben wird und was nicht?

---

## Hinweise

- Du darfst bestehenden Code anpassen, aber keine externen Bibliotheken hinzufügen.
- Fragen zum Setup oder zur Aufgabenstellung sind ausdrücklich erlaubt – wie im echten Berufsalltag auch.
