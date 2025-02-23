package org.petsFirst.api.post;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.petsFirst.api.EndPoints;
import org.petsFirst.api.UrlConfiguration;

public class LoginAPI {

	@Test (description = "Login: Verify login funtionality")
	public void testLogin() {

		UrlConfiguration.v1();
		String requestBody = "{\n" + "  \"email\": \"muhammadzeeshan@glowfishlabs.com\",\n"
				+ "  \"password\": \"Glowfish@123\",\n" + "  \"deviceToken\": \"FCM token\",\n"
				+ "  \"deviceType\": 1\n" + "}";

		given().contentType("application/json").body(requestBody).when().post(EndPoints.LOGIN)
				.then().statusCode(200).extract().response();

	}

}
