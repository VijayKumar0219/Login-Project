package tests;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import resources.Base;

public class ThreeTest extends Base {
	
	WebDriver driver;
	
	@Test
	public void testThree() throws IOException, InterruptedException {
		
		System.out.println("Test Three");
		driver = initializeDriver();
		
		driver.get("https://tutorialsninja.com/demo/");
		
		Thread.sleep(2000);
		
		Assert.assertTrue(false);
	}
     
    
}
