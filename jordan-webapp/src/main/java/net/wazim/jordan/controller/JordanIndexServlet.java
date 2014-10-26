package net.wazim.jordan.controller;

import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.util.FreemarkerTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.eclipse.jetty.http.HttpStatus.OK_200;

public class JordanIndexServlet extends HttpServlet {

    private final BluRayDatabase database;

    public JordanIndexServlet(BluRayDatabase database) {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(OK_200);

        resp.getWriter().println(new FreemarkerTemplate("index.ftl")
                .with("numOfBluRays", String.valueOf(database.getAllBluRays().size()))
                .with("blurays", database.getAllBluRays())
                .processTemplate());
    }

}
