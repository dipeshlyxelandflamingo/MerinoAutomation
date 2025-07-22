package generic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MerinoUtility {

	public static void usernamefield(WebDriver driver, String value) {

		WebElement user = driver.findElement(By.xpath("//input[@name='uname']"));
		user.sendKeys(value);

	}

	public static void passwordfield(WebDriver driver, String value) {

		WebElement pass = driver.findElement(By.xpath("//input[@name='psw']"));
		pass.sendKeys(value);

	}

	public static void submitbutton(WebDriver driver) {

		WebElement submit = driver.findElement(By.xpath("//input[@value='Submit']"));

		submit.click();

	}

	public static void scrolUptoElement(WebDriver driver, WebElement ele) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);

	}

	public static WebElement waitForVisibilityOfElement(WebDriver driver, By loc) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(loc));

	}

	public static WebElement waitForElementToBeClickable(WebDriver driver, By loc) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(loc));
		
	}



	public static void selectbyvalue(WebElement Emt, String value) {

		Select DD = new Select(Emt);
		DD.selectByValue(value);
	}

	public static void selectbyvindex(WebElement Emt, int value) {

		Select DD = new Select(Emt);
		DD.selectByIndex(value);
	}

}
