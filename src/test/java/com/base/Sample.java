package com.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.endpoints.Endpoints;
import com.payload.address.AddressPayload;
import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.AddUserAddress_Output_Pojo;
import com.pojo.address.CityList;
import com.pojo.address.CityList_Input_Pojo;
import com.pojo.address.CityList_Output_Pojo;
import com.pojo.address.DeleteUserAddress_Output_Pojo;
import com.pojo.address.DeleteUserAddress_input_Pojo;
import com.pojo.address.GetUserAddress_Output_Pojo;
import com.pojo.address.StateList;
import com.pojo.address.StateList_Output_Pojo;
import com.pojo.address.UpdateUserAddress_Input_Pojo;
import com.pojo.address.UpdateUserAddress_Output_Pojo;
import com.pojo.login.Login_Output_Pojo;
import com.pojo.product.SearchProduct_Input_Pojo;
import com.pojo.product.SearchProduct_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Sample extends BaseClass {

	String logtoken;
	String stateId;
	int state_Id;
	int cityId;
	String address_id;
	static AddressPayload addressPayload = new AddressPayload();

	@Test(priority = 1)
	public void login() throws FileNotFoundException, IOException {

		addHeader("accept", "application/json");

		addBasicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));

		Response response = requestType("POST", Endpoints.POSTMANBASICAUTHLOGIN);

		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);

		Assert.assertEquals(actstatusCode, 200, "verify status code");

		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String actfirstName = login_Output_Pojo.getData().getFirst_name();
		System.out.println(actfirstName);
		Assert.assertEquals(actfirstName, "Ajith", "verify first name");
		logtoken = login_Output_Pojo.getData().getLogtoken();
	}

	@Test(priority = 4)
	public void addUserAddress() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

//		AddUserAddress_Input_Pojo address_Input_Pojo = new AddUserAddress_Input_Pojo("Ajith", "Augustin", "8547062549",
//				"BASS", state_Id, cityId, 101, "685612", "Munnar", "Home");

		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = addressPayload.addUserAddressPayload("Ajith", "Augustin", "8547062549","BASS", state_Id, cityId, 101, "685612", "Munnar", "Home");
		addBody(addUserAddress_Input_Pojo);

		Response response = requestType("POST", Endpoints.ADDUSERADDRESS);

		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify status code");

		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String actMessage = addUserAddress_Output_Pojo.getMessage();
		int addressId = addUserAddress_Output_Pojo.getAddress_id();
		address_id = String.valueOf(addressId);
		Assert.assertEquals(actMessage, "Address added successfully", "Verify Address added successfully");
	}

	@Test(priority = 2)
	public void stateList() {

		addHeader("accept", "application/json");

		Response response = requestType("GET", Endpoints.STATELIST);

		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "verify status code");

		StateList_Output_Pojo stateList_Output_Pojo = response.as(StateList_Output_Pojo.class);
		ArrayList<StateList> stateList = stateList_Output_Pojo.getData();

		for (StateList eachStateList : stateList) {
			String actStateName = eachStateList.getName();

			if (actStateName.equals("Kerala")) {

				int state_Id = eachStateList.getId();
				stateId = String.valueOf(state_Id);
				System.out.println(state_Id);
				Assert.assertEquals(actStateName, "Kerala", "Verify state name as Kerala");

			}

		}

	}

	@Test(priority = 3)
	public void cityList() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(stateId);
		addBody(cityList_Input_Pojo);

		Response response = requestType("POST", Endpoints.CITYLIST);
		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);
		ArrayList<CityList> Citylist = cityList_Output_Pojo.getData();

		for (CityList eachCityList : Citylist) {

			String actCityName = eachCityList.getName();
			if (actCityName.equals("Munnar")) {

				cityId = eachCityList.getId();
				System.out.println(cityId);
				Assert.assertEquals(actCityName, "Munnar", "Verify city name as Munnar");

			}

		}
	}

	@Test(priority = 5)
	public void updateUserAddress() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(address_id,
				"Ajith", "Augustin", "8547062549", "KDHP", state_Id, cityId, 101, "685612", "Munnar", "Home");
		addBody(updateUserAddress_Input_Pojo);

		Response response = requestType("PUT", Endpoints.UPDATEUSERADDRESS);

		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "verify status code");

		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = response.as(UpdateUserAddress_Output_Pojo.class);
		String actMessage = updateUserAddress_Output_Pojo.getMessage();
		System.out.println(actMessage);
		Assert.assertEquals(actMessage, "Address updated successfully", "Verify Address updated successfully");

	}

	@Test(priority = 6)
	public void getUserAddress() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		Response response = requestType("GET", Endpoints.GETUSERADDRESS);

		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "verify status code");

		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		String actMessage = getUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(actMessage, "OK", "verify get user address");

	}

	@Test(priority = 7)
	public void deleteUserAddress() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		DeleteUserAddress_input_Pojo deleteUserAddress_input_Pojo = new DeleteUserAddress_input_Pojo(address_id);
		addBody(deleteUserAddress_input_Pojo);

		Response response = requestType("DELETE", Endpoints.DELETEUSERADDRESS);

		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "verify status code");

		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String actMessage = deleteUserAddress_Output_Pojo.getMessage();
		System.out.println(actMessage);
		Assert.assertEquals(actMessage, "Address deleted successfully", "verify Address deleted successfully");

	}

	@Test(priority = 8)
	public void searchProduct() {

		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		SearchProduct_Input_Pojo searchProduct_Input_Pojo = new SearchProduct_Input_Pojo("nuts");
		addBody(searchProduct_Input_Pojo);

		Response response = requestType("POST", Endpoints.SEARCHPRODUCT);

		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "verify status code");

		SearchProduct_Output_Pojo searchProduct_Output_Pojo = response.as(SearchProduct_Output_Pojo.class);
		String actMessage = searchProduct_Output_Pojo.getMessage();
		Assert.assertEquals(actMessage, "OK", "Verify search product");

	}
}
