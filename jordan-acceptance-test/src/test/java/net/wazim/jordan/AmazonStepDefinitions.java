package net.wazim.jordan;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.wazim.jordan.builder.AmazonHtmlResponseBuilder;
import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.properties.JordanTestSpecificProperties;
import net.wazim.jordan.stub.AmazonStub;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AmazonStepDefinitions {

    private String responseBodyAsString;
    private int httpResponseCode;
    private AmazonStub stub;


    @Given("^Project Jordan is started$")
    public void projectJordanIsStarted() throws Throwable {
        stub = new AmazonStub();
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

    @When("^I hit the API page$")
    public void I_hit_the_API_page() throws Throwable {
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod("http://localhost:12500/jordan/api/all");
        httpResponseCode = client.executeMethod(getMethod);
        responseBodyAsString = getMethod.getResponseBodyAsString();
    }

    @Then("^I have a list of (\\d+) Blu Rays$")
    public void iHaveAListOfBluRays(int expectedNumberOfBluRays) throws Throwable {
        assertThat(httpResponseCode, is(OK_200));
        assertThat(responseBodyAsString, containsString("We currently have " + expectedNumberOfBluRays + " Blu Rays in our library."));
        stub.stopServer();
    }

    @Then("^the response is OK$")
    public void the_response_is_OK() throws Throwable {
        assertThat(httpResponseCode, is(OK_200));
        stub.stopServer();
    }

    @Then("^the response should contain \"([^\"]*)\"$")
    public void the_response_should_contain(String arg1) throws Throwable {
        assertThat(responseBodyAsString, containsString(arg1));
        stub.stopServer();
    }


    private String getSamplePage1() {
        return new AmazonHtmlResponseBuilder()
                .with(new BluRay("The Godfather", 1.00, 1.00, "http://amazon.co.uk/thegodfather", false, 100))
                .withCurrentPageNumber(1)
                .withTotalPageNumbers(2).build();
    }

    private String getSamplePage2() {
        return new AmazonHtmlResponseBuilder()
                .withCurrentPageNumber(2)
                .withTotalPageNumbers(2).build();
    }
}
