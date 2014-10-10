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

public class BluRayParser {

    public static ArrayList<BluRay> listOfBluRays = new ArrayList<BluRay>();

    public static ArrayList<BluRay> parseIntoBluRays(JordanHttpResponse response, URI requestUrl) {
        String responseAsString = response.getResponseBody();

        if (!Jsoup.parse(responseAsString).getElementsByClass("pagnCur").isEmpty()) {
            int currentPage = Integer.parseInt(Jsoup.parse(responseAsString).getElementsByClass("pagnCur").first().text());
            int lastPage = Integer.parseInt(Jsoup.parse(responseAsString).getElementsByClass("pagnDisabled").first().text());

            createBluRaysFromHtml(responseAsString);

            while (currentPage < lastPage) {
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
            listOfBluRays.add(new BluRay(
                    getBluRayName(bluRayElement),
                    getBluRayPrice(bluRayElement),
                    getBluRayUsedPrice(bluRayElement),
                    false));
        }
    }

    private static String getBluRayUsedPrice(Element bluRayElement) {
        String price = bluRayElement.getElementsByClass("price").get(1).text();
        if (price == null) {
            return "£0.00";
        }
        return price;
    }

    private static String getBluRayPrice(Element bluRayElement) {
        String price = bluRayElement.getElementsByClass("price").first().text();
        if (price == null) {
            return "£0.00";
        }
        return price;
    }

    private static String getBluRayName(Element bluRayElement) {
        return bluRayElement.getElementsByClass("bold").first().text();
    }

}
