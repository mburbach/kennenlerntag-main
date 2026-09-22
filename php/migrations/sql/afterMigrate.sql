INSERT INTO person (first_name, last_name)
SELECT 'Max', 'Mustermann'
WHERE NOT EXISTS (
    SELECT 1
    FROM person
    WHERE first_name = 'Max' AND last_name = 'Mustermann'
);

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Bus', 'PUBLIC_TRANSPORT', p.id
FROM person p
WHERE p.first_name = 'Max'
  AND p.last_name = 'Mustermann'
  AND NOT EXISTS (
      SELECT 1
      FROM vehicle_type
      WHERE name = 'Bus' AND type = 'PUBLIC_TRANSPORT'
  );



-- TODO: Entfernen, wenn Controller für VehicleType existiert.