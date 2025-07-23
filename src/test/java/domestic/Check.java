package domestic;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Base.BaseClass;

public class Check extends BaseClass{
	
	

	 @Test(description = "Verify test")
	    void TC_01() throws Exception {
	        
	            Thread.sleep(5000);
	            driver.navigate().to("https://www.google.com/");

	           // driver.findElement(By.id("cookie_action_close_header")).click();
	           // driver.findElement(By.xpath("//span[@class='close']")).click();
	            
	            System.out.println("Test Succsess");
	            
	           System.out.println(driver.getCurrentUrl());
	            
	            
	            

	            
	        }
	        
	 }
	 

