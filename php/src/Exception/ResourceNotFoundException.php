<?php

declare(strict_types=1);

namespace App\Exception;

class ResourceNotFoundException extends \RuntimeException
{
    // TODO: Ist das optimal? Exception Konzept in Ruhe durchdenken.
    public function __construct(string $message)
    {
        parent::__construct($message);
    }
}
