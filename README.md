## Department app

[![Build Status](https://travis-ci.com/kagire/department-app.svg?branch=main)](https://travis-ci.com/kagire/department-app)
[![Coverage Status](https://coveralls.io/repos/github/kagire/department-app/badge.svg?branch=main)](https://coveralls.io/github/kagire/department-app?branch=main)

[to Software requirements specification](documentation/Software%20Requirements%20Specification.md)

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

```
java -jar ./web-app/target/web-app-1.0.jar
```

`^C` to stop app (`ctrl + C` or `cmd + C`).

#### Use

Application available at `localhost:8080`