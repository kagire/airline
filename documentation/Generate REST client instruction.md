## Generate REST client instruction

### Prepare

At first, you need to create/generate plain maven project (i named it **swagger-codegen-project**). There is 2 ways to do this:
1. Create maven project using IDE (example for Intellij IDEA: create project -> maven _OR_ create Java project -> add framework support "Maven")
2. [Spring Initializer](https://start.spring.io/) (no dependencies can be added)

### Code

Next you need to modify code.  
`pom.xml`:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>swagger-codegen-project</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>16</maven.compiler.source>
        <maven.compiler.target>16</maven.compiler.target>
        <jersey-version>3.0.2</jersey-version>
        <jackson-version>2.8.9</jackson-version>
        <jodatime-version>2.10.10</jodatime-version>
        <maven-plugin-version>1.0.0</maven-plugin-version>
        <junit-version>4.13.2</junit-version>
        <springfox-version>2.9.1</springfox-version>
        <threetenbp-version>1.5.1</threetenbp-version>
        <datatype-threetenbp-version>2.12.2</datatype-threetenbp-version>
        <spring-boot-starter-test-version>2.5.4</spring-boot-starter-test-version>
        <spring-boot-starter-web-version>2.5.4</spring-boot-starter-web-version>
        <junit-version>4.12</junit-version>
        <migbase64-version>2.2</migbase64-version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.1.10</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.core</groupId>
            <artifactId>jersey-client</artifactId>
            <version>${jersey-version}</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.media</groupId>
            <artifactId>jersey-media-multipart</artifactId>
            <version>${jersey-version}</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.media</groupId>
            <artifactId>jersey-media-json-jackson</artifactId>
            <version>${jersey-version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.jaxrs</groupId>
            <artifactId>jackson-jaxrs-base</artifactId>
            <version>${jackson-version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>${jackson-version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>${jackson-version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.12.5</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.jaxrs</groupId>
            <artifactId>jackson-jaxrs-json-provider</artifactId>
            <version>${jackson-version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-joda</artifactId>
            <version>${jackson-version}</version>
        </dependency>
        <dependency>
            <groupId>joda-time</groupId>
            <artifactId>joda-time</artifactId>
            <version>${jodatime-version}</version>
        </dependency>
        <dependency>
            <groupId>com.brsanthu</groupId>
            <artifactId>migbase64</artifactId>
            <version>${migbase64-version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit-version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>${spring-boot-starter-test-version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>${spring-boot-starter-web-version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${springfox-version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${springfox-version}</version>
        </dependency>
        <dependency>
            <groupId>org.threeten</groupId>
            <artifactId>threetenbp</artifactId>
            <version>${threetenbp-version}</version>
        </dependency>
        <dependency>
            <groupId>com.github.joschi.jackson</groupId>
            <artifactId>jackson-datatype-threetenbp</artifactId>
            <version>${datatype-threetenbp-version}</version>
        </dependency>

        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.1</version>
        </dependency>

        <dependency>
            <groupId>javax.annotation</groupId>
            <artifactId>javax.annotation-api</artifactId>
            <version>1.3.2</version>
        </dependency>

        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <version>2.0.1.Final</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.12.5</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.12.5</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.12.5</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>5.2.1</version>
                <executions>
                    <execution>
                        <id>spring-boot-api</id>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/api.yaml</inputSpec>
                            <generatorName>spring</generatorName>
                            <configOptions>
                                <dateLibrary>joda</dateLibrary>
                            </configOptions>
                            <library>spring-boot</library>
                            <apiPackage>com.kagire.api</apiPackage>
                            <modelPackage>com.kagire.api.model</modelPackage>
                            <invokerPackage>com.kagire.api.handler</invokerPackage>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-deploy-plugin</artifactId>
                <version>3.0.0-M1</version>
                <executions>
                    <execution>
                        <id>default-deploy</id>
                        <phase>deploy</phase>
                        <goals>
                            <goal>deploy</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <!-- Build an executable JAR -->
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <classpathPrefix>lib/</classpathPrefix>
                            <mainClass>com.kagire.SpringMain</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

Create application.properties at _src/main/java/resources_.

`application.properties`:

```properties
server.port=8081
```

Now you need REST api config from swagger to be able to generate code. To do that, you need json representation of swagger output, so
1. Create `api.yaml` at _src/main/java/resources_
2. Launch REST app from already created project (rest module)
3. Open `localhost:8081/swagger-ui/` and make sure everything is like you want (models, controllers)
4. Open `http://localhost:8081/v2/api-docs` to get swagger json
5. Save it to `api.yaml`

Finally, get project ready for start - add `main` method. To fo so, create `SpringMain` class in _src/main/java/com.kagire_

```java
package com.kagire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringMain.class, args);
    }
}
```

### Run

To create executable jar you need to install project:

```console
mvn clean install
```

Now get this jar from _target_ folder to any you like and launch it:

```console
java -jar swagger-codegen-project-1.0-SNAPSHOT.jar
```

You can use your web app to mare requests to this API