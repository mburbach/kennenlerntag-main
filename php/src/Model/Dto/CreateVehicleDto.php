<?php

declare(strict_types=1);

namespace App\Model\Dto;

use Symfony\Component\Validator\Constraints as Assert;

final readonly class CreateVehicleDto
{
    public function __construct(
        #[Assert\NotBlank]
        public ?string $name = null,

        #[Assert\NotNull]
        public ?int $vehicleTypeId = null,

        #[Assert\NotNull]
        public ?\DateTimeImmutable $registrationDate = null,

        #[Assert\Positive]
        public int $weight = 0,

        public bool $readyForService = false,

        #[Assert\Positive]
        public int $numberOfAxes = 0,

        #[Assert\Positive]
        public int $maxSpeed = 0,
    ) {
    }
}
