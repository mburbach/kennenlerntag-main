<?php

declare(strict_types=1);

namespace App\Doctrine\Statistics;

use Doctrine\Bundle\DoctrineBundle\Attribute\AsMiddleware;
use Doctrine\DBAL\Driver;
use Doctrine\DBAL\Driver\Connection;
use Doctrine\DBAL\Driver\Middleware;
use Doctrine\DBAL\Driver\Middleware\AbstractConnectionMiddleware;
use Doctrine\DBAL\Driver\Middleware\AbstractDriverMiddleware;
use Doctrine\DBAL\Driver\Middleware\AbstractStatementMiddleware;
use Doctrine\DBAL\Driver\Result;
use Doctrine\DBAL\Driver\Statement;

#[AsMiddleware]
final class StatisticsMiddleware implements Middleware
{
    public function __construct(private readonly QueryStatistics $statistics)
    {
    }

    public function wrap(Driver $driver): Driver
    {
        $statistics = $this->statistics;

        return new class($driver, $statistics) extends AbstractDriverMiddleware {
            public function __construct(Driver $driver, private readonly QueryStatistics $statistics)
            {
                parent::__construct($driver);
            }

            public function connect(array $params): Connection
            {
                $statistics = $this->statistics;

                return new class(parent::connect($params), $statistics) extends AbstractConnectionMiddleware {
                    public function __construct(Connection $connection, private readonly QueryStatistics $statistics)
                    {
                        parent::__construct($connection);
                    }

                    public function prepare(string $sql): Statement
                    {
                        $statistics = $this->statistics;

                        return new class(parent::prepare($sql), $statistics) extends AbstractStatementMiddleware {
                            public function __construct(Statement $statement, private readonly QueryStatistics $statistics)
                            {
                                parent::__construct($statement);
                            }

                            public function execute(): Result
                            {
                                $this->statistics->increment();

                                return parent::execute();
                            }
                        };
                    }

                    public function query(string $sql): Result
                    {
                        $this->statistics->increment();

                        return parent::query($sql);
                    }

                    public function exec(string $sql): int|string
                    {
                        $this->statistics->increment();

                        return parent::exec($sql);
                    }
                };
            }
        };
    }
}
