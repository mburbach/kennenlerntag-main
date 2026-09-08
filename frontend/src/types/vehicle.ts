// Entspricht den DTOs des Vehicle Manager Service
// (VehicleResult, VehicleTypeResult, PersonResult).

export type VehicleTypeCategory = 'PUBLIC_TRANSPORT' | 'CARGO' | 'SERVICE';

export interface Person {
  id: number;
  firstName: string;
  lastName: string;
}

export interface VehicleType {
  id: number;
  name: string;
  type: VehicleTypeCategory;
  personResponsible: Person;
}

export interface Vehicle {
  id: number;
  name: string;
  vehicleType: VehicleType;
  registrationDate: string;
  weight: number;
  readyForService: boolean;
  numberOfAxes: number;
  maxSpeed: number;
}
