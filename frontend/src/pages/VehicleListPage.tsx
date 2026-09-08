import { useMemo, useState } from 'react';
import { LoadDurationHint } from '../components/LoadDurationHint';
import { StateMessage } from '../components/StateMessage';
import { VehicleFilter } from '../components/VehicleFilter';
import { VehicleTable } from '../components/VehicleTable';
import { useVehicles } from '../hooks/useVehicles';
import type { Vehicle } from '../types/vehicle';

function matchesSearchTerm(vehicle: Vehicle, searchTerm: string): boolean {
  const person = vehicle.vehicleType.personResponsible;
  const haystack = [
    vehicle.name,
    vehicle.vehicleType.name,
    person.firstName,
    person.lastName,
  ]
    .join(' ')
    .toLowerCase();

  return haystack.includes(searchTerm.trim().toLowerCase());
}

export function VehicleListPage() {
  const { vehicles, isLoading, error, durationMs, reload } = useVehicles();
  const [searchTerm, setSearchTerm] = useState('');
  const [onlyReadyForService, setOnlyReadyForService] = useState(false);

  const visibleVehicles = useMemo(
    () =>
      vehicles
        .filter((vehicle) => matchesSearchTerm(vehicle, searchTerm))
        .filter((vehicle) => !onlyReadyForService || vehicle.readyForService),
    [vehicles, searchTerm, onlyReadyForService],
  );

  return (
    <section className="page">
      <header className="page__header">
        <div>
          <h1>Fahrzeug-Übersicht</h1>
          <LoadDurationHint durationMs={durationMs} vehicleCount={vehicles.length} />
        </div>
        <button type="button" className="button" onClick={reload} disabled={isLoading}>
          {isLoading ? 'Lädt …' : 'Neu laden'}
        </button>
      </header>

      {error && (
        <StateMessage variant="error" title="Fahrzeuge konnten nicht geladen werden">
          <p>{error}</p>
          <p>
            Läuft der Vehicle Manager Service auf <code>http://localhost:8080</code>?
          </p>
        </StateMessage>
      )}

      {isLoading && !error && (
        <StateMessage variant="loading" title="Fahrzeuge werden geladen …" />
      )}

      {!isLoading && !error && (
        <>
          <VehicleFilter
            searchTerm={searchTerm}
            onSearchTermChange={setSearchTerm}
            onlyReadyForService={onlyReadyForService}
            onOnlyReadyForServiceChange={setOnlyReadyForService}
          />
          <VehicleTable vehicles={visibleVehicles} />
        </>
      )}
    </section>
  );
}
