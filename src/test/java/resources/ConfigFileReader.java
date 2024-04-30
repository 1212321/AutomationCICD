package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.core.options.CucumberProperties;

public class ConfigFileReader {

// welcome
	
	public String getReportConfigPath() throws IOException{
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "/src/test/resources/Configuration.properties");
		prop.load(fis);
		String reportConfigPath = prop.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}

}
