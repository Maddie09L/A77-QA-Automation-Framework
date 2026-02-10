import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import POM.HomePage;
import POM.LoginPage;

import java.time.Duration;

public class LogoutKoel extends BaseTest {
    @Test
    public void logoutofKoel() {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        getDriver().get("https://qa.koel.app/");

        loginPage.provideEmail("madeleiny.mason@testpro.io");
        loginPage.providePassword("Leomydog20!");
        loginPage.clickSubmit();

        homePage.waitForUserBadge();

        homePage.logOut();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("login"),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email'], input[name='email']"))
        ));

        Assert.assertTrue(getDriver().getCurrentUrl().contains("login")
                        || getDriver().findElements(By.cssSelector("input[type='email'], input[name='email']")).size() > 0,
                "Logout failed: user is not on login page and login form is not visible.");
    }

}


