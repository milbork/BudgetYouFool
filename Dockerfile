FROM openjdk:8u212-jre-alpine3.9
ADD target/BudgetYouFool-1.0-SNAPSHOT.jar BudgetYouFool-1.0-SNAPSHOT.jar
EXPOSE 10101
CMD java -jar BudgetYouFool-1.0-SNAPSHOT.jar



