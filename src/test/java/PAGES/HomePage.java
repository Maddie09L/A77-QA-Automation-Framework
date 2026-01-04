package PAGES;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    By firstPlaylist = By.xpath("//li[@class='playlist playlist']");
    By playlistNameField = By.xpath("//input[@data-testid='inline-playlist-name-input']");
    By renamePlaylistSuccessMsg = By.xpath("//div[@class='alertify-logs top right']//div[contains(@class, 'success')]");


    public void doubleClickPlaylist() { doubleClick(firstPlaylist);}
    public void enterNewPlaylistName(String newName) {
        findElement(playlistNameField).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        findElement(playlistNameField).sendKeys(newName);
        findElement(playlistNameField).sendKeys(Keys.ENTER);
    }

    public String getRenamePlaylistSuccessMsg() {
        return findElement(renamePlaylistSuccessMsg).getText();
    }

}
