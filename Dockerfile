FROM openjdk:8u212-jre-alpine3.9
ADD target/BudgetYouFool-1.0-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar BudgetYouFool-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","BudgetYouFool-1.0-SNAPSHOT.jar"]
