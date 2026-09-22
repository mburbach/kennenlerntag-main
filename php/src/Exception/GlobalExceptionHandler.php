<?php

declare(strict_types=1);

namespace App\Exception;

use Symfony\Component\EventDispatcher\Attribute\AsEventListener;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpKernel\Event\ExceptionEvent;
use Symfony\Component\HttpKernel\Exception\HttpExceptionInterface;
use Symfony\Component\Validator\Exception\ValidationFailedException;

#[AsEventListener]
final class GlobalExceptionHandler
{
    // TODO: Prüfen, ob alle Exceptions sinnvoll gefangen sind.

    public function __invoke(ExceptionEvent $event): void
    {
        $ex = $event->getThrowable();

        $response = match (true) {
            $ex instanceof FailedToSaveVehicleException => $this->handleFailedToSaveVehicle($ex),
            $ex instanceof HttpExceptionInterface
                && $ex->getPrevious() instanceof ValidationFailedException => $this->handleValidation($ex->getPrevious()),
            $ex instanceof InvalidVehicleTypeCategoryException => $this->handleInvalidVehicleTypeCategory($ex),
            $ex instanceof ResourceNotFoundException => $this->handleResourceNotFound($ex),
            default => null,
        };

        if (null !== $response) {
            $event->setResponse($response);
        }
    }

    private function handleFailedToSaveVehicle(FailedToSaveVehicleException $ex): Response
    {
        //        TODO: Bessere Fehlermeldung und Status Code optimieren
        return new Response($ex->getMessage(), Response::HTTP_CONFLICT);
    }

    // TODO: ex.getMessage ausgeben ist für das Frontend nicht optimal
    private function handleValidation(ValidationFailedException $ex): Response
    {
        return new Response('Validation failed: '.$ex->getMessage(), Response::HTTP_BAD_REQUEST);
    }

    private function handleInvalidVehicleTypeCategory(InvalidVehicleTypeCategoryException $ex): Response
    {
        return new Response($ex->getMessage(), Response::HTTP_BAD_REQUEST);
    }

    private function handleResourceNotFound(ResourceNotFoundException $ex): Response
    {
        return new Response($ex->getMessage(), Response::HTTP_NOT_FOUND);
    }
}
