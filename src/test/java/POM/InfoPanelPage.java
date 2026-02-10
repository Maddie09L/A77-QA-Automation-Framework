package POM;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InfoPanelPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By infoButton = By.cssSelector("#mainFooter button.control");

    private By lyricsTab = By.cssSelector("#extraTabLyrics");
    private By artistTab = By.cssSelector("#extraTabArtist");
    private By albumTab = By.cssSelector("#extraTabAlbum");

    public InfoPanelPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void openInfoPanel(){
        safeClick(infoButton);
        wait.until(ExpectedConditions.elementToBeClickable(infoButton)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(lyricsTab));
    }
    public void clickLyricsTab () {
        wait.until(ExpectedConditions.elementToBeClickable(lyricsTab)).click();

    }
    public void clickArtistTab () {
        wait.until(ExpectedConditions.elementToBeClickable(artistTab)).click();
    }
    public void clickAlbumTab () {
        wait.until(ExpectedConditions.elementToBeClickable(albumTab)).click();

    }
    private void safeClick(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (ElementClickInterceptedException e) {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }
}
