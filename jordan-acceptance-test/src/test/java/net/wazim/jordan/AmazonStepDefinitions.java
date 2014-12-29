package net.wazim.jordan;

import com.dumbster.smtp.SimpleSmtpServer;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.wazim.jordan.builder.AmazonHtmlResponseBuilder;
import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.mail.JordanMailSender;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.properties.JordanTestSpecificProperties;
import net.wazim.jordan.stub.AmazonStub;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AmazonStepDefinitions {

    private String responseBodyAsString;
    private int httpResponseCode;
    private AmazonStub stub;
    private JordanRunner jordanRunner;
    private SimpleSmtpServer smtpServer;

    @Given("^Project Jordan is started$")
    public void projectJordanIsStarted() throws Throwable {
        stub = new AmazonStub();
        stub.createPageAndPrimeResponse("/amazon/bluray", 200, getSamplePage1());
        stub.createPageAndPrimeResponse("/amazon/bluray?page=2", 200, getSamplePage2());

        jordanRunner = new JordanRunner(new JordanTestSpecificProperties(), new InMemoryPersistableDatabase(), new JordanMailSender("localhost", "1500"), 12500);
    }

    @Given("^Project Jordan is started with no primed response$")
    public void Project_Jordan_is_started_with_no_primed_response() throws Throwable {
        stub = new AmazonStub();
        jordanRunner = new JordanRunner(new JordanTestSpecificProperties(), new InMemoryPersistableDatabase(), new JordanMailSender("localhost", "1500"), 12500);
    }

    @Given("^The email server is started$")
    public void The_email_server_is_started() throws Throwable {
        smtpServer = SimpleSmtpServer.start(1500);
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

    @When("^The user requests an email for when \"([^\"]*)\" becomes available to \"([^\"]*)\"$")
    public void The_user_requests_an_email_for_when_becomes_available_to(String demandedBluRay, String addressToEmail) throws Throwable {
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod("http://localhost:12500/jordan/email");
        postMethod.setParameter("title", demandedBluRay);
        postMethod.setParameter("address", addressToEmail);
        httpResponseCode = client.executeMethod(postMethod);
        responseBodyAsString = postMethod.getResponseBodyAsString();
    }


    @Given("^\"([^\"]*)\" becomes available$")
    public void becomes_available(String bluRay) throws Throwable {
        stub.createPageAndPrimeResponse("/amazon/bluray", 200, new AmazonHtmlResponseBuilder().with(new BluRay(bluRay, 0.10, 0.10, "http://x.x", true, 100)).build());
        jordanRunner.refresh();
    }

    @Then("^I have a list of (\\d+) Blu Rays$")
    public void iHaveAListOfBluRays(int expectedNumberOfBluRays) throws Throwable {
        assertThat(httpResponseCode, is(OK_200));
        assertThat(responseBodyAsString, containsString("We currently have <span class=\"librarySize\">" + expectedNumberOfBluRays + "</span> Blu Rays in our library."));
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

    @Then("^The user receives an email$")
    public void The_user_receives_an_email() throws Throwable {
        assertThat(smtpServer.getReceivedEmailSize(), is(1));
    }

    @After
    public void afterScenario() {
        if (smtpServer != null) {
            smtpServer.stop();
        }
        stub.stopServer();
        jordanRunner.stopServer();
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
