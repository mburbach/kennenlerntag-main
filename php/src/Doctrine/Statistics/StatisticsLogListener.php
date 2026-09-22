<?php

declare(strict_types=1);

namespace App\Doctrine\Statistics;

use Monolog\Attribute\WithMonologChannel;
use Psr\Log\LoggerInterface;
use Symfony\Component\EventDispatcher\Attribute\AsEventListener;
use Symfony\Component\HttpKernel\Event\RequestEvent;
use Symfony\Component\HttpKernel\Event\TerminateEvent;
use Symfony\Component\HttpKernel\KernelEvents;

/**
 * Gibt pro Request die Anzahl der ausgefuehrten SQL-Statements im Log aus
 * (entspricht den "Session Metrics" von Hibernate im Java-Service).
 */
#[WithMonologChannel('doctrine')]
final class StatisticsLogListener
{
    public function __construct(
        private readonly QueryStatistics $statistics,
        private readonly LoggerInterface $logger,
    ) {
    }

    #[AsEventListener(event: KernelEvents::REQUEST, priority: 4096)]
    public function onRequest(RequestEvent $event): void
    {
        if ($event->isMainRequest()) {
            $this->statistics->clear();
        }
    }

    #[AsEventListener(event: KernelEvents::TERMINATE)]
    public function onTerminate(TerminateEvent $event): void
    {
        $request = $event->getRequest();
        $this->logger->debug('Session Metrics: {count} SQL statements executed for {method} {path}', [
            'count' => $this->statistics->getStatementCount(),
            'method' => $request->getMethod(),
            'path' => $request->getPathInfo(),
        ]);
    }
}
