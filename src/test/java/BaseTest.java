import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public String url = "https://qa.koel.app/";

    @BeforeSuite
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void launchBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }
    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        System.out.println(">>> @AfterMethod - closeBrowser() called");
        if (driver != null) {
            try {
                driver.quit();
                System.out.println(">>> Driver.quit() completed");
            } catch (Exception e) {
                System.out.println(">>> Error during driver.quit(): " + e.getMessage());
            }
        } else {
            System.out.println(">>> Driver was null in closeBrowser()");
        }
    }

}