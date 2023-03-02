package com.reports;

import java.util.List;

import com.base.BaseClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting extends BaseClass {
	/**
	 * @see used to generate JVM report
	 * @param jsonFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void generateJvmReport(String jsonFile) throws FileNotFoundException, IOException {

		File File = new File(getProjectPath() + getPropertyFileValue("jvmPath"));

		Configuration configuration = new Configuration(File, "omrbranch");

		configuration.addClassifications("Browser", "Chrome");
		configuration.addClassifications("Version", "109");
		configuration.addClassifications("OS", "Windows");
		configuration.addClassifications("Sprint", "117");

		List<String> jsonFiles = new ArrayList<String>();

		jsonFiles.add(jsonFile);

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
	}

}
