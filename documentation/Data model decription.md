## Data model description

### Data model

Database stores:
* Information about `employee` - name, birthdate, salary, related department
* Information about `department` - name

Here is ER model:  
![Cant load](./resources/ER_model.png "ER model")  

### Scripts

Tables:  

```sql
CREATE TABLE IF NOT EXISTS public.department
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT department_pkey PRIMARY KEY (id)
)
```

```sql
CREATE TABLE IF NOT EXISTS public.employee
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1),
    name character varying NOT NULL,
    date_of_birth date NOT NULL,
    salary integer NOT NULL,
    department_id integer NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT department_id_fk FOREIGN KEY (department_id)
    REFERENCES public.department (id) MATCH SIMPLE
)
```

Init:  

```sql
INSERT INTO public.department (name) VALUES ('first'),('second'),('third');

INSERT INTO public.employee (name, date_of_birth, salary, department_id) VALUES
  ('Dominik Toretto', '1980-01-13', 600, 1),
  ('Argus Key Gr.', '1993-05-21', 1300, 1),
  ('Spider Man', '1995-07-03', 490, 3);
```




