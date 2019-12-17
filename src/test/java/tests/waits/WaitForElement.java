package tests.waits;


import driver.DriverManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WaitForElement {

    private static WebDriverWait getWebdriverWait() {
        return new WebDriverWait(DriverManager.getDriver(), 4);
    }

    public static Boolean waitUntilElementVisable(WebElement element) {
        WebDriverWait webDriverWait = getWebdriverWait();
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            return Boolean.TRUE;
        } catch (TimeoutException e) {
            return Boolean.FALSE;
        }
    }

    public static Boolean waitUntilElementClickable(WebElement element) {
        WebDriverWait webDriverWait = getWebdriverWait();
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            return Boolean.TRUE;
        } catch (TimeoutException e) {
            return Boolean.FALSE;
        }
    }

    public static void waitForHomePage() {
        WebDriverWait webDriverWait = getWebdriverWait();
        webDriverWait.until(ExpectedConditions.urlContains("home"));
    }


}
