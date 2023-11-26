# Skaffold Development for Spring Boot Application

This repository contains a sample setup for developing a Spring Boot application using Skaffold. Skaffold allows you to streamline the development and deployment of your application in Kubernetes.

## Prerequisites

Before you begin, ensure you have the following tools installed:

- Docker
- Skaffold
- Java Development Kit (JDK)
- Kubernetes Cluster (Minikube)

## Getting Started

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/glad2os/spring-assignment4.git
   cd spring-assignment4
   ```

2. Build the Docker image for your Spring Boot application:

   ```bash
   skaffold build
   ```

3. Start the Skaffold development environment:

   ```bash
   skaffold dev
   ```

   This will continuously monitor your application's files for changes and automatically rebuild and redeploy when changes are detected.

4. Access your Spring Boot application locally at [http://localhost:8080](http://localhost:8080).

## Debugging

To enable debugging for your Spring Boot application, follow these steps:

1. Open your IDE (e.g., IntelliJ IDEA).

2. Set up a remote debugging configuration with the following parameters:

    - Host: localhost
    - Port: 5050

3. Start the debugger in your IDE.

4. Your Spring Boot application running in the Kubernetes cluster will now be available for debugging on port 5050.

## Cleaning Up

To stop the Skaffold development environment, simply press `Ctrl + C` in your terminal.