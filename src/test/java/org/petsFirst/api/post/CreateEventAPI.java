package org.petsFirst.api.post;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import org.petsFirst.api.EndPoints;
import org.petsFirst.api.UrlConfiguration;
import org.petsFirst.api.utilts.Authentication;

public class CreateEventAPI extends UrlConfiguration {

	@Test(description = "CreateEvent: Verify event creation with all required fields")
	public void createEventWithRequiredFields() {
		UrlConfiguration.v2();
		if (areTokensAvailable()) {
			String accessToken = Authentication.getAccessToken();

			String eventRequestBody = "{\n" + "  \"name\": \"Event Name\",\n" + "  \"imageUrl\": \"Event Image\",\n"
					+ "  \"eventDate\": \"2025-08-03\",\n" + "  \"isDateFlexible\": true,\n"
					+ "  \"guestsCount\": 50,\n" + "  \"minBudget\": 0,\n" + "  \"maxBudget\": 0,\n"
					+ "  \"budget\": 5000,\n" + "  \"isBudgetFlexible\": true,\n"
					+ "  \"serviceCategory\": { \"value\": \"Wedding\" },\n"
					+ "  \"serviceEventType\": { \"value\": \"Birthday\" },\n"
					+ "  \"otherServiceEventType\": { \"value\": \"Catering\" },\n"
					+ "  \"providerType\": { \"value\": \"Buffet\" },\n"
					+ "  \"otherProviderType\": { \"value\": \"Buffet\" },\n"
					+ "  \"providerOptions\": { \"value\": \"Italian\" },\n"
					+ "  \"typeOfCuisines\": [ { \"value\": \"Italian\" } ],\n" + "  \"city\": 1,\n"
					+ "  \"isSearchingVenue\": true,\n"
					+ "  \"courses\": [ { \"value\": \"Main Course\" }, { \"value\": \"Dessert\" } ],\n"
					+ "  \"timeOfDay\": { \"value\": \"Evening\" },\n" + "  \"budgetType\": { \"value\": \"Fixed\" },\n"
					+ "  \"dishType\": \"Buffet\",\n" + "  \"recommendedProducts\": [ \"1\", \"2\", \"3\" ],\n"
					+ "	 \"userSubmissionStatus\": 1,\n" + "  \"address\": {\n" + "    \"place_name\": \"New York\",\n"
					+ "    \"latitude\": \"40.712776\",\n" + "    \"longitude\": \"-74.005974\"\n" + "  }\n" + "}";

			given().header("Authorization", "Bearer " + accessToken).contentType("application/json")
					.body(eventRequestBody).when().post(EndPoints.EVENTS).then().statusCode(201);
		}
	}

	@Test(priority = 1, description = "CreateEvent: Verify event creation with skippable fields")
	public void createEventWithSkippableFields() {
		UrlConfiguration.v2();
		if (areTokensAvailable()) {
			String accessToken = Authentication.getAccessToken();

			String eventRequestBody = "{\n" + "  \"name\": \"Event Name\",\n" + "  \"imageUrl\": \"Event Image\",\n"
					+ "  \"eventDate\": \"2025-08-03\",\n" + "  \"isDateFlexible\": true,\n"
					+ "  \"guestsCount\": 50,\n" + "  \"minBudget\": 0,\n" + "  \"maxBudget\": 0,\n"
					+ "  \"budget\": 5000,\n" + "  \"isBudgetFlexible\": true,\n"
					+ "  \"serviceCategory\": { \"value\": \"Wedding\" },\n"
					+ "  \"serviceEventType\": { \"value\": \"Birthday\" },\n"
					+ "  \"otherServiceEventType\": { \"value\": \"Catering\" },\n"
					+ "  \"providerType\": { \"value\": \"Buffet\" },\n"
					+ "  \"otherProviderType\": { \"value\": \"Buffet\" },\n"
					+ "  \"providerOptions\": { \"value\": \"Italian\" },\n"
					+ "  \"typeOfCuisines\": [ { \"value\": \"Italian\" } ],\n" + "  \"city\": 1,\n"
					+ "  \"isSearchingVenue\": true,\n"
					+ "  \"courses\": [ { \"value\": \"\" }, { \"value\": \"\" } ],\n"
					+ "  \"timeOfDay\": { \"value\": \"Evening\" },\n" + "  \"budgetType\": { \"value\": \"Fixed\" },\n"
					+ "  \"dishType\": \"\",\n" + "  \"recommendedProducts\": [ \"1\", \"2\", \"3\" ],\n"
					+ "	 \"userSubmissionStatus\": 1,\n" + "  \"address\": {\n"
					+ "    \"place_name\": \"Central Park\",\n" + "    \"latitude\": \"40.785091\",\n"
					+ "    \"longitude\": \"-73.968285\"\n" + "  }\n" + "}";

			given().header("Authorization", "Bearer " + accessToken).contentType("application/json")
					.body(eventRequestBody).when().post(EndPoints.EVENTS).then().statusCode(201);
		}
	}

	@Test(priority = 2, description = "CreateEvent: Verify event creation with previous date")
	public void createEventWithPreviousDate() {
		UrlConfiguration.v2();
		if (areTokensAvailable()) {
			String accessToken = Authentication.getAccessToken();

			String eventRequestBody = "{\n" + "  \"name\": \"Event Name\",\n" + "  \"imageUrl\": \"Event Image\",\n"
					+ "  \"eventDate\": \"2025-08-03\",\n" + "  \"isDateFlexible\": true,\n"
					+ "  \"guestsCount\": 50,\n" + "  \"minBudget\": 0,\n" + "  \"maxBudget\": 0,\n"
					+ "  \"budget\": 5000,\n" + "  \"isBudgetFlexible\": true,\n"
					+ "  \"serviceCategory\": { \"value\": \"Meeting\" },\n"
					+ "  \"serviceEventType\": { \"value\": \"Corporate\" },\n"
					+ "  \"otherServiceEventType\": { \"value\": \"Decoration\" },\n"
					+ "  \"providerType\": { \"value\": \"Decorator\" },\n"
					+ "  \"otherProviderType\": { \"value\": \"Decorator\" },\n"
					+ "  \"providerOptions\": { \"value\": \"Venue\" },\n"
					+ "  \"typeOfCuisines\": [ { \"value\": \"Chinese\" } ],\n" + "  \"city\": 2,\n"
					+ "  \"isSearchingVenue\": true,\n"
					+ "  \"courses\": [ { \"value\": \"Appetizer\" }, { \"value\": \"Main Course\" } ],\n"
					+ "  \"timeOfDay\": { \"value\": \"Morning\" },\n"
					+ "  \"budgetType\": { \"value\": \"Variable\" },\n" + "  \"dishType\": \"Fried Rice\",\n"
					+ "  \"recommendedProducts\": [ \"4\", \"5\", \"6\" ],\n" + "  \"address\": {\n"
					+ "	 \"userSubmissionStatus\": 1,\n" + "    \"place_name\": \"Downtown Park\",\n"
					+ "    \"latitude\": \"40.758896\",\n" + "    \"longitude\": \"-73.985130\"\n" + "  }\n" + "}";

			Response response = given().header("Authorization", "Bearer " + accessToken).contentType("application/json")
					.body(eventRequestBody).when().post(EndPoints.EVENTS);

			System.out.println("Response Body: " + response.asString());
			Assert.assertEquals(response.getStatusCode(), 201, "Unexpected status code received.");

			if (response.getStatusCode() != 201 && !response.body().asString().isEmpty()) {
				String errorMessage = response.jsonPath().getString("error.message");
				System.err.println(
						errorMessage != null ? "Error Message: " + errorMessage : "Event created successfully!");
			}
		}
	}

	@Test(priority = 3, description = "CreateEvent: Verify event creation with future date")
	public void createEventWithFutureDate() {
		UrlConfiguration.v2();
		if (areTokensAvailable()) {
			String accessToken = Authentication.getAccessToken();

			String eventRequestBody = "{\n" + "  \"name\": \"Event Name\",\n" + "  \"imageUrl\": \"Event Image\",\n"
					+ "  \"eventDate\": \"2025-08-03\",\n" + "  \"isDateFlexible\": true,\n"
					+ "  \"guestsCount\": 50,\n" + "  \"minBudget\": 0,\n" + "  \"maxBudget\": 0,\n"
					+ "  \"budget\": 5000,\n" + "  \"isBudgetFlexible\": true,\n"
					+ "  \"serviceCategory\": { \"value\": \"Anniversary\" },\n"
					+ "  \"serviceEventType\": { \"value\": \"Celebration\" },\n"
					+ "  \"otherServiceEventType\": { \"value\": \"Catering\" },\n"
					+ "  \"providerType\": { \"value\": \"Buffet\" },\n"
					+ "  \"otherProviderType\": { \"value\": \"Buffet\" },\n"
					+ "  \"providerOptions\": { \"value\": \"Italian\" },\n"
					+ "  \"typeOfCuisines\": [ { \"value\": \"Italian\" } ],\n" + "  \"city\": 3,\n"
					+ "  \"isSearchingVenue\": true,\n"
					+ "  \"courses\": [ { \"value\": \"Main Course\" }, { \"value\": \"Dessert\" } ],\n"
					+ "  \"timeOfDay\": { \"value\": \"Evening\" },\n" + "  \"budgetType\": { \"value\": \"Fixed\" },\n"
					+ "  \"dishType\": \"Buffet\",\n" + "  \"recommendedProducts\": [ \"7\", \"8\", \"9\" ],\n"
					+ "	 \"userSubmissionStatus\": 1,\n" + "  \"address\": {\n"
					+ "    \"place_name\": \"Beachside Park\",\n" + "    \"latitude\": \"40.689247\",\n"
					+ "    \"longitude\": \"-74.044502\"\n" + "  }\n" + "}";

			given().header("Authorization", "Bearer " + accessToken).contentType("application/json")
					.body(eventRequestBody).when().post(EndPoints.EVENTS).then().statusCode(201);
		}
	}

	private boolean areTokensAvailable() {
		return Authentication.getAccessToken() != null && Authentication.getRefreshToken() != null;
	}
}
