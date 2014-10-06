package net.wazim.jordan.utils;

import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.domain.BluRay;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class BluRayParser {

    public static ArrayList<BluRay> parseIntoBluRays(JordanHttpResponse response, int pageNo) {
        ArrayList<BluRay> listOfBluRays = new ArrayList<BluRay>();
        String responseAsString = response.getResponseBody();
        for (int i = ((24*pageNo) - 24); i < (24*pageNo); i++) {
            if (responseAsString.contains("result_" + i)) {
                int resultPosition = responseAsString.indexOf("<div id=\"result_" + i + "\"");
                int resultPositionOfNextBluRay = responseAsString.indexOf("<div id=\"result_" + (i + 1) + "\"");
                if (resultPosition > 0 && resultPositionOfNextBluRay > 0) {
                    String result = responseAsString.substring(resultPosition, resultPositionOfNextBluRay);
                    Document parse = Jsoup.parse(result);
                    Elements bluRayName = parse.select(".bold");
                    Elements bluRayPrice = parse.select(".price");
                    if (bluRayPrice.size() == 1) {
                        listOfBluRays.add(new BluRay(bluRayName.first().text(), "£0.00", "£0.00", false));
                    } else {
                        listOfBluRays.add(new BluRay(bluRayName.first().text(), bluRayPrice.first().text(), bluRayPrice.get(1).text(), false));
                    }
                }
            }
        }
        return listOfBluRays;
    }

}
