package net.wazim.jordan.stub;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import java.net.URI;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AmazonStubTest {

    @Test
    public void amazonStubStartsAndReturnsPrimedValue() {
        AmazonStub amazonStub = new AmazonStub();
        amazonStub.primeToResponse(HttpStatus.OK_200, "This is the response");

        JordanHttpClient webClient = new JordanHttpClient();
        JordanHttpResponse response = webClient.getRequest(URI.create("http://localhost:11511/amazon"));

        assertThat(response.getResponseBody(), is("This is the response"));
        assertThat(response.getResponseCode(), is(200));
    }

}