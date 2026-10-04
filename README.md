# 🚀 Spring Boot Customer Management Microservice

A containerized Spring Boot REST API featuring a dynamic, dark-mode web dashboard for customer management. Built with Java 17 and Maven, fully containerized using Docker multi-stage builds, and optimized for deployment.

---

## 📌 Features

- **RESTful Endpoints:** Complete JSON CRUD operations under `/customers`.
- **Embedded Web Dashboard:** Single-page frontend (`index.html`) served directly from Spring Boot static resources.
- **Multi-Stage Dockerfile:** Uses `maven:3.9-eclipse-temurin-17` for efficient build caching and `eclipse-temurin:17-jre` for a lightweight runtime footprint.
- **Docker Hub Integration:** Ready for remote image distribution and CI/CD pipelines.
---

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot 3, Maven
- **Frontend:** HTML5, CSS3, JavaScript (Fetch API)
- **Containerization:** Docker, Docker Desktop, Docker Hub

---

## 🔌 API Endpoints

| HTTP Method | Endpoint | Description | Request / Response |
| :--- | :--- | :--- | :--- |
| `GET` | `/` | Serves the interactive Customer Dashboard UI | HTML Page |
| `GET` | `/customers` | Fetch all customer records | Returns `JSON Array` |
| `GET` | `/customers/{id}` | Fetch a single customer by ID | Returns `JSON Object` |
| `POST` | `/customers` | Create a new customer record | Accepts/Returns `JSON Object` |
| `PUT` | `/customers/{id}` | Update an existing customer | Accepts/Returns `JSON Object` |
| `DELETE` | `/customers/{id}` | Delete a customer record | Returns plain text status |

---

## ⚡ Quick Start (Local Docker Execution)

### Prerequisites
- Docker Installed and running
- Git

### 1. Clone the Repository
```bash
git clone [https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git](https://github.com/BilalKhan484/CICD-Pipeline-aws.git)
cd CICD-Pipeline-aws
