package net.wazim.jordan;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.properties.JordanTestSpecificProperties;
import net.wazim.jordan.stub.AmazonIndividualPageBuilder;
import net.wazim.jordan.stub.AmazonStub;
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

public class JordanServerTest {

    private JordanServer jordanServer;
    private HttpClient httpClient;
    private HttpMethod method;
    private int id;
    private AmazonStub stub;

    @Before
    public void setupJordanServer() {
        InMemoryPersistableDatabase database = new InMemoryPersistableDatabase();
        BluRay bluRay = new BluRay("The Godfather", new Double(1.99), new Double(2.99), "http://localhost:11511/amazon/thegodfather", true, 100);
        id = bluRay.getId();
        database.saveBluRay(bluRay);
        database.saveBluRay(new BluRay("Michael Jackson's This Is It", new Double(0.59), new Double(0.29), "http://www.amazon.co.uk/mjthisisit", true, 100));

        jordanServer = new JordanServer(new JordanTestSpecificProperties(), database, 12500);
        httpClient = new HttpClient();
        method = new GetMethod("http://localhost:12500/jordan");
        stub = new AmazonStub();
    }

    @After
    public void shutdownJordanServer() {
        jordanServer.stopServer();
        stub.stopServer();
    }

    @Test
    public void jordanApiServletReturnsValidXml() throws IOException {
        method = new GetMethod("http://localhost:12500/jordan/api/all");
        int responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("<name>The Godfather</name>"));
//        assertThat(method.getResponseBodyAsString(), containsString("<newPrice>£1.99</newPrice>"));
//        assertThat(method.getResponseBodyAsString(), containsString("<usedPrice>£2.99</usedPrice>"));

        assertThat(method.getResponseBodyAsString(), containsString("<name>Michael Jackson's This Is It</name>"));
//        assertThat(method.getResponseBodyAsString(), containsString("<newPrice>£0.59</newPrice>"));
//        assertThat(method.getResponseBodyAsString(), containsString("<usedPrice>£0.29</usedPrice>"));
    }

    @Test
    public void jordanApiReturnsValidJson() throws IOException {
        method = new GetMethod("http://localhost:12500/jordan/api/all?format=json");
        int responseCode = httpClient.executeMethod(method);
        assertThat(responseCode, is(HttpStatus.OK_200));
        System.out.println("method = " + method.getResponseBodyAsString());
        assertThat(method.getResponseBodyAsString(), containsString("The Godfather"));
    }

    @Test
    public void jordanIndexServletReturnsANiceCleanPage() throws IOException {
        method = new GetMethod("http://localhost:12500/jordan");
        int responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("The Godfather"));
    }

    @Test
    public void bluRayIsRemovedIfItIsNotInteresting() throws IOException {
        method = new GetMethod("http://localhost:12500/jordan");
        int responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("We currently have <span class=\"librarySize\">2</span> Blu Rays in our library."));

        method = new GetMethod("http://localhost:12500/jordan/not-interested?movie="+id);
        httpClient.executeMethod(method);

        method = new GetMethod("http://localhost:12500/jordan");
        responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("We currently have <span class=\"librarySize\">1</span> Blu Rays in our library."));
    }

    @Test
    public void manuallyUpdatesListing() throws IOException {
        method = new GetMethod("http://localhost:12500/jordan");
        int responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("The Godfather"));
        assertThat(method.getResponseBodyAsString(), containsString("1.99"));
        assertThat(method.getResponseBodyAsString(), containsString("2.99"));

        primeAmazonWithNewPrice("The Godfather", 0.50, 0.25);

        method = new GetMethod("http://localhost:12500/jordan/manual-update?movie="+id);
        responseCode = httpClient.executeMethod(method);
        assertThat(responseCode, is(HttpStatus.OK_200));

        method = new GetMethod("http://localhost:12500/jordan");
        responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("The Godfather"));
        assertThat(method.getResponseBodyAsString(), containsString("0.5"));
        assertThat(method.getResponseBodyAsString(), containsString("0.25"));
    }

    private void primeAmazonWithNewPrice(String blurayName, double newPrice, double usedPrice) {
        stub.createPageAndPrimeResponse("/amazon/"+blurayName.replace(" ", "").toLowerCase(), 200,
                new AmazonIndividualPageBuilder()
                        .withName(blurayName).withNewPrice(newPrice).withUsedPrice(usedPrice).build());
    }


}
