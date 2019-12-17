package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.LogInToPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import page.objects.HomePage;

import static org.testng.AssertJUnit.assertTrue;

public class AddToObserveTest extends TestBase {

    @Test
    @Parameters({"user", "password"})
    public void addToObserveTest(String user, String password) {
        reporter.setTest("addToObserve");
        reporter.CurrentTest().log(Status.INFO, "Rozpoczynamy Test dodawania do obserwowanych");

        //Logujemy się do aplikacji:
        try {
            assertTrue(LogInToPage.LogInToApplication(user, password, reporter.CurrentTest()));
            reporter.CurrentTest().log(Status.PASS, "Logowanie zakonczone poprawnie");
        } catch (AssertionError e) {
            reporter.CurrentTest().log(Status.FAIL, "Nie udało sie poprawnie zalogowac do strony");
            Assert.fail();
        }
        HomePage homePage = new HomePage(reporter.CurrentTest());
        //Dodajemy uzytkownika do obserwowanych
        homePage.observeUser();
        //Weryfikujemy czy uzytkownik zostal dodany poprawnie
        try {
            assertTrue(homePage.verifyObserved());
            reporter.CurrentTest().log(Status.PASS, "Test wykonany poprawnie.");
        } catch (AssertionError e) {
            reporter.CurrentTest().log(Status.FAIL, "Test nie został poprawnie wykonany");
            Assert.fail();
        }
    }

}
