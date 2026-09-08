interface LoadDurationHintProps {
  durationMs: number | null;
  vehicleCount: number;
}

/**
 * Zeigt an, wie lange das Laden der Liste gedauert hat.
 * Genau diese Wartezeit meldet der Fachbereich (siehe AUFGABE.md).
 */
export function LoadDurationHint({ durationMs, vehicleCount }: LoadDurationHintProps) {
  if (durationMs === null) {
    return null;
  }

  const isSlow = durationMs >= 1000;

  return (
    <p className={`duration ${isSlow ? 'duration--slow' : ''}`}>
      {vehicleCount} Fahrzeuge geladen in{' '}
      <strong>{durationMs.toLocaleString('de-DE')} ms</strong>
      {isSlow && <span className="duration__warning"> · spürbar langsam</span>}
    </p>
  );
}
