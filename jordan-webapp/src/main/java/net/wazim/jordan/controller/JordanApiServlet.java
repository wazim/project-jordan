package net.wazim.jordan.controller;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;

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
        List<BluRay> allBluRays;
        String limit = req.getParameter("limit");

        if ("INTERESTED".equals(limit != null ? limit.toUpperCase() : limit)) {
            allBluRays = database.getAllInterestingBluRays();
        } else {
            allBluRays = database.getAllBluRays();
        }

        String format = req.getParameter("format");
        if (format == null) {
            format = "xml";
        }

        if ("json".equals(format.toLowerCase())) {
            resp.setContentType("application/json");
            resp.getWriter().println(getJson(allBluRays).toString());
        } else {
            resp.setContentType("application/xml");
            resp.getWriter().println(getXml(allBluRays).asXML());
        }

    }

    private JSONArray getJson(List<BluRay> allBluRays) {
        JSONArray allMovies = new JSONArray();
        for (BluRay bluRay : allBluRays) {
            JSONObject movie = new JSONObject();
            movie.put("name", bluRay.getName());
            movie.put("usedPrice", bluRay.getPriceUsed());
            movie.put("newPrice", bluRay.getPriceNew());
            movie.put("url", bluRay.getUrl());
            allMovies.put(movie.toString());
        }
        return allMovies;
    }

    private Document getXml(List<BluRay> allBluRays) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("blurays");

        for (BluRay bluRay : allBluRays) {
            Element bluray = root.addElement("bluray");
            bluray.addElement("name").addText(bluRay.getName());
            bluray.addElement("usedPrice").addText(Double.toString(bluRay.getPriceUsed()));
            bluray.addElement("newPrice").addText(Double.toString(bluRay.getPriceNew()));
        }
        return document;
    }
}
