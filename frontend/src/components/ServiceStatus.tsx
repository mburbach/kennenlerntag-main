interface ServiceStatusProps {
  readyForService: boolean;
}

export function ServiceStatus({ readyForService }: ServiceStatusProps) {
  return (
    <span className={`status status--${readyForService ? 'ready' : 'blocked'}`}>
      <span className="status__dot" aria-hidden="true" />
      {readyForService ? 'Einsatzbereit' : 'Nicht bereit'}
    </span>
  );
}
