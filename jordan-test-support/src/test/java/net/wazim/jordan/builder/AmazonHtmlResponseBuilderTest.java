package net.wazim.jordan.builder;

import org.junit.Test;

import static net.wazim.jordan.fixtures.BluRayDataFixtures.someUnownedBluRay;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class AmazonHtmlResponseBuilderTest {

    @Test
    public void amazonHtmlResponseBuilderBuildsGoodHtml() {
        String amazonHtmlResponse = new AmazonHtmlResponseBuilder()
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .withCurrentPageNumber(1)
                .withTotalPageNumbers(1)
                .build();

        assertThat(amazonHtmlResponse, containsString("result_0"));
        assertThat(amazonHtmlResponse, containsString("result_1"));
        assertThat(amazonHtmlResponse, containsString("result_2"));
        assertThat(amazonHtmlResponse, containsString("result_3"));
    }

}