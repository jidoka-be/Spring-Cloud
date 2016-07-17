# Spring Cloud demo

### Container setup
* [MongoDB](https://hub.docker.com/_/mongo/)
* [RabbitMQ](https://hub.docker.com/r/frodenas/rabbitmq/)

These dependencies can be installed through docker.

### Projects

#### [Configuration Server](http://localhost:8000/)
Central Configuration Server which will fetch pre-configured settings for all configured services. These [settings](https://github.com/jidoka-be/Spring-Cloud-Configuration) are stored in a separate git repository.

##### Using encryption

###### Authentication with BitBucket
>The configuration files are stored in [BitBucket](http://cloud.spring.io/spring-cloud-config/spring-cloud-config.html), in a private repository.
>Authentication is required! Storing plain-text passwords for the Basic Authentication, is not a good idea.
>Spring Cloud Config Server has an encryption/decryption endpoint. The functionality behind these endpoints are triggered when an attribute in a property/yml file has ciphering.

###### How to encrypt BitBucket password

###### Prepare you environment

Download the [unlimited strength JCE](http://www.oracle.com/technetwork/java/javase/downloads/index.html) from Oracle's website. Once downloaded, you'll need to extract the zip file. You need to copy the **local_policy.jar** and **US_export_policy.jar** into **$JAVA_HOME/jre/lib/security** directory. Before doing this though, you should make a backup copy of the existing policy files!

###### Create an encryption key and keystore

`keytool -genkeypair -alias demoKey -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" -keypass changeme -keystore server.jks -storepass letmein -validity 365`

###### Encryption

`curl localhost:8000/encrypt -d 'Hello Spring Boot!'`

###### Decryption

`curl localhost:8000/decrypt -d 'AQCLJG5FvxQrLAC6Q9iDmkegrIh1yJ4K2BeRzNdu3PkMZXv1cMSJ/C3flllHITinxgKecX3I8MPIf6sSsSIaZCV9YW/MzQ6VYVQ8Mt4GIxbPfFRFpvOhBmsCDomZ5lQ5eWoDmS1yHpIMeES37YVLW/uM8LJTNRg16ywFy/pdxbhzQt5g0KEe8N8FIDk32lrvo5qB3e0hYv3gOfUAEYzt9ZjyQmXJqpAgWB+85Su7gIyxW7f5Q20hNoA5RgG87ofrmFD2M0LgcjQMbeEKIUqYAZjOYKAJG/xUwU9WEkTgw8nKgSmkkPxMPorSIBwwgXlYDGlLU3DM+lT91Nky8ydH3CTuKSN1j7OhfRRLWPcYLNFC+BLqT/4tjcCrVP2tvCepN/yZ9pN8eA6L1zQqoHPzpBkj'`

The encrypted password can be placed in a property/yml file:

###### Starting server with ciphered properties

`'{cipher}AQCLJG5FvxQrLAC6Q9iDmkegrIh1yJ4K2BeRzNdu3PkMZXv1cMSJ/C3flllHITinxgKecX3I8MPIf6sSsSIaZCV9YW/MzQ6VYVQ8Mt4GIxbPfFRFpvOhBmsCDomZ5lQ5eWoDmS1yHpIMeES37YVLW/uM8LJTNRg16ywFy/pdxbhzQt5g0KEe8N8FIDk32lrvo5qB3e0hYv3gOfUAEYzt9ZjyQmXJqpAgWB+85Su7gIyxW7f5Q20hNoA5RgG87ofrmFD2M0LgcjQMbeEKIUqYAZjOYKAJG/xUwU9WEkTgw8nKgSmkkPxMPorSIBwwgXlYDGlLU3DM+lT91Nky8ydH3CTuKSN1j7OhfRRLWPcYLNFC+BLqT/4tjcCrVP2tvCepN/yZ9pN8eA6L1zQqoHPzpBkj'`

Following environment variables should be provided on the system:
>encrypt.keyStore.location=classpath:/server.jks
>encrypt.keyStore.password=letmein
>encrypt.keyStore.alias=demoKey
>encrypt.keyStore.secret=changeme

#### [Discovery Service](http://localhost:8100/)
Service Discovery is one of the key tenets of a microservice based architecture. Trying to hand configure each client or some form of convention can be very difficult to do and can be very brittle. Eureka is the Netflix Service Discovery Server and Client. The server can be configured and deployed to be highly available, with each server replicating state about the registered services to the others.

#### [MBTI Service](http://localhost:9200/)

##### Available endpoints
* GET /mbti/{type}
* GET /mbti/search/{searchTerm}

#### [Customer Service](http://localhost:9100/)

##### Available endpoints
* GET /company
* GET /company/{id}
* GET /project/{id}
* GET /project/name/{name}
* POST /project

#### [Employee Service](http://localhost:9000/)

##### Available endpoints
* GET /employee
* GET /employee/{id}
* POST /employee/{id}/project

#### [Hystrix Dashboard](http://localhost:8889/hystrix/)
The Hystrix Dashboard allows you to monitor Hystrix metrics in real time.

>http://localhost:9000/hystrix.stream is the stream that can be monitored.

#### [API Gateway](http://localhost:9999/api/)
Routing in an integral part of a microservice architecture. For example, / may be mapped to your web application, /api/users is mapped to the user service and /api/shop is mapped to the shop service. Zuul is a JVM based router and server side load balancer by Netflix.

#### [Tracing Service](http://localhost:9411/)
Tracing is simple, in theory. As a request flows from one component to another in a system, through ingress and egress points, tracers add logic where possible to perpetuate a unique trace ID that’s generated when the first request is made. As a request arrives at a component along its journey, a new span ID is assigned for that component and added to the trace. A trace represents the whole journey of a request, and a span is each individual hop along the way, each request. Spans may contain tags, or metadata, that can be used to later contextualize the request. Spans typically contain common tags like start timestamps and stop timestamp, though it’s easy to associate semantically relevant tags like an a business entity ID with a span.

The Zipkin web UI makes it easy to analyze and query Zipkin data.