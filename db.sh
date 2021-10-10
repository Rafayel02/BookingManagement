#!/usr/bin/env sh
set +e

docker stop booking-management
docker rm booking-management

docker run -d \
  -p 5436:5432 \
  -e POSTGRES_USER=booking \
  -e POSTGRES_DB=booking_management \
  -e POSTGRES_PASSWORD=booking \
  --name booking-management \
  postgres