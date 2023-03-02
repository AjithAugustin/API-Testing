package com.stepdefinition;

import org.junit.Assert;

import io.cucumber.java.en.Then;

public class CommonStep {
	
	/**
	 * @see used toverify the status code
	 * @param int1
	 */
	@Then("User verify the status code is {int}")
	public void userVerifyTheStatusCodeIs(int expStatusCode) {
		
		int actStatusCode = TC1_LoginStep.globalDatas.getStatusCode();
		Assert.assertEquals("verify status code", expStatusCode,actStatusCode);
	    
	}

}
