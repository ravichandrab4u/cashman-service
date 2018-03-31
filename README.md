#To Run Cacheman Service run following command
mvn spring-boot:run

Command to build docker image
./mvnw install dockerfile:build

POST Man Project is inculded in the Project which contains all sample request

Sample Health Requests

Request1
Test Service
http://localhost:9092/health

Response1
Health is OK

Swagger documentation URL
http://localhost:9092/swagger-ui.html


Pending/Improvements

1) Add BDD Test Cases using cobertura
2) This project can be extended to other demoninations
3) Add JUnit Test Cases
4) Add Integration Test Cases
5) Add Static Code analyser using SonarQube
