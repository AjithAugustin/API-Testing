package com.payload.product;

import com.pojo.product.SearchProduct_Input_Pojo;

public class ProductPayload {
	
	/**
	 * @see used to add search product payload
	 * @param text
	 * @return
	 */
	public SearchProduct_Input_Pojo searchProductPayload(String text) {
      
		SearchProduct_Input_Pojo searchProduct_Input_Pojo = new SearchProduct_Input_Pojo(text);
		return searchProduct_Input_Pojo;
	
	}

}
