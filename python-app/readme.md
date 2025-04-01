# Survey Request Sender

A simple Python script that automatically generates and sends random survey data to a Java web server.

## Features

- Generates random survey data (age, gender, region, surveyID, score)
- Sends HTTP POST requests to a specified endpoint
- Configurable to send multiple requests with time intervals

## Requirements

- Python 3.6+
- requests library

## Installation

```
pip3 install requests
```

## Usage

1. Ensure the Java web server is running on http://localhost:8080
2. Run the script:

```
python request_sender.py
```

By default, the script will:
- Send 10 random survey requests
- Wait 10 seconds between each request
- Display the sent data and response from the server

## Customization

Edit the script to modify:
- The target URL (currently http://localhost:8080/surveys)
- Number of requests to send
- Time interval between requests
- Data generation parameters
