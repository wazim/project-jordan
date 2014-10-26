package net.wazim.jordan.utils;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.domain.BluRay;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

                String address = requestUrl.toString();
                String newPage = "sr_pg_" + currentPage;
                String secondNewPage = "page=" + currentPage;
                address.replace("sr_pg_1", newPage);
                address.replace("page=1", secondNewPage);
                requestUrl.resolve(address);
                String newAddress = address.replace("sr_pg_1", newPage);
                newAddress = newAddress.replace("page=1", secondNewPage);

                try {
                    nextPageResponse = new JordanHttpClient().getRequest(new URIBuilder().setPath(newAddress).addParameter("page", String.valueOf(currentPage++)).build());
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

            double priceRange = 1.25;
            double zeroValue = 0.00;

            if (((Double.compare(getBluRayUsedPrice(bluRayElement), priceRange) == -1) || (Double.compare(getBluRayUsedPrice(bluRayElement), priceRange) == -1)) &&
                    !(Double.compare(getBluRayPrice(bluRayElement), zeroValue) == 0) && !(Double.compare(getBluRayUsedPrice(bluRayElement), zeroValue) == 0)) {
                listOfBluRays.add(new BluRay(
                        bluRayName,
                        getBluRayPrice(bluRayElement),
                        getBluRayUsedPrice(bluRayElement),
                        getBluRayUrl(bluRayElement),
                        false));

                log.info(String.format("Added %s to the database", bluRayName));
            } else {

            }

        }
    }

    private static double getBluRayUsedPrice(Element bluRayElement) {
        Elements price = bluRayElement.getElementsByClass("price");

        if (price.isEmpty() || price.size() == 1) {
            return 0.00;
        }

        return Double.parseDouble(price.get(1).text().replaceAll("£", ""));
    }

    private static double getBluRayPrice(Element bluRayElement) {
        Elements price = bluRayElement.getElementsByClass("price");

        if (price.isEmpty() || price.size() == 0) {
            return 0.00;
        }

        return Double.parseDouble(price.first().text().replaceAll("£", ""));
    }

    private static String getBluRayName(Element bluRayElement) {
        System.out.println("bluRayElement = " + bluRayElement.getElementsByClass("newaps").first().getElementsByClass("bold"));
        return bluRayElement.getElementsByClass("bold").first().text();
    }

    private static String getBluRayUrl(Element bluRayElement) {
        return bluRayElement.getElementsByClass("newaps").first().getElementsByAttribute("href").attr("href");
    }
}
