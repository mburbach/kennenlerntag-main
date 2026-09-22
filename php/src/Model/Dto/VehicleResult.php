<?php

declare(strict_types=1);

namespace App\Model\Dto;

use Symfony\Component\Validator\Constraints as Assert;

final readonly class VehicleResult
{
    public function __construct(
        #[Assert\NotBlank]
        public ?int $id,

        #[Assert\NotBlank]
        public ?string $name,

        #[Assert\NotBlank]
        public ?VehicleTypeResult $vehicleType,

        #[Assert\NotBlank]
        public ?\DateTimeImmutable $registrationDate,

        public int $weight,

        public bool $readyForService,

        public int $numberOfAxes,

        public int $maxSpeed,
    ) {
    }
}
