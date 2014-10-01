package net.wazim.jordan.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.StringWriter;

public class JordanHttpClient {

    private final HttpClient httpClient;

    public JordanHttpClient() {
        httpClient = new HttpClient();
    }

    public JordanHttpResponse getRequest(String requestUrl) {
        HttpMethod method = new GetMethod(requestUrl);
        try {
            int responseCode = httpClient.executeMethod(method);
            InputStream responseBody = method.getResponseBodyAsStream();
            StringWriter responseBodyString = new StringWriter();
            IOUtils.copy(responseBody, responseBodyString);
            return new JordanHttpResponse(responseCode, responseBodyString.toString());
        } catch (Exception e) {
            return new JordanHttpResponse(e);
        }
    }

}
