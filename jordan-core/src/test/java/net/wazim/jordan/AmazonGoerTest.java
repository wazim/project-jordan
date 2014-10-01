package net.wazim.jordan;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static net.wazim.jordan.JordanProperties.AMAZON_BASE_URL;
import static net.wazim.jordan.JordanProperties.AMAZON_BLU_RAY_URL;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AmazonGoerTest {

    @Test
    public void amazonGoerHitsAmazonHomepageReturnsStatusCode200() {
        AmazonGoer amazonGoer = new AmazonGoer();
        amazonGoer.go(AMAZON_BASE_URL);
        assertEquals(amazonGoer.responseCode(), 200);
    }

    @Test
    public void amazonGoerHitsBadAmazonPageReturnsStatusCode404() {
        AmazonGoer amazonGoer = new AmazonGoer();
        amazonGoer.go(AMAZON_BASE_URL + "/failed");
        assertEquals(amazonGoer.responseCode(), 404);
    }

    @Test
    public void amazonGoerHitsThePageWhichContainsBluRaysThatAreLessThanFivePounds() {
        AmazonGoer amazonGoer = new AmazonGoer();
        amazonGoer.go(AMAZON_BLU_RAY_URL);
        assertThat(amazonGoer.responseBody(), showsBluRaysUnder£5());
    }

    @Test
    public void amazonGoerReturnsAListOfBluRaysUnder£5() {
        AmazonGoer amazonGoer = new AmazonGoer();
        amazonGoer.go(AMAZON_BLU_RAY_URL);

        assertThat(amazonGoer.bluRays().get(0).name(), is("Dredd (Blu-ray 3D + Blu-ray)"));
        assertThat(amazonGoer.bluRays().get(0).price(), is("£6.00"));
        assertFalse(amazonGoer.bluRays().get(0).isOwned());
    }

    private Matcher<? super String> showsBluRaysUnder£5() {
        return new TypeSafeMatcher<String>() {
            String responseBody;

            @Override
            protected boolean matchesSafely(String responseBody) {
                this.responseBody = responseBody;
                return responseBody.contains("Blu-ray") && responseBody.contains("Under &pound;5");
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the response did not contain the string Blu-ray : Under £5 was " + responseBody);
            }
        };
    }

}
