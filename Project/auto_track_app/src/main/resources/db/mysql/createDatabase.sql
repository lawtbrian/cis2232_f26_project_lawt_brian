# For localhost
DROP DATABASE IF EXISTS cis2232_auto_track;
CREATE DATABASE cis2232_auto_track;
USE cis2232_auto_track;

-- ------------------------------------------------------------------------------
-- Auto Track - Vehicle Cost & Maintenance Manager
-- This table stores vehicle information used to evaluate the estimated
-- overall cost of purchasing a used vehicle.
-- ------------------------------------------------------------------------------

CREATE TABLE vehicle (
                         vehicleId              INT             NOT NULL AUTO_INCREMENT,
                         make                   VARCHAR(100)    NOT NULL,
                         model                  VARCHAR(100)    NOT NULL,
                         year                   INT             NOT NULL,
                         mileage                DOUBLE          NOT NULL,
                         askingPrice            DOUBLE          NOT NULL,
                         marketValue            DOUBLE          NOT NULL,
                         accidentClaimAmount    DOUBLE          NOT NULL DEFAULT 0,
                         estimatedRepairs       DOUBLE          NOT NULL DEFAULT 0,
                         ownershipCount         INT             NOT NULL,
                         inspectionStatus       BOOLEAN         NOT NULL,
                         otherCosts             DOUBLE          NOT NULL DEFAULT 0,
                         PRIMARY KEY (vehicleId)
);

INSERT INTO vehicle
(make, model, year, mileage, askingPrice, marketValue,
 accidentClaimAmount, estimatedRepairs, ownershipCount,
 inspectionStatus, otherCosts)
VALUES
    ('Toyota', 'Corolla', 2020, 85000, 12500, 15000, 0, 800, 2, TRUE, 500),
    ('Honda', 'Civic', 2019, 92000, 11800, 14000, 2500, 600, 2, TRUE, 400),
    ('Ford', 'Escape', 2021, 67000, 16500, 18500, 0, 300, 1, TRUE, 450);