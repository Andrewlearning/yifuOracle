import requests
import random
import time

URL = "http://localhost:8080/surveys"
HEADERS = {"Content-Type": "application/json"}
GENDERS = ["male", "female", "other"]

"""
Sample request:
curl -X POST http://localhost:8080/surveys \
  -H "Content-Type: application/json" \
  -d '{
    "age": 26,
    "gender": "male",
    "region": "SG",
    "surveyID": "abc124",
    "score": 4
  }'
"""

def generate_random_data():
    return {
        "age": random.randint(0, 100),
        "gender": random.choice(GENDERS),
        "region": "SG",
        "surveyID": str(int(time.time() * 1000)),
        "score": random.randint(1, 5)
    }

def send_request(data):
    response = requests.post(URL, headers=HEADERS, json=data)
    print(f"Sent data: {data}, Response: {response.status_code}, {response.text}")

def main():
    for _ in range(10):
        data = generate_random_data()
        send_request(data)
        time.sleep(10)

if __name__ == "__main__":
    main()
