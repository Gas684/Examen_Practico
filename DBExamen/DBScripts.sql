CREATE DATABASE DBExamen;

USE DBExamen;

CREATE TABLE genders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE jobs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
);

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    gender_id INT,
    job_id INT,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthdate DATE NOT NULL,
    FOREIGN KEY (gender_id) REFERENCES genders(id),
    FOREIGN KEY (job_id) REFERENCES jobs(id)
);

CREATE TABLE employee_worked_hours (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    worked_hours INT NOT NULL,
    worked_date DATE NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

-- Insertando datos en la tabla genders
INSERT INTO genders (name) VALUES ('Hombre');
INSERT INTO genders (name) VALUES ('Mujer');
INSERT INTO genders (name) VALUES ('No-binario');
INSERT INTO genders (name) VALUES ('Otro');

-- Insertando datos en la tabla jobs
INSERT INTO jobs (name, salary) VALUES ('Software Engineer', 75.00);
INSERT INTO jobs (name, salary) VALUES ('Product Manager', 90.00);
INSERT INTO jobs (name, salary) VALUES ('Gerente', 100.00);