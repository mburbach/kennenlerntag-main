# Vehicle Manager – Frontend

Kleine React-Anwendung, die die Fahrzeug-Übersicht so zeigt, wie der Fachbereich sie
in der App sieht. Die Seite lädt ihre Daten über `GET /vehicles` aus dem
Vehicle Manager Service.

## Voraussetzungen

- Node.js 20 oder neuer
- laufender Vehicle Manager Service auf `http://localhost:8080` (siehe `../README.md`)

## Starten

```bash
cd frontend
npm install
npm run dev
```

Die Anwendung läuft danach auf http://localhost:5173 und leitet auf die
Fahrzeug-Übersicht unter `/vehicles` weiter.

Der Vite-Dev-Server reicht alle Requests unter `/api` an `http://localhost:8080`
weiter (siehe `vite.config.ts`). Am Backend ist dafür keine Änderung nötig,
insbesondere kein CORS-Setup.

## Weitere Skripte

| Befehl            | Beschreibung                                     |
|-------------------|--------------------------------------------------|
| `npm run dev`     | Dev-Server mit Hot Reload                        |
| `npm run build`   | Typprüfung (`tsc`) und Produktions-Build         |
| `npm run preview` | Produktions-Build lokal ausliefern               |

## Struktur

```
src/
  api/vehicleApi.ts          Aufruf von GET /vehicles inkl. Zeitmessung
  components/                Präsentationskomponenten (Tabelle, Filter, Badges)
  hooks/useVehicles.ts       Laden, Ladezustand, Fehler und Neu-Laden
  pages/VehicleListPage.tsx  Seite "Fahrzeug-Übersicht"
  types/vehicle.ts           TypeScript-Abbild der Backend-DTOs
  App.tsx                    Routing und Navigation
  main.tsx                   Einstiegspunkt
```

## Hinweis zur Ladezeit

Über der Tabelle steht, wie lange der letzte Request gedauert hat. Über
"Neu laden" lässt sich die Messung wiederholen, ohne die Seite neu zu laden.
Genau diese Wartezeit ist es, die der Fachbereich in `AUFGABE.md` meldet.

`main.tsx` verzichtet bewusst auf `StrictMode`, damit ein Seitenaufruf im
Dev-Modus genau einen Request an `/vehicles` auslöst und die Messung nicht
verfälscht wird.
