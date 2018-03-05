package gracenote.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gracenote.resources.HttpClient;
import gracenote.resources.JsonSchemaValidator;
import org.junit.Assert;

import java.io.IOException;

/**
 * Created by iparmar on 01/07/2018.
 */

public class CommonSteps
    {
    private HttpClient client;

    public CommonSteps(HttpClient client) {
        this.client = client;
    }

    @Given("^The API's are up and running for Hathway$")
    public void the_API_s_are_up_and_running_for_Hathway() throws IOException {
        client.checkStatusForAppServices();
        Assert.assertEquals(200, client.getResponseStatusCode());
    }

    @When("^User perform GET request$")
    public void user_perform_GET_request() throws IOException {
        System.out.println("API URL : " + client.getAPIUrl() + "\n");
        client.performGETRequest();
    }

    @And("^The response code should be (\\d+)$")
        public void theResponseCodeShouldBeResponseCode (int expectedCode) {
            System.out.println("\nResponse Code : " + client.getResponseStatusCode() + "\n");
            Assert.assertEquals(expectedCode, client.getResponseStatusCode());
    }

    @Then("^User should see the JSON response$")
    public void user_should_see_the_JSON_response() throws IOException {
        System.out.println("\nResponse : " + client.getJSONResponse() + "\n");
    }

    @Then("^Response schema should be as per expected schema \"([^\"]*)\"$")
    public void response_schema_should_be_as_per_expected_schema(String expectedSchemaFilePath) throws IOException {
        Assert.assertTrue("Failure to validate schema : ", JsonSchemaValidator.validate(client.getJSONResponse(), expectedSchemaFilePath));
    }

    @Given("^User wants to request \"([^\"]*)\"$")
    public void user_wants_to_request(String api) {
        client.setApiUrl(api);
    }

    }
