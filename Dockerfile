FROM lolhens/baseimage-openjre
ADD target/merchant.jar merchant.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "springbootApp.jar"]