package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public RequestSpecification ReqSpec() throws IOException
	{
		if(req==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		 req = new RequestSpecBuilder().setBaseUri(getGlobalValue("BaseURL")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
		 
		 return req;
		}
		return req;
	}

	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties(); //this class will help to read the .properties file from project
		FileInputStream fis = new FileInputStream("/Users/vaibhav/eclipse-workspace/ApiFramework/src/test/java/resources/global.properties");
		// above like will find the .properties file and read all of it's content
		prop.load(fis); // this file will provide all the .properties file info to prop.
		return prop.getProperty(key); // this like will return BaseURL value from .properties file
		
		
		
	}

	public String getJsonPath(Response response, String key)
	{
		String res = response.asString();

		JsonPath js = new JsonPath(res);
		String place_id = js.getString(key);
		//System.out.println(place_id);

		return place_id;
		
	}
	
}
