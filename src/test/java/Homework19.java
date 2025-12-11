import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework19 extends BaseTest {

    @Test
    public void deletePlaylist () throws InterruptedException {
        String expectedMessage = "Deleted playlist \"A77.\"";

        provideEmail("madeleiny.mason@testpro.io");
        providePassword("FU2nVt8d");
        clickOnLogInButton();

        openPlaylist();
        clickDeletePlaylistButton();

        String notificationText = getNotificationText();

        Assert.assertEquals(notificationText, expectedMessage);

    }

    private void openPlaylist() {
        WebElement selectPlaylist = driver.findElement(By.xpath("//section[@id='playlists']//a[normalize-space()='A77']\n" ));
        selectPlaylist.click();
    }

    private void clickDeletePlaylistButton() {
        WebElement deletePlaylist = driver.findElement(By.xpath("//header//button[@title='Delete this playlist'] "));
        deletePlaylist.click();
    }

    private String getNotificationText() {
        By notificationLocator = By.xpath("//div[@class='alertify-logs top right']//div[contains(@class, 'success')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement notification = wait.until(
                ExpectedConditions.visibilityOfElementLocated(notificationLocator)
        );

        return notification.getText().trim();
    }
}
