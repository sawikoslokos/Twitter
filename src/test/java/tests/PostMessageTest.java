package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.LogInToPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import page.objects.HomePage;

import static org.testng.AssertJUnit.assertTrue;

public class PostMessageTest extends TestBase {


    @Test
    @Parameters({"user", "password", "message"})
    public void postMessageTest(String user, String password, String message) {
        reporter.setTest("postMessageTest");
        reporter.CurrentTest().log(Status.INFO, "Rozpoczynamy logowanie do strony");

        //Logowanie do twittera
        try {
            assertTrue(LogInToPage.LogInToApplication(user, password, reporter.CurrentTest()));
            reporter.CurrentTest().log(Status.PASS, "Logowanie zakonczone poprawnie");
        } catch (AssertionError e) {
            reporter.CurrentTest().log(Status.FAIL, "Nie udało sie poprawnie zalogowac do strony");
            Assert.fail();
        }
        //wpisujemy tweeta
        reporter.CurrentTest().log(Status.INFO, "Wpisujemy tweeta o tresci: " + message);
        HomePage homePage = new HomePage(reporter.CurrentTest());
        homePage.postMessage(message);

        reporter.CurrentTest().log(Status.INFO, "weryfikujemy czy Tweet został poprawnie wpisany.");

        //werfikujemy czy zostal wpisany:

        try {
            assertTrue(homePage.verifyTweet(message));
            reporter.CurrentTest().log(Status.PASS, "Poprawnie wprowadzono wiadomość, Tweet jest widoczny na Twojej tablicy.");
        } catch (AssertionError e) {
            reporter.CurrentTest().log(Status.FAIL, "Wiadomosc nie zostala poprawnie wprowadzona, Tweet nie widnieje na Twojej Tablicy");
            Assert.fail();
        }


    }

}
