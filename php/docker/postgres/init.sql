-- Entspricht "?currentSchema=vehicle_manager" aus der Spring-Konfiguration:
-- Die Tabellen liegen im Schema vehicle_manager, das per search_path voreingestellt ist.
-- Die Test-Datenbank (Suffix _test, siehe config/packages/doctrine.yaml) wird gleich mit angelegt.
CREATE DATABASE vehicle_manager_test;

\connect vehicle_manager
CREATE SCHEMA IF NOT EXISTS vehicle_manager;
ALTER DATABASE vehicle_manager SET search_path TO vehicle_manager;

\connect vehicle_manager_test
CREATE SCHEMA IF NOT EXISTS vehicle_manager;
ALTER DATABASE vehicle_manager_test SET search_path TO vehicle_manager;
