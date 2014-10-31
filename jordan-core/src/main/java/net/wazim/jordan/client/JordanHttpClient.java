package net.wazim.jordan.client;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

import static net.wazim.jordan.client.fixtures.JordanHttpErrorHandler.newJordanCssErrorHandler;
import static net.wazim.jordan.client.fixtures.JordanHttpErrorHandler.newJordanJavascriptErrorHandler;

public class JordanHttpClient {

    private final WebClient webClient;
    private static final Logger log = LoggerFactory.getLogger(JordanHttpClient.class);

    public JordanHttpClient() {
        webClient = new WebClient(BrowserVersion.CHROME);

        webClient.setCssErrorHandler(newJordanCssErrorHandler());
        webClient.setJavaScriptErrorListener(newJordanJavascriptErrorHandler());
        webClient.setJavaScriptTimeout(15000);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setJavaScriptEnabled(false);
    }

    public JordanHttpResponse getRequest(URI requestUrl) {
        HtmlPage page;
        String pageAsXml = null;
        int responseCode = 0;
        try {
            page = webClient.getPage(String.valueOf(requestUrl));
            pageAsXml = page.asXml();
            responseCode = page.getWebResponse().getStatusCode();
        } catch (IOException e) {
            log.error(String.format("Failed to connect to page (%s)",  Thread.currentThread().getName()));
            log.debug(e.toString());
        }

        return new JordanHttpResponse(responseCode, pageAsXml);
    }

}