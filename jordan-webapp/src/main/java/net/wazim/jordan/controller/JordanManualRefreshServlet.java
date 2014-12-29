package net.wazim.jordan.controller;

import net.wazim.jordan.JordanListingUpdater;
import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JordanManualRefreshServlet extends HttpServlet {

    private final BluRayDatabase database;
    private static final Logger log = LoggerFactory.getLogger(JordanManualRefreshServlet.class);


    public JordanManualRefreshServlet(BluRayDatabase database) {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movie = req.getParameter("movie");
        if(movie != null) {
            int movieId = Integer.parseInt(movie);
            BluRay bluRayById = database.findBluRayById(movieId);
            double originalNewPrice = bluRayById.getPriceNew();
            double originalUsedPrice = bluRayById.getPriceUsed();

            log.info(String.format("Manually updating %s", bluRayById.getName()));
            new JordanListingUpdater(database).updateBluRay(bluRayById);
            BluRay updatedBluRay = database.findBluRayById(movieId);
            resp.setStatus(200);
            if(updatedBluRay.getPriceUsed() == originalUsedPrice &&
                   updatedBluRay.getPriceNew() == originalNewPrice) {
                log.info(String.format("Not updating %s", bluRayById.getName()));
            } else {
                resp.getWriter().println(String.format("Updated\nNew Price: %s\nUsed Price: %s", updatedBluRay.getPriceNew(), updatedBluRay.getPriceUsed()));
            }
        }
    }


}
