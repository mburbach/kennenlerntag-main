import { VehicleTableRow } from './VehicleTableRow';
import type { Vehicle } from '../types/vehicle';

interface VehicleTableProps {
  vehicles: Vehicle[];
}

export function VehicleTable({ vehicles }: VehicleTableProps) {
  if (vehicles.length === 0) {
    return <p className="empty">Keine Fahrzeuge gefunden.</p>;
  }

  return (
    <div className="table-wrapper">
      <table className="vehicle-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Fahrzeug</th>
            <th>Typ</th>
            <th>Kategorie</th>
            <th>Verantwortlich</th>
            <th>Zulassung</th>
            <th>Gewicht</th>
            <th>Achsen</th>
            <th>Max. Geschwindigkeit</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {vehicles.map((vehicle) => (
            <VehicleTableRow key={vehicle.id} vehicle={vehicle} />
          ))}
        </tbody>
      </table>
    </div>
  );
}
