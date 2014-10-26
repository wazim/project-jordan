package net.wazim.jordan.controller;

import net.wazim.jordan.persistence.BluRayDatabase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.eclipse.jetty.http.HttpStatus.OK_200;

public class JordanRemoveInterestServlet extends HttpServlet {

    private final BluRayDatabase database;

    public JordanRemoveInterestServlet(BluRayDatabase database) {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(OK_200);

        String movie = req.getParameter("movie");

        database.removeInterest(movie);

        resp.getWriter().println(movie + " is deemed not interesting");
        resp.sendRedirect("/jordan");
    }

}
