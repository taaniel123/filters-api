-------------------------------------------------------------------------------
-- ROLES
-------------------------------------------------------------------------------
DO
$do$
    BEGIN
        IF NOT EXISTS (SELECT
                       FROM pg_roles
                       WHERE rolname = 'db_owner')
        THEN
            CREATE ROLE db_owner NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
        END IF;
    END
$do$;
-------------------------------------------------------------------------------
-- SCHEMA
-------------------------------------------------------------------------------
CREATE SCHEMA IF NOT EXISTS filters;

ALTER SCHEMA filters
    OWNER TO db_owner;

REVOKE ALL ON SCHEMA filters FROM PUBLIC;
GRANT USAGE ON SCHEMA filters TO db_owner;