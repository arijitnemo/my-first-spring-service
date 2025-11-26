# Project Name

## Description
This project is built using **Java**, **Spring Boot**, and **Maven**. It includes features for editing and deleting entries via a web interface.

## Start URL
http://localhost:8080/api/messages

## H2 Database Console
http://localhost:8080/h2-console 
(JDBC URL: `jdbc:h2:mem:mydb`, User Name: `admin`, Password: `admin`)

## Features
- Add new entries through a user-friendly web form.
- Edit entries using a dynamic form.
- Delete entries with confirmation.
- RESTful API integration.

## Prerequisites
- Java 21 or higher
- Maven 3.8+
- Spring Boot 3.5.7+
- Dedicated Google GenAI SDK (API Key method) dependency below:
<dependency>
    <groupId>com.google.genai</groupId>
    <artifactId>google-genai</artifactId>
    <version>1.28.0</version>
</dependency>

- get GEMINI_API_KEY from - https://aistudio.google.com/app/api-keys
- And set this key in Run config ENVIRONMENT VARIABLE

## Setup
1. Clone the repository:
   git clone https://github.com/arijitnemo/my-first-spring-service
