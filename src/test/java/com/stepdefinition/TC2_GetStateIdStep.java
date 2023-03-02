package com.stepdefinition;

import java.util.ArrayList;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.StateList;
import com.pojo.address.StateList_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC2_GetStateIdStep extends BaseClass {

	Response response;
	

	/**
	 * @see used to add headers for to statelist
	 */
	@Given("User add headers for to statelist")
	public void userAddHeadersForToStatelist() {

		addHeader("accept", "application/json");

	}

	/**
	 * @see used to send request for statelist endpoint
	 * @param string
	 */
	@When("User send {string} request for statelist endpoint")
	public void userSendRequestForStatelistEndpoint(String type) {

		response = requestType(type, Endpoints.STATELIST);
		
	}

	/**
	 * @see used to verify the statelist response message
	 * @param string
	 */
	@Then("User verify the statelist response message matches {string} and saved the state id")
	public void userVerifyTheStatelistResponseMessageMatchesAndSavedTheStateId(String expStateName ) {
		
		StateList_Output_Pojo stateList_Output_Pojo = response.as(StateList_Output_Pojo.class);
		ArrayList<StateList> stateList = stateList_Output_Pojo.getData();

		for (StateList eachStateList : stateList) {
			String actStateName = eachStateList.getName();

			if (actStateName.equals(expStateName)) {

				int stateIdNum = eachStateList.getId();
				TC1_LoginStep.globalDatas.setStateIdNum(stateIdNum);
				String state_Id = String.valueOf(stateIdNum);
				TC1_LoginStep.globalDatas.setStateId(state_Id);
				System.out.println(state_Id);
				Assert.assertEquals("Verify state name as Kerala", expStateName,actStateName);
               
			}

		}

	}

}
