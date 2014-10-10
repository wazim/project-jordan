package net.wazim.jordan;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.wazim.jordan.builder.AmazonHtmlResponseBuilder;
import net.wazim.jordan.stub.AmazonStub;

import static net.wazim.jordan.fixtures.BluRayDataFixtures.someUnownedBluRay;
import static net.wazim.jordan.properties.JordanTestSpecificProperties.AMAZON_QUERY_URL;
import static org.junit.Assert.assertEquals;

public class AmazonStepDefinitions {

    private AmazonGoer amazonGoer;

    @Given("^I have started jordan$")
    public void jordanIsStarted() {
        amazonGoer = new AmazonGoer();
        AmazonStub stub = new AmazonStub();
        stub.createPageAndPrimeResponse("/amazon/bluray", 200, getSamplePage1());
        stub.createPageAndPrimeResponse("/amazon/bluray?page=2", 200, getSamplePage2());
        amazonGoer = new AmazonGoer();
    }

    @When("^Jordan goes to amazon$")
    public void jordanMakesRequestToAmazon() {
        amazonGoer.go(AMAZON_QUERY_URL);
    }

    @Then("^I should have (\\d+) blu rays$")
    public void numberOfBluRaysAvailableIs(String numberOfBlurays) {
        assertEquals(Integer.parseInt(numberOfBlurays), amazonGoer.bluRays().size());
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
