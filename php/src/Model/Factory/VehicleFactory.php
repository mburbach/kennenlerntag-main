<?php

declare(strict_types=1);

namespace App\Model\Factory;

use App\Model\Dto\CreateVehicleDto;
use App\Model\Dto\VehicleResult;
use App\Model\Entity\Vehicle;
use App\Model\Entity\VehicleType;

class VehicleFactory
{
    public function __construct(private readonly VehicleTypeFactory $vehicleTypeFactory)
    {
    }

    public function toResult(Vehicle $entity): VehicleResult
    {
        return new VehicleResult(
            id: $entity->getId(),
            name: $entity->getName(),
            vehicleType: $this->vehicleTypeFactory->toResult($entity->getVehicleType()),
            registrationDate: $entity->getRegistrationDate(),
            weight: $entity->getWeight(),
            readyForService: $entity->isReadyForService(),
            numberOfAxes: $entity->getNumberOfAxes(),
            maxSpeed: $entity->getMaxSpeed(),
        );
    }

    public function toEntity(CreateVehicleDto $dto, VehicleType $vehicleType): Vehicle
    {
        $entity = new Vehicle();
        $entity->setName($dto->name);
        $entity->setVehicleType($vehicleType);
        $entity->setRegistrationDate($dto->registrationDate);
        $entity->setWeight($dto->weight);
        $entity->setReadyForService($dto->readyForService);
        $entity->setNumberOfAxes($dto->numberOfAxes);
        $entity->setMaxSpeed($dto->maxSpeed);

        return $entity;
    }
}
