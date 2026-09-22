<?php

declare(strict_types=1);

namespace App\Model\Factory;

use App\Model\Dto\PersonResult;
use App\Model\Entity\Person;

class PersonFactory
{
    public function toResult(Person $personResponsible): PersonResult
    {
        return new PersonResult(
            id: $personResponsible->getId(),
            firstName: $personResponsible->getFirstName(),
            lastName: $personResponsible->getLastName(),
        );
    }
}
