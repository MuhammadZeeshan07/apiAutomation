package org.petsFirst.api.delete;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.petsFirst.api.EndPoints;
import org.petsFirst.api.UrlConfiguration;
import org.petsFirst.api.utilts.Authentication;
import org.petsFirst.api.get.GetEventAPI;

public class DeleteEventAPI extends UrlConfiguration {

	@Test (description = "DeleteEvent: Verify event deletion funtionality")
	public void deleteEvent() {

		
		UrlConfiguration.v1();
		if (areTokensAvailable())  {
			String accessToken = Authentication.getAccessToken();

		GetEventAPI getEventAPI = new GetEventAPI();
		getEventAPI.getEvent();
		String eventId = getEventAPI.getEventId();
		System.out.println("Deleting the following id " + eventId);

		given().header("Authorization", "Bearer " + accessToken).pathParam("id", eventId).when()
				.put(EndPoints.EVENTS + "/{id}/cancel").then().statusCode(200).log().all();
		}
		
		
	}
	
	private boolean areTokensAvailable() {
		return Authentication.getAccessToken() != null && Authentication.getRefreshToken() != null;
	}
	
}
