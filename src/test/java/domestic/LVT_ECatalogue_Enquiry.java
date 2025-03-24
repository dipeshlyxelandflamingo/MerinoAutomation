package domestic;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import generic.MerinoUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LVT_ECatalogue_Enquiry {

	WebDriver driver;
	FileInputStream file;
	FileOutputStream fileOut;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	DataFormatter formate;

	@BeforeTest
	public void OpenBrowser() throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://staging.merinolaminates.com/en/");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

		MerinoUtility.usernamefield(driver, "stagingMerinoUser");
		MerinoUtility.passwordfield(driver, "s!@5I#6@M34!70");
		MerinoUtility.submitbutton(driver);

		file = new FileInputStream("C:\\Users\\LYXELANDFLAMINGO\\Desktop\\Merino\\Domestic.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet("Domestic");
		formate = new DataFormatter();

	}

	@Test (description = "Open LVT FLooring Page and Scroll Down to Form and fill the form with all Valid Input")
	void TC_01() throws Exception {
		try {
			driver.navigate().to("https://staging.merinolaminates.com/en/product-category/lvt-flooring/loom/");
			driver.findElement(By.id("cookie_action_close_header")).click();
			Thread.sleep(5000);

			Actions actions = new Actions(driver);
			actions.scrollByAmount(0, 400).perform();

			// Open & Close E Catalogue form
			driver.findElement(By.xpath("//*[@class='Button DarkBg disablePopup ECataloguePopup']")).click();
			Thread.sleep(2000);

			// Entering Name, Email, Mobile Number
			driver.findElement(By.xpath("(//*[@placeholder='Enter your full name'])[1]")).sendKeys("dipesh");
			driver.findElement(By.xpath("(//*[@placeholder='Enter your email-id'])[1]"))
					.sendKeys("dipesh.singh@lyxelandflamingo.com");
			driver.findElement(By.xpath("(//*[@placeholder='Enter your mobile number'])[1]")).sendKeys("6354899390");

			// Selecting values from "You Are", "State", "City" 's Drop down.
			WebElement YouAreDD = driver.findElement(By.xpath("(//*[@name='you_are'])[1]"));
			Select YouAre = new Select(YouAreDD);
			YouAre.selectByValue("Builder");

			WebElement StateDD = driver.findElement(By.xpath("(//*[@name='state'])[1]"));
			Select State = new Select(StateDD);
			State.selectByValue("Uttar Pradesh");

			WebElement CityDD = driver.findElement(By.xpath("(//*[@name='city'])[1]"));
			Select City = new Select(CityDD);
			City.selectByValue("Gautam Buddha Nagar");

			// Click on "Age Confirmation" Checkbox and submit button.
			driver.findElement(By.xpath("(//*[@name='age_confirm'])[1]")).click();
			driver.findElement(By.xpath("(//*[@class='Button DarkBg wpcf7-form-control wpcf7-submit'])[1]")).click();

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
			sheet.getRow(23).createCell(4).setCellValue("Form Filled with All Valid Input!");
			sheet.getRow(23).createCell(4).setCellValue("Form Submitting Succsessfully!");
		} catch (Exception e) {
			sheet.getRow(23).createCell(4).setCellValue("Form Not Submitting Succsessfully! ");

		}
	}

	@AfterClass
	void TearDown() throws Exception {
		try {
			fileOut = new FileOutputStream("C:\\Users\\LYXELANDFLAMINGO\\Desktop\\Merino\\Domestic.xlsx");
			workbook.write(fileOut);
		} finally {
			workbook.close();
			file.close();
		}
		driver.quit();

	}

}