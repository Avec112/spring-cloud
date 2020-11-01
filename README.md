# Spring Cloud Demo

* Spring Cloud Gateway
* Spring Cloud Discovery Server
* Two different microservices (load balanced)
* Simple Web Client (frontend)

## Requirements
* Java 15 (you can change in pom.xml but no older than Java 8)
* Maven 3.6.x (older might work) 
* Httpie, Curl, Postman or any Browser (I use Httpie)

## Howto
* From root: `> mvn clean install`
* Start each microservice: `> mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=[port]` (start green on two different ports and do the same for blue)
* Start Gateway `> mvn spring-boot:run` (port 8080)
* Start Discovery Server (Eureka): `> mvn spring-boot:run` (port 8761, access http://localhost:8761)
* Run: `> http :8080/blue` (do it twice and verify both (blue) services replaying)
* Do same for green if you want to
* You can also run frontend (webclient): `> mvn spring-boot:run` (port 80, http://localhost/blue or /green) 