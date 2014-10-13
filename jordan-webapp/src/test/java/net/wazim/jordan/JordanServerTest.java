package net.wazim.jordan;

import jdk.nashorn.internal.ir.annotations.Ignore;
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
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JordanServerTest {

    private JordanServer jordanServer;
    private HttpClient httpClient;
    private HttpMethod method;

    @Before
    public void setupJordanServer() {
        InMemoryPersistableDatabase database = new InMemoryPersistableDatabase();
        database.saveBluRay(new BluRay("The Godfather", new BigDecimal(1.99), new BigDecimal(2.99), false));
        database.saveBluRay(new BluRay("Michael Jackson's This Is It", new BigDecimal(0.59), new BigDecimal(0.29), false));

        jordanServer = new JordanServer(new JordanTestSpecificProperties(), database);
        httpClient = new HttpClient();
        method = new GetMethod("http://localhost:12500/jordan");
    }

    @After
    public void shutdownJordanServer() {
        jordanServer.stopServer();
    }

    @Test
    public void jordanIndexServletTestReturnsOk() throws IOException {
        int responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("Welcome to Project Jordan"));
    }

    @Ignore
    public void jordanApiServletReturnsValidXml() throws IOException {
        method = new GetMethod("http://localhost:12500/jordan/api/all");
        int responseCode = httpClient.executeMethod(method);

        assertThat(responseCode, is(HttpStatus.OK_200));
        assertThat(method.getResponseBodyAsString(), containsString("<name>The Godfather</name>"));
        assertThat(method.getResponseBodyAsString(), containsString("<newPrice>£1.99</newPrice>"));
        assertThat(method.getResponseBodyAsString(), containsString("<usedPrice>£2.99</usedPrice>"));

        assertThat(method.getResponseBodyAsString(), containsString("<name>Michael Jackson's This Is It</name>"));
        assertThat(method.getResponseBodyAsString(), containsString("<newPrice>£0.59</newPrice>"));
        assertThat(method.getResponseBodyAsString(), containsString("<usedPrice>£0.29</usedPrice>"));
    }

}