<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use App\Migration\SqlScript;
use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

final class Version000000 extends AbstractMigration
{
    public function getDescription(): string
    {
        return 'Create vehicle, person, vehicle_type tables';
    }

    public function up(Schema $schema): void
    {
        foreach (SqlScript::statements('V000_000__Create_vehicle__person__vehicle_type_tables.sql') as $sql) {
            $this->addSql($sql);
        }
    }
}
