package domestic;

import org.testng.annotations.Test;
import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Curations extends BaseClass {

	@Test(description = "Open Curation Page and Scroll Down to Form and fill the form with all Valid Input")
	void TC_01() throws Exception {
		try {
			Thread.sleep(2000);
			driver.navigate().to("https://staging.merinolaminates.com/en/curations/");

			driver.findElement(By.id("cookie_action_close_header")).click();

			Actions actions = new Actions(driver);
			actions.scrollByAmount(0, 7300).perform();

			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@name='Name'])[1]")).sendKeys("Dipesh");
			driver.findElement(By.xpath("(//input[@name='email'])[1]")).sendKeys("dipesh.singh@lyxelandflamingo.com");
			driver.findElement(By.xpath("(//input[@name='mobile'])[1]")).sendKeys("6354899390");

			WebElement CountryDropdown = driver.findElement(By.xpath("(//Select[@name='Country'])[1]"));
			Select Country = new Select(CountryDropdown);
			Country.selectByValue("India");

			WebElement StateDropdown = driver.findElement(By.id("stateDropDown"));
			Select State = new Select(StateDropdown);
			State.selectByValue("Uttar Pradesh");

			WebElement CityDropdown = driver.findElement(By.xpath("(//Select[@name='city'])[1]"));
			Select City = new Select(CityDropdown);
			City.selectByValue("Gautam Buddha Nagar");

			WebElement Youare = driver.findElement(By.xpath("(//select[@name='you_are'])[1]"));
			Select WhoRYou = new Select(Youare);
			WhoRYou.selectByValue("Contractor");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@name='age_confirm'])[1]")).click();

			driver.findElement(By.xpath("(//input[@type='submit'])[3]")).click();

			WebElement ThanksMsg = driver
					.findElement(By.xpath("//div[text()='Thank you for your message. It has been sent.']"));
			System.out.println(ThanksMsg.getText());

			sheet.getRow(1).createCell(3).setCellValue("Form Filled with All Valid Input!");
			sheet.getRow(1).createCell(4).setCellValue("Form Submitting Succsessfully!");
		} catch (Exception e) {

			sheet.getRow(1).createCell(4).setCellValue("Form Not Submitting Succsessfully! ");

		}

	}

}
