package net.wazim.jordan;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import static org.jsoup.Jsoup.parse;

public class JordanListingUpdater {

    private static final Logger log = Logger.getLogger(JordanListingUpdater.class.getName());

    private final BluRayDatabase database;

    public JordanListingUpdater(BluRayDatabase database) {
        this.database = database;
    }

    public void updateFilms() {
        List<BluRay> allBluRays = database.getAllBluRays();
        for (BluRay bluRay : allBluRays) {
            updateBluRay(bluRay);
        }
    }

    private void updateBluRay(BluRay bluRay) {
        JordanHttpClient client = new JordanHttpClient();
        JordanHttpResponse response = client.getRequest(URI.create(bluRay.getUrl()));
        if (response.getResponseCode() == 200) {
            Document document = parse(response.getResponseBody());
            updateUsedPrice(bluRay, document);
            updateNewPrice(bluRay, document);
            deleteBluRayIfOutOfPriceRange(bluRay, document);
        } else {
            log.info("Failed to connect to Amazon on this occasion");
        }
    }

    private void deleteBluRayIfOutOfPriceRange(BluRay bluRay, Document document) {
        double updatedNewPrice = Double.parseDouble(document.getElementsByClass("a-color-price").get(1).text().replace("£", ""));
        double updatedUsedPrice = Double.parseDouble(document.getElementsByClass("a-color-price").get(2).text().replace("£", ""));

        if (updatedNewPrice > 1.24 && updatedUsedPrice > 1.24) {
            database.deleteBluRay(bluRay);
            log.info("Deleted " + bluRay.getName());
        }
    }

    private void updateNewPrice(BluRay bluRay, Document document) {
        double updatedNewPrice = Double.parseDouble(document.getElementsByClass("a-color-price").get(1).text().replace("£", ""));

        if (updatedNewPrice < bluRay.getPriceNew()) {
            bluRay.setPriceNew(updatedNewPrice);
            database.updateBluray(bluRay);
            log.info("Updated new price of " + bluRay.getName());
        }
    }

    private void updateUsedPrice(BluRay bluRay, Document document) {
        double updatedUsedPrice = Double.parseDouble(document.getElementsByClass("a-color-price").get(2).text().replace("£", ""));

        if (updatedUsedPrice < bluRay.getPriceUsed()) {
            bluRay.setPriceUsed(updatedUsedPrice);
            database.updateBluray(bluRay);
            log.info("Updated used price of " + bluRay.getName());
        }
    }
}
