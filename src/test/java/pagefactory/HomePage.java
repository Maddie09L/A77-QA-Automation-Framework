package pagefactory;

import POM.BasePage;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy (xpath = "//li[@class='playlist playlist']")
    WebElement firstPlaylist;

    @FindBy (xpath = "//input[@data-testid='inline-playlist-name-input']")
    WebElement playlistNameField;

    @FindBy (xpath = "//div[@class='alertify-logs top right']//div[contains(@class, 'success')]")
    WebElement renamePlaylistSuccessMsg;



    public HomePage doubleClickPlaylist() {
       actions.doubleClick(firstPlaylist).perform();
        return this;
    }
    public HomePage enterNewPlaylistName(String newName) {
        playlistNameField.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        playlistNameField.sendKeys(newName);
        playlistNameField.sendKeys(Keys.ENTER);
        return this;
    }

    public String getRenamePlaylistSuccessMsg() {
        return renamePlaylistSuccessMsg.getText();
    }

}

