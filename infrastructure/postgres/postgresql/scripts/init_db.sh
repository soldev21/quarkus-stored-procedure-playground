#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER bingouser;
    ALTER USER bingouser WITH PASSWORD '123456';
    CREATE DATABASE bingo;
    GRANT ALL PRIVILEGES ON DATABASE bingo TO bingouser;
EOSQL

psql -v ON_ERROR_STOP=1 --username "bingouser" --dbname "bingo" <<-EOSQL
    CREATE SCHEMA bingo AUTHORIZATION bingouser;
EOSQL