docker-compose down
docker image rm emart-item-service
docker build . -t emart-item-service
docker-compose up -d