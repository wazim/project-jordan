package net.wazim.jordan.controller;

import net.wazim.jordan.util.FreemarkerTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.eclipse.jetty.http.HttpStatus.OK_200;

public class JordanIndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(OK_200);

        resp.getWriter().println(new FreemarkerTemplate("index.ftl").processTemplate());
    }

}
