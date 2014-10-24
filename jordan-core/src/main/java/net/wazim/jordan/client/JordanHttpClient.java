package net.wazim.jordan.client;

import com.gargoylesoftware.htmlunit.BrowserVersion;
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
        webClient = new WebClient(BrowserVersion.CHROME);

//        HtmlPage currentPage = null;
//        try {
//            currentPage = webClient.getPage("http://www.amazon.co.uk/s/ref=sr_pg_1?rh=n%3A283926%2Cn%3A%21573408%2Cn%3A%21712388%2Cn%3A293962011%2Cp_n_binding_browse-bin%3A383380011%2Cp_36%3A-850&page=1&bbn=293962011&ie=UTF8&qid=1413304845");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        webClient.setCssErrorHandler(newJordanCssErrorHandler());
        webClient.setJavaScriptErrorListener(newJordanJavascriptErrorHandler());
        webClient.setJavaScriptTimeout(15000);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setJavaScriptEnabled(false);

    }

    public JordanHttpResponse getRequest(URI requestUrl) {
        HtmlPage page = null;
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