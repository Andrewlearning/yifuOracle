# 默认目标
.DEFAULT_GOAL := help

help:
	@echo "Available commands:"
	@echo "  make build      - Build Java package and Docker image"
	@echo "  make clean      - Clean Maven target directory"
	@echo "  make package    - Build Java package only"
	@echo "  make docker     - Build Docker image only"
	@echo "  make local-run  - Run the application locally"
	@echo "  make docker-run - Run the application in Docker container"

build: package docker

clean:
	mvn clean

package:
	mvn clean package

docker:
	docker build -t javawebserver .

docker-run:
	docker run -p 8080:8080 -v $(HOME)/.ssh/oci:/root/.oci javawebserver

local-run:
	java -jar target/javawebserver-1.0-SNAPSHOT.jar server config.yml

.PHONY: help build clean package docker docker-run run
