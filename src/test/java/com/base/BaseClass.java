package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	RequestSpecification reqSpec;
	Response response;

	/**
	 * @see used to add header
	 * @param key
	 * @param value
	 */
	public void addHeader(String key, String value) {

		reqSpec = RestAssured.given().header(key, value);

	}

	/**
	 * @see used to add header
	 * @param header
	 */
	public void addHeaders(Headers header) {
		reqSpec = RestAssured.given().headers(header);
	}

	/**
	 * @see used to add basic authentication for login
	 * @param username
	 * @param password
	 */
	public void addBasicAuth(String username, String password) {

		reqSpec = reqSpec.auth().preemptive().basic(username, password);

	}

	/**
	 * @see used to add query parameter
	 * @param key
	 * @param value
	 */
	public void addQueryParam(String key, String value) {

		reqSpec = reqSpec.queryParam(key, value);

	}

	/**
	 * @see used to add path parameter
	 * @param key
	 * @param value
	 */
	public void addPathParam(String key, String value) {

		reqSpec = reqSpec.pathParam(key, value);

	}

	/**
	 * @see used to add request body as String
	 * @param body
	 */
	public void addBody(String body) {

		reqSpec = reqSpec.body(body);
	}

	/**
	 * @see used to add request body as Object
	 * @param body
	 */
	public void addBody(Object body) {

		reqSpec = reqSpec.body(body);
	}

	/**
	 * @see used to given request type and endpoint
	 * @param type
	 * @param endPoint
	 * @return
	 */
	public Response requestType(String type, String endPoint) {

		switch (type) {
		case "GET":
			response = reqSpec.log().all().get(endPoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endPoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endPoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endPoint);
			break;

		default:
			break;
		}
		return response;

	}

	/**
	 * @see used to get status code
	 * @param response
	 * @return
	 */
	public int getStatusCode(Response response) {

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		return statusCode;

	}

	/**
	 * @see used to get request boby as String
	 * @param response
	 * @return
	 */
	public String getReqBodyAsString(Response response) {

		String asString = response.asString();
		System.out.println(asString);
		return asString;
	}

	/**
	 * @see used to get request boby as pretty String
	 * @param response
	 * @return
	 */
	public String getReqBodyAsPrettyString(Response response) {

		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		return asPrettyString;
	}

	/**
	 * @see used to get the project path
	 * @return String
	 */
	public static String getProjectPath() {

		String path = System.getProperty("user.dir");
		return path;

	}

	/**
	 * @see used to get property file value
	 * @param key
	 * @return String
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(getProjectPath() + "\\Config\\config.properties"));
		Object object = properties.get(key);
		String value = (String) object;
		return value;
	}

}
