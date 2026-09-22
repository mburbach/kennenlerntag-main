FROM docker.io/library/php:8.5-cli

# Erweiterungen fuer PostgreSQL (Doctrine DBAL) und Symfony (intl, zip fuer Composer)
RUN apt-get update \
    && apt-get install -y --no-install-recommends libpq-dev libicu-dev libzip-dev unzip git \
    && docker-php-ext-install pdo_pgsql intl zip \
    && rm -rf /var/lib/apt/lists/*

COPY --from=docker.io/library/composer:latest /usr/bin/composer /usr/bin/composer

WORKDIR /app

COPY docker/entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]
# Der eingebaute PHP-Webserver genuegt fuer die lokale Entwicklung (wie "spring-boot:run").
CMD ["php", "-S", "0.0.0.0:8080", "-t", "public"]
