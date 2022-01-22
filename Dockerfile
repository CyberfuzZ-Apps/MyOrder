FROM openjdk
WORKDIR MY_order
ADD target/MyOrder-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar app.jar
