<?php

declare(strict_types=1);

namespace App\Tests\Model\Factory;

use App\Exception\InvalidVehicleTypeCategoryException;
use App\Model\Factory\PersonFactory;
use App\Model\Factory\VehicleTypeFactory;
use App\Model\Shared\VehicleTypeCategory;
use PHPUnit\Framework\TestCase;

final class VehicleTypeFactoryTest extends TestCase
{
    private VehicleTypeFactory $vehicleTypeFactory;

    protected function setUp(): void
    {
        $this->vehicleTypeFactory = new VehicleTypeFactory(self::createStub(PersonFactory::class));
    }

    public function testToEntityShouldMapValidEnumValue(): void
    {
        $result = $this->vehicleTypeFactory->toEntity('CARGO');

        self::assertSame('CARGO', $result->getName());
        self::assertSame(VehicleTypeCategory::CARGO, $result->getType());
    }

    public function testToEntityShouldMapTrimmedCaseInsensitiveValue(): void
    {
        $result = $this->vehicleTypeFactory->toEntity('  service  ');

        self::assertSame('  service  ', $result->getName());
        self::assertSame(VehicleTypeCategory::SERVICE, $result->getType());
    }

    public function testToEntityShouldThrowExceptionForInvalidEnumValue(): void
    {
        try {
            $this->vehicleTypeFactory->toEntity('plane');
            self::fail('Expected InvalidVehicleTypeCategoryException');
        } catch (InvalidVehicleTypeCategoryException $exception) {
            self::assertStringContainsString("Invalid vehicle type category 'plane'", $exception->getMessage());
            self::assertStringContainsString('PUBLIC_TRANSPORT', $exception->getMessage());
            self::assertStringContainsString('CARGO', $exception->getMessage());
            self::assertStringContainsString('SERVICE', $exception->getMessage());
        }
    }
}
