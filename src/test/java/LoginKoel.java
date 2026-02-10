import org.testng.Assert;
import org.testng.annotations.Test;
import POM.HomePage;
import POM.LoginPage;

public class LoginKoel extends BaseTest {
    @Test
    public void loginValidEmailValidPassword() {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        getDriver().get("https://qa.koel.app/");

        loginPage.provideEmail("madeleiny.mason@testpro.io");
        loginPage.providePassword("Leomydog20!");
        loginPage.clickSubmit();

        getDriver().get(url);
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
        getDriver().quit();
    }
}