package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException {
		stepDefination m = new stepDefination();

		if (stepDefination.place_id == null) {
			m.add_place_payload("Vick", "USA", "Eng + Spanish");
			m.user_calls_using_http_method_request("AddPlaceAPI", "POST");
			m.verify_if_place_id_maps_to_with("Vick", "getPlaceAPI");
		}
	}

}
