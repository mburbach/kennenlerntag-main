<?php

declare(strict_types=1);

namespace App\Tests\Model\Dto;

use App\Model\Dto\CreateVehicleDto;
use PHPUnit\Framework\Attributes\BeforeClass;
use PHPUnit\Framework\TestCase;
use Symfony\Component\Validator\ConstraintViolationInterface;
use Symfony\Component\Validator\ConstraintViolationListInterface;
use Symfony\Component\Validator\Validation;
use Symfony\Component\Validator\Validator\ValidatorInterface;

final class CreateVehicleRequestTest extends TestCase
{
    private static ValidatorInterface $validator;

    #[BeforeClass]
    public static function setUpValidator(): void
    {
        self::$validator = Validation::createValidatorBuilder()->enableAttributeMapping()->getValidator();
    }

    public function testWhenNameIsEmptyThenViolation(): void
    {
        $dto = new CreateVehicleDto(
            name: '',
            vehicleTypeId: 1,
            registrationDate: new \DateTimeImmutable(),
            weight: 1000,
            numberOfAxes: 2,
            maxSpeed: 180,
        );

        $violations = self::$validator->validate($dto);
        self::assertTrue(self::hasViolationFor($violations, 'name'));
    }

    public function testWhenNameIsNullThenViolation(): void
    {
        $dto = new CreateVehicleDto(
            name: null,
            vehicleTypeId: 1,
            registrationDate: new \DateTimeImmutable(),
            weight: 1000,
            numberOfAxes: 2,
            maxSpeed: 180,
        );

        $violations = self::$validator->validate($dto);
        self::assertTrue(self::hasViolationFor($violations, 'name'));
    }

    public function testWhenVehicleTypeIsNullThenViolation(): void
    {
        $dto = new CreateVehicleDto(
            name: 'Testfahrzeug',
            vehicleTypeId: null,
            registrationDate: new \DateTimeImmutable(),
            weight: 1000,
            numberOfAxes: 2,
            maxSpeed: 180,
        );

        $violations = self::$validator->validate($dto);
        self::assertTrue(self::hasViolationFor($violations, 'vehicleTypeId'));
    }

    public function testWhenRegistrationDateIsNullThenViolation(): void
    {
        $dto = new CreateVehicleDto(
            name: 'Testfahrzeug',
            vehicleTypeId: 1,
            registrationDate: null,
            weight: 1000,
            numberOfAxes: 2,
            maxSpeed: 180,
        );

        $violations = self::$validator->validate($dto);
        self::assertTrue(self::hasViolationFor($violations, 'registrationDate'));
    }

    public function testWhenWeightIsZeroThenViolation(): void
    {
        $dto = new CreateVehicleDto(
            name: 'Testfahrzeug',
            vehicleTypeId: 1,
            registrationDate: new \DateTimeImmutable(),
            weight: 0,
            numberOfAxes: 2,
            maxSpeed: 180,
        );

        $violations = self::$validator->validate($dto);
        self::assertTrue(self::hasViolationFor($violations, 'weight'));
    }

    public function testWhenWeightIsNegativeThenViolation(): void
    {
        $dto = new CreateVehicleDto(
            name: 'Testfahrzeug',
            vehicleTypeId: 1,
            registrationDate: new \DateTimeImmutable(),
            weight: -1,
            numberOfAxes: 2,
            maxSpeed: 180,
        );

        $violations = self::$validator->validate($dto);
        self::assertTrue(self::hasViolationFor($violations, 'weight'));
    }

    public function testWhenNumberOfAxesIsZeroThenViolation(): void
    {
        $dto = new CreateVehicleDto(
            name: 'Testfahrzeug',
            vehicleTypeId: 1,
            registrationDate: new \DateTimeImmutable(),
            weight: 1000,
            numberOfAxes: 0,
            maxSpeed: 180,
        );

        $violations = self::$validator->validate($dto);
        self::assertTrue(self::hasViolationFor($violations, 'numberOfAxes'));
    }

    public function testWhenMaxSpeedIsZeroThenViolation(): void
    {
        $dto = new CreateVehicleDto(
            name: 'Testfahrzeug',
            vehicleTypeId: 1,
            registrationDate: new \DateTimeImmutable(),
            weight: 1000,
            numberOfAxes: 2,
            maxSpeed: 0,
        );

        $violations = self::$validator->validate($dto);
        self::assertTrue(self::hasViolationFor($violations, 'maxSpeed'));
    }

    public function testWhenReadyForServiceNotSetThenDefaultIsFalse(): void
    {
        $dto = new CreateVehicleDto(
            name: 'Testfahrzeug',
            vehicleTypeId: 1,
            registrationDate: new \DateTimeImmutable(),
            weight: 1000,
            numberOfAxes: 2,
            maxSpeed: 180,
        );

        self::assertFalse($dto->readyForService);
    }

    public function testWhenAllFieldsValidThenNoViolations(): void
    {
        $dto = new CreateVehicleDto(
            name: 'Testfahrzeug',
            vehicleTypeId: 1,
            registrationDate: new \DateTimeImmutable(),
            weight: 1200,
            readyForService: true,
            numberOfAxes: 2,
            maxSpeed: 180,
        );

        $violations = self::$validator->validate($dto);
        self::assertCount(0, $violations);
    }

    private static function hasViolationFor(ConstraintViolationListInterface $violations, string $propertyPath): bool
    {
        foreach ($violations as $violation) {
            \assert($violation instanceof ConstraintViolationInterface);
            if ($propertyPath === $violation->getPropertyPath()) {
                return true;
            }
        }

        return false;
    }
}
