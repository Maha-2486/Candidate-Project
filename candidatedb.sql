CREATE DATABASE candidatedb;
USE candidatedb;
INSERT INTO candidate VALUES ('RA1000', 'Rahul',  85, 90, 78, 'PASS', 'A');
INSERT INTO candidate VALUES ('PR1001', 'Priya',  40, 35, 30, 'FAIL', 'C');
INSERT INTO candidate VALUES ('AM1002', 'Amit',   92, 88, 95, 'PASS', 'A+');
SELECT * FROM candid_seq;
SELECT * FROM candidate;
-- Create candidate table
CREATE TABLE candidate (
    id      VARCHAR(20),
    name    VARCHAR(100),
    m1      INT,
    m2      INT,
    m3      INT,
    result  VARCHAR(10),
    grade   VARCHAR(20)
);

-- Create sequence table (replaces Oracle sequence)
CREATE TABLE candid_seq (
    nextval INT
);

-- Insert starting value
INSERT INTO candid_seq VALUES(1000);
SHOW TABLES;