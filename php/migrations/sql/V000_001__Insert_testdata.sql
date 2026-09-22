-- Testdaten für den Vehicle Manager Service
-- 5 Personen, 20 Fahrzeugtypen, 20 Fahrzeuge (je ein eigener Typ pro Fahrzeug)

INSERT INTO person (first_name, last_name) VALUES ('Anna',  'Müller');
INSERT INTO person (first_name, last_name) VALUES ('Ben',   'Schmidt');
INSERT INTO person (first_name, last_name) VALUES ('Clara', 'Weber');
INSERT INTO person (first_name, last_name) VALUES ('David', 'Fischer');
INSERT INTO person (first_name, last_name) VALUES ('Emma',  'Bauer');

-- Fahrzeugtypen – Anna Müller verantwortet die Linienbusse
INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Solaris Urbino 12', 'PUBLIC_TRANSPORT', p.id FROM person p WHERE p.first_name = 'Anna' AND p.last_name = 'Müller';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'MAN Lion''s City 12', 'PUBLIC_TRANSPORT', p.id FROM person p WHERE p.first_name = 'Anna' AND p.last_name = 'Müller';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Mercedes-Benz Citaro', 'PUBLIC_TRANSPORT', p.id FROM person p WHERE p.first_name = 'Anna' AND p.last_name = 'Müller';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'VDL Citea LLE', 'PUBLIC_TRANSPORT', p.id FROM person p WHERE p.first_name = 'Anna' AND p.last_name = 'Müller';

-- Fahrzeugtypen – Ben Schmidt verantwortet die Straßenbahnen
INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Siemens Avenio', 'PUBLIC_TRANSPORT', p.id FROM person p WHERE p.first_name = 'Ben' AND p.last_name = 'Schmidt';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Bombardier Flexity', 'PUBLIC_TRANSPORT', p.id FROM person p WHERE p.first_name = 'Ben' AND p.last_name = 'Schmidt';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Stadler Tango', 'PUBLIC_TRANSPORT', p.id FROM person p WHERE p.first_name = 'Ben' AND p.last_name = 'Schmidt';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Alstom Citadis', 'PUBLIC_TRANSPORT', p.id FROM person p WHERE p.first_name = 'Ben' AND p.last_name = 'Schmidt';

-- Fahrzeugtypen – Clara Weber verantwortet die Sattelzüge
INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Volvo FH16', 'CARGO', p.id FROM person p WHERE p.first_name = 'Clara' AND p.last_name = 'Weber';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Scania R 500', 'CARGO', p.id FROM person p WHERE p.first_name = 'Clara' AND p.last_name = 'Weber';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'MAN TGX 18.510', 'CARGO', p.id FROM person p WHERE p.first_name = 'Clara' AND p.last_name = 'Weber';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'DAF XF 480', 'CARGO', p.id FROM person p WHERE p.first_name = 'Clara' AND p.last_name = 'Weber';

-- Fahrzeugtypen – David Fischer verantwortet die Kleintransporter
INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Mercedes-Benz Sprinter', 'CARGO', p.id FROM person p WHERE p.first_name = 'David' AND p.last_name = 'Fischer';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'VW Crafter', 'CARGO', p.id FROM person p WHERE p.first_name = 'David' AND p.last_name = 'Fischer';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Ford Transit', 'CARGO', p.id FROM person p WHERE p.first_name = 'David' AND p.last_name = 'Fischer';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Renault Master', 'CARGO', p.id FROM person p WHERE p.first_name = 'David' AND p.last_name = 'Fischer';

-- Fahrzeugtypen – Emma Bauer verantwortet die Pannenhilfe
INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'MAN TGE Abschleppwagen', 'SERVICE', p.id FROM person p WHERE p.first_name = 'Emma' AND p.last_name = 'Bauer';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Iveco Daily Werkstattwagen', 'SERVICE', p.id FROM person p WHERE p.first_name = 'Emma' AND p.last_name = 'Bauer';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Mercedes-Benz Vito Servicewagen', 'SERVICE', p.id FROM person p WHERE p.first_name = 'Emma' AND p.last_name = 'Bauer';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'VW Transporter Pannendienst', 'SERVICE', p.id FROM person p WHERE p.first_name = 'Emma' AND p.last_name = 'Bauer';

-- Linienbusse – 4 Fahrzeuge
INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Linienbus 001', vt.id, '2022-03-10 07:00:00+01', 14, true,  2, 80 FROM vehicle_type vt WHERE vt.name = 'Solaris Urbino 12';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Linienbus 002', vt.id, '2022-06-22 07:00:00+01', 14, true,  2, 80 FROM vehicle_type vt WHERE vt.name = 'MAN Lion''s City 12';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Linienbus 003', vt.id, '2023-01-05 07:00:00+01', 14, false, 2, 80 FROM vehicle_type vt WHERE vt.name = 'Mercedes-Benz Citaro';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Linienbus 004', vt.id, '2023-09-18 07:00:00+01', 14, true,  2, 80 FROM vehicle_type vt WHERE vt.name = 'VDL Citea LLE';

-- Straßenbahnen – 4 Fahrzeuge
INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Straßenbahn 001', vt.id, '2021-04-12 06:30:00+01', 40, true,  4, 70 FROM vehicle_type vt WHERE vt.name = 'Siemens Avenio';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Straßenbahn 002', vt.id, '2021-11-03 06:30:00+01', 40, true,  4, 70 FROM vehicle_type vt WHERE vt.name = 'Bombardier Flexity';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Straßenbahn 003', vt.id, '2022-07-29 06:30:00+01', 40, false, 4, 70 FROM vehicle_type vt WHERE vt.name = 'Stadler Tango';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Straßenbahn 004', vt.id, '2024-02-14 06:30:00+01', 40, true,  4, 70 FROM vehicle_type vt WHERE vt.name = 'Alstom Citadis';

-- Sattelzüge – 4 Fahrzeuge
INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Sattelzug 001', vt.id, '2020-08-01 05:00:00+01', 25, true,  5, 100 FROM vehicle_type vt WHERE vt.name = 'Volvo FH16';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Sattelzug 002', vt.id, '2021-02-17 05:00:00+01', 25, true,  5, 100 FROM vehicle_type vt WHERE vt.name = 'Scania R 500';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Sattelzug 003', vt.id, '2022-10-30 05:00:00+01', 25, false, 5, 100 FROM vehicle_type vt WHERE vt.name = 'MAN TGX 18.510';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Sattelzug 004', vt.id, '2023-05-11 05:00:00+01', 25, true,  5, 100 FROM vehicle_type vt WHERE vt.name = 'DAF XF 480';

-- Kleintransporter – 4 Fahrzeuge
INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Kleintransporter 001', vt.id, '2021-06-08 08:00:00+01', 3, true,  2, 130 FROM vehicle_type vt WHERE vt.name = 'Mercedes-Benz Sprinter';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Kleintransporter 002', vt.id, '2022-01-25 08:00:00+01', 3, true,  2, 130 FROM vehicle_type vt WHERE vt.name = 'VW Crafter';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Kleintransporter 003', vt.id, '2022-09-09 08:00:00+01', 3, false, 2, 130 FROM vehicle_type vt WHERE vt.name = 'Ford Transit';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Kleintransporter 004', vt.id, '2024-03-22 08:00:00+01', 3, true,  2, 130 FROM vehicle_type vt WHERE vt.name = 'Renault Master';

-- Pannenhilfe – 4 Fahrzeuge
INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Pannenhilfe 001', vt.id, '2020-12-01 07:00:00+01', 5, true,  2, 120 FROM vehicle_type vt WHERE vt.name = 'MAN TGE Abschleppwagen';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Pannenhilfe 002', vt.id, '2021-08-14 07:00:00+01', 5, true,  2, 120 FROM vehicle_type vt WHERE vt.name = 'Iveco Daily Werkstattwagen';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Pannenhilfe 003', vt.id, '2022-04-27 07:00:00+01', 5, false, 2, 120 FROM vehicle_type vt WHERE vt.name = 'Mercedes-Benz Vito Servicewagen';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Pannenhilfe 004', vt.id, '2023-11-05 07:00:00+01', 5, true,  2, 120 FROM vehicle_type vt WHERE vt.name = 'VW Transporter Pannendienst';
