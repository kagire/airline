DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT department_id_pk PRIMARY KEY (id)
);

CREATE TABLE employee (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	date_of_birth DATE NOT NULL,
	salary INT NOT NULL,
	department_id INT NOT NULL,
	CONSTRAINT employee_id_pk PRIMARY KEY (id),
	CONSTRAINT department_id_fk FOREIGN KEY (department_id) REFERENCES department(id)
);