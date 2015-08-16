package tab.audio.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;

/**
 * Created by andrey.tesluk on 10.08.2015.
 */
public class JettyServer{

    public static Logger log = LoggerFactory.getLogger(JettyServer.class);

    public static final int WEB_PORT = 8080;
    public static final int RASPI_PORT = 7385;
    public static final int REST_PORT = 7386;

    private Server server;
    private ServletContextHandler context;

    public JettyServer(int port) {
        log.info(String.format("Init jetty server on port %s", port));
        this.server = new Server(port);
        context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
    }

    public void addJerseyPackage(String pack, String path) {
        log.info(String.format("Allocate package %s to %s", pack, path));
        ServletHolder servletHolder = context.addServlet(
                ServletContainer.class, path);
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter(
                "jersey.config.server.provider.packages", pack);
    }

    public void addServlet(Class<? extends HttpServlet> servletClass,
                           String path) {
        log.info(String.format("Allocate servlet %s to %s",
                servletClass.getName(), path));
        context.addServlet(servletClass, path);
    }

    public void addStaticContent(String pathToStatic, String path) {
        log.info(String.format("Allocate static content (%s) to %s", pathToStatic, path));
        ServletHolder servletHolder = context.addServlet(DefaultServlet.class,
                path);
        servletHolder.setInitParameter("resourceBase", pathToStatic);
        servletHolder.setInitParameter("pathInfoOnly", "true");
    }

@Deprecated
    public void run() {
        log.info("Start jetty server");
        try {
            server.start();
        } catch (Exception e) {
            log.error("Error in Jetty server: ", e);
        }
    }

}
