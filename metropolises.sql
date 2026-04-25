CREATE DATABASE IF NOT EXISTS metropolis_db;
USE metropolis_db;

DROP TABLE IF EXISTS metropolises;

CREATE TABLE metropolises (
                              metropolis CHAR(64),
                              continent CHAR(64),
                              population BIGINT
);

INSERT INTO metropolises VALUES ('Mumbai', 'Asia', 20400000);
INSERT INTO metropolises VALUES ('New York', 'North America', 21295000);
INSERT INTO metropolises VALUES ('San Francisco', 'North America', 5780000);
INSERT INTO metropolises VALUES ('London', 'Europe', 8580000);
INSERT INTO metropolises VALUES ('Rome', 'Europe', 2715000);
INSERT INTO metropolises VALUES ('Melbourne', 'Australia', 3900000);
INSERT INTO metropolises VALUES ('San Jose', 'North America', 7354555);
INSERT INTO metropolises VALUES ('Rostov-on-Don', 'Europe', 1052000);