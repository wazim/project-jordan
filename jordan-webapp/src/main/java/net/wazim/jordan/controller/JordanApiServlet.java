package net.wazim.jordan.controller;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
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
            bluray.addElement("li").addText(bluRay.getName());
            //bluray.addElement("usedPrice").addText(Double.toString(bluRay.getPriceUsed()));
            //bluray.addElement("newPrice").addText(Double.toString(bluRay.getPriceNew()));
        }

        StringWriter out = new StringWriter();
        OutputFormat outFormat = OutputFormat.createPrettyPrint();
        outFormat.setXHTML(true);
        outFormat.setNewlines(true);
        outFormat.setTrimText(true);
        outFormat.setIndent("");
        HTMLWriter writer = new HTMLWriter(out,outFormat);
        writer.write(document);
        writer.flush();

        resp.getWriter().println("<html><body>");
        resp.getWriter().println("<ul>");
        resp.getWriter().println(out.toString());
        resp.getWriter().println("</ul>");
        resp.getWriter().println("</body></html>");
    }
}
