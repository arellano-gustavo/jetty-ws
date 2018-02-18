# jetty-ws
Expone un WebSocket desplegado en un Jetty embebido y con Maven Shade se crea un UberJar

Después de clonar, el modo de ejecución es como sigue:<br/>
<ul>
  <li>cd jetty-websocket-example</li>
  <li>mvn clean package</li>
  <li>java -jar target/jetty-websocket-example-0.0.1-SNAPSHOT-fat.jar</li>
  <li>abrir con un browser el archivo cliente.html</li>
</ul>
