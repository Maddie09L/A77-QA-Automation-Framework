package TESTS;

import org.testng.Assert;
import org.testng.annotations.Test;
import PAGES.HomePage;
import PAGES.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailValidPassword() {

        LoginPage loginPage = new LoginPage(BaseTest.getDriver());
        HomePage homePage = new HomePage(BaseTest.getDriver());

        loginPage.provideEmail("madeleiny.mason@testpro.io");
        loginPage.providePassword("FU2nVt8d");
        loginPage.clickSubmit();

//      Added ChromeOptions argument below to fix websocket error
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");

        //driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // TODO (for students): Review the configuration as part of HW15

        String url = "https://qa.koel.app/";
        BaseTest.getDriver().get(url);
        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), url);
        BaseTest.getDriver().quit();
    }
    @Test
    public void loginInvalidEmailInvalidPassword() {

        LoginPage loginPage = new LoginPage(BaseTest.getDriver());


        loginPage.provideEmail("demo@testpro.io");
        loginPage.providePassword("FUn5t8d");
        loginPage.clickSubmit();

        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), BaseTest.url);
    }

}
