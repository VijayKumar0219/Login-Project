package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	
	WebDriver driver;
	Logger log;
	
	@Test(dataProvider="getLoginData")
	public void login(String email,String password,String expectedresult) throws IOException, InterruptedException {
		
		
		
		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountDropDown().click();
		log.debug("Clicked on My Account dropdown");
		landingpage.loginOption().click();
		log.debug("Clicked on login option");
		
		Thread.sleep(3000);
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(email);
		log.debug("Email addressed got entered");
		loginpage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginpage.loginButton().click();
		log.debug("Clicked on Login Button");
		
		AccountPage accountpage = new AccountPage(driver);
		
		String actualresult = null;
		
		try {
		  
			if(accountpage.accountverifyPage().isDisplayed()){
				log.debug("User got logged in");
			    actualresult = "Successful";
			}
		}catch(Exception e)	{
			
		    log.debug("User didn't log in");
			actualresult = "Failure";
			
		}
		
        if(actualresult.equals(expectedresult)) {
			
			log.info("Login Test got passed");
			
		}else {
			
			log.error("Login Test got failed");
		}
	}
	
	@BeforeMethod
	public void openApplication() throws IOException {
		
		log = LogManager.getLogger(LoginTest.class.getName());
		
		driver = initializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
	}
	
	@AfterMethod
	public void closure() {
		
		driver.close();
		log.debug("Browser got closed");
	}
    
	@DataProvider
	public Object[][] getLoginData() {
		
		Object[][] data = {{"vijaykumar.s@gmail.com","Selenium@123","Successful"},{"dummy@gmail.com","dummy","Failure"}};
		
		return data;
	}
}
