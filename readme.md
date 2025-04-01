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

## Makefile
You can just run make file command to complie or run the program
```
Available commands:
  make build      - Build Java package and Docker image
  make clean      - Clean Maven target directory
  make package    - Build Java package only
  make docker     - Build Docker image only
  make local-run  - Run the application locally
  make docker-run - Run the application in Docker container
```

## Search and visualize
Display as a table the average score per gender
```
search "ocid1.tenancy.oc1..aaaaaaaagb6cprwnqyfccfx5zrfxt56fx5sbqdgeven3yvlgrshazqrwrvea/ocid1.loggroup.oc1.ap-singapore-1.amaaaaaaybq4p6yapanw4aypd6hrwfb7qbf4uiabmrhxbzhotffpekh2ydfa/ocid1.log.oc1.ap-singapore-1.amaaaaaaybq4p6yaq65dmqohulmmau6eusn77bgh72yiezlz5sshfpfps3uq"
| where type = 'survey.received' | isNotNull(data.gender)
| summarize avg(data.score) as average_score by data.gender
```

Create a visualization that displays the number of surveys per gender
```
search "ocid1.tenancy.oc1..aaaaaaaagb6cprwnqyfccfx5zrfxt56fx5sbqdgeven3yvlgrshazqrwrvea/ocid1.loggroup.oc1.ap-singapore-1.amaaaaaaybq4p6yapanw4aypd6hrwfb7qbf4uiabmrhxbzhotffpekh2ydfa/ocid1.log.oc1.ap-singapore-1.amaaaaaaybq4p6yaq65dmqohulmmau6eusn77bgh72yiezlz5sshfpfps3uq"
| where type = 'survey.received' | isNotNull(data.gender)
| summarize count() by data.gender
```