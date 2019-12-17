package page.objects;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import tests.waits.WaitForElement;

import java.sql.Driver;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class HomePage {
    private ExtentTest Reporter;

    @FindBy(css = ".css-1dbjc4n:nth-child(3) > .css-4rbku5 > .css-901oao")
    private WebElement composeTweet;

    @FindBy(xpath = "//div[@class='public-DraftStyleDefault-block public-DraftStyleDefault-ltr']")
    private WebElement tweetMessage;

    @FindBy(xpath = "//div[@class='css-18t94o4 css-1dbjc4n r-urgr8i r-42olwf r-sdzlij r-1phboty r-rs99b7 r-1w2pmg r-1n0xq6e r-1vuscfd r-1dhvaqw r-1fneopy r-o7ynqc r-6416eg r-lrvibr']")
    private WebElement postTweet;

    @FindBy(xpath = "//section[@class = 'css-1dbjc4n']/div")
    private List<WebElement> messageTable;

    @FindBy(css = ".css-18t94o4:nth-child(1) .css-18t94o4 > .css-901oao > .css-901oao > .css-901oao")
    private WebElement observeFirst;

    public HomePage(ExtentTest reporter) {
        this.Reporter = reporter;
        PageFactory.initElements(DriverManager.getDriver(), this);

    }

    public void postMessage(String message) {

        Reporter.log(Status.INFO, "klikamy na Dodaj nowego tweeta");
        try {
            assertTrue(WaitForElement.waitUntilElementClickable(composeTweet));
        } catch (AssertionError e) {
            Reporter.log(Status.FAIL, "Nie odnaleziono obiektu tworzenia tweeta");
            Assert.fail();
        }
        composeTweet.click();

        Reporter.log(Status.INFO, "Wprowadzamy wiadomosc tweeta");
        try {
            assertTrue(WaitForElement.waitUntilElementVisable(tweetMessage));
        } catch (AssertionError e) {
            Reporter.log(Status.FAIL, "Nie odnaleziono pola wprowadzania wiadomosci");
            Assert.fail();
        }
        tweetMessage.sendKeys(message);

        Reporter.log(Status.INFO, "Zatwierdzamy tweeta");
        try {
            assertTrue(WaitForElement.waitUntilElementClickable(postTweet));
        } catch (AssertionError e) {
            Reporter.log(Status.FAIL, "Nie doczekano sie przycisku zatwierdzajacego tweeta");
            Assert.fail();
        }
        postTweet.click();
        WaitForElement.waitForHomePage();


    }


    public Boolean verifyTweet(String message) {
        PageFactory.initElements(DriverManager.getDriver(), this);


        for (WebElement element : messageTable) {
            try {
                assertTrue(element.getText().contains(message));
                return Boolean.TRUE;
            } catch (AssertionError e) {
                continue;
            }
        }
        return Boolean.FALSE;
    }

    public void observeUser() {
        Reporter.log(Status.INFO, "Dodajemy do obserwowanych pierwsza proponowaną osobę");
        try {
            assertTrue(WaitForElement.waitUntilElementClickable(observeFirst));
        } catch (AssertionError e) {
            Reporter.log(Status.FAIL, "Nie odnaleziono żadnych polecanych osób.");
            Assert.fail();
        }
        observeFirst.click();
    }

    public Boolean verifyObserved() {
        Reporter.log(Status.INFO, "Weryfikujemy czy użytkownik został dodany do obserwowanych");
        PageFactory.initElements(DriverManager.getDriver(), this);
        try {
            assertTrue(WaitForElement.waitUntilElementClickable(observeFirst));
        } catch (AssertionError e) {
            Reporter.log(Status.FAIL, "Nie odnaleziono żadnych polecanych osób.");
            Assert.fail();
        }

        try {
            assertTrue(observeFirst.getText().equals("Obserwowani") || observeFirst.getText().equals("Following"));
            Reporter.log(Status.PASS, "Poprawnie dodano konto do obserwowania");
            return Boolean.TRUE;
        } catch (AssertionError e) {
            Reporter.log(Status.FAIL, "Konto nie zostało dodane do obserwowania");
            return Boolean.FALSE;
        }

    }
}
