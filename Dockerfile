FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /spring
COPY . .
RUN mvn clean install -DskipTests

CMD mvn spring-boot:run -DskipTests