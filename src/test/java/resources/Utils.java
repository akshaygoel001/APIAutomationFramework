package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification reqSpec;
	ResponseSpecification respSpec;
	PrintStream logLocation;
	public RequestSpecification requestSpecBuilder() {
		if(reqSpec==null) {
			try {
				logLocation=new PrintStream(new FileOutputStream("log.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			reqSpec=new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseURI"))
					.addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(logLocation))
					.addFilter(ResponseLoggingFilter.logResponseTo(logLocation))
					.build();
			return reqSpec;
		}
		
		return reqSpec;
	}
	public ResponseSpecification responseSpecBuilder() {
		respSpec=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		return respSpec;
	}
	public String getGlobalValue(String key) {
		Properties prop=new Properties();
		FileInputStream fs;
		try {
			fs=new FileInputStream(".\\src\\test\\java\\resources\\global.properties");
			prop.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
		
	}
	public String getJsonPath(Response response,String key) {
		String respString=response.asString();
		JsonPath js=new JsonPath(respString);
		return js.getString(key);
	}

}
