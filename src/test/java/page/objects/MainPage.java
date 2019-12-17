package page.objects;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import tests.waits.WaitForElement;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class MainPage {
    private ExtentTest Reporter;

    @FindBy(css = ".js-username-field")
    private WebElement loginField;

    @FindBy(css = ".js-password-field")
    private WebElement passwordField;

    @FindBy(css = ".EdgeButtom--medium")
    private WebElement submitButton;


    public MainPage(ExtentTest reporter) {
        PageFactory.initElements(DriverManager.getDriver(), this);
        this.Reporter = reporter;
    }

    public void setUserName(String userName) {
        Reporter.log(Status.INFO, "Wprowadzamy nazwe uzytkownika");
        try {
            assertTrue(WaitForElement.waitUntilElementVisable(loginField));
        } catch (AssertionError e) {
            Reporter.log(Status.FAIL, "Nie odnaleziono obiektu nazwy uzytkownika");
            Assert.fail();
        }

        loginField.sendKeys(userName);

        try {
            assertEquals(userName, loginField.getAttribute("value"));
            Reporter.log(Status.PASS, "Nazwa Uzytkownika poprawnie wprowadzona");
        } catch (AssertionError e) {

            Reporter.log(Status.FAIL, "Nazwa Uzytkownika nie zostala wprowadzona");
            Assert.fail();
        }

    }

    public void setPassword(String password) {
        Reporter.log(Status.INFO, "Wprowadzamy hasło użytkownika");
        try {
            assertTrue(WaitForElement.waitUntilElementVisable(passwordField));
        } catch (AssertionError e) {
            Reporter.log(Status.FAIL, "Nie odnaleziono obiektu hasla");
            Assert.fail();
        }


        passwordField.sendKeys(password);

        try {
            assertTrue(passwordField.getAttribute("value") != "");
            Reporter.log(Status.PASS, "Poprawnie wprowadzono hasło");
        } catch (AssertionError e) {
            Reporter.log(Status.FAIL, "Nie wprowadzono hasla");
            Assert.fail();
        }

    }

    public void clickSubmitButton() {
        Reporter.log(Status.INFO, "Wciskamy przycisk logowania");
        try {
            assertTrue(WaitForElement.waitUntilElementClickable(submitButton));
        } catch (AssertionError e) {
            Reporter.log(Status.FAIL, "Nie odnaleziono przycisku logowania");
            Assert.fail();
        }
        submitButton.click();

    }


}
