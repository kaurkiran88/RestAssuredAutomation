package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class TestBase {
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public String StatusLine_200 = "HTTP/1.1 200 OK";

	public Properties prop;
	public Random rand = new Random();
	public int randomNum;
	public String key;
	public String token;
	
	public int statusCode;
	public String statusLine;

	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 

		randomNum = rand.nextInt((20 - 1) + 1) + 1;
		key = prop.getProperty("key");
	    token = prop.getProperty("token");
		
	}
	

}
