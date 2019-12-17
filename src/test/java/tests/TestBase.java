package tests;

import driver.DriverManager;
import driver.DriverUtils;
import logger.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestBase {

    public static Reporter reporter;

    @Parameters({"user", "password"})
    @BeforeMethod
    public void beforeTest() {
        this.reporter = new Reporter();
        DriverManager.getDriver();
        DriverUtils.goToPage("http://twitter.com/login");


    }


    @AfterMethod
    public void afterTest() {
        DriverManager.closeDriver();
        reporter.saveReports();
    }

}
