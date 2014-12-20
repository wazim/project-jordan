package net.wazim.jordan.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

public class JordanHttpClient {

    private RestTemplate webClient;
    private static final Logger log = LoggerFactory.getLogger(JordanHttpClient.class);

    public JordanHttpClient() {
        webClient = new RestTemplate();
        webClient.setErrorHandler(new JordanResponseErrorHandler());
    }

    public JordanHttpResponse getRequest(URI requestUrl) {
        ResponseEntity<String> response = webClient.getForEntity(requestUrl.toASCIIString(), String.class);

        return new JordanHttpResponse(response.getStatusCode().value(), response.getBody());
    }


    private class JordanResponseErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            log.error("Response error");
        }
    }

}