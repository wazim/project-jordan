package net.wazim.jordan.client;

import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

public class JordanHttpClient {

    private Logger logger = LoggerFactory.getLogger(JordanHttpClient.class);

    private RestTemplate webClient;

    public JordanHttpClient() {
        webClient = new RestTemplate();
        webClient.setErrorHandler(new JordanResponseErrorHandler());
    }

    public JordanHttpResponse getRequest(URI requestUrl) {
//        HttpHost proxy = ProxyProvider.getProxy();
//        logger.info("Using " + proxy.toString() + " for " + requestUrl.toASCIIString());
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build()
        );
        webClient.setRequestFactory(clientHttpRequestFactory);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpHeaders.set("Accept-Encoding", "gzip, deflate, sdch, br");
        httpHeaders.set("Accept-Language", "en-US,en;q=0.8");
        httpHeaders.set("User-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = webClient.exchange(requestUrl.toASCIIString(), HttpMethod.GET, requestEntity, String.class);

        return new JordanHttpResponse(response.getStatusCode().value(), response.getBody());
    }

    private class JordanResponseErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            // no op
        }
    }

}