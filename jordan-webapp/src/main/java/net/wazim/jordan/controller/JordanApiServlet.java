package net.wazim.jordan.controller;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.util.FreemarkerTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JordanApiServlet extends HttpServlet {

    private final BluRayDatabase database;

    public JordanApiServlet(BluRayDatabase database) {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BluRay> allBluRays = database.getAllBluRays();
        resp.getWriter().println(new FreemarkerTemplate("api.ftl").with("blurays", allBluRays).processTemplate());
    }
}
