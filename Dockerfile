# --- Phase 1 : Compilation (Maven) ---
# On utilise une image Maven officielle pour construire le projet
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# On compile le projet (skipTests gagne du temps car Jenkins a déjà testé)
RUN mvn clean package -DskipTests

# --- Phase 2 : Exécution (Image finale) ---
# C'est ici que l'erreur se produisait. On utilise maintenant eclipse-temurin (très stable)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# On récupère le .jar créé à la phase 1
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]