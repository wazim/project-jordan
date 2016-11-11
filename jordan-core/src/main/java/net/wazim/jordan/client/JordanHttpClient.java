package net.wazim.jordan.client;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;

public class JordanHttpClient {

    private RestTemplate webClient;

    public JordanHttpClient() {
        webClient = new RestTemplate();
        webClient.setErrorHandler(new JordanResponseErrorHandler());
    }

    public JordanHttpResponse getRequest(URI requestUrl) {
        Proxy proxy = ProxyProvider.getProxy();
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