package net.wazim.jordan.utils;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.domain.BluRay;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class BluRayParser {

    private static final Logger log = Logger.getLogger(BluRayParser.class.getName());

    public static ArrayList<BluRay> listOfBluRays = new ArrayList<BluRay>();

    public static ArrayList<BluRay> parseIntoBluRays(JordanHttpResponse response, URI requestUrl) {
        String responseAsString = response.getResponseBody();

        if (!Jsoup.parse(responseAsString).getElementsByClass("pagnCur").isEmpty()) {
            int currentPage = Integer.parseInt(Jsoup.parse(responseAsString).getElementsByClass("pagnCur").first().text());
            int lastPage = Integer.parseInt(Jsoup.parse(responseAsString).getElementsByClass("pagnDisabled").first().text());

            createBluRaysFromHtml(responseAsString);

            while (currentPage < lastPage) {
                log.info(String.format("Page %d of %d", currentPage, lastPage));
                JordanHttpResponse nextPageResponse = null;
                try {
                    nextPageResponse = new JordanHttpClient().getRequest(new URIBuilder().setPath(requestUrl.toString()).addParameter("page", String.valueOf(currentPage++)).build());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                createBluRaysFromHtml(nextPageResponse.getResponseBody());
            }
        }

        return listOfBluRays;
    }

    //TODO: [Jon] Refactor this as it is ugly the way it concatenates lists.
    private static void createBluRaysFromHtml(String responseAsString) {
        Elements firstRowBluRayElements = Jsoup.parse(responseAsString).getElementsByClass("fstRowGrid");
        Elements remainingBluRays = Jsoup.parse(responseAsString).getElementsByClass("rsltGrid");

        List<Element> allBluRays = new ArrayList<Element>();

        for (Element firstRowBluRayElement : firstRowBluRayElements) {
            allBluRays.add(firstRowBluRayElement);
        }

        for (Element remainingBluRay : remainingBluRays) {
            allBluRays.add(remainingBluRay);
        }

        for (Element bluRayElement : allBluRays) {
            String bluRayName = getBluRayName(bluRayElement);

            listOfBluRays.add(new BluRay(
                    bluRayName,
                    getBluRayPrice(bluRayElement),
                    getBluRayUsedPrice(bluRayElement),
                    false));

            log.info(String.format("Added %s to the database", bluRayName));
        }
    }

    private static BigDecimal getBluRayUsedPrice(Element bluRayElement) {
        Elements price = bluRayElement.getElementsByClass("price");

        if (price.isEmpty() || price.size() == 1) {
            BigDecimal nullPrice = new BigDecimal(0.00);
            return nullPrice;
        }
        BigDecimal usedPrice = new BigDecimal(price.get(1).text().replaceAll("£", ""));

        return usedPrice;
    }

    private static BigDecimal getBluRayPrice(Element bluRayElement) {
        Elements price = bluRayElement.getElementsByClass("price");

        if (price.isEmpty() || price.size() == 0) {
            BigDecimal nullPrice = new BigDecimal(0.00);
            return nullPrice;
        }
        BigDecimal newPrice = new BigDecimal(price.first().text().replaceAll("£", ""));

        return newPrice;
    }

    private static String getBluRayName(Element bluRayElement) {
        return bluRayElement.getElementsByClass("bold").first().text();
    }

}
