package Base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import generic.MerinoUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public static FileInputStream file;
	public static FileOutputStream fileOut;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static DataFormatter formatter;

	@BeforeClass
	public void OpenBrowser() {

		// 1. Browser Setup
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

		// 2. Login Flow
		driver.get("https://staging.merinolaminates.com/en/login/");

		// Wait for elements before interacting
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='uname']")));

		MerinoUtility.usernamefield(driver, "stagingMerinoUser");
		MerinoUtility.passwordfield(driver, "@M34!705I#6s!@");
		MerinoUtility.submitbutton(driver);
		try {
			// 3. Excel Initialization
			file = new FileInputStream("C:/Users/LYXELANDFLAMINGO/Desktop/Merino/Domestic.xlsx");
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet("Domestic");
			formatter = new DataFormatter();

		} catch (Exception e) {
			System.err.println("🚨 ERROR in setup: " + e.getMessage());
			e.printStackTrace();
			if (driver != null) {
				driver.quit();
			}
			throw new RuntimeException("Test initialization failed", e);
		}
	}

	@AfterClass
	public void TearDown() {
		try {
			// Save Excel
			if (workbook != null) {
				fileOut = new FileOutputStream("C:/Users/LYXELANDFLAMINGO/Desktop/Merino/Domestic.xlsx");
				workbook.write(fileOut);
			}
		} catch (IOException e) {
			System.err.println("🚨 ERROR saving Excel: " + e.getMessage());
		} finally {
			// Close resources
			try {
				if (fileOut != null)
					fileOut.close();
				if (workbook != null)
					workbook.close();
				if (file != null)
					file.close();
			} catch (IOException e) {
				System.err.println("🚨 ERROR closing files: " + e.getMessage());
			}
			// Close browser
			if (driver != null) {
				driver.quit();
			}
		}
	}
}