import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class GenericSeleniumTest {
    protected static List<WebDriver> drivers;
    private static final String HUB_URL = "http://selenium_hub:4444/wd/hub";
    // private static final String HUB_URL = "http://localhost:4444/wd/hub";

    @BeforeAll
    public static void initDrivers() throws MalformedURLException {
        drivers = new ArrayList<>();
        drivers.add(new RemoteWebDriver(new URL(HUB_URL), new ChromeOptions()));
        drivers.add(new RemoteWebDriver(new URL(HUB_URL), new FirefoxOptions()));
    }

    public void catchException(Exception e, WebDriver driver) {
        System.err.println(e.getMessage());
        driver.quit();
        Assertions.fail();
    }


    @AfterAll
    public static void closeDrivers() {
        for (WebDriver driver: drivers) {
            driver.quit();
        }
    }
}
