#!/bin/bash
# Update system packages
apt-get update -y
apt-get upgrade -y

# Install Docker and Docker Compose
apt-get install -y docker.io docker-compose-v2
systemctl start docker
systemctl enable docker

# Create app directory
mkdir -p /home/ubuntu/app
cd /home/ubuntu/app

# Create docker-compose.yml on the server
cat <<EOF > docker-compose.yml
services:
  db:
    image: postgres:15-alpine
    container_name: postgres-db
    restart: always
    environment:
      POSTGRES_DB: customerdb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: secretpassword
    volumes:
      - postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres -d customerdb"]
      interval: 5s
      timeout: 5s
      retries: 5
      start_period: 15s

  api:
    image: ${docker_image}
    container_name: springapi-container
    restart: always
    ports:
      - "80:8080"
    environment:
      DB_HOST: db
      DB_PORT: 5432
      DB_NAME: customerdb
      DB_USER: postgres
      DB_PASS: secretpassword
    depends_on:
      db:
        condition: service_healthy

volumes:
  postgres_data:
EOF

# Start the multi-container stack
docker compose up -d