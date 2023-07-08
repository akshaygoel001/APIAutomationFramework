package stepdefinitions;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() {
		StepDefinition step=new StepDefinition();
		if(StepDefinition.placeId==null) {
			step.add_place_payload_with_and("House", "Spanish", "29 New Lane,Delhi");
			step.user_calls_place_api_with_http_request("AddPlaceAPI","POST");
			step.verify_place_id_created_maps_to_using("House", "GetPlaceAPI");
		}
	}

}
