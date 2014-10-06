package net.wazim.jordan.utils;

import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.domain.BluRay;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class BluRayParser {

    public static ArrayList<BluRay> parseIntoBluRays(JordanHttpResponse response) {
        ArrayList<BluRay> listOfBluRays = new ArrayList<BluRay>();
        String responseAsString = response.getResponseBody();
        for (int i = 0; i < 24; i++) {
            if (responseAsString.contains("result_" + i)) {
                int resultPosition = responseAsString.indexOf("<div id=\"result_" + i + "\"");
                int resultPositionOfNextBluRay = responseAsString.indexOf("<div id=\"result_" + (i + 1) + "\"");
                if (resultPosition > 0 && resultPositionOfNextBluRay > 0) {
                    String result = responseAsString.substring(resultPosition, resultPositionOfNextBluRay);
                    Document parse = Jsoup.parse(result);
                    System.out.print(parse);
                    Elements bluRayName = parse.select(".bold");
                    Elements bluRayPrice = parse.select(".price");
                    listOfBluRays.add(new BluRay(bluRayName.first().text(), bluRayPrice.first().text(), bluRayPrice.get(1).text(), false));
                }
            }
        }
        return listOfBluRays;
    }

}
