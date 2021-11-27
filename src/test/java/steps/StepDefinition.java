package steps;

import static base.APIBase.doRequest;
import static org.testng.Assert.assertTrue;
import static utils.ExcelReader.getData;
import static utils.FileUtility.getResourcePath;
import static utils.FileUtility.jsonFileToJsonObject;

import base.APIBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import utils.validation.Comparator;

public class StepDefinition {

  String endpoint;
  String requestType;
  List excelData;
  JSONObject requestBody;
  Response response;

  @Given("I have end point {string} and request type is {string}")
  public void iHaveEndPointAndRequestTypeIs(String endpoint, String requestType) {

    System.out.println("Execution Started..s");
    this.endpoint = endpoint;
    this.requestType = requestType;
  }


  @Given("Create request from file {string}")
  public void createRequestFromFile(String fileName) {
    excelData = Arrays.asList(getData(getResourcePath(fileName), 0));
  }


  @Given("I have json body request in {string}")
  public void iHaveJsonBodyRequestIn(String jsonFileName) {
    requestBody = jsonFileToJsonObject(jsonFileName);
  }

  @Then("Send request to server")
  public void sendRequestToServer() {
    response = doRequest(requestType, APIBase.envProp.getProperty("baseurl") + endpoint,
        requestBody.toString());
  }

  @Given("I have json body request in {string} and data file is {string}")
  public void iHaveJsonBodyRequestInAndDataFileIs(String jsonFile, String excelFile) {
  }

  @Then("expected response code should be {int}")
  public void expectedResponseCodeShouldBe(int responseCode) {
    boolean isResponseCodeExpected = response.statusCode() == responseCode;
    assertTrue(isResponseCodeExpected);

  }


  @Then("expected response code should be follow:")
  public void expectedResponseCodeShouldBeFollow(String expectedJsonString) {
    JSONObject expectedJson = new JSONObject(expectedJsonString);
    Map<Object, Object> responseMap = response.jsonPath().get();
    Comparator.jsonComparisonNonExtensible(expectedJson, new JSONObject(responseMap));
  }
}