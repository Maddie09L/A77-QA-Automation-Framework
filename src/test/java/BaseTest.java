import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;


public class BaseTest {

    public static WebDriver driver;
    public WebDriverWait wait;
    public String url = "https://qa.koel.app/";

    @BeforeSuite
    static void setupClass() {
         //WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        //System.setProperty("webdriver.edge.driver", "C:\\WebDriver\\edgedriver_win64\\msedgedriver.exe");
        //WebDriverManager.firefoxdriver().setup();
    }
    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void launchBrowser(String BaseUrl) throws MalformedURLException {
        //ChromeOptions options = new ChromeOptions();
        //EdgeOptions options = new EdgeOptions();
        //options.addArguments("--remote-allow-origins=*");

        //driver = new ChromeDriver(options);
        //driver = new EdgeDriver(options);
        //driver = new FirefoxDriver();
        //driver.manage().window().maximize();
        driver = pickBrowser(System.getProperty("browser"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        url = BaseUrl;
        navigateToPage();

    }

    private void navigateToPage() {
        driver.get(url);

    }


    public void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@type= 'email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnLogInButton() {
        WebElement logInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();
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

    public static WebDriver pickBrowser (String browser) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        String gridUrl = "http://192.168.40.191:4444";

        switch (browser){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "MicrosoftEdge":
                System.setProperty("webdriver.edge.driver", "C:\\WebDriver\\edgedriver_win64\\msedgedriver.exe");
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(options);
            case "grid-firefox":
                caps.setCapability("browserName","firefox");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
            case "grid-MicrosoftEdge":
                caps.setCapability("browserName","MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
            case "grid-chrome":
                caps.setCapability("browserName","chrome");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
        }
    }
}