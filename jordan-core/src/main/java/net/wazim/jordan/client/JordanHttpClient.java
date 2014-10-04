package net.wazim.jordan.client;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import static net.wazim.jordan.client.fixtures.JordanHttpErrorHandler.newJordanCssErrorHandler;
import static net.wazim.jordan.client.fixtures.JordanHttpErrorHandler.newJordanJavascriptErrorHandler;

public class JordanHttpClient {

    private final WebClient webClient;

    public JordanHttpClient() {
        webClient = new WebClient();
        webClient.setCssErrorHandler(newJordanCssErrorHandler());
        webClient.setJavaScriptErrorListener(newJordanJavascriptErrorHandler());
        webClient.setJavaScriptTimeout(15000);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);

    }

    public JordanHttpResponse getRequest(URI requestUrl) {
        HtmlPage page;
        String pageAsXml = null;
        int responseCode = 0;
        try {
            page = webClient.getPage(String.valueOf(requestUrl));
            pageAsXml = page.asXml();
            responseCode = page.getWebResponse().getStatusCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JordanHttpResponse(responseCode, pageAsXml);
    }

}