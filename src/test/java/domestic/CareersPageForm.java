package domestic;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import generic.MerinoUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CareersPageForm {
	WebDriver driver;
	FileInputStream file;
	FileOutputStream fileOut;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	DataFormatter formate;

	@BeforeClass
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

	@Test(description = "Open Curation Page and Scroll Down to Form and fill the form with all Valid Input")
	void TC_01() throws Exception {

		Thread.sleep(2000);
		driver.navigate().to(
				"https://staging.merinolaminates.com/en/job-apply/?jid=Area%20Sales%20Manager%20%E2%80%93%20Project%20&%20OEM%20Sales&jit=37033");

		driver.findElement(By.id("cookie_action_close_header")).click();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 200).perform();

		try {

			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@name='Name'])[1]")).sendKeys("Dipesh");
			driver.findElement(By.xpath("(//input[@name='email'])[1]")).sendKeys("dipesh.singh@lyxelandflamingo.com");
			driver.findElement(By.xpath("(//input[@name='mobile'])[1]")).sendKeys("6354899390");

			WebElement DOB = driver.findElement(By.name("date-of-birth"));
			DOB.sendKeys("21-03-1999");
			
			WebElement GenderDD = driver.findElement(By.name("gender"));
			
			MerinoUtility.selectbyvalue(GenderDD, "Male");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

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
