package net.wazim.jordan;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

import static org.jsoup.Jsoup.parse;

public class JordanListingUpdater {

    private static final Logger log = LoggerFactory.getLogger(JordanListingUpdater.class);

    private final BluRayDatabase database;

    public JordanListingUpdater(BluRayDatabase database) {
        this.database = database;
    }

    public void updateFilms() {
        log.info("Running Jordan Updater");
        List<BluRay> allBluRays = database.getAllBluRays();
        allBluRays.forEach(this::updateBluRay);
        log.info("Jordan updater completed.");
    }

    private void updateBluRay(BluRay bluRay) {
        JordanHttpClient client = new JordanHttpClient();
        JordanHttpResponse response = client.getRequest(URI.create(bluRay.getUrl()));
        if (response.getResponseCode() == 200 && bluRay.getIsInteresting()) {
            Document document = parse(response.getResponseBody());
            updateUsedPrice(bluRay, document);
            updateNewPrice(bluRay, document);
            deleteBluRayIfOutOfPriceRange(bluRay, document);
        } else {
            log.info("Failed to connect to Amazon on this occasion");
        }
    }

    private void deleteBluRayIfOutOfPriceRange(BluRay bluRay, Document document) {
        try {
            double updatedNewPrice = Double.parseDouble(document.getElementsByClass("olp-padding-right").get(0).getElementsByClass("a-color-price").get(0).text().replace("£", ""));
            double updatedUsedPrice = Double.parseDouble(document.getElementsByClass("olp-padding-right").get(1).getElementsByClass("a-color-price").get(0).text().replace("£", ""));

            if (updatedNewPrice > 1.24 && updatedUsedPrice > 1.24) {
                database.deleteBluRay(bluRay);
                log.info("Deleted " + bluRay.getName() + " ("+bluRay.getUrl()+") - " +
                        "Reason: New Price: " + updatedNewPrice + " & Used Price: "+updatedUsedPrice);
            }
        } catch (Exception e) {
            log.warn("Could not parse the Amazon Page for " + bluRay.getName());
        }

    }

    private void updateNewPrice(BluRay bluRay, Document document) {
        try {
            double updatedNewPrice = Double.parseDouble(document.getElementsByClass("a-color-price").get(1).text().replace("£", ""));

            if (updatedNewPrice < bluRay.getPriceNew()) {
                bluRay.setPriceNew(updatedNewPrice);
                database.updateBluray(bluRay);
                log.info("Updated new price of " + bluRay.getName());
            }
        } catch (Exception e) {
            log.warn("Could not parse the Amazon Page for " + bluRay.getName());
        }
    }

    private void updateUsedPrice(BluRay bluRay, Document document) {
        try {
            double updatedUsedPrice = Double.parseDouble(document.getElementsByClass("a-color-price").get(2).text().replace("£", ""));

            if (updatedUsedPrice < bluRay.getPriceUsed()) {
                bluRay.setPriceUsed(updatedUsedPrice);
                database.updateBluray(bluRay);
                log.info("Updated used price of " + bluRay.getName());
            }
        } catch (Exception e) {
            log.warn("Could not parse the Amazon Page for " + bluRay.getName());
        }
    }
}
