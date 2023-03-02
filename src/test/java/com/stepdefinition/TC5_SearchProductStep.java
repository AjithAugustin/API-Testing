package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.payload.product.ProductPayload;
import com.pojo.product.SearchProduct_Input_Pojo;
import com.pojo.product.SearchProduct_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC5_SearchProductStep extends BaseClass {

	Response response;
	static ProductPayload productPayload = new ProductPayload();

	/**
	 * @see used to search product for accessing searchProduct endpoint
	 */
	@Given("User search product for accessing searchProduct endpoint")
	public void userSearchProductForAccessingSearchProductEndpoint() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

	}

	/**
	 * @see used to add request body for search product
	 * @param string
	 */
	@When("User add request body for search product {string}")
	public void userAddRequestBodyForSearchProduct(String text) {
		
		SearchProduct_Input_Pojo searchProduct_Input_Pojo = productPayload.searchProductPayload(text);
		addBody(searchProduct_Input_Pojo);

	}

	/**
	 * @see used to send {string} request for searchProduct endpoint
	 * @param string
	 */
	@When("User send {string} request for searchProduct endpoint")
	public void userSendRequestForSearchProductEndpoint(String type) {
		
		response = requestType(type, Endpoints.SEARCHPRODUCT);
		
		int actstatusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(actstatusCode);

	}

	/**
	 * @see used to verify the searchProduct response message
	 * @param string
	 */
	@Then("User verify the searchProduct response message matches {string}")
	public void userVerifyTheSearchProductResponseMessageMatches(String expMessage) {
		
		SearchProduct_Output_Pojo searchProduct_Output_Pojo = response.as(SearchProduct_Output_Pojo.class);
		String actMessage = searchProduct_Output_Pojo.getMessage();
		Assert.assertEquals("Verify search product", expMessage,actMessage);

	}

}
