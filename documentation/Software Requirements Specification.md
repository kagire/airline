# Department app

### Purpose

This document is about a simple multi-module maven app for managing people at some company or organisation.

### Document conventions

This document uses the following conventions:

* DB or db - database
* ER - entity relationship
* HR - human resources

### Project scope

The purpose of department app is to create employee management system for company or organisation with user-friendly interface. Project contains 2 database profiles: for production stage and in-memory for develop stage.

### References

[Static html prototypes](html_prototype)

## Description

This app is independent product, that means it's not a version or extension of smth.

### Product perspective

A database stores information about employees and departments they belong to. This information is only used for people who working with this app to manage employee.

### User class and characteristics

Users of the system should be able to retrieve information about departments and employees that belong to any department. No profiles/authority needed because of locality of the system. User should be able to do:

* Employee:
  * Create new
  * View and edit exact one
  * View list of all entities
  * Delete

* Department:
  * Create new
  * View and edit exact one
  * View list of all entities
  * View related employees
  * Delete

### Operating environment

Operating environment for the app:

* Operating system: Linux/Windows/Mac OS X
* Database: PostgreSQL
* Platform: Java
* Tool: Maven

### Design and implementation constraints

1. SQL commands for data model
2. REST and Web modules to launch

### Assumption dependencies

Restrictions about versions of tools:

* Maven - 3.6.3 or above
* Guice (included in Maven) - not recommended to use version below 5.x
* jdk 11 or above

## System features

### Description and priority

The department app maintains information on departments and what it contains and helps HR managers not to do their job at paper. This project has medium priority because it's useful but job still can be done without it.

### Functional requirements

* Its required to set up address for database and user/password attributes to connect, especially if database located not on machine where app is launched
* You need to add new exception responses and handlers if adding new functionality

Modules in this app:

* department-app (root)
* web-app
* domain
* service
* service-impl
* db
* repository
* repository-jdbc

## Data requirements

### Entity overview

Database stores:
* Information about `employee` - name, birthdate, salary, related department
* Information about `department` - name

### Data model
Here is ER model:  
![Cant load](./resources/ER_model.png "ER model")

## External interface requirements:

### User interfaces

* front-end software: html Thymeleaf
* back-end software: Java

### Hardware interfaces

* Ubuntu
* Browser

### Software interfaces

* Operating system: Ubuntu, but launch is OS independent
* Database: postgreSQL, h2 for dev-stage
* Thymeleaf - for front-end view

### Communication interfaces

This project supports all types of web browsers. We are using simple electronic forms for the lookup and editing, etc.

## Nonfunctional requirements

### Safety requirements

U need to have recovery method in case your database or machine damaged to prevent data loss. Possible solutions: do backup manually, do scheduled backup (for example daily), do logs.