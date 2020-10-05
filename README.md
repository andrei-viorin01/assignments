# FIS Assignment Offer REST API
Restful Services for FIS technical assignment

## Prerequisites
- JDK 1.8
- Maven 3.0 or later
- PostgresSQL DB 4

## Installing
1. Download the project.
2. Run 'mvn clean package' to download the required dependencies and generate the jar.
3. Setup the PostgreSQL database and update the configuration details in the PersistanceConfiguration.class
(url, username, password) and setup the schema (schema.sql).
4. From the target directory run 'java -jar assignment-0.0.1-SNAPSHOT.jar' to start the application.

## API Documentation
1. Create offer (example) <br>
URL: http://localhost:8080/api/v1/offers <br>
Method: POST <br>
Header: content-type: application/json <br>
Body: <br>
{ <br>
	"offerName": "test offer", <br>
	"offerDescription": "This is a test offer", <br>
	"status": "ACTIVE", <br>
	"createdDt": "", <br>
	"activeDays": 5, <br>
	"expiryDt": "" <br>
} <br>
<br>
2. Get all ACTIVE offers <br>
URL: http://localhost:8080/api/v1/offers <br>
Method: GET <br>
<br>
3. Get offer by id <br>
URL: http://localhost:8080/api/v1/offers/{id} <br>
Method: GET <br>
<br>
4. Update offer with id <br>
URL: http://localhost:8080/api/v1/offers/{id} <br>
Method: PUT <br>
<br>
5. Cancel offer with id (status is set to CANCELLED) <br>
URL: http://localhost:8080/api/v1/offers/{id}/cancel <br>
Method: PUT <br>
<br>