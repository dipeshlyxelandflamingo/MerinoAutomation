package listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import email.EmailUtiility;
import java.io.File;

public class TestExecutionListener implements ITestListener, ISuiteListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Optional: Do nothing here to prevent email being sent after individual tests
    }

    @Override
    public void onStart(ISuite suite) {
        System.out.println("Suite Started: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("==============================================");
        System.out.println("Test Suite Finished: " + suite.getName());
        System.out.println("==============================================");

        try {
            String reportPath = System.getProperty("user.dir") + "/test-output/Default suite/Default test.html";
            String excelPath = "C:\\Users\\LYXELANDFLAMINGO\\Desktop\\Merino\\Domestic.xlsx";

            File reportFile = new File(reportPath);
            if (!reportFile.exists()) {
                System.err.println("Report file not found at: " + reportPath);
                return;
            }

            File excelFile = new File(excelPath);
            if (!excelFile.exists()) {
                System.err.println("Excel file not found at: " + excelPath);
                return;
            }

            // Email details
            String subject = "Test Report for Suite: " + suite.getName();
            String body = "Hello Team, \n\nThe test suite has finished execution. Please find the attached report and Excel file.\n\nThanks,\nAutomation Team";
           // String to = "dipesh.singh@lyxelandflamingo.com";
            String[] to = {"ravi.pal@lyxelandflamingo.com", "ruchi.singh@lyxelandflamingo.com"};

            // Send email with attachments
            EmailUtiility.sendEmail(to, subject, body, reportPath, excelPath);
            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
}