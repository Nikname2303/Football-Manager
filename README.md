# Football Manager

## Introduction

Hello there! This application is designed to give you the opportunity to manage your teams and players. You can add new players or teams, update them, and delete them if needed. Additionally, the app includes assigning and transferring functionalities. With the Football Manager app, you can build your dream team)
## Technologies and Tools Used

This project leverages a range of modern technologies and tools to ensure robust performance:
- **Java 21**
- **Spring Boot**
- **Liquibase**
- **MapStruct**
- **Lombok**
- **Hibernate**

## Features 

- **Reading**: Users can view information about teams or players. This includes fetching a single record or a list of all records.
- **Adding**: Users can add new players or teams. Players can also be added without a team (e.g., if they don’t have a contract at the moment).
- **Updating**: Users can update teams or players. To do so, only the ID is required.
- **Deleting**: If necessary, users can delete players or teams by ID.

## Setup Instructions

Ensure you have the following software installed:

- **Java 21**
- **Docker Version 4.30.0 (149282)**
- **Maven 3.9.5**

### Step 1
Clone project on your local device

**Clone the repository**:
```bash
git clone https://github.com/Nikname2303/Football-Manager
cd football-manager
```


### Step 2
Create .env file in root folder and put that into
```
DEBUG_PORT=5005
MYSQL_DATABASE=football_manager
MYSQL_DOCKER_PORT=3306
MYSQL_LOCAL_PORT=3305
MYSQL_ROOT_LOGIN=root
MYSQL_ROOT_PASSWORD=MySQL1234
SPRING_DOCKER_PORT=8080
SPRING_LOCAL_PORT=8088

```
### Step 3
Run that command in terminal
```bash
docker-compose up --build
```
The application may take a few minutes to start and could crash during the startup process. Please wait patiently until it fully starts.
