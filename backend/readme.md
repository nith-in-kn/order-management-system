# run in cmd
chmod +x ./backend/orderservice/mvnw

### run anywhere 
./backend/order-service/mvnw spring-boot:run -f ./backend/order-service/pom.xml

### just build without running (check for compile errors)
./backend/order-service/mvnw compile -f ./backend/order-service/pom.xml

### run tests only
./backend/order-service/mvnw test -f ./backend/order-service/pom.xml

### build a jar
./backend/order-service/mvnw package -f ./backend/order-service/pom.xml

## shortcut
### setup
alias run-order="./backend/order-service/mvnw spring-boot:run -f ./backend/order-service/pom.xml"

### then just type
run-order