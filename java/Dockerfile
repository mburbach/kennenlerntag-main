# Build: Jar mit dem Maven Wrapper bauen (Tests laufen separat ueber ./mvnw test)
FROM docker.io/library/eclipse-temurin:25-jdk AS build
WORKDIR /build
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN ./mvnw -B -q dependency:go-offline
COPY src src
RUN ./mvnw -B -q package -DskipTests

# Runtime
FROM docker.io/library/eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /build/target/vehiclemanagerservice-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
