import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



public class Homework17 extends BaseTest {

    @Test
    public void addSongToPlaylist() {

        driver.get("https://qa.koel.app/");

        provideEmail("madeleiny.mason@testpro.io");
        providePassword("FU2nVt8d");
        clickOnLogInButton();

        String songName = "Take my Hand - piano";
        searchSong(songName);

        clickViewAll();
        selectFistSong();

        clickAddSong();

        String playListName = "A77";
        choosePlaylist(playListName);

        String expectedMessage = "Added 1 song into \"A77.\"";
        String actualMessage  = getNotificationText();

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    private void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@type= 'email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    private void providePassword (String password) {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    private void clickOnLogInButton () {
        WebElement logInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();
    }

    private void searchSong(String songName) {
        WebElement song = driver.findElement(By.xpath("//input[@type='search']"));
        song.sendKeys(songName);
        song.click();
    }

    private void clickViewAll() {
        WebElement viewAll = driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
        viewAll.click();
    }

    private void selectFistSong() {
        WebElement firstSong = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/div/div/div[1]/table/tr[1]/td[2]"));
        firstSong.click();
    }

    private void clickAddSong() {
        WebElement addSong = driver.findElement(By.xpath("//button[@class='btn-add-to']"));
        addSong.click();
    }

    private void choosePlaylist(String playListName) {
        String xpath = String.format(
                "//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(normalize-space(), '%s')]",
                playListName
        );

        WebElement newPlayList = driver.findElement(By.xpath(xpath));
        newPlayList.click();
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
