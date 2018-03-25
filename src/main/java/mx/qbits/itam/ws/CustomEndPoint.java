package mx.qbits.itam.ws;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import javax.websocket.server.ServerEndpoint;

/* 
https://examples.javacodegeeks.com/enterprise-java/tomcat/apache-tomcat-websocket-tutorial/
https://dzone.com/articles/sample-java-web-socket-client
http://www.eclipse.org/jetty/documentation/9.4.x/embedding-jetty.html
https://examples.javacodegeeks.com/enterprise-java/jetty/jetty-websocket-example/
goose
*/

@ServerEndpoint("/websocketendpoint")
public class CustomEndPoint {
    // queue holds the list of connected clients
    private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
    private static Thread rateThread; // rate publisher thread
    
    static {
        // rate publisher thread, generates a new value for USD rate every 2
        // seconds.
        rateThread = new Thread() {
            public void run() {
                DecimalFormat df = new DecimalFormat("#.####");
                while (true) {
                    double d = 1 + Math.random();
                    if (queue != null)
                        sendAll(df.format(d));
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                    }
                }
            };
        };
        rateThread.start();
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        try {
            System.out.println("received msg " + msg + " from " + session.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void open(Session session) {
        queue.add(session);
        System.out.println("New session opened: " + session.getId());
    }

    @OnError
    public void error(Session session, Throwable t) {
        queue.remove(session);
        System.err.println("Error on session " + session.getId());
    }

    @OnClose
    public void closedConnection(Session session) {
        queue.remove(session);
        System.out.println("session closed: " + session.getId());
    }

    private static void sendAll(String msg) {
        try {
            /* Send the new rate to all open WebSocket sessions */
            ArrayList<Session> closedSessions = new ArrayList<Session>();
            for (Session session : queue) {
                if (!session.isOpen()) {
                    System.err.println("Closed session: " + session.getId());
                    closedSessions.add(session);
                } else {
                    session.getBasicRemote().sendText(msg);
                }
            }
            queue.removeAll(closedSessions);
            System.out.println("Sending " + msg + " to " + queue.size() + " clients");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
