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

	protected WebDriver driver;
	public FileInputStream file;
	public FileOutputStream fileOut;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public DataFormatter formatter;

	@BeforeClass
	public void OpenBrowser() throws Throwable {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://staging.merinolaminates.com/en/");
		driver.manage().window().maximize();
		System.out.println("Page Title: " + driver.getTitle());

		MerinoUtility.usernamefield(driver, "stagingMerinoUser");
		MerinoUtility.passwordfield(driver, "s!@5I#6@M34!70");
		MerinoUtility.submitbutton(driver);

		file = new FileInputStream("C:\\Users\\LYXELANDFLAMINGO\\Desktop\\Merino\\Domestic.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet("Domestic");
		formatter = new DataFormatter();
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
