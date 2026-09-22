<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use App\Migration\SqlScript;
use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

final class Version000001 extends AbstractMigration
{
    public function getDescription(): string
    {
        return 'Insert testdata';
    }

    public function up(Schema $schema): void
    {
        foreach (SqlScript::statements('V000_001__Insert_testdata.sql') as $sql) {
            $this->addSql($sql);
        }
    }
}
