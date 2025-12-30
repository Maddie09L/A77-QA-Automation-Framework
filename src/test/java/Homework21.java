import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework21 extends BaseTest {

    @Test
    public void renamePlaylist() {

        String oldName = "A77";
        String newName = "A77 Test pro";
        String expectedMessage = "Updated playlist \"A77 Test pro.\"";

        provideEmail("madeleiny.mason@testpro.io");
        providePassword("FU2nVt8d");
        clickOnLogInButton();

        openPlaylist(oldName);
        renamePlaylistWithActions(oldName, newName);


        Assert.assertTrue(isPlaylistVisible(newName), "Playlist was not renamed to: " + newName);

    }

    private void openPlaylist(String name) {
        By playlistLocator = By.xpath("//section[@id='playlists']//a[normalize-space()='A77']\n");
        WebElement playlist = wait.until(ExpectedConditions.elementToBeClickable(playlistLocator));
        playlist.click();

    }

    private void renamePlaylistWithActions(String oldName, String newName) {
        Actions actions = new Actions(driver);

        By renamePlaylistLocator = By.xpath("//section[@id='playlists']//a[normalize-space()='A77']\n");
        WebElement playlist = wait.until(ExpectedConditions.elementToBeClickable(renamePlaylistLocator));

        actions.doubleClick(playlist).perform();

        By renameInputLocator = By.xpath("//input[@data-testid='inline-playlist-name-input']");
        WebElement renameInput = wait.until(ExpectedConditions.elementToBeClickable(renameInputLocator));

        actions.click(renameInput)
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(newName)
                .sendKeys(Keys.ENTER)
                .perform();

        By renamedPlaylistLocator = By.xpath("//section[@id='playlists']//a[normalize-space()='A77 Test pro']\n");
        wait.until(ExpectedConditions.visibilityOfElementLocated(renamedPlaylistLocator));

    }

    private boolean isPlaylistVisible(String name) {
        try {
            By PlaylistLocator = By.xpath("//section[@id='playlists']//a[normalize-space()='" + name + "']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(PlaylistLocator));
            return true;
        } catch (Exception e){
            return false;
        }

    }

}

