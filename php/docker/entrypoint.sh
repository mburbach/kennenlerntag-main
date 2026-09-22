#!/bin/sh
set -e

# Abhaengigkeiten installieren, falls vendor/ noch fehlt (Quellcode ist als Volume gemountet)
if [ ! -f vendor/autoload_runtime.php ]; then
    composer install --no-interaction --prefer-dist
fi

# Entspricht Flyway beim Start der Spring-Boot-Anwendung:
# Migrationen ausfuehren, danach afterMigrate.sql (siehe AfterMigrateListener).
if [ "${RUN_MIGRATIONS:-1}" = "1" ]; then
    php bin/console doctrine:migrations:migrate --no-interaction --allow-no-migration
fi

exec "$@"
