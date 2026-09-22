<?php

declare(strict_types=1);

namespace App\Exception;

use App\Model\Entity\Vehicle;

class FailedToSaveVehicleException extends \RuntimeException
{
    public function __construct(Vehicle $vehicle)
    {
        parent::__construct('Failed to save vehicle: '.
            $vehicle::class.'@'.spl_object_id($vehicle));
    }
}
