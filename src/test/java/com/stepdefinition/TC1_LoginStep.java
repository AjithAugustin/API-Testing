package com.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.global.GlobalDatas;
import com.pojo.login.Login_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC1_LoginStep extends BaseClass {
	
	static Response response;
	static GlobalDatas globalDatas = new GlobalDatas();
	
	/**
	 * @see used to add header
	 */
	@Given("User add header")
	public void userAddHeader() {
		
		addHeader("accept", "application/json");
	    
	}
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @see used to add basic authentication for login
	 */
	@When("User add basic authentication for login")
	public void userAddBasicAuthenticationForLogin() throws FileNotFoundException, IOException {
		
		addBasicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));
	   
	}
	/**
	 * @see used to send request for login endpoint
	 * @param string
	 */
	@When("User send {string} request for login endpoint")
	public void userSendRequestForLoginEndpoint(String type) {
	    
		response = requestType(type, Endpoints.POSTMANBASICAUTHLOGIN);
		
		int actstatusCode = getStatusCode(response);
		globalDatas.setStatusCode(actstatusCode);
		
		
	}
	/**
	 * @see used to verify the login response body firstname and get the logtoken saved
	 * @param string
	 */
	@Then("User verify the login response body firstname present as {string} and get the logtoken saved")
	public void userVerifyTheLoginResponseBodyFirstnamePresentAsAndGetTheLogtokenSaved(String expFirstName) {
		
		//Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		//String actFirstName = login_Output_Pojo.getData().getFirst_name();
		String actFirstName = login_Output_Pojo.getData().getFirst_name();
		System.out.println(actFirstName);
		Assert.assertEquals("verify first name",expFirstName, actFirstName);
		String logtoken = login_Output_Pojo.getData().getLogtoken();
	    globalDatas.setLogtoken(logtoken);
	}


}
