package net.wazim.jordan.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.Proxy;
import java.net.URI;

public class JordanHttpClient {

    private Logger logger = LoggerFactory.getLogger(JordanHttpClient.class);

    private RestTemplate webClient;

    public JordanHttpClient() {
        webClient = new RestTemplate();
        webClient.setErrorHandler(new JordanResponseErrorHandler());
    }

    public JordanHttpResponse getRequest(URI requestUrl) {
        Proxy proxy = ProxyProvider.getProxy();
        logger.info("Using " + proxy.toString() + " for " + requestUrl.toASCIIString());
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);
        webClient.setRequestFactory(requestFactory);
        ResponseEntity<String> response = webClient.getForEntity(requestUrl.toASCIIString(), String.class);

        return new JordanHttpResponse(response.getStatusCode().value(), response.getBody());
    }

    private class JordanResponseErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            // no op
        }
    }

}