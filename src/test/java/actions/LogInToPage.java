package actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.objects.MainPage;


public class LogInToPage {

    public static Boolean LogInToApplication(String userName, String password, ExtentTest reporter) {
        MainPage mainPage = new MainPage(reporter);
        mainPage.setUserName(userName);
        mainPage.setPassword(password);
        mainPage.clickSubmitButton();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 60);
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(".message-text")),
                    ExpectedConditions.or(
                            ExpectedConditions.titleIs("Home / Twitter"),
                            ExpectedConditions.titleIs("Główna / Twitter"))));
        } catch (TimeoutException e) {
            reporter.log(Status.FAIL, "Nie odnaleziono na stronie ani blędu ani poprawnego tytułu");
            return Boolean.FALSE;
        }
        if (DriverManager.getDriver().getTitle().equalsIgnoreCase("Home / Twitter") || DriverManager.getDriver().getTitle().equalsIgnoreCase("Główna / Twitter")) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
