package net.wazim.jordan.controller;

import net.wazim.jordan.persistence.BluRayDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JordanEmailServlet extends HttpServlet {

    private final BluRayDatabase database;
    private static final Logger log = LoggerFactory.getLogger(JordanEmailServlet.class);

    public JordanEmailServlet(BluRayDatabase database) {
        this.database = database;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String address = req.getParameter("address");

        if(title != null && address != null) {
            database.registerEmailAddressForBluRay(title, address);
            log.info(String.format("Jordan will email %s when %s becomes available", address, title));
            resp.sendRedirect("/jordan");
        }
    }
}
