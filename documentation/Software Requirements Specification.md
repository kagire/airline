# Department app

This is a simple app for managing people at some company or organisation

### Description

#### Model

Database stores:
* Information about `employee` - name, birthdate, salary, related department;
* Information about `department` - name.  

ER model:  
![Cant load](/home/kagire/IdeaProjects/department-app/documentation/resources/ER_model.png "ER model")

#### User interface

App contains a view model to provide all information and edit possibilities to user. User able to:  
* `employee` - CRUD operations and view exact one;
* `department` - CRUD operations.

Mock employee page (edit form):
![Cant load](/home/kagire/IdeaProjects/department-app/documentation/resources/mock_employee_page.png "Mock employee form")

[Static html prototypes](html_prototype)

### Environment

Requirements:
* **h2 embedded database** and/or **postgres db**
* OS-independent launch - only **java** required
* **Maven 3.6.3** and **jdk 11** to build (install)

### Note and instruction

Project was developed and build on Ubuntu OS.

#### Build app

```shell
mvn clean install
```







