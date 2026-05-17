-- liquibase formatted sql
-- changeset danila:20260517.add_database_general_information.sql

CREATE TABLE IF NOT EXISTS manufacturer (
                                             id          BIGSERIAL PRIMARY KEY,
                                             name        VARCHAR(100) NOT NULL UNIQUE,
                                             created_at  TIMESTAMPTZ DEFAULT NOW(),
                                             updated_at  TIMESTAMPTZ DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_manufacturers_name ON manufacturer(name);

CREATE TYPE product_type AS ENUM ('PERSONAL_COMPUTER', 'LAPTOP', 'MONITOR', 'HARD_DRIVE');

CREATE TABLE IF NOT EXISTS product (
                                        id                  BIGSERIAL PRIMARY KEY,
                                        serial_number       VARCHAR(50) NOT NULL UNIQUE,
                                        manufacturer_id     BIGINT NOT NULL REFERENCES manufacturer(id) ON DELETE RESTRICT,
                                        product_type        product_type NOT NULL,
                                        price               NUMERIC(12,2) NOT NULL CHECK (price >= 0),
                                        stock_quantity      INTEGER NOT NULL DEFAULT 0 CHECK (stock_quantity >= 0),
                                        created_at          TIMESTAMPTZ DEFAULT NOW(),
                                        updated_at          TIMESTAMPTZ DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_products_manufacturer_id ON product(manufacturer_id);
CREATE INDEX IF NOT EXISTS idx_products_product_type ON product(product_type);
CREATE INDEX IF NOT EXISTS idx_products_serial_number ON product(serial_number);

CREATE TABLE IF NOT EXISTS personal_computer (
                                                 product_id      BIGINT PRIMARY KEY REFERENCES product(id) ON DELETE CASCADE,
                                                 form_factor     VARCHAR(20) NOT NULL CHECK (form_factor IN ('DESKTOP', 'NETTOP', 'ALL-IN-ONE'))
);

CREATE TABLE IF NOT EXISTS laptop (
                                       product_id      BIGINT PRIMARY KEY REFERENCES product(id) ON DELETE CASCADE,
                                       screen_size     INTEGER NOT NULL CHECK (screen_size IN (13, 14, 15, 17))
);

CREATE TABLE IF NOT EXISTS monitor (
                                        product_id      BIGINT PRIMARY KEY REFERENCES product(id) ON DELETE CASCADE,
                                        diagonal        NUMERIC(5,1) NOT NULL CHECK (diagonal > 0)
);

CREATE TABLE IF NOT EXISTS hard_drive (
                                           product_id      BIGINT PRIMARY KEY REFERENCES product(id) ON DELETE CASCADE,
                                           capacity_gb     NUMERIC(5,1) NOT NULL CHECK (capacity_gb > 0)
);

CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS
'
    BEGIN
        NEW.updated_at = NOW();
        RETURN NEW;
    END;
'
    LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_manufacturers_updated_at ON manufacturer;
CREATE TRIGGER trg_manufacturers_updated_at
    BEFORE UPDATE ON manufacturer
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

DROP TRIGGER IF EXISTS trg_products_updated_at ON product;
CREATE TRIGGER trg_products_updated_at
    BEFORE UPDATE ON product
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
