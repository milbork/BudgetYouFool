FROM openjdk:8-jre-alpine
ADD target/BudgetYouFool-1.0-SNAPSHOT.jar BudgetYouFool-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=container","-jar","/app.jar"]


