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

INSERT INTO department (name) VALUES ('first'),('second'),('third');

INSERT INTO employee (name, date_of_birth, salary, department_id) VALUES
  ('Dominik Toretto', '1980-01-13', 600, 1),
  ('Argus Key Gr.', '1993-05-21', 1300, 1),
  ('Spider Man', '1995-07-03', 490, 3);