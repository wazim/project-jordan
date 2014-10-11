package net.wazim.jordan;

import net.wazim.jordan.controller.JordanIndexServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JordanServer {

    private final ServletContextHandler context;
    private final Server server;

    public JordanServer() {
        server = new Server(12500);

        context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new JordanIndexServlet()), "/*");

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
