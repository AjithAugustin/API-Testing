package com.runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClass;
import com.reports.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags=(" "),
	    dryRun=false,
		publish=true,
		plugin= {"pretty","json:target\\Output.json"},
		snippets=SnippetType.CAMELCASE,
		stepNotifications=true, 
		monochrome=true,
		features="src\\test\\resources\\Features",
		glue="com.stepdefinition"
		)
public class TestRunnerClass extends BaseClass {

	/**
	 * @see used to run after all feature file
	 * @throws IOException
	 */
	@AfterClass
	public static void afterClass() throws IOException {

		Reporting.generateJvmReport(getProjectPath() + getPropertyFileValue("jsonPath"));

	}

}

