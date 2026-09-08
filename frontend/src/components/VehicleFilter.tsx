interface VehicleFilterProps {
  searchTerm: string;
  onSearchTermChange: (value: string) => void;
  onlyReadyForService: boolean;
  onOnlyReadyForServiceChange: (value: boolean) => void;
}

export function VehicleFilter({
  searchTerm,
  onSearchTermChange,
  onlyReadyForService,
  onOnlyReadyForServiceChange,
}: VehicleFilterProps) {
  return (
    <div className="filter">
      <input
        type="search"
        className="filter__search"
        placeholder="Fahrzeug, Typ oder verantwortliche Person suchen …"
        value={searchTerm}
        onChange={(event) => onSearchTermChange(event.target.value)}
        aria-label="Fahrzeuge durchsuchen"
      />
      <label className="filter__checkbox">
        <input
          type="checkbox"
          checked={onlyReadyForService}
          onChange={(event) => onOnlyReadyForServiceChange(event.target.checked)}
        />
        Nur einsatzbereite Fahrzeuge
      </label>
    </div>
  );
}
