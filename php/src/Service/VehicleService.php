<?php

declare(strict_types=1);

namespace App\Service;

use App\Exception\FailedToSaveVehicleException;
use App\Exception\ResourceNotFoundException;
use App\Model\Dto\CreateVehicleDto;
use App\Model\Dto\VehicleResult;
use App\Model\Factory\VehicleFactory;
use App\Repository\VehicleRepository;
use App\Repository\VehicleTypeRepository;
use Doctrine\DBAL\Exception as DBALException;
use Doctrine\ORM\Exception\ORMException;

class VehicleService
{
    public function __construct(
        private readonly VehicleRepository $vehicleRepository,
        private readonly VehicleTypeRepository $vehicleTypeRepository,
        private readonly VehicleFactory $vehicleFactory,
    ) {
    }

    /**
     * @return list<VehicleResult>
     */
    public function getAllVehicles(): array
    {
        $vehicleEntities = $this->vehicleRepository->findAll();

        return array_map($this->vehicleFactory->toResult(...), $vehicleEntities);
    }

    public function addVehicle(CreateVehicleDto $dto): VehicleResult
    {
        $vehicleType = $this->vehicleTypeRepository->find($dto->vehicleTypeId)
            ?? throw new ResourceNotFoundException('Vehicle type with ID '.$dto->vehicleTypeId.' not found.');
        $entity = $this->vehicleFactory->toEntity($dto, $vehicleType);

        try {
            $savedEntity = $this->vehicleRepository->save($entity);

            return $this->vehicleFactory->toResult($savedEntity);
        } catch (DBALException|ORMException) {
            throw new FailedToSaveVehicleException($entity);
        }
    }

    public function deleteVehicle(int $id): VehicleResult
    {
        $vehicle = $this->vehicleRepository->find($id)
            ?? throw new ResourceNotFoundException('Vehicle with ID '.$id.' not found.');
        // Anders als Hibernate setzt Doctrine die generierte ID nach dem Loeschen auf null.
        // Damit die Antwort wie im Java-Service die ID enthaelt, wird vorher gemappt.
        $vehicleResult = $this->vehicleFactory->toResult($vehicle);
        $this->vehicleRepository->delete($vehicle);

        return $vehicleResult;
    }
}
