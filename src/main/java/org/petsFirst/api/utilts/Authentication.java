package org.petsFirst.api.utilts;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class Authentication {

	private static String accessToken;
	private static String refreshToken;

	public static String getAccessToken() {
		if (accessToken == null) {
			login();
		}
		return accessToken;
	}

	public static String getRefreshToken() {
		if (refreshToken == null) {
			login();
		}
		return refreshToken;
	}

	private static void login() {
		
		String requestBody = "{\n" + "  \"email\": \"muhammadzeeshan@glowfishlabs.com\",\n"
				+ "  \"password\": \"Glowfish@123\",\n" + "  \"deviceToken\": \"FCM token\",\n"
				+ "  \"deviceType\": 1\n" + "}";

		Response response = given().contentType("application/json").body(requestBody).when()
				.post("https://api-dev.youcater.me/v1/auth/login/customer/email").then().statusCode(200).extract().response();

	//	System.out.println("Login Response: " + response.asString());
		accessToken = response.path("data.tokens.access.token");
		refreshToken = response.path("data.tokens.refresh.token");
	}

	public static void clearTokens() {
		accessToken = null;
		refreshToken = null;
	}

}
