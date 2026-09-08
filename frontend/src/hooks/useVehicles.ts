import { useCallback, useEffect, useState } from 'react';
import { fetchVehicles } from '../api/vehicleApi';
import type { Vehicle } from '../types/vehicle';

interface UseVehiclesState {
  vehicles: Vehicle[];
  isLoading: boolean;
  error: string | null;
  /** Ladedauer des letzten erfolgreichen Requests in Millisekunden. */
  durationMs: number | null;
  reload: () => void;
}

export function useVehicles(): UseVehiclesState {
  const [vehicles, setVehicles] = useState<Vehicle[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [durationMs, setDurationMs] = useState<number | null>(null);
  const [reloadToken, setReloadToken] = useState(0);

  const reload = useCallback(() => setReloadToken((token) => token + 1), []);

  useEffect(() => {
    let cancelled = false;

    setIsLoading(true);
    setError(null);

    fetchVehicles()
      .then((result) => {
        if (cancelled) return;
        setVehicles(result.vehicles);
        setDurationMs(result.durationMs);
      })
      .catch((cause: unknown) => {
        if (cancelled) return;
        setVehicles([]);
        setDurationMs(null);
        setError(
          cause instanceof Error
            ? cause.message
            : 'Unbekannter Fehler beim Laden der Fahrzeuge.',
        );
      })
      .finally(() => {
        if (!cancelled) setIsLoading(false);
      });

    return () => {
      cancelled = true;
    };
  }, [reloadToken]);

  return { vehicles, isLoading, error, durationMs, reload };
}
