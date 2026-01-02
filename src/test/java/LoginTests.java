import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.HomePage;
import pagefactory.LoginPage;

import java.time.Duration;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailValidPassword() {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("madeleiny.mason@testpro.io")
                 .providePassword("FU2nVt8d")
                 .clickSubmit();

//      Added ChromeOptions argument below to fix websocket error
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");

        //driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // TODO (for students): Review the configuration as part of HW15

        String url = "https://qa.koel.app/";
        getDriver().get(url);
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
        getDriver().quit();
    }
    @Test
    public void loginInvalidEmailInvalidPassword() {

        LoginPage loginPage = new LoginPage(getDriver());


        loginPage.provideEmail("demo@testpro.io")
                .providePassword("FUn5t8d")
                .clickSubmit();

        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }

}
