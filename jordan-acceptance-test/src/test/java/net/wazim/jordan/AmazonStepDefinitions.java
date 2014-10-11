package net.wazim.jordan;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.wazim.jordan.builder.AmazonHtmlResponseBuilder;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.properties.JordanTestSpecificProperties;
import net.wazim.jordan.stub.AmazonStub;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import static net.wazim.jordan.fixtures.BluRayDataFixtures.someUnownedBluRay;
import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AmazonStepDefinitions {

    private AmazonGoer amazonGoer;
    private String responseBodyAsString;
    private int httpResponseCode;

    @Given("^Project Jordan is started$")
    public void projectJordanIsStarted() throws Throwable {
        AmazonStub stub = new AmazonStub();
        stub.createPageAndPrimeResponse("/amazon/bluray", 200, getSamplePage1());
        stub.createPageAndPrimeResponse("/amazon/bluray?page=2", 200, getSamplePage2());

        new JordanRunner(new JordanTestSpecificProperties(), new InMemoryPersistableDatabase());
    }


    @When("^I go to the Web App$")
    public void goToJordanWebApp() throws Throwable {
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod("http://localhost:12500/jordan");
        httpResponseCode = client.executeMethod(getMethod);
        responseBodyAsString = getMethod.getResponseBodyAsString();
    }

    @When("^I hit the status page$")
    public void I_hit_the_status_page() throws Throwable {
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod("http://localhost:12500/jordan/status");
        httpResponseCode = client.executeMethod(getMethod);
        responseBodyAsString = getMethod.getResponseBodyAsString();
    }

    @Then("^I have a list of (\\d+) Blu Rays$")
    public void iHaveAListOfBluRays(int expectedNumberOfBluRays) throws Throwable {
        assertThat(httpResponseCode, is(OK_200));
        assertThat(responseBodyAsString, containsString("We currently have " + expectedNumberOfBluRays + " Blu Rays in our library."));
    }

    @Then("^the response is OK$")
    public void the_response_is_OK() throws Throwable {
        assertThat(httpResponseCode, is(OK_200));
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
