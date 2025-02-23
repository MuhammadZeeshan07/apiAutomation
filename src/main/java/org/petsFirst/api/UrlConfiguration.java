package org.petsFirst.api;

import io.restassured.RestAssured;

public class UrlConfiguration {

	public static void v1() {

		RestAssured.baseURI = "https://api-dev.youcater.me/v1";

	}

	public static void v2() {

		RestAssured.baseURI = "https://api-dev.youcater.me/v2";

	}

}
