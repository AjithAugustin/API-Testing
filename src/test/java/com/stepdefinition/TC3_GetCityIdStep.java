package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.CityList;
import com.pojo.address.CityList_Input_Pojo;
import com.pojo.address.CityList_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC3_GetCityIdStep extends BaseClass {

	Response response;

	/**
	 * @see used to add headers for to citylist
	 */
	@Given("User add headers for to citylist")
	public void userAddHeadersForToCitylist() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

	}

	/**
	 * @see used to add request body for citylist
	 * @param string
	 */
	@When("User add request body for citylist and enter stateId")
	public void userAddRequestBodyForCitylistAndEnterStateId() {

		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(TC1_LoginStep.globalDatas.getStateId());
		addBody(cityList_Input_Pojo);

	}

	/**
	 * @see used to send request for citylist endpoint
	 * @param string
	 */
	@When("User send {string} request for citylist endpoint")
	public void userSendRequestForCitylistEndpoint(String type) {

		response = requestType(type, Endpoints.CITYLIST);
		

	}

	/**
	 * @see used to verify the citylist response message
	 * @param string
	 */
	@Then("User verify the citylist response message matches {string} and saved the city id")
	public void userVerifyTheCitylistResponseMessageMatchesAndSavedTheCityId(String expCityName) {

		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);
		ArrayList<CityList> Citylist = cityList_Output_Pojo.getData();

		for (CityList eachCityList : Citylist) {

			String actCityName = eachCityList.getName();
			if (actCityName.equals(expCityName)) {

				int cityId = eachCityList.getId();
				TC1_LoginStep.globalDatas.setCityId(cityId);
				System.out.println(cityId);

				Assert.assertEquals("Verify city name as Munnar", expCityName, actCityName);

			}

		}

	}

}
