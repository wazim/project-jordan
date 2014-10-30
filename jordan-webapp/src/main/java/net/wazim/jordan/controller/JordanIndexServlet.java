package net.wazim.jordan.controller;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.util.FreemarkerTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static org.eclipse.jetty.http.HttpStatus.OK_200;

public class JordanIndexServlet extends HttpServlet {

    public static final String ALL = "ALL";
    private static final String SELECT = "ONLY INTERESTED";
    private final BluRayDatabase database;

    public JordanIndexServlet(BluRayDatabase database) {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(OK_200);

//        allBluRays.sort(compareNamesAlphabetically());

        List<BluRay> allBluRays;
        String displaying;

        if(ALL.equalsIgnoreCase(req.getParameter("include"))){
            allBluRays = database.getAllBluRays();
            displaying = ALL;
        }
        else {
            allBluRays = database.getAllInterestingBluRays();
            displaying = SELECT;
        }

        resp.getWriter().println(new FreemarkerTemplate("index.ftl")
                .with("numOfBluRays", String.valueOf(allBluRays.size()))
                .with("blurays", allBluRays)
                .with("displaying", displaying)
                .processTemplate());

    }

    private Comparator<BluRay> compareNamesAlphabetically() {
        return new Comparator<BluRay>() {
            @Override
            public int compare(BluRay o1, BluRay o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }

}
