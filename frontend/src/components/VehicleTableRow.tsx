import { CategoryBadge } from './CategoryBadge';
import { ServiceStatus } from './ServiceStatus';
import type { Vehicle } from '../types/vehicle';

interface VehicleTableRowProps {
  vehicle: Vehicle;
}

function formatDate(isoDate: string): string {
  const date = new Date(isoDate);
  return Number.isNaN(date.getTime())
    ? isoDate
    : date.toLocaleDateString('de-DE', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
      });
}

export function VehicleTableRow({ vehicle }: VehicleTableRowProps) {
  const { personResponsible } = vehicle.vehicleType;

  return (
    <tr>
      <td className="cell--id">{vehicle.id}</td>
      <td className="cell--name">{vehicle.name}</td>
      <td>{vehicle.vehicleType.name}</td>
      <td>
        <CategoryBadge category={vehicle.vehicleType.type} />
      </td>
      <td>
        {personResponsible.firstName} {personResponsible.lastName}
      </td>
      <td className="cell--number">{formatDate(vehicle.registrationDate)}</td>
      <td className="cell--number">{vehicle.weight} t</td>
      <td className="cell--number">{vehicle.numberOfAxes}</td>
      <td className="cell--number">{vehicle.maxSpeed} km/h</td>
      <td>
        <ServiceStatus readyForService={vehicle.readyForService} />
      </td>
    </tr>
  );
}
