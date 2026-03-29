# Stage 1: Build
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

# Copy build files
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# FIX 1: Ensure the wrapper is executable
RUN chmod +x mvnw

# Download dependencies (this layer is cached)
RUN ./mvnw dependency:go-offline

# Copy source and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Security: Run as non-root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# FIX 2: Copy the specific fat JAR (ignoring sources/javadoc)
COPY --from=build /app/target/[!slim]*.jar app.jar

# Render uses the PORT environment variable
EXPOSE 8080

# Use the -D flag to pass the port dynamically to the JVM
ENTRYPOINT ["java", "-Xmx400m", "-jar", "app.jar"]