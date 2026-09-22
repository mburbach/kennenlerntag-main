<?php

declare(strict_types=1);

namespace App\Tests\Integrationtest\Utils;

use App\Model\Dto\CreateVehicleDto;
use App\Model\Entity\Person;
use App\Model\Entity\Vehicle;
use App\Model\Entity\VehicleType;
use App\Model\Shared\VehicleTypeCategory;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\SerializerInterface;

final class TestModelUtil
{
    private const int WEIGHT = 1500;
    private const bool READY_FOR_SERVICE = true;
    private const int NUMBER_OF_AXES = 10;
    private const int MAX_SPEED = 120;
    public const int VEHICLE_TYPE = 1;

    private readonly \DateTimeImmutable $registrationDate;

    public function __construct(
        private readonly EntityManagerInterface $entityManager,
        private readonly SerializerInterface $serializer,
    ) {
        $this->registrationDate = new \DateTimeImmutable();
    }

    /**
     * @template T of object
     *
     * @param class-string<T> $class
     *
     * @return T
     */
    public function getResult(Response $result, string $class): object
    {
        return $this->serializer->deserialize($result->getContent(), $class, 'json');
    }

    /**
     * @template T of object
     *
     * @param class-string<T> $elementType
     *
     * @return list<T>
     */
    public function getResultList(Response $result, string $elementType): array
    {
        return $this->serializer->deserialize($result->getContent(), $elementType.'[]', 'json');
    }

    public function toJson(object $object): string
    {
        return $this->serializer->serialize($object, 'json');
    }

    private function createRandomVehicleName(): string
    {
        return 'Test Vehicle '.bin2hex(random_bytes(16));
    }

    private function createRandomVehicleTypeName(): string
    {
        return 'Test Vehicle Type '.bin2hex(random_bytes(16));
    }

    public function createVehicleDto(): CreateVehicleDto
    {
        $vehicleType = $this->createVehicleTypeEntity();
        $this->entityManager->persist($vehicleType);
        $this->entityManager->flush();

        return new CreateVehicleDto(
            name: $this->createRandomVehicleName(),
            vehicleTypeId: $vehicleType->getId(),
            registrationDate: $this->registrationDate,
            weight: self::WEIGHT,
            readyForService: self::READY_FOR_SERVICE,
            numberOfAxes: self::NUMBER_OF_AXES,
            maxSpeed: self::MAX_SPEED,
        );
    }

    private function createPerson(): Person
    {
        return (new Person())->setFirstName('Test')->setLastName('Person');
    }

    public function createVehicleEntity(): Vehicle
    {
        return (new Vehicle())
            ->setName($this->createRandomVehicleName())
            ->setRegistrationDate($this->registrationDate)
            ->setWeight(self::WEIGHT)
            ->setReadyForService(self::READY_FOR_SERVICE)
            ->setNumberOfAxes(self::NUMBER_OF_AXES)
            ->setMaxSpeed(self::MAX_SPEED)
            ->setVehicleType($this->createVehicleTypeEntity());
    }

    public function createVehicleTypeEntity(): VehicleType
    {
        $person = $this->createPerson();

        return (new VehicleType())
            ->setName($this->createRandomVehicleTypeName())
            ->setType(VehicleTypeCategory::CARGO)
            ->setPersonResponsible($person);
    }

    /**
     * Speichert in einer eigenen "Transaktion" und entkoppelt die Entity danach
     * vom EntityManager, wie @Transactional im Java-Test.
     */
    public function saveEntity(Vehicle $vehicle): Vehicle
    {
        $this->entityManager->persist($vehicle);
        $this->entityManager->flush();
        $this->entityManager->clear();

        return $vehicle;
    }
}
