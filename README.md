# jetty-ws
Expone un WebSocket desplegado en un Jetty embebido y con Maven Shade se crea un UberJar

Después de clonar, el modo de ejecución es como sigue:
cd jetty-websocket-example
mvn clean package
java -jar target/jetty-websocket-example-0.0.1-SNAPSHOT-fat.jar
