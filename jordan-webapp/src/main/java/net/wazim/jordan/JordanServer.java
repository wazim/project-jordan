package net.wazim.jordan;

import net.wazim.jordan.controller.JordanApiServlet;
import net.wazim.jordan.controller.JordanIndexServlet;
import net.wazim.jordan.controller.JordanRemoveInterestServlet;
import net.wazim.jordan.controller.JordanStatusPageServlet;
import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.properties.JordanProperties;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JordanServer {

    private final ServletContextHandler context;
    private final Server server;

    public JordanServer(JordanProperties properties, BluRayDatabase database, int port) {
        server = new Server(port);

        context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/jordan");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new JordanIndexServlet(database)), "");
        context.addServlet(new ServletHolder(new JordanStatusPageServlet()), "/status");
        context.addServlet(new ServletHolder(new JordanApiServlet(database)), "/api/all");
        context.addServlet(new ServletHolder(new JordanRemoveInterestServlet(database)), "/not-interested");

        try {
            server.start();
        } catch (Exception e) {
            System.out.println("Jordan server failed to start! " + e);
        }
    }


    public void stopServer() {
        try {
            server.stop();
        } catch (Exception e) {
            System.out.println("Can't stop Jordan " + e);
            e.printStackTrace();
        }
    }

}
