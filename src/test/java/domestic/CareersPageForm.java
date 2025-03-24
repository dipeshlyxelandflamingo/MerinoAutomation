package domestic;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import Base.BaseClass;
import generic.MerinoUtility;


public class CareersPageForm extends BaseClass{
	

	@Test(description = "Open Curation Page and Scroll Down to Form and fill the form with all Valid Input")
	void TC_01() throws Exception {
		try {
		
		Thread.sleep(2000);
		driver.navigate().to(
				"https://staging.merinolaminates.com/en/job-apply/?jid=Area%20Sales%20Manager%20%E2%80%93%20Project%20&%20OEM%20Sales&jit=37033");

		driver.findElement(By.id("cookie_action_close_header")).click();

		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 200).perform();

	
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@name='Name'])[1]")).sendKeys("Dipesh");
			driver.findElement(By.xpath("(//input[@name='email'])[1]")).sendKeys("dipesh.singh@lyxelandflamingo.com");
			driver.findElement(By.xpath("(//input[@name='mobile'])[1]")).sendKeys("6354899390");

			WebElement DOB = driver.findElement(By.name("date-of-birth"));
			DOB.sendKeys("21-03-1999");

			WebElement GenderDD = driver.findElement(By.name("gender"));
			MerinoUtility.selectbyvalue(GenderDD, "Male");

			WebElement Youare = driver.findElement(By.xpath("(//select[@name='you_are'])[1]"));
			MerinoUtility.selectbyvalue(Youare, "Contractor");

			WebElement CountryDropdown = driver.findElement(By.xpath("(//Select[@name='Country'])[1]"));
			MerinoUtility.selectbyvalue(CountryDropdown, "India");

			WebElement StateDropdown = driver.findElement(By.id("stateDropDown"));
			MerinoUtility.selectbyvalue(StateDropdown, "Uttar Pradesh");

			WebElement CityDropdown = driver.findElement(By.xpath("(//Select[@name='city'])[1]"));
			MerinoUtility.selectbyvalue(CityDropdown, "Gautam Buddha Nagar");

			WebElement HighestEducationDD = driver.findElement(By.name("HighestEducation"));
			MerinoUtility.selectbyvalue(HighestEducationDD, "BCA");

			WebElement TotalExperienceYear = driver.findElement(By.name("TotalExperience"));
			MerinoUtility.selectbyvalue(TotalExperienceYear, "4 Years");

			WebElement TotalExperienceMonth = driver.findElement(By.name("MonthExperience"));
			MerinoUtility.selectbyvindex(TotalExperienceMonth, 4);

			WebElement EmployeeType = driver.findElement(By.name("CurrentEmployer"));
			MerinoUtility.selectbyvalue(EmployeeType, "Employed");

			WebElement DesignationType = driver.findElement(By.name("CurrentDesignation"));
			DesignationType.sendKeys("QA");

			WebElement CTC = driver.findElement(By.name("CurrentAnnualCTC"));
			CTC.sendKeys("2.2");

			// Actions actions = new Actions(driver);
			actions.scrollByAmount(0, 400).perform();

			driver.findElement(By.xpath("//input[@name='Resume']")).sendKeys("C:\\Users\\LYXELANDFLAMINGO\\upload.docx");

			//String filePath = "C:\\Users\\LYXELANDFLAMINGO\\upload.docx";

			// Use sendKeys() to send the file path
			//uploadElement.sendKeys(filePath);

			driver.findElement(By.name("age_confirm")).click();

			driver.findElement(By.xpath("(//input[@type='submit'])[3]")).click();
			
			sheet.getRow(2).createCell(4).setCellValue("Form Filled with All Valid Input!");
			sheet.getRow(2).createCell(4).setCellValue("Form Submitting Succsessfully!");
		}catch(Exception e) 
		{
			sheet.getRow(2).createCell(4).setCellValue("Form Not Submitting Succsessfully! ");
			
		}

	}

}
