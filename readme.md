# Java Web Server

A simple RESTful API server built with Dropwizard. And can collect the user survey and upload to oracle cloud logging service.

## Project Structure

```
src/main/java/com/example/javawebserver/
├── api/                  # REST API resources
├── config/               # Configuration classes
├── model/                # Data models
└── JavaWebServerApplication.java  # Application entry point
```

## Build & Run

### Prerequisites
- Java 1.8+
- Maven 3.6+

### Dependencies
- Dropwizard 2.1.4
- Oracle OCI SDK 3.31.0

### Commands

Build the project locally:
```
mvn clean package
```

Run the server locally:
```
java -jar target/javawebserver-1.0-SNAPSHOT.jar server config.yml
```

## API Endpoints

- `GET /hello`: Hello world endpoint
- `POST /surveys`: Submit survey data

### Example Requests

Test the survey endpoint:
```
curl -X POST http://localhost:8080/surveys \
  -H "Content-Type: application/json" \
  -d '{
    "age": 27,
    "gender": "male",
    "region": "SG",
    "surveyID": "abc125",
    "score": 1
  }'
```

## Docker

Build image:
```
docker build -t javawebserver .
```

Run container and upload your oci identity
```
docker run -p 8080:8080 -v <your oci config key path>:/root/.oci javawebserver
```

