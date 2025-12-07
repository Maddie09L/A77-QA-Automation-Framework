
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework18 extends BaseTest {

    @Test
    public void PlaySong() {

        driver.get("https://qa.koel.app/");

        provideEmail("madeleiny.mason@testpro.io");
        providePassword("FU2nVt8d");
        clickOnLogInButton();

        clickPlayNextSong();
        clickPlayButton();

        boolean isPauseButtonVisible = isPauseButtonDisplayed();
        boolean isSoundBarVisible = isSoundBarDisplayed();

        Assert.assertTrue(isPauseButtonVisible || isSoundBarVisible);

    }

    private void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@type= 'email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    private void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    private void clickOnLogInButton() {
        WebElement logInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();
    }

    private void clickPlayNextSong() {
        WebElement playNextSong = driver.findElement(By.xpath("//i[@title='Play next song']"));
        playNextSong.click();
    }

    private void clickPlayButton() {
        WebElement playButton = driver.findElement(By.xpath("//span[@title='Play or resume']"));
        playButton.click();
    }

    private boolean isPauseButtonDisplayed() {
        try {
            WebElement pauseButton = driver.findElement(By.xpath("//span[@title='Pause']"));
            return pauseButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isSoundBarDisplayed() {
        try {
            WebElement soundBar = driver.findElement(By.xpath("//div[@class='bars']"));
            return soundBar.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}