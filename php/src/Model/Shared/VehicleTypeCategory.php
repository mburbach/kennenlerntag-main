<?php

declare(strict_types=1);

namespace App\Model\Shared;

enum VehicleTypeCategory: string
{
    case PUBLIC_TRANSPORT = 'PUBLIC_TRANSPORT';
    case CARGO = 'CARGO';
    case SERVICE = 'SERVICE';
}
