package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
// test
public class stepDefination extends Utils {

	Response res;
	RequestSpecification Req1;
	ResponseSpecification Response;
	static String place_id;
	TestDataBuild t = new TestDataBuild();
	
	@Given("Add Place Payload {string}  {string} {string}")
	public void add_place_payload(String name, String address, String language) throws IOException {

		

		Req1 = given().spec(ReqSpec())
				.body(t.addPlacePayload(name, address, language));

	}

	@When("user calls {string} using http {string} method request")
	public void user_calls_using_http_method_request(String resource, String method) {

		// Write code here that turns the phrase above into concrete actions
		Response = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		
		System.out.println(resourceAPI.getResource().toString());
		
		if (method.equalsIgnoreCase("POST"))
		res = Req1.when().post(resourceAPI.getResource());
		else if  (method.equalsIgnoreCase("GET"))
			res = Req1.when().get(resourceAPI.getResource());
		else if  (method.equalsIgnoreCase("DELETE"))
			res = Req1.when().delete(resourceAPI.getResource());

	}

	@Then("user check if API call got the status code success as {int}")
	public void user_check_if_api_call_got_the_status_code_success_as(Integer int1) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(res.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
		// Write code here that turns the phrase above into concrete actions

		//JsonPath js = new JsonPath(res.asString());
		assertEquals(getJsonPath(res, keyValue), ExpectedValue);

		
	}
	
	@Then("verify if place_id maps to {string} with {string}")
	public void verify_if_place_id_maps_to_with(String expectedName, String resource) throws IOException {
		
	 place_id =	getJsonPath(res,"place_id");
	Req1 = given().spec(ReqSpec()).queryParam("place_id", place_id);
	System.out.println(place_id);
		user_calls_using_http_method_request(resource, "GET");
		String actualName=getJsonPath(res,"name");
		assertEquals(expectedName, actualName);
		
	    
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    

		Req1 = given().spec(ReqSpec()).body(t.deletePlace(place_id));
		
	}
}
