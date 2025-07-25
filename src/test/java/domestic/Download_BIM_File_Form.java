package domestic;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import Base.BaseClass;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import generic.MerinoUtility;


public class Download_BIM_File_Form extends BaseClass {

	
    @Test(description = "Verify BIM form submission with valid inputs")
    public void testBimFormSubmission() {
        try {
            driver.get("https://staging.merinolaminates.com/en/bim/");
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie_action_close_header"))).click();
            System.out.println("Navigated to BIM page");
            Thread.sleep(2000);
            Actions actions = new Actions(driver);
    		actions.scrollByAmount(0, 2600).perform();

            driver.findElement(By.xpath("(//img[@class='down-img'])[2]")).click();

            // Fill form
            driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Dipesh");
            driver.findElement(By.xpath("(//input[@name='mobile'])[1]")).sendKeys("6354899390");
            driver.findElement(By.xpath("(//input[@name='email'])[1]")).sendKeys("dipesh.singh@lyxelandflamingo.com");
            driver.findElement(By.xpath("//input[@name='business']")).sendKeys("Abcd");

            WebElement stateDropdown = driver.findElement(By.id("stateDropDown"));
            new Select(stateDropdown).selectByValue("Uttar Pradesh");

            WebElement cityDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("(//select[@name='city'])[1]"))
            );
            new Select(cityDropdown).selectByValue("Gautam Buddha Nagar");

            driver.findElement(By.xpath("(//input[@name='age_confirm'])[1]")).click();
            driver.findElement(By.xpath("(//input[@type='submit'])[1]")).click();

            try {
				By OTP = By.xpath("//input[@class='onlyNumber']");
				MerinoUtility.waitForVisibilityOfElement(driver, OTP);
				driver.findElement(OTP).sendKeys("899390");

				By SubmitOTPButton = By.xpath("(//input[@name='submit'])[1]");
				MerinoUtility.waitForVisibilityOfElement(driver, SubmitOTPButton);
				driver.findElement(SubmitOTPButton).click();

				WebElement ThanksMsg = driver.findElement(By.xpath("//h2[text()='Thank You']"));
				System.out.println(ThanksMsg.getText());

			} catch (Exception e) {

				WebElement DownloadStartMsg = driver
						.findElement(By.xpath("//div[text()='Your download has been started.']"));
				System.out.println(DownloadStartMsg.getText());
			}
			sheet.getRow(1).createCell(3).setCellValue("Form Filled with All Valid Input!");
			sheet.getRow(1).createCell(4).setCellValue("Form Submitting Succsessfully!");
		} catch (Exception e) {
			sheet.getRow(1).createCell(4).setCellValue("Form Not Submitting Succsessfully! ");

		}
	}

	

}
