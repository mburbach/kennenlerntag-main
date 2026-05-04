-- Testdaten für den Vehicle Manager Service
-- 5 Personen, 5 Fahrzeugtypen (je eine andere Person), 20 Fahrzeuge (je 4 pro Typ)

INSERT INTO person (first_name, last_name) VALUES ('Anna',  'Müller');
INSERT INTO person (first_name, last_name) VALUES ('Ben',   'Schmidt');
INSERT INTO person (first_name, last_name) VALUES ('Clara', 'Weber');
INSERT INTO person (first_name, last_name) VALUES ('David', 'Fischer');
INSERT INTO person (first_name, last_name) VALUES ('Emma',  'Bauer');

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Linienbus', 'PUBLIC_TRANSPORT', p.id FROM person p WHERE p.first_name = 'Anna'  AND p.last_name = 'Müller';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Straßenbahn', 'PUBLIC_TRANSPORT', p.id FROM person p WHERE p.first_name = 'Ben'   AND p.last_name = 'Schmidt';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Sattelzug', 'CARGO', p.id FROM person p WHERE p.first_name = 'Clara' AND p.last_name = 'Weber';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Kleintransporter', 'CARGO', p.id FROM person p WHERE p.first_name = 'David' AND p.last_name = 'Fischer';

INSERT INTO vehicle_type (name, type, person_responsible)
SELECT 'Pannenhilfe', 'SERVICE', p.id FROM person p WHERE p.first_name = 'Emma'  AND p.last_name = 'Bauer';

-- Linienbus – 4 Fahrzeuge
INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Linienbus 001', vt.id, '2022-03-10 07:00:00+01', 14, true,  2, 80 FROM vehicle_type vt WHERE vt.name = 'Linienbus';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Linienbus 002', vt.id, '2022-06-22 07:00:00+01', 14, true,  2, 80 FROM vehicle_type vt WHERE vt.name = 'Linienbus';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Linienbus 003', vt.id, '2023-01-05 07:00:00+01', 14, false, 2, 80 FROM vehicle_type vt WHERE vt.name = 'Linienbus';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Linienbus 004', vt.id, '2023-09-18 07:00:00+01', 14, true,  2, 80 FROM vehicle_type vt WHERE vt.name = 'Linienbus';

-- Straßenbahn – 4 Fahrzeuge
INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Straßenbahn 001', vt.id, '2021-04-12 06:30:00+01', 40, true,  4, 70 FROM vehicle_type vt WHERE vt.name = 'Straßenbahn';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Straßenbahn 002', vt.id, '2021-11-03 06:30:00+01', 40, true,  4, 70 FROM vehicle_type vt WHERE vt.name = 'Straßenbahn';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Straßenbahn 003', vt.id, '2022-07-29 06:30:00+01', 40, false, 4, 70 FROM vehicle_type vt WHERE vt.name = 'Straßenbahn';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Straßenbahn 004', vt.id, '2024-02-14 06:30:00+01', 40, true,  4, 70 FROM vehicle_type vt WHERE vt.name = 'Straßenbahn';

-- Sattelzug – 4 Fahrzeuge
INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Sattelzug 001', vt.id, '2020-08-01 05:00:00+01', 25, true,  5, 100 FROM vehicle_type vt WHERE vt.name = 'Sattelzug';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Sattelzug 002', vt.id, '2021-02-17 05:00:00+01', 25, true,  5, 100 FROM vehicle_type vt WHERE vt.name = 'Sattelzug';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Sattelzug 003', vt.id, '2022-10-30 05:00:00+01', 25, false, 5, 100 FROM vehicle_type vt WHERE vt.name = 'Sattelzug';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Sattelzug 004', vt.id, '2023-05-11 05:00:00+01', 25, true,  5, 100 FROM vehicle_type vt WHERE vt.name = 'Sattelzug';

-- Kleintransporter – 4 Fahrzeuge
INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Kleintransporter 001', vt.id, '2021-06-08 08:00:00+01', 3, true,  2, 130 FROM vehicle_type vt WHERE vt.name = 'Kleintransporter';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Kleintransporter 002', vt.id, '2022-01-25 08:00:00+01', 3, true,  2, 130 FROM vehicle_type vt WHERE vt.name = 'Kleintransporter';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Kleintransporter 003', vt.id, '2022-09-09 08:00:00+01', 3, false, 2, 130 FROM vehicle_type vt WHERE vt.name = 'Kleintransporter';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Kleintransporter 004', vt.id, '2024-03-22 08:00:00+01', 3, true,  2, 130 FROM vehicle_type vt WHERE vt.name = 'Kleintransporter';

-- Pannenhilfe – 4 Fahrzeuge
INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Pannenhilfe 001', vt.id, '2020-12-01 07:00:00+01', 5, true,  2, 120 FROM vehicle_type vt WHERE vt.name = 'Pannenhilfe';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Pannenhilfe 002', vt.id, '2021-08-14 07:00:00+01', 5, true,  2, 120 FROM vehicle_type vt WHERE vt.name = 'Pannenhilfe';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Pannenhilfe 003', vt.id, '2022-04-27 07:00:00+01', 5, false, 2, 120 FROM vehicle_type vt WHERE vt.name = 'Pannenhilfe';

INSERT INTO vehicle (name, vehicle_type, registration_date, weight, ready_for_service, number_of_axes, max_speed)
SELECT 'Pannenhilfe 004', vt.id, '2023-11-05 07:00:00+01', 5, true,  2, 120 FROM vehicle_type vt WHERE vt.name = 'Pannenhilfe';
