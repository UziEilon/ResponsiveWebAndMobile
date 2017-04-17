import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.Cookie.Builder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;

import ch.qos.logback.core.net.server.Client;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;
import com.perfectomobile.selenium.util.EclipseConnector;

public class RemoteWebDriverTest {

	//	String host = "172.30.200.153:8080";
    String browserName = "mobileOS";
	String host = "partners.perfectomobile.com";
	RemoteWebDriver driver;
	PerfectoExecutionContext _perfectoExecutionContext;
	ReportiumClient _client;

	@BeforeTest
	@Parameters({"targetEnvironment"})
	public void setup(String targetEnvironment) throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		switch (targetEnvironment) {
		case "Galaxy S6":
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "ADADB879");
			capabilities.setCapability("browserName", "mobileChrome");
			break;

		case "iPhone":
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("deviceName", "076164908B998B9944CE5E3A50E1028D5708AAEF");
			capabilities.setCapability("browserName", "mobileSafari");
			break;

		case "Chrome 56":
			capabilities.setCapability("platformName", "Windows");
			capabilities.setCapability("platformVersion", "7");
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("browserVersion", "56");
			capabilities.setCapability("resolution", "1280x1024");
			capabilities.setCapability("location", "US East");
			capabilities.setCapability("deviceType", "WEB");
			break;

		case "Firefox 52":
			capabilities.setCapability("platformName", "Windows");
			capabilities.setCapability("platformVersion", "8.1");
			capabilities.setCapability("browserName", "Firefox");
			capabilities.setCapability("browserVersion", "52");
			capabilities.setCapability("resolution", "1366x768");
			capabilities.setCapability("location", "US East");
			break;
			
			
			
			
		}
		capabilities.setCapability("user", "dmitriyy@perfectomobile.com");
		capabilities.setCapability("password", "Spartak79");
		driver = new RemoteWebDriver(new URL("http://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
	
		_perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder().withWebDriver(driver).build(); 
		_client = new ReportiumClientFactory().createPerfectoReportiumClient(_perfectoExecutionContext);
		_client.testStart("DeutscheBank", new TestContext());

	

	try {
        // write your code here

    	
    	
		driver.get(
				"http://db.com/ir/index_en.htm");
		//Thread.sleep(5000);
		//driver.findElementByXPath("//*[@class=\"menu-toggle-button\"]").click();
		screenshot();
		
		Thread.sleep(5000);
		
		
		Map<String, Object> VO1 = new HashMap<>();
		VO1.put("content", "More");
		VO1.put("screen.top", "35%");
		VO1.put("screen.height", "43%");
		VO1.put("screen.left", "0%");
		VO1.put("screen.width", "100%");
		VO1.put("timeout", "60");
		driver.executeScript("mobile:text:select",VO1);
		 try {	
		Map<String, Object> VO2 = new HashMap<>();
		VO2.put("content", "More");
		VO2.put("screen.top", "35%");
		VO2.put("screen.height", "43%");
		VO2.put("screen.left", "0%");
		VO2.put("screen.width", "100%");
		VO2.put("timeout", "60");
		driver.executeScript("mobile:text:select",VO2);
		 } catch (Exception e) {
	            e.printStackTrace();
		 }
		Thread.sleep(3000);
		try {
		switchToContext(driver, "WEBVIEW");
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	driver.findElementByXPath("//*[@id='acceptButton']").click();
		 } catch (Exception e) {
	            e.printStackTrace();
		 }
		
		Thread.sleep(3000);
    	switchToContext(driver, "WEBVIEW");
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	driver.findElementByXPath("//*[@class='logo']//img").click();
    	screenshot();
    	
    	
    	Thread.sleep(3000);
    	switchToContext(driver, "WEBVIEW");
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	driver.findElementByXPath("//*[@id='pi_15645']/section[1]/a").click();
    	Thread.sleep(10000);
    	
    	
    	
    	
    	
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            // Retrieve the URL of the Single Test Report, can be saved to your execution summary and used to download the report at a later point
        	 String reportURL = (String)(driver.getCapabilities().getCapability(WindTunnelUtils.SINGLE_TEST_REPORT_URL_CAPABILITY));

        	 System.out.println("Report URL: " + _client.getReportUrl());
     		_client.testStop(TestResultFactory.createSuccess());
        	 
            driver.close();

            // In case you want to download the report or the report attachments, do it here.
            // PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\report");
            // PerfectoLabUtils.downloadAttachment(driver, "video", "C:\\test\\report\\video", "flv");
            // PerfectoLabUtils.downloadAttachment(driver, "image", "C:\\test\\report\\images", "jpg");

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    System.out.println("Run ended");
	
}

	private void switchToContext(RemoteWebDriver driver2, String string) {
		// TODO Auto-generated method stub
		
	}

	private void screenshot() {
		// TODO Auto-generated method stub
		
	}

}
