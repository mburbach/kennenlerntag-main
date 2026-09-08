import type { VehicleTypeCategory } from '../types/vehicle';

const CATEGORY_LABELS: Record<VehicleTypeCategory, string> = {
  PUBLIC_TRANSPORT: 'ÖPNV',
  CARGO: 'Fracht',
  SERVICE: 'Service',
};

interface CategoryBadgeProps {
  category: VehicleTypeCategory;
}

export function CategoryBadge({ category }: CategoryBadgeProps) {
  return (
    <span className={`badge badge--${category.toLowerCase()}`}>
      {CATEGORY_LABELS[category] ?? category}
    </span>
  );
}
