import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;


public class BaseTest {

    public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    public static String url = "https://qa.koel.app/";
    public static Wait<WebDriver> fluentWait;

    public Actions actions = null;

    public static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return threadDriver.get();
    }

    @DataProvider(name = "IncorrectLoginData")
    public Object [][] getDataFromProviders(){

        return new Object[][] {
                {"invalid@testpro.io", "invalidPass"},
                {"demo@testpro.io",""},
                {"",""}
        };
    }


    @BeforeSuite
    static void setupClass() {
         //WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        //System.setProperty("webdriver.edge.driver", "C:\\WebDriver\\edgedriver_win64\\msedgedriver.exe");
        //WebDriverManager.firefoxdriver().setup();
    }
    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void setupBrowser (String BaseUrl) throws MalformedURLException {
         threadDriver.set(pickBrowser(System.getProperty("browser")));

         getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         getDriver().manage().window().maximize();

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        fluentWait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(200));

        actions = new Actions(getDriver());

        url = BaseUrl;
        navigateToPage();

    }
    //single testing execution
    public void launchBrowser(String BaseUrl) throws MalformedURLException {
        //ChromeOptions options = new ChromeOptions();
        //EdgeOptions options = new EdgeOptions();
        //options.addArguments("--remote-allow-origins=*");

        //driver = new ChromeDriver(options);
        //driver = new EdgeDriver(options);
        //driver = new FirefoxDriver();

        driver = pickBrowser(System.getProperty("browser"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        fluentWait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(200));

        actions = new Actions(driver);

        url = BaseUrl;
        navigateToPage();

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

    public void tearDown() {
        WebDriver driver = threadDriver.get();

        try {
            if (driver != null) {
                driver.quit(); // ✅ ends the whole session properly
            }
        } catch (NoSuchSessionException e) {
            // ✅ session already closed - ignore
        } finally {
            threadDriver.remove(); // ✅ always clean ThreadLocal
        }

    }
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

    private void navigateToPage() {
       getDriver().get(url);

    }


    public WebDriver pickBrowser(String browser) throws MalformedURLException {

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
            //Cloud Execution
            case "cloud-Edge":
                return lambdaTestEdge();
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
        }
    }
    public WebDriver lambdaTestEdge() throws MalformedURLException {

        String hubUrl = "https://hub.lambdatest.com/wd/hub";

        EdgeOptions browserOptions = new EdgeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("dev");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "madeleinymason");
        ltOptions.put("accessKey", "LT_66UFV9Bcy0WjLUhqQ8qBOq5NwoVVnN1TeZRZzaPdRx4GmI3");
        ltOptions.put("project", "Edge Test");
        ltOptions.put("name",this.getClass().getName());
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
        return new RemoteWebDriver(URI.create(hubUrl).toURL(),browserOptions);

    }
}