# Analysis

## key questions
* How many rooms does the cinema have?
* In the future, is there any plan for allowing the customer to pay and schedule a movie through this API?
* Are the reviews going to be shown in some place?
* How many new movies will there be?

## Assumptios base on questions

* There is one room, however in future there could be more than one. So it is important project tables and all the necessary to this.
* It is possible. In that case our component has to be able of storing user information.
* Yes. For that reason it needs capability to save them.
* There will be many new movies.

## Technical base

* As there is not complex logic I am taking layered architecture approach.
* Given the call to Movie Info API needs to be sync, so best option is using resttemplate.
* Everything in database side will be H2 database.
* In order to stay inside challenge scope I will have only one relational database for everything.
* Thinking in having a loose couple and avoiding one enormous component. I am splitting our component in Movie service, Management service and commons lib.
* Movie service: It covers all the features related with movies that a customer can do.
* Management service: It covers all the management features.
* Every service has a data.sql script for inserting initial data.

## Production

In order to stay inside the time of the challenge there are some taskes I am not going to do. However I think in a real system they are important. Next you can find them:

1. Have a separated and independent security service responsible for security. At security level there should exist roles and scopes for users amd a jwt token authentication system. Futhermore, in case client for this component are few and controlled, then client credentials authentication and user credentials flow should be implemented.
2. Centralize cross functionalities like logs and instrumentation having an aspect module (spring-aspect).
3. Using master slave architecture.
4. Change commons to release version.
5. Avoid duplicated code, put exception handler in commons.

## Run Movie Service dockerfile linux

1. Copy application-local.properties in main resource folder for movie service project
2. Open your terminal.
3. Go to movie-service main folder where is stored this project.
4. Execute next command "sudo docker build -t movie-service-11:latest ."
5. Execute next command "sudo docker run -e "SPRING_PROFILES_ACTIVE=local" -dp 8090:8084  movie-service-11:latest"
6. For opening Swagger use http://localhost:8090/swagger-ui.html

## Run Management Service dockerfile linux

1. Open your terminal.
2. Go to management-service main folder where is stored this project.
3. Execute next command "sudo docker build -t management-service-11:latest ."
4. Execute next command "sudo docker run -dp 8091:8083  management-service-11:latest"
5. For opening Swagger use http://localhost:8091/swagger-ui.html