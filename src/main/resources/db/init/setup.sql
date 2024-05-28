SET search_path = filters;

CREATE DATABASE filters_db
    WITH ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8';

ALTER DATABASE filters_db OWNER TO db_owner;