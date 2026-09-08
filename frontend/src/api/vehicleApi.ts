import type { Vehicle } from '../types/vehicle';

// Alle Aufrufe laufen ueber /api und werden vom Vite-Dev-Server
// an http://localhost:8080 weitergereicht (siehe vite.config.ts).
const API_BASE_URL = '/api';

export interface FetchVehiclesResult {
  vehicles: Vehicle[];
  /** Dauer des Requests in Millisekunden - so, wie der Fachbereich sie erlebt. */
  durationMs: number;
}

export async function fetchVehicles(): Promise<FetchVehiclesResult> {
  const startedAt = performance.now();

  const response = await fetch(`${API_BASE_URL}/vehicles`, {
    headers: { Accept: 'application/json' },
  });

  if (!response.ok) {
    throw new Error(
      `Fahrzeuge konnten nicht geladen werden (HTTP ${response.status} ${response.statusText}).`,
    );
  }

  const vehicles: Vehicle[] = await response.json();

  return {
    vehicles,
    durationMs: Math.round(performance.now() - startedAt),
  };
}
