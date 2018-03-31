#To Run Cacheman Service run following command
mvn spring-boot:run

Command to build docker image
./mvnw install dockerfile:build

Sample Requests

Request1
Test Service
http://localhost:9092/health

Response1
Health is OK

Swagger documentation URL
http://localhost:9092/swagger-ui.html
