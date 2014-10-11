package net.wazim.jordan;

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

public class JordanServerTest {

    private JordanServer jordanServer;
    private HttpClient httpClient;
    private HttpMethod method;

    @Before
    public void setupJordanServer() {
        jordanServer = new JordanServer(new JordanTestSpecificProperties(), new InMemoryPersistableDatabase());
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

}
