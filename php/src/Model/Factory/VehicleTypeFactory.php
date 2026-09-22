<?php

declare(strict_types=1);

namespace App\Model\Factory;

use App\Exception\InvalidVehicleTypeCategoryException;
use App\Model\Dto\VehicleTypeResult;
use App\Model\Entity\VehicleType;
use App\Model\Shared\VehicleTypeCategory;

class VehicleTypeFactory
{
    public function __construct(private readonly PersonFactory $personFactory)
    {
    }

    public function toResult(VehicleType $vehicleType): VehicleTypeResult
    {
        return new VehicleTypeResult(
            id: $vehicleType->getId(),
            name: $vehicleType->getName(),
            type: $vehicleType->getType(),
            personResponsible: $this->personFactory->toResult($vehicleType->getPersonResponsible()),
        );
    }

    //                TODO: Add person responsible when implemented
    public function toEntity(string $vehicleType): VehicleType
    {
        $category = self::getVehicleTypeCategory($vehicleType);

        return (new VehicleType())->setName($vehicleType)->setType($category);
    }

    private static function getVehicleTypeCategory(string $vehicleType): VehicleTypeCategory
    {
        $category = VehicleTypeCategory::tryFrom(strtoupper(trim($vehicleType)));
        if (null === $category) {
            $allowedValues = implode(', ', array_map(
                static fn (VehicleTypeCategory $category): string => $category->name,
                VehicleTypeCategory::cases(),
            ));

            throw new InvalidVehicleTypeCategoryException(
                "Invalid vehicle type category '".$vehicleType."'. Allowed values: ".$allowedValues.'.',
            );
        }

        return $category;
    }
}
