<?php

declare(strict_types=1);

namespace App\Migration;

/**
 * Liest die SQL-Skripte unter migrations/sql (1:1 aus den Flyway-Migrationen uebernommen)
 * und zerlegt sie in einzelne Statements, da PDO pro Aufruf nur ein Statement ausfuehrt.
 */
final class SqlScript
{
    /**
     * @return list<string>
     */
    public static function statements(string $fileName): array
    {
        $path = \dirname(__DIR__, 2).'/migrations/sql/'.$fileName;
        $content = file_get_contents($path);
        if (false === $content) {
            throw new \RuntimeException(\sprintf('SQL script "%s" could not be read.', $path));
        }

        $statements = [];
        foreach (preg_split('/;\s*$/m', $content) as $chunk) {
            $withoutComments = trim(preg_replace('/^\s*--.*$/m', '', $chunk));
            if ('' !== $withoutComments) {
                $statements[] = trim($chunk);
            }
        }

        return $statements;
    }
}
