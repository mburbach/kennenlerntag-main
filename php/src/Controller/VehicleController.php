<?php

declare(strict_types=1);

namespace App\Controller;

use App\Model\Dto\CreateVehicleDto;
use App\Model\Dto\VehicleResult;
use App\Service\VehicleService;
use Nelmio\ApiDocBundle\Attribute\Model;
use OpenApi\Attributes as OA;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpKernel\Attribute\MapRequestPayload;
use Symfony\Component\Routing\Attribute\Route;

#[OA\Tag(name: 'vehicle')]
class VehicleController extends AbstractController
{
    public const VEHICLE_DTO_EXAMPLE = [
        'name' => 'City Bus 42',
        'vehicleTypeId' => 1,
        'registrationDate' => '2026-04-20T10:15:30+02:00',
        'weight' => 1500,
        'readyForService' => true,
        'numberOfAxes' => 2,
        'maxSpeed' => 120,
    ];

    public function __construct(private readonly VehicleService $vehicleService)
    {
    }

    // TODO: https://github.com/tkaczmarzyk/specification-arg-resolver/tree/master einführen (bzw. PHP-Pendant)
    #[Route('/vehicle', name: 'vehicle_add', methods: ['POST'])]
    #[OA\Post(
        summary: 'Add a vehicle',
        description: 'This endpoint adds a new vehicle. Check vehicle_type table for generated testdata. One type was generated on start up.',
        requestBody: new OA\RequestBody(
            description: 'Vehicle to create',
            required: true,
            content: new OA\JsonContent(ref: new Model(type: CreateVehicleDto::class), example: self::VEHICLE_DTO_EXAMPLE),
        ),
    )]
    #[OA\Response(response: 200, description: 'Vehicle successfully created', content: new OA\JsonContent(ref: new Model(type: VehicleResult::class)))]
    #[OA\Response(response: 400, description: 'Validation error')]
    #[OA\Response(response: 404, description: 'Vehicle type not found')]
    #[OA\Response(response: 409, description: 'Vehicle could not be saved')]
    public function addVehicle(#[MapRequestPayload] CreateVehicleDto $dto): JsonResponse
    {
        $vehicleResult = $this->vehicleService->addVehicle($dto);

        return $this->json($vehicleResult);
    }

    #[Route('/vehicle/{id}', name: 'vehicle_delete', requirements: ['id' => '\d+'], methods: ['DELETE'])]
    #[OA\Delete(summary: 'Delete vehicle', description: 'This endpoint deletes a vehicle by id.')]
    #[OA\Parameter(name: 'id', description: 'ID of the vehicle to delete', in: 'path', example: 3)]
    #[OA\Response(response: 200, description: 'Vehicle successfully deleted', content: new OA\JsonContent(ref: new Model(type: VehicleResult::class)))]
    #[OA\Response(response: 404, description: 'Vehicle not found')]
    public function deleteVehicle(int $id): JsonResponse
    {
        $vehicleResult = $this->vehicleService->deleteVehicle($id);

        return $this->json($vehicleResult);
    }

    #[Route('/vehicles', name: 'vehicle_list', methods: ['GET'])]
    #[OA\Get(summary: 'Get all vehicles', description: 'This endpoint returns all vehicles.')]
    #[OA\Response(
        response: 200,
        description: 'List of vehicles',
        content: new OA\JsonContent(type: 'array', items: new OA\Items(ref: new Model(type: VehicleResult::class))),
    )]
    public function getAllVehicle(): JsonResponse
    {
        $vehicles = $this->vehicleService->getAllVehicles();

        return $this->json($vehicles);
    }
}
