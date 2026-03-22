## run
./backend/orderservice/mvnw spring-boot:run -f ./backend/orderservice/pom.xml
- mvnw spring-boot:run
- mvnw clean
- mvnw compile
- mvnw test
- mvnw package
- mvnw install

## Steps to Forward and Publicize Port 8000 for codespace
gh codespace ports forward 8000:8000
gh codespace ports visibility 8000:public
