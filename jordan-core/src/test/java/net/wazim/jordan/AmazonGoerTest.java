package net.wazim.jordan;

import net.wazim.jordan.domain.BluRay;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import java.net.URI;
import static net.wazim.jordan.properties.JordanProductionProperties.AMAZON_BASE_URL;
import static net.wazim.jordan.properties.JordanProductionProperties.AMAZON_QUERY_URL;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AmazonGoerTest {

    /**
     * Random test.
     */
    private AmazonGoer amazonGoer;

    @Before
    public void setUp() {
        amazonGoer = new AmazonGoer();
    }

    @Test
    public void amazonGoerHitsAmazonHomepageReturnsStatusCode200() {
        amazonGoer.go(AMAZON_BASE_URL);
        assertEquals(amazonGoer.responseCode(), 200);
    }

    @Test
    public void amazonGoerHitsBadAmazonPageReturnsStatusCode404() {
        amazonGoer.go(URI.create(AMAZON_BASE_URL + "failed"));
        assertEquals(amazonGoer.responseCode(), 404);
    }

    @Test
    public void amazonGoerHitsThePageWhichContainsBluRaysThatAreLessThanFivePounds() {
        amazonGoer.go(AMAZON_QUERY_URL);
        assertThat(amazonGoer.responseBody(), showsBluRaysUnder£5());
    }

    @Test
    public void amazonGoerReturnsAListOfBluRaysUnder£5() {
        amazonGoer.go(AMAZON_QUERY_URL);

        assertThat(amazonGoer.bluRays().size(), is(24));

        assertThat(theFirstBluRay().name(), is("Transformers: Dark of the Moon [Blu-ray + DVD] [2011] [Region Free]"));
        assertThat(theFirstBluRay().priceNew(), is("£1.64"));
        assertThat(theFirstBluRay().priceUsed(), is("£2.48"));
        assertThat(theFirstBluRay().isOwned(), is(false));
    }

    private BluRay theFirstBluRay() {
        return amazonGoer.bluRays().get(0);
    }

    private Matcher<? super String> showsBluRaysUnder£5() {
        return new TypeSafeMatcher<String>() {
            String responseBody;

            @Override
            protected boolean matchesSafely(String responseBody) {
                this.responseBody = responseBody;
                return responseBody.contains("Blu-ray") && responseBody.contains("Under £5");
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the response did not contain the string Blu-ray : Under £5 was " + responseBody);
            }
        };
    }

}
