package tab.audio.server;

import tab.audio.server.servlet.HelloServlet;

/**
 * Created by andrey.tesluk on 10.08.2015.
 */
public class Main {
    public static void main(String[] args) {
        JettyServer server = new JettyServer(JettyServer.WEB_PORT);
        server.addServlet(HelloServlet.class,"/hello");

        server.run();
    }
}
