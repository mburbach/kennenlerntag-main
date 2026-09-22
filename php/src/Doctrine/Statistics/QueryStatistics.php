<?php

declare(strict_types=1);

namespace App\Doctrine\Statistics;

/**
 * Zaehlt die an die Datenbank gesendeten Statements
 * (entspricht Hibernate Statistics#getPrepareStatementCount).
 */
final class QueryStatistics
{
    private int $statementCount = 0;

    public function increment(): void
    {
        ++$this->statementCount;
    }

    public function getStatementCount(): int
    {
        return $this->statementCount;
    }

    public function clear(): void
    {
        $this->statementCount = 0;
    }
}
