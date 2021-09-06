DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;


CREATE TABLE department
(
    id bigint AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR NOT NULL,
    employee_count int NOT NULL DEFAULT 0
);

CREATE TABLE employee
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR NOT NULL,
    date_of_birth date NOT NULL,
    salary int NOT NULL,
    department_id int NOT NULL,
    foreign key (department_id) references department(id)
);

INSERT INTO department (name) VALUES ('first'),('second'),('third');

INSERT INTO employee (name, date_of_birth, salary, department_id) VALUES
  ('Dominik Toretto', '1980-01-13', 600, 1),
  ('Argus Key Gr.', '1993-05-21', 1300, 1),
  ('Spider Man', '1995-07-03', 490, 3);