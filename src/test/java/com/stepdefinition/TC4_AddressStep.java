package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.payload.address.AddressPayload;
import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.AddUserAddress_Output_Pojo;
import com.pojo.address.DeleteUserAddress_Output_Pojo;
import com.pojo.address.DeleteUserAddress_input_Pojo;
import com.pojo.address.GetUserAddress_Output_Pojo;
import com.pojo.address.UpdateUserAddress_Input_Pojo;
import com.pojo.address.UpdateUserAddress_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC4_AddressStep extends BaseClass {

	String address_Id;
	Response response;
	static AddressPayload addressPayload = new AddressPayload();

	/**
	 * @see used to add header and bearer autherization for accessing address
	 *      endpoint
	 * 
	 */
	@Given("User add header and bearer autherization for accessing address endpoint")
	public void userAddHeaderAndBearerAutherizationForAccessingAddressEndpoint() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

	}

	/**
	 * @see used to add request body for add new address
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 */

	@When("User add request body for add new address {string},{string},{string},{string},{string},{string},{string} and {string}")
	public void userAddRequestBodyForAddNewAddressAnd(String first_name, String last_name, String mobile,
		String apartment, String country, String zipcode, String address, String address_type) {
		int countryId = Integer.parseInt(country);
		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = addressPayload.addUserAddressPayload(first_name,last_name, mobile, apartment, TC1_LoginStep.globalDatas.getStateIdNum(),
				TC1_LoginStep.globalDatas.getCityId(), countryId, zipcode, address, address_type);
		addBody(addUserAddress_Input_Pojo);
	}

	/**
	 * @see used to send request for addUserAddress endpoint
	 * @param type
	 */
	@When("User send {string} request for addUserAddress endpoint")
	public void userSendRequestForAddUserAddressEndpoint(String type) {

		response = requestType(type, Endpoints.ADDUSERADDRESS);

	}

	/**
	 * @see used to verify the address response message
	 * @param expMessage
	 */
	@Then("User verify the address response message matches {string}")
	public void userVerifyTheAddressResponseMessageMatches(String expMessage) {
		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String actMessage = addUserAddress_Output_Pojo.getMessage();
		System.out.println(actMessage);
		Assert.assertEquals("Verify Add User Address Success Message", expMessage, actMessage);
		int addressId = addUserAddress_Output_Pojo.getAddress_id();
		address_Id = String.valueOf(addressId);
		TC1_LoginStep.globalDatas.setAddress_Id(address_Id);

	}

	/**
	 * @see used to add header and bearer autherization for accessing
	 *      updateUserAddress endpoint
	 * 
	 */
	@Given("User add header and bearer autherization for accessing updateUserAddress endpoint")
	public void userAddHeaderAndBearerAutherizationForAccessingUpdateUserAddressEndpoint() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

	}

	/**
	 * @see used to add request body for update new address
	 * @param address_id
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 */
	@When("User add request body for update new address {string},{string},{string},{string},{string},{string},{string} and {string}")
	public void userAddRequestBodyForUpdateNewAddressAnd(String first_name, String last_name, String mobile,
	    String apartment, String country, String zipcode, String address, String address_type) {
		int countryId = Integer.parseInt(country);
		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = addressPayload.updateUserAddressPayload(TC1_LoginStep.globalDatas.getAddress_Id(), first_name, last_name, mobile, apartment,
				TC1_LoginStep.globalDatas.getStateIdNum(), TC1_LoginStep.globalDatas.getCityId(), countryId, zipcode,
		address, address_type);
		addBody(updateUserAddress_Input_Pojo);
	}

	/**
	 * @see used to send request for updateUserAddress endpoint
	 * @param type
	 */
	@When("User send {string} request for updateUserAddress endpoint")
	public void userSendRequestForUpdateUserAddressEndpoint(String type) {

		response = requestType(type, Endpoints.UPDATEUSERADDRESS);

	}

	/**
	 * @see used to verify the updateUserAddress response message
	 * @param expMessage
	 */
	@Then("User verify the updateUserAddress response message matches {string}")
	public void userVerifyTheUpdateUserAddressResponseMessageMatches(String expMessage) {

		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = response.as(UpdateUserAddress_Output_Pojo.class);
		String actMessage = updateUserAddress_Output_Pojo.getMessage();
		System.out.println(actMessage);
		Assert.assertEquals("Verify Address updated successfully", expMessage, actMessage);

	}

	/**
	 * @see used to add header and bearer autherization for accessing getUserAddress
	 *      endpoint
	 * 
	 */
	@Given("User add header and bearer autherization for accessing getUserAddress endpoint")
	public void userAddHeaderAndBearerAutherizationForAccessingGetUserAddressEndpoint() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

	}

	/**
	 * @see used to send request for getUserAddress endpoint
	 * @param type
	 */
	@When("User send {string} request for getUserAddress endpoint")
	public void userSendRequestForGetUserAddressEndpoint(String type) {

		response = requestType(type, Endpoints.GETUSERADDRESS);

	}

	/**
	 * @see used to verify the getUserAddress response message
	 * @param expMessage
	 */
	@Then("User verify the getUserAddress response message matches {string}")
	public void userVerifyTheGetUserAddressResponseMessageMatches(String expMessage) {

		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		String actMessage = getUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals("verify get user address", expMessage, actMessage);

	}

	/**
	 * @see used to add header and bearer autherization for accessing
	 *      deleteUserAddress endpoint
	 * 
	 */
	@Given("User add header and bearer autherization for accessing deleteUserAddress endpoint")
	public void userAddHeaderAndBearerAutherizationForAccessingDeleteUserAddressEndpoint() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

	}

	/**
	 * @see used to add request body for delete address
	 * @param address_id
	 */
	@When("User add request body for delete address")
	public void userAddRequestBodyForDeleteAddress() {

		DeleteUserAddress_input_Pojo deleteUserAddress_input_Pojo = addressPayload
				.deleteUserAddressPayload(TC1_LoginStep.globalDatas.getAddress_Id());
		addBody(deleteUserAddress_input_Pojo);
	}

	/**
	 * @see used to send request for deleteUserAddress endpoint
	 * @param type
	 */
	@When("User send {string} request for deleteUserAddress endpoint")
	public void userSendRequestForDeleteUserAddressEndpoint(String type) {

		response = requestType(type, Endpoints.DELETEUSERADDRESS);

	}

	/**
	 * @see used to verify the deleteUserAddress response message
	 * @param expMessage
	 */
	@Then("User verify the deleteUserAddress response message matches {string}")
	public void userVerifyTheDeleteUserAddressResponseMessageMatches(String expMessage) {

		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String actMessage = deleteUserAddress_Output_Pojo.getMessage();
		System.out.println(actMessage);
		Assert.assertEquals("verify Address deleted successfully", expMessage, actMessage);

	}

}
