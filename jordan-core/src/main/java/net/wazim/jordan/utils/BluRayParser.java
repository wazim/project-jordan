package net.wazim.jordan.utils;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.client.MetacriticRatingRetriever;
import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static net.wazim.jordan.utils.BluRayNameCleaner.cleanName;

public class BluRayParser {

    private static final Logger log = LoggerFactory.getLogger(BluRayParser.class);

    public static void parseIntoBluRays(JordanHttpResponse response, URI requestUrl, BluRayDatabase database) {
        String responseAsString = response.getResponseBody();

        if (!Jsoup.parse(responseAsString).getElementsByClass("pagnCur").isEmpty()) {
            int currentPage = Integer.parseInt(Jsoup.parse(responseAsString).getElementsByClass("pagnCur").first().text());
            int lastPage = Integer.parseInt(Jsoup.parse(responseAsString).getElementsByClass("pagnDisabled").first().text());

            createBluRaysFromHtml(responseAsString, database);

            while (currentPage <= lastPage) {
                log.debug(String.format("Page %d of %d", currentPage, lastPage));
                createABluRay(requestUrl, database, currentPage + 1);
                currentPage++;
            }
        }
    }

    private static int createABluRay(final URI requestUrl, final BluRayDatabase database, final int currentPage) {
        new Thread("" + currentPage) {
            public void run() {
                try {
                    JordanHttpResponse nextPageResponse = new JordanHttpClient().getRequest(new URI(requestUrl + "&page=" + currentPage));
                    createBluRaysFromHtml(nextPageResponse.getResponseBody(), database);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return currentPage;
    }

    private static void createBluRaysFromHtml(String responseAsString, BluRayDatabase database) {
        try {
            Elements firstRowBluRayElements = Jsoup.parse(responseAsString).getElementsByClass("fstRowGrid");
            Elements remainingBluRays = Jsoup.parse(responseAsString).getElementsByClass("rsltGrid");

            List<Element> allBluRays = firstRowBluRayElements.stream().collect(Collectors.toList());

            allBluRays.addAll(remainingBluRays.stream().collect(Collectors.toList()));

            for (Element bluRayElement : allBluRays) {
                String bluRayName = getBluRayName(bluRayElement);

                double priceRange = 1.25;
                double zeroValue = 0.00;

                if (((Double.compare(getBluRayUsedPrice(bluRayElement), priceRange) == -1) || (Double.compare(getBluRayUsedPrice(bluRayElement), priceRange) == -1)) &&
                        !(Double.compare(getBluRayPrice(bluRayElement), zeroValue) == 0) && !(Double.compare(getBluRayUsedPrice(bluRayElement), zeroValue) == 0)) {
                    BluRay newBluRay = new BluRay(
                            bluRayName,
                            getBluRayPrice(bluRayElement),
                            getBluRayUsedPrice(bluRayElement),
                            getBluRayUrl(bluRayElement),
                            true,
                            100);

                    database.saveBluRay(newBluRay);
                } else {
                    log.debug(String.format("Could not add Blu ray from response: %s", responseAsString));
                }
            }
        } catch (Exception e) {
            log.error("Could not parse content " + responseAsString);
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
