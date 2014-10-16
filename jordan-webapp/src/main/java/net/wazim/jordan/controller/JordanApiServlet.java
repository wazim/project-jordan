package net.wazim.jordan.controller;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

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

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("blurays");

        for (BluRay bluRay : allBluRays) {
            Element bluray = root.addElement("bluray");
            bluray.addElement("name").addText(bluRay.getName());
            bluray.addElement("usedPrice").addText(bluRay.getPriceUsed().toString());
            bluray.addElement("newPrice").addText(bluRay.getPriceNew().toString());
        }

        resp.getWriter().println(document.asXML());
    }
}
