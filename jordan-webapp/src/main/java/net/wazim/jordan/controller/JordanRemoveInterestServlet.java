package net.wazim.jordan.controller;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.eclipse.jetty.http.HttpStatus.OK_200;

public class JordanRemoveInterestServlet extends HttpServlet {

    private final BluRayDatabase database;
    private static final Logger log = LoggerFactory.getLogger(JordanRemoveInterestServlet.class);

    public JordanRemoveInterestServlet(BluRayDatabase database) {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(OK_200);

        int movieId = Integer.parseInt(req.getParameter("movie"));
        BluRay bluRay = database.findBluRayById(movieId);
        database.removeInterest(movieId);

        log.info(String.format("User has marked %s as not interesting (%s)", bluRay.getName(), movieId));
        resp.getWriter().println(bluRay.getName() + " is deemed not interesting");
        resp.sendRedirect("/jordan");
    }

}
