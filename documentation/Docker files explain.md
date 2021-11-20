## Docker files explain

### Links

Files described:
1) [Dockerfile-web](../Dockerfile-web)
2) [Dockerfile-rest](../Dockerfile-rest)
3) [docker-compose](../docker-compose.yml)

### Dockerfile
In both Dockerfiles here is:
```dockerfile
FROM adoptopenjdk:11-jre-hotspot
```
- Getting 11 jre for compiling  

```dockerfile
COPY rest/target/rest-1.0.jar rest.jar
```
- Copying mvn-packaged jar to docker image with `<module name>.jar` name

```dockerfile
EXPOSE 8081
```
- Exposing container port for incoming connections  

```dockerfile
ENTRYPOINT ["java", "-jar", "rest.jar"]
```
- Running `java -jar <jarname>` to start application  

### docker-compose

3 services: `department-app-rest`, `department-app-db`, `department-app-web`.  

- `image`: gets created image for container - just specifying name
- `build`: setting build context (`.` - everything, `dockerfile` - dockerfile name)
- `container_name`: setting container's name
- `ports`: setting first number as local port and second as container's port
- `volumes`: specifying docker-managed files
- `depends_on`: specified container's boot order (db -> rest -> web)
- `networks`: specified network in purpose for containers can receive requests
- `environment`: overriding environmental variables for containers

Also, 1 network exists.