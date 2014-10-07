package net.wazim.jordan.stub;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AmazonStubTest {

    private AmazonStub amazonStub;

    @Before
    public void setup() {
        amazonStub = new AmazonStub();
        amazonStub.createPageAndPrimeResponse("/amazon&page=1", HttpStatus.OK_200, "This is page 1");
        amazonStub.createPageAndPrimeResponse("/amazon&page=2", HttpStatus.OK_200, "This is page 2");
    }

    @After
    public void shutDown() {
        amazonStub.stopServer();
    }

    @Test
    public void amazonStubStartsAndReturnsPrimedValue() {
        JordanHttpClient webClient = new JordanHttpClient();
        JordanHttpResponse response = webClient.getRequest(URI.create("http://localhost:11511/amazon&page=1"));
        assertThat(response.getResponseBody(), containsWithinTheBody("This is page 1"));
        assertThat(response.getResponseCode(), is(200));

        JordanHttpResponse response2 = webClient.getRequest(URI.create("http://localhost:11511/amazon&page=2"));
        assertThat(response2.getResponseBody(), containsWithinTheBody("This is page 2"));
        assertThat(response2.getResponseCode(), is(200));
    }

    private Matcher<? super String> containsWithinTheBody(final String response) {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(String s) {
                return s.contains(response);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("does not contain the response");
            }
        };
    }

}