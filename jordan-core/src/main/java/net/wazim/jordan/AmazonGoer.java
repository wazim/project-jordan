package net.wazim.jordan;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.domain.BluRay;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static net.wazim.jordan.utils.BluRayParser.numberOfPages;
import static net.wazim.jordan.utils.BluRayParser.parseIntoBluRays;

public class AmazonGoer {

    private static final Logger log = Logger.getLogger(AmazonGoer.class.getName());

    private JordanHttpResponse response;
    private ArrayList<BluRay> bluRays;

    //temporary variable to store the number of pages in our search, need
    //a proper way to figure it out
    private int pageNo;

    public AmazonGoer() {
        bluRays = new ArrayList<BluRay>();
    }

    public void go(URI requestUrl) {
        JordanHttpClient client = new JordanHttpClient();

        String address1 = requestUrl.toString();
        URI newUri1 = requestUrl.resolve(address1);
        response = client.getRequest(newUri1);
        log.info(response.getResponseBody());
        bluRays = parseIntoBluRays(response, 1);
        pageNo = numberOfPages(response);

        for (int i = 2; i < (pageNo + 1); i++) {

            String address = requestUrl.toString();
            String newPage = "sr_pg_" + i;
            String secondNewPage = "page=" + i;
            String newAddress = address.replace("sr_pg_1", newPage);
            newAddress = newAddress.replace("page=1", secondNewPage);
            URI newUri = requestUrl.resolve(newAddress);
            response = client.getRequest(newUri);
            log.info(response.getResponseBody());
            bluRays = parseIntoBluRays(response, i);

        }
    }

    public int responseCode() {
        return response.getResponseCode();
    }

    public String responseBody() {
        return response.getResponseBody();
    }

    public List<BluRay> bluRays() {
        return bluRays;
    }

}
