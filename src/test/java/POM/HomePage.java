package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;



    By userBadge = By.id("userBadge");
    By logoutButton = By.cssSelector("#userBadge a[href*='logout'], #userBadge [data-testid*='logout']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void waitForUserBadge() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userBadge));
    }

    public void logOut() {
        By userBadge = By.id("userBadge"); // top right area
        By logoutLink = By.cssSelector("#userBadge a[href*='logout'], #userBadge [data-testid*='logout']");

        // 1) open the dropdown
        wait.until(ExpectedConditions.elementToBeClickable(userBadge)).click();

        // 2) click logout
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();

        // 3) confirm logout happened (wait for login)
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("login"),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email'], input[name='email']"))
        ));
    }

}

    //public HomePage(WebDriver givenDriver) {
    //    super(givenDriver);
    //}
    //By firstPlaylist = By.xpath("//li[@class='playlist playlist']");
    //By playlistNameField = By.xpath("//input[@data-testid='inline-playlist-name-input']");
    //By renamePlaylistSuccessMsg = By.xpath("//div[@class='alertify-logs top right']//div[contains(@class, 'success')]");


    //public void doubleClickPlaylist() { doubleClick(firstPlaylist);}
    //public void enterNewPlaylistName(String newName) {
      //  findElement(playlistNameField).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
    // findElement(playlistNameField).sendKeys(newName);
   //     findElement(playlistNameField).sendKeys(Keys.ENTER);
   // }

//    public String getRenamePlaylistSuccessMsg() {
  //      return findElement(renamePlaylistSuccessMsg).getText();
  //  }


