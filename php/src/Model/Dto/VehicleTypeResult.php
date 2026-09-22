<?php

declare(strict_types=1);

namespace App\Model\Dto;

use App\Model\Shared\VehicleTypeCategory;
use Symfony\Component\Validator\Constraints as Assert;

final readonly class VehicleTypeResult
{
    public function __construct(
        #[Assert\NotBlank]
        public ?int $id,

        #[Assert\NotBlank]
        public ?string $name,

        #[Assert\NotBlank]
        public ?VehicleTypeCategory $type,

        #[Assert\NotBlank]
        public ?PersonResult $personResponsible,
    ) {
    }
}
