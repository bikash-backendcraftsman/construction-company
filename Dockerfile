FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml ./
COPY construction-common/pom.xml construction-common/
COPY construction-site-management/pom.xml construction-site-management/
COPY material-supply-management/pom.xml material-supply-management/
COPY customer-management/pom.xml customer-management/
COPY price-management/pom.xml price-management/
COPY document-management/pom.xml document-management/
COPY construction-application/pom.xml construction-application/
RUN chmod +x mvnw && ./mvnw -B -DskipTests dependency:go-offline
COPY . .
RUN ./mvnw -B -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/construction-application/target/construction-company.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
