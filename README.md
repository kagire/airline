## Department app

[![Build Status](https://travis-ci.com/kagire/department-app.svg?branch=main)](https://travis-ci.com/kagire/department-app)
[![Coverage Status](https://coveralls.io/repos/github/kagire/department-app/badge.svg?branch=main)](https://coveralls.io/github/kagire/department-app?branch=main)

[to Software requirements specification](documentation/Software%20Requirements%20Specification.md)  
[Generate REST client instruction](documentation/Generate%20REST%20client%20instruction.md)  
[to project presentation](documentation/presentation/Department_app.pdf)

#### Used technologies

* Apache maven - build
* Spring - project framework
* Thymeleaf - view
* PostgreSQL, h2 embedded - databases
* Log4j2 - logging tool
* Jupiter - test engine
* Mock MVC - mock responses for tests

#### Get app

```
git clone https://github.com/kagire/department-app.git
```
```
cd department-app
```

#### Test app

Test can be run without installed postgres. For launching, use:  

```
mvn clean test
```

#### Build app

```
mvn clean install -DskipTests
```

#### Run app

Application uses Spring Boot embedded Tomcat server, so it can be launched without installing any
new packages, maven will do it by itself.  

App uses postgres for storing data, so you can get it and use model from [Data model description](documentation/Data%20model%20decription.md)  

To run rest app:  

```
java -jar ./rest/target/rest-1.0.jar
```

To run web app (_Note: rest app should be already launched_):  

```
java -jar ./web-app/target/web-app-1.0.jar
```

`^C` to stop app (`ctrl + C` or `cmd + C`).

#### Use

Application available at `localhost:8080`  

To access REST using CURL:  

```
curl localhost:8081/departments
```

#### Small Q&A about technologies

1. Why Spring?

Spring - great platform for developing web applications, what, obviously, perfectly suits for my project - java web app.  

2. Why 2 databases used?

Because u need to install postgres to launch this app, but it is good opportunity for both developers and people who want try this app not to set up database and download it.
So tests, for example, can be run without setting up database amd only by using flyway, h2 embedded and spring profile for launching "test" one.

3. Why log4j2 instead of Logback?

Just individual preferences and a simpler setup.
