package net.wazim.jordan.stub;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AmazonStub extends HttpServlet {

    private AmazonServlet servlet;
    private Server server;
    private final ServletContextHandler context;

    public AmazonStub() {
        server = new Server(11511);

        context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        servlet = new AmazonServlet(200, "OK");
        context.addServlet(new ServletHolder(servlet), "/amazon");

        startServer();
    }

    private void startServer() {
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createPageAndPrimeResponse(String path, int primedResponseCode, String primedResponseBody) {
        context.addServlet(new ServletHolder(new AmazonServlet(primedResponseCode, primedResponseBody)), path);
    }

    private static class AmazonServlet extends HttpServlet {

        private int responseCode;
        private String responseBody;

        public AmazonServlet(int responseCode, String responseBody) {
            this.responseCode = responseCode;
            this.responseBody = responseBody;
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html");
            resp.setStatus(responseCode);
            resp.getWriter().println(responseBody);
        }

    }
}
