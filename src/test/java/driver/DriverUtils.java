package driver;

public class DriverUtils {

    public static void goToPage(String url) {
        DriverManager.getDriver().navigate().to(url);
        DriverManager.getDriver().manage().window().maximize();
    }

    public static void closePage() {
        DriverManager.closeDriver();
    }

}
