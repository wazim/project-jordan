package net.wazim.jordan;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.domain.BluRay;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static net.wazim.jordan.utils.BluRayParser.parseIntoBluRays;

public class AmazonGoer {

    private static final Logger log = Logger.getLogger(AmazonGoer.class.getName());

    private JordanHttpResponse response;
    private ArrayList<BluRay> bluRays;

    public AmazonGoer() {
        bluRays = new ArrayList<BluRay>();
    }

    public void go(URI requestUrl) {
        JordanHttpClient client = new JordanHttpClient();

        response = client.getRequest(requestUrl);
//        log.info(response.getResponseBody());
        bluRays = parseIntoBluRays(response, requestUrl);

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
