package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
	
	RequestSpecification req;
	ResponseSpecification respSpec;
	Response resp;
	TestDataBuild data;
	public static String placeId; 
	
	@Given("Add Place Payload with {string} , {string} and {string}")
	public void add_place_payload_with_and(String name, String language, String address) {
		System.out.println("add_place_payload_with_and");
		data=new TestDataBuild();
		req=given()
			.spec(requestSpecBuilder())
		.body(data.addPlacePayload(name,language,address));
	}

	@When("User calls {string} with HTTP {string} request")
	public void user_calls_place_api_with_http_request(String resource,String httpMehtod) {
		System.out.println("user_calls_place_api_with_http_request");
		System.out.println(APIResources.valueOf(resource).getResource());
		if(httpMehtod.equalsIgnoreCase("POST")) {
			resp=req.when()
					.post(APIResources.valueOf(resource).getResource());
		}
		else if(httpMehtod.equalsIgnoreCase("DELETE")) {
			resp=req.when()
					.delete(APIResources.valueOf(resource).getResource());
		}
		else if(httpMehtod.equalsIgnoreCase("GET")) {
			resp=req.when()
					.get(APIResources.valueOf(resource).getResource());
		}
		resp
		.then()
		.spec(responseSpecBuilder())
		.extract().response();
	}

	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
	    System.out.println("api_call_got_success_with_status_code");
		assertEquals(resp.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    System.out.println("in_response_body_is");
	    assertEquals(getJsonPath(resp,key), value);
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) {
		System.out.println("verify_place_id_created_maps_to_using");
		placeId=getJsonPath(resp,"place_id");
		req=given()
		.spec(requestSpecBuilder())
				.queryParam("place_id",placeId);
		user_calls_place_api_with_http_request(resource,"GET");
		String actualName=getJsonPath(resp,"name");
		assertEquals(expectedName, actualName);
	}
	@Given("Delete Place Payload with place_id")
	public void delete_place_payload_with_place_id() {
		System.out.println("delete_place_payload_with_place_id");
		System.out.println(placeId);
		data=new TestDataBuild();
		req=given()
			.spec(requestSpecBuilder())
		.body(data.deletePlacePayload(placeId));
	}

}
