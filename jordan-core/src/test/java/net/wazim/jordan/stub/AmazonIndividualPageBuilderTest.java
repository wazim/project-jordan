package net.wazim.jordan.stub;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class AmazonIndividualPageBuilderTest {

    @Test
    public void buildsAnIndividualPageNicely() {
        String page = new AmazonIndividualPageBuilder().withName("The Godfather").withNewPrice(0.99).withUsedPrice(0.59).build();
        assertThat(page, containsString("The Godfather"));
        assertThat(page, containsString("£0.99"));
        assertThat(page, containsString("£0.59"));
    }

}