<?php

declare(strict_types=1);

namespace App\Model\Dto;

use Symfony\Component\Validator\Constraints as Assert;

final readonly class PersonResult
{
    public function __construct(
        #[Assert\NotBlank]
        public ?int $id,

        #[Assert\NotBlank]
        public ?string $firstName,

        #[Assert\NotBlank]
        public ?string $lastName,
    ) {
    }
}
