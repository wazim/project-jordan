package net.wazim.jordan.utils;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.client.MetacriticRatingRetriever;
import net.wazim.jordan.domain.BluRay;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static net.wazim.jordan.utils.BluRayNameCleaner.cleanName;

public class BluRayParser {

    private static final Logger log = LoggerFactory.getLogger(BluRayParser.class);

    public static ArrayList<BluRay> listOfBluRays = new ArrayList<BluRay>();

    public static ArrayList<BluRay> parseIntoBluRays(JordanHttpResponse response, URI requestUrl) {
        String responseAsString = response.getResponseBody();

        if (!Jsoup.parse(responseAsString).getElementsByClass("pagnCur").isEmpty()) {
            int currentPage = Integer.parseInt(Jsoup.parse(responseAsString).getElementsByClass("pagnCur").first().text());
            int lastPage = Integer.parseInt(Jsoup.parse(responseAsString).getElementsByClass("pagnDisabled").first().text());

            createBluRaysFromHtml(responseAsString);

            while (currentPage <= lastPage) {
                log.info(String.format("Page %d of %d", currentPage, lastPage));
                JordanHttpResponse nextPageResponse = null;

                try {
                    nextPageResponse = new JordanHttpClient().getRequest(new URI(requestUrl + "&page=" + currentPage++));
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
                        true,
                        getMetacriticScore(bluRayName)));

                log.info(String.format("Added %s to the database", bluRayName));
            } else {
                //No op
            }

        }
    }

    private static int getMetacriticScore(String bluRayName) {
        return new MetacriticRatingRetriever().getScoreFor(bluRayName);
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
        return cleanName(bluRayElement.getElementsByClass("bold").first().text());
    }

    private static String getBluRayUrl(Element bluRayElement) {
        return bluRayElement.getElementsByClass("newaps").first().getElementsByAttribute("href").attr("href");
    }
}
