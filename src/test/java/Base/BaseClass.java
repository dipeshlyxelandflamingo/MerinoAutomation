package Base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import generic.MerinoUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static FileInputStream file;
	public static FileOutputStream fileOut;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static DataFormatter formatter;

	@BeforeClass
	public void OpenBrowser() throws Throwable {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://staging.merinolaminates.com/en/login/");
		driver.manage().window().maximize();
		System.out.println("Page Title: " + driver.getTitle());

		MerinoUtility.usernamefield(driver, "stagingMerinoUser");
		MerinoUtility.passwordfield(driver, "@M34!705I#6s!@");
		MerinoUtility.submitbutton(driver);

		file = new FileInputStream("C:/Users/LYXELANDFLAMINGO/Desktop/Merino/Domestic.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet("Domestic");
		formatter = new DataFormatter();
	}

	@AfterClass
	public void TearDown() throws Exception {

		try {
			fileOut = new FileOutputStream("C:/Users/LYXELANDFLAMINGO/Desktop/Merino/Domestic.xlsx");
			workbook.write(fileOut);
		} finally {
			if (fileOut != null) {
				fileOut.close();
			}
			workbook.close();
			file.close();
		}
		driver.quit();
	}
}
