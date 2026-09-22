<?php

declare(strict_types=1);

namespace App\EventListener;

use App\Migration\SqlScript;
use Doctrine\DBAL\Connection;
use Symfony\Component\Console\ConsoleEvents;
use Symfony\Component\Console\Command\Command;
use Symfony\Component\Console\Event\ConsoleTerminateEvent;
use Symfony\Component\EventDispatcher\Attribute\AsEventListener;

/**
 * Entspricht dem Flyway-Callback afterMigrate.sql: wird nach jedem erfolgreichen
 * "doctrine:migrations:migrate" ausgefuehrt, auch wenn keine Migration offen war.
 */
#[AsEventListener(event: ConsoleEvents::TERMINATE)]
final class AfterMigrateListener
{
    public function __construct(private readonly Connection $connection)
    {
    }

    public function __invoke(ConsoleTerminateEvent $event): void
    {
        if ('doctrine:migrations:migrate' !== $event->getCommand()?->getName()
            || Command::SUCCESS !== $event->getExitCode()
            || $event->getInput()->hasOption('dry-run') && $event->getInput()->getOption('dry-run')) {
            return;
        }

        foreach (SqlScript::statements('afterMigrate.sql') as $sql) {
            $this->connection->executeStatement($sql);
        }
    }
}
