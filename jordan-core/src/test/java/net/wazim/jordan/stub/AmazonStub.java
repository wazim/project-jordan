package net.wazim.jordan.stub;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AmazonStub extends HttpServlet {

    public AmazonStub(){
        Server server = new Server(11511);
        ServletHandler handler = new ServletHandler();

        server.setHandler(handler);

        handler.addServletWithMapping(AmazonStub.class, "/");

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
