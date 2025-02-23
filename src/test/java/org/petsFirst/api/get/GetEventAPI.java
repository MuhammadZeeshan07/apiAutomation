package org.petsFirst.api.get;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.petsFirst.api.EndPoints;
import org.petsFirst.api.UrlConfiguration;
import org.petsFirst.api.utilts.Authentication;

public class GetEventAPI extends UrlConfiguration {

	private String eventId;

	@Test(description = "GetEvent: Verify event retrieval funtionality")
	public void getEvent() {

		UrlConfiguration.v1();

		String accessToken = Authentication.getAccessToken();

		Response response = given().header("Authorization", "Bearer " + accessToken).when().get(EndPoints.EVENTS).then()
				.statusCode(200).extract().response();

		eventId = response.jsonPath().getString("data.findAll { it.status == 1 }[0].id");
		System.out.println("Getting the following id " + eventId);
	}

	public String getEventId() {
		return eventId;
	}
}
