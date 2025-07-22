package domestic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseClass;
import generic.MerinoUtility;

public class Curations extends BaseClass {
	
	 @Test(description = "Open Curation Page and Scroll Down to Form and fill the form with all Valid Input")
	    void TC_01() throws Exception {
	        try {
	            Thread.sleep(2000);
	            driver.navigate().to("https://staging.merinolaminates.com/en/curations/");

	            driver.findElement(By.id("cookie_action_close_header")).click();
	            driver.findElement(By.xpath("//span[@class='close']")).click();

	            // Scroll down
	            Actions actions = new Actions(driver);
	            actions.scrollByAmount(0, 7300).perform();
	            Thread.sleep(2000);

	            // Fill form
	            driver.findElement(By.xpath("(//input[@name='Name'])[1]")).sendKeys("Dipesh");
	            driver.findElement(By.xpath("(//input[@name='email'])[1]")).sendKeys("dipesh.singh@lyxelandflamingo.com");
	            driver.findElement(By.xpath("(//input[@name='mobile'])[1]")).sendKeys("6354899390");

	            WebElement CountryDropdown = driver.findElement(By.xpath("(//Select[@name='Country'])[1]"));
	            new Select(CountryDropdown).selectByValue("India");

	            WebElement StateDropdown = driver.findElement(By.id("stateDropDown"));
	            new Select(StateDropdown).selectByValue("Uttar Pradesh");

	            WebElement CityDropdown = driver.findElement(By.xpath("(//Select[@name='city'])[1]"));
	            new Select(CityDropdown).selectByValue("Gautam Buddha Nagar");

	            WebElement Youare = driver.findElement(By.xpath("(//select[@name='you_are'])[1]"));
	            new Select(Youare).selectByValue("Contractor");

	            Thread.sleep(2000);
	            driver.findElement(By.id("acceptTerms")).click();
	            driver.findElement(By.xpath("(//input[@type='submit'])[3]")).click();

	            // Wait for possible error or success
	            Thread.sleep(3000);

	            // --- Check for ERROR message first ---
	            try {
	                WebElement errorMsg = driver.findElement(By.xpath("//*[contains(text(),'There was an error trying to send your message')]"));
	                System.out.println("❌ " + errorMsg.getText());

	                sheet.getRow(4).createCell(3).setCellValue("Form Filled with All Valid Input!");
	                sheet.getRow(4).createCell(4).setCellValue("Form Not Submitting Successfully! Error: " + errorMsg.getText());

	                // Mark test as FAILED
	                Assert.fail("Form submission failed with error: " + errorMsg.getText());
	                return;

	            } catch (Exception noError) {
	                // No error found, continue
	            }

	            // --- Try OTP Thank You Message ---
	            try {
	                By OTP = By.xpath("//input[@class='onlyNumber']");
	                MerinoUtility.waitForVisibilityOfElement(driver, OTP);
	                driver.findElement(OTP).sendKeys("899390");

	                By SubmitOTPButton = By.xpath("(//input[@name='submit'])[1]");
	                MerinoUtility.waitForVisibilityOfElement(driver, SubmitOTPButton);
	                driver.findElement(SubmitOTPButton).click();

	                WebElement ThanksMsg = driver.findElement(By.xpath("//h2[text()='Thank You']"));
	                System.out.println("✅ " + ThanksMsg.getText());

	                sheet.getRow(4).createCell(3).setCellValue("Form Filled with All Valid Input!");
	                sheet.getRow(4).createCell(4).setCellValue("Form Submitting Successfully!");

	            } catch (Exception e1) {
	                try {
	                    WebElement ThanksMsg = driver.findElement(By.xpath("//div[text()='Thank you for your message. It has been sent.']"));
	                    System.out.println("✅ " + ThanksMsg.getText());

	                    sheet.getRow(4).createCell(3).setCellValue("Form Filled with All Valid Input!");
	                    sheet.getRow(4).createCell(4).setCellValue("Form Submitting Successfully!");
	                } catch (Exception e2) {
	                    System.out.println("❌ Unknown response after submit.");
	                    sheet.getRow(4).createCell(4).setCellValue("Form Not Submitting Successfully! Unknown Response.");
	                    Assert.fail("Unknown response after submission.");
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            sheet.getRow(4).createCell(4).setCellValue("Form Not Submitting Successfully! Exception Occurred.");
	            Assert.fail("Test failed due to exception: " + e.getMessage());
	        }
	    }
	}