package com.payload.address;


import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.CityList_Input_Pojo;
import com.pojo.address.DeleteUserAddress_input_Pojo;
import com.pojo.address.UpdateUserAddress_Input_Pojo;

public class AddressPayload {
	
	/**
	 * @see used to add user address payload
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 * @return
	 */
	public AddUserAddress_Input_Pojo addUserAddressPayload(String first_name, String last_name, String mobile, String apartment, int state,
			int city, int country, String zipcode, String address, String address_type) {
		
		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo(first_name, last_name, mobile, apartment, state, city, country, zipcode, address, address_type);
		return addUserAddress_Input_Pojo;
		
	}
	
	/**
	 * @see used to update user address payload
	 * @param address_id
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 * @return
	 */
    public UpdateUserAddress_Input_Pojo updateUserAddressPayload(String address_id, String first_name, String last_name, String mobile,
			String apartment, int state, int city, int country, String zipcode, String address, String address_type) {
    	
    	UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(address_id, first_name, last_name, mobile, apartment, state, city, country, zipcode, address, address_type);
		return updateUserAddress_Input_Pojo;

	}
    
    /**
     * @see used to delete user address payload
     * @param address_id
     * @return
     */
    public DeleteUserAddress_input_Pojo deleteUserAddressPayload(String address_id) {
    	
    	DeleteUserAddress_input_Pojo deleteUserAddress_input_Pojo = new DeleteUserAddress_input_Pojo(address_id);
		return deleteUserAddress_input_Pojo;
    	
    	
	}
    
    /**
     * @see used for city list payload
     * @param state_id
     * @return
     */
    public CityList_Input_Pojo cityListPayload(String state_id) {
 
    	CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(state_id);
		return cityList_Input_Pojo;
    	
    }
}
