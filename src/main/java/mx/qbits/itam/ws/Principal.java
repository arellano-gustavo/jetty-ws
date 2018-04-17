package mx.qbits.itam.ws;

import javax.websocket.server.ServerContainer;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class Principal {
    private final static Logger LOG = Logger.getLogger(Principal.class);
    
    public static void main(String...argv) {
        try {
            doit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doit() throws Exception {
        LOG.info("Oky !!! Lets do this !!!!!"); 
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/WebSocket");
        server.setHandler(context);

        // Solo se necesitan estas 2 lineas: 
        ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(context);
        wscontainer.addEndpoint(CustomEndPoint.class);

        server.start();
        server.join();
    }

}


