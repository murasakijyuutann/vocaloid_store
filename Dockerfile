# Railway-optimized Dockerfile
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built JAR (Railway builds with Maven automatically)
COPY target/*.jar app.jar

# Railway provides PORT environment variable
EXPOSE $PORT

# Run the application
CMD ["java", "-Xmx512m", "-jar", "app.jar"]
