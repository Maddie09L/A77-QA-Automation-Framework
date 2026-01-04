import POM.HomePage;
import POM.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static POM.LoginPage.*;

public class Homework22 extends BaseTest {

    @Test
    public void renamePlaylist(){


        String newName = "A77 Test pro";
        String expectedMessage = "Updated playlist \"A77 Test pro.\"";

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login();
        homePage.doubleClickPlaylist();
        homePage.enterNewPlaylistName(newName);
        Assert.assertEquals(homePage.getRenamePlaylistSuccessMsg(),expectedMessage);

    }
}
