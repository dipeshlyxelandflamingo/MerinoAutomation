package domestic;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Base.BaseClass;
import generic.MerinoUtility;

public class Design_Explorer_Email_Enquiry extends BaseClass {

	@Test
	public void TC_01() {
		try {
		driver.navigate().to("https://staging.merinolaminates.com/en/design/10002-mangfall-beech/?cat=76");

		driver.findElement(By.id("cookie_action_close_header")).click();

		By EnquiryButton = By.xpath("//a[@href='#enqiryForm']");
		MerinoUtility.waitForElementToBeClickable(driver, EnquiryButton);
		driver.findElement(EnquiryButton).click();

		driver.findElement(By.name("Name")).sendKeys("Dipesh");
		driver.findElement(By.name("email")).sendKeys("dipesh.singh@lyxelandflamingo.com");
		driver.findElement(By.name("mobile")).sendKeys("6354899390");

		WebElement CountryDD = driver.findElement(By.name("Country"));
		MerinoUtility.selectbyvalue(CountryDD, "India");

		WebElement StateDD = driver.findElement(By.name("state"));
		MerinoUtility.selectbyvalue(StateDD, "Uttar Pradesh");

		WebElement CityDD = driver.findElement(By.name("city"));
		MerinoUtility.selectbyvalue(CityDD, "Gautam Buddha Nagar");

		WebElement YouAre = driver.findElement(By.name("you_are"));
		MerinoUtility.selectbyvalue(YouAre, "Contractor");

		driver.findElement(By.name("age_confirm")).click();

		By SubmitButton = By.xpath("(//input[@value='Submit'])[1]");
		MerinoUtility.waitForElementToBeClickable(driver, SubmitButton);
		driver.findElement(SubmitButton).click();
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
					.findElement(By.xpath("//div[text()='Thank you for your message. It has been sent.']"));
			System.out.println(DownloadStartMsg.getText());
		}
		sheet.getRow(5).createCell(3).setCellValue("Form Filled with All Valid Input!");
		sheet.getRow(5).createCell(4).setCellValue("Form Submitting Succsessfully!");
	} catch (Exception e) {
		sheet.getRow(5).createCell(4).setCellValue("Form Not Submitting Succsessfully! ");

	}
	}
}
