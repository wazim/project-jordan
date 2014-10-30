package net.wazim.jordan;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.properties.JordanTestSpecificProperties;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JordanItemRemovalTest {

    private JordanServer jordanServer;
    private HttpClient httpClient;
    private HttpMethod method;
    private InMemoryPersistableDatabase database;

    @Before
    public void setupJordanServer() {
        database = new InMemoryPersistableDatabase();

        jordanServer = new JordanServer(new JordanTestSpecificProperties(), database);
        httpClient = new HttpClient();
        method = new GetMethod("http://localhost:12500/jordan");
    }

    @After
    public void shutdownJordanServer() {
        jordanServer.stopServer();
    }

    @Test
    public void bluRayWithAMinusSymbolIsRemovedIfItIsNotInteresting() throws IOException {
        database.saveBluRay(newBluRay("The Godfather -"));

        method = new GetMethod("http://localhost:12500/jordan");
        int responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("We currently have <span class=\"librarySize\">1</span> Blu Rays in our library."));

        method = new GetMethod("http://localhost:12500/jordan/not-interested?movie=The%20Godfather%20-");
        httpClient.executeMethod(method);

        method = new GetMethod("http://localhost:12500/jordan");
        responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("We currently have <span class=\"librarySize\">0</span> Blu Rays in our library."));
    }

    @Test
    public void bluRayWithRoundBracketSymbolIsRemovedIfItIsNotInteresting() throws IOException {
        database.saveBluRay(newBluRay("The Godfather (Hi)"));

        method = new GetMethod("http://localhost:12500/jordan");
        int responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("We currently have <span class=\"librarySize\">1</span> Blu Rays in our library."));

        method = new GetMethod("http://localhost:12500/jordan/not-interested?movie=The%20Godfather%20(Hi)");
        httpClient.executeMethod(method);

        method = new GetMethod("http://localhost:12500/jordan");
        responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("We currently have <span class=\"librarySize\">0</span> Blu Rays in our library."));
    }

    @Test
    public void bluRayWithoutAnAmpersandSymbolIsRemovedIfItIsNotInteresting() throws IOException {
        database.saveBluRay(newBluRay("Fast And Furious 5"));

        method = new GetMethod("http://localhost:12500/jordan");
        int responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("We currently have <span class=\"librarySize\">1</span> Blu Rays in our library."));

        method = new GetMethod("http://localhost:12500/jordan/not-interested?movie=Fast%20And%20Furious%205");
        httpClient.executeMethod(method);

        method = new GetMethod("http://localhost:12500/jordan");
        responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("We currently have <span class=\"librarySize\">0</span> Blu Rays in our library."));
    }


    @Test
    public void bluRayWithADotIsRemovedIfItIsNotInteresting() throws IOException {
        database.saveBluRay(newBluRay("BLU-RAY FAST and FURIOUS: NEUES MODELL..."));

        method = new GetMethod("http://localhost:12500/jordan");
        int responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("We currently have <span class=\"librarySize\">1</span> Blu Rays in our library."));

        method = new GetMethod("http://localhost:12500/jordan/not-interested?movie=BLU-RAY%20FAST%20and%20FURIOUS:%20NEUES%20MODELL...");
        httpClient.executeMethod(method);

        method = new GetMethod("http://localhost:12500/jordan");
        responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("We currently have <span class=\"librarySize\">0</span> Blu Rays in our library."));
    }

    private BluRay newBluRay(String name) {
        return new BluRay(name, new Double(1.99), new Double(2.99), "http://amazon.co.uk/thegodfather", true, 100);
    }

}
