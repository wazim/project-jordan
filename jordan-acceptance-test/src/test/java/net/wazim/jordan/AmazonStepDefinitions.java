package net.wazim.jordan;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.wazim.jordan.builder.AmazonHtmlResponseBuilder;
import net.wazim.jordan.client.fixtures.TestOnlyPersistableDatabase;
import net.wazim.jordan.properties.JordanTestSpecificProperties;
import net.wazim.jordan.stub.AmazonStub;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import static net.wazim.jordan.fixtures.BluRayDataFixtures.someUnownedBluRay;
import static net.wazim.jordan.properties.JordanTestSpecificProperties.AMAZON_QUERY_URL;
import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class AmazonStepDefinitions {

    private AmazonGoer amazonGoer;
    private String responseBodyAsString;
    private int httpResponseCode;

    @Given("^I have started jordan$")
    public void jordanIsStarted() {
        amazonGoer = new AmazonGoer();
        AmazonStub stub = new AmazonStub();
        stub.createPageAndPrimeResponse("/amazon/bluray", 200, getSamplePage1());
        stub.createPageAndPrimeResponse("/amazon/bluray?page=2", 200, getSamplePage2());
        amazonGoer = new AmazonGoer();
    }

    @Given("^Project Jordan is started$")
    public void projectJordanIsStarted() throws Throwable {
        AmazonStub stub = new AmazonStub();
        stub.createPageAndPrimeResponse("/amazon/bluray", 200, getSamplePage1());
        stub.createPageAndPrimeResponse("/amazon/bluray?page=2", 200, getSamplePage2());

        new JordanRunner(new JordanTestSpecificProperties(), new TestOnlyPersistableDatabase());
    }

    @When("^Jordan goes to amazon$")
    public void jordanMakesRequestToAmazon() {
        amazonGoer.go(AMAZON_QUERY_URL);
    }


    @When("^I go to the Web App$")
    public void goToJordanWebApp() throws Throwable {
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod("http://localhost:12500/jordan");
        httpResponseCode = client.executeMethod(getMethod);
        responseBodyAsString = getMethod.getResponseBodyAsString();
    }

    @Then("^I should have (\\d+) blu rays$")
    public void numberOfBluRaysAvailableIs(String numberOfBlurays) {
        assertEquals(Integer.parseInt(numberOfBlurays), amazonGoer.bluRays().size());
    }

    @Then("^I have a list of (\\d+) Blu Rays$")
    public void iHaveAListOfBluRays(int expectedNumberOfBluRays) throws Throwable {
        assertThat(httpResponseCode, is(OK_200));
        assertThat(responseBodyAsString, containsString("We currently have " + expectedNumberOfBluRays + " Blu Rays in our library."));
    }

    private String getSamplePage1() {
        return new AmazonHtmlResponseBuilder()
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .withCurrentPageNumber(1)
                .withTotalPageNumbers(2).build();
    }

    private String getSamplePage2() {
        return new AmazonHtmlResponseBuilder()
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .with(someUnownedBluRay())
                .withCurrentPageNumber(2)
                .withTotalPageNumbers(2).build();
    }
}
