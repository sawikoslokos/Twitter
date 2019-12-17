package logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class Reporter {

    protected static ExtentReports extent;
    private ExtentTest currentTest;

    public Reporter() {


    }

    private static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports();
            extent.attachReporter(new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html"));
        }
        return extent;
    }

    public ExtentTest CurrentTest() {
        return this.currentTest;
    }

    public void setTest(String testName) {
        this.currentTest = getReporter().createTest(testName);
    }

    public void saveReports() {
        extent.flush();
    }

}
