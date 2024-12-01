DROP SCHEMA IF EXISTS "multi-wh" CASCADE;

CREATE SCHEMA "multi-wh";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Drop the Warehouse Status Enum if it exists
DROP TYPE IF EXISTS warehouse_status;
CREATE TYPE warehouse_status AS ENUM ('ACTIVE', 'DEACTIVE');

-- Drop the warehouse table if it exists
DROP TABLE IF EXISTS "warehouse".warehouses CASCADE;

CREATE TABLE "warehouse".warehouses
(
    id uuid NOT NULL,
    name character varying NOT NULL,
    warehouse_status warehouse_status NOT NULL,
    failure_messages character varying,
    CONSTRAINT warehouses_pkey PRIMARY KEY (id)
);

-- Drop the warehouse_location table if it exists
DROP TABLE IF EXISTS "warehouse".warehouse_location CASCADE;

CREATE TABLE "warehouse".warehouse_location
(
    id uuid NOT NULL,
    warehouse_id uuid NOT NULL,
    latitude float NOT NULL,
    longitude float NOT NULL,
    address character varying NOT NULL,
    city character varying NOT NULL,
    province character varying NOT NULL,
    district character varying NOT NULL,
    postal_code character varying NOT NULL,
    CONSTRAINT warehouse_location_pkey PRIMARY KEY (id),
    CONSTRAINT fk_warehouse_id FOREIGN KEY (warehouse_id)
        REFERENCES "warehouse".warehouses (id) ON DELETE CASCADE
);


