<?php

declare(strict_types=1);

namespace App\Tests\Integrationtest;

use App\Model\Dto\VehicleResult;
use App\Model\Entity\Vehicle;
use App\Repository\VehicleRepository;
use App\Tests\Integrationtest\Utils\TestModelUtil;
use Doctrine\DBAL\Connection;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\KernelBrowser;
use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;
use Symfony\Component\Serializer\SerializerInterface;

/**
 * Voraussetzung: Die Test-Datenbank ist migriert ("composer test" erledigt das).
 */
final class VehicleControllerIntegrationTest extends WebTestCase
{
    // TODO: Int Tests in extra Package, um Ausführung von Unit- und Int. Tests zu trennen für z.B.
    // SonarQube
    private const string VEHICLE_NAME1 = 'Vehicle Name 1';
    private const string VEHICLE_NAME2 = 'Vehicle Name 2';

    private KernelBrowser $mockMvc;

    private VehicleRepository $vehicleRepository;

    private TestModelUtil $testModelUtil;

    protected function setUp(): void
    {
        $this->mockMvc = static::createClient();
        $container = static::getContainer();

        $this->vehicleRepository = $container->get(VehicleRepository::class);
        $this->testModelUtil = new TestModelUtil(
            $container->get(EntityManagerInterface::class),
            $container->get(SerializerInterface::class),
        );

        $this->cleanup();
    }

    protected function tearDown(): void
    {
        $this->cleanup();

        parent::tearDown();
    }

    private function cleanup(): void
    {
        static::getContainer()->get(Connection::class)
            ->executeStatement(file_get_contents(\dirname(__DIR__).'/resources/sql/cleanup.sql'));
    }

    public function testShouldSaveVehicle(): void
    {
        //        given
        $vehicle = $this->testModelUtil->createVehicleDto();

        //        when
        $this->mockMvc->request(
            'POST',
            '/vehicle',
            server: ['CONTENT_TYPE' => 'application/json'],
            content: $this->testModelUtil->toJson($vehicle),
        );
        self::assertResponseIsSuccessful();
        $result = $this->mockMvc->getResponse();

        self::assertNotNull($result);

        $vehicleResult = $this->testModelUtil->getResult($result, VehicleResult::class);

        //        then
        // Check if the response contains the expected vehicle details
        self::assertNotNull($vehicleResult);
        self::assertSame($vehicle->name, $vehicleResult->name);
        self::assertSame(TestModelUtil::VEHICLE_TYPE, $vehicleResult->vehicleType->id);
        self::assertSame(self::truncatedToMinutes($vehicle->registrationDate), self::truncatedToMinutes($vehicleResult->registrationDate));
        self::assertSame($vehicle->weight, $vehicleResult->weight);
        self::assertSame($vehicle->readyForService, $vehicleResult->readyForService);
        self::assertSame($vehicle->numberOfAxes, $vehicleResult->numberOfAxes);
        self::assertSame($vehicle->maxSpeed, $vehicleResult->maxSpeed);

        // Check if the vehicle was saved correctly
        $vehicleEntity = $this->vehicleRepository->find($vehicleResult->id);
        self::assertInstanceOf(Vehicle::class, $vehicleEntity);

        self::assertSame($vehicle->name, $vehicleEntity->getName());
        self::assertSame(self::truncatedToMinutes($vehicle->registrationDate), self::truncatedToMinutes($vehicleEntity->getRegistrationDate()));
        self::assertSame($vehicle->weight, $vehicleEntity->getWeight());
        self::assertSame($vehicle->readyForService, $vehicleEntity->isReadyForService());
        self::assertSame($vehicle->numberOfAxes, $vehicleEntity->getNumberOfAxes());
        self::assertSame($vehicle->maxSpeed, $vehicleEntity->getMaxSpeed());
    }

    public function testShouldGetAllVehicle(): void
    {
        $vehicle1 = $this->testModelUtil->createVehicleEntity();
        $vehicle2 = $this->testModelUtil->createVehicleEntity();

        $vehicle1->setName(self::VEHICLE_NAME1);
        $vehicle2->setName(self::VEHICLE_NAME2);

        $vehicle1 = $this->testModelUtil->saveEntity($vehicle1);
        $vehicle2 = $this->testModelUtil->saveEntity($vehicle2);

        //        when
        $this->mockMvc->request('GET', '/vehicles');
        self::assertResponseIsSuccessful();

        $vehicleResults = $this->testModelUtil->getResultList($this->mockMvc->getResponse(), VehicleResult::class);

        //        then
        self::assertNotNull($vehicleResults);
        self::assertCount(2, $vehicleResults);

        $actual = array_map(
            static fn (VehicleResult $resultVehicle): array => [$resultVehicle->name, $resultVehicle->vehicleType->id],
            $vehicleResults,
        );
        $expected = [
            [$vehicle1->getName(), $vehicle1->getVehicleType()->getId()],
            [$vehicle2->getName(), $vehicle2->getVehicleType()->getId()],
        ];
        sort($actual);
        sort($expected);
        self::assertSame($expected, $actual);
    }

    private static function truncatedToMinutes(\DateTimeInterface $dateTime): int
    {
        return intdiv($dateTime->getTimestamp(), 60);
    }
}
