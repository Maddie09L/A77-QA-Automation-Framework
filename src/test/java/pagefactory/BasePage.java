package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
   protected WebDriver driver;
   protected WebDriverWait wait;
   protected Actions actions;

    By allSongsList = By.xpath("//a[@class='songs active']");
    By soundBarVisualizer = By.xpath("//div[@data-testid='sound-bar-play']");


    public BasePage (WebDriver givenDriver) {
        this.driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        PageFactory.initElements(this.driver,this);

    }
    public WebElement findElement (By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void click (By locator){findElement(locator).click(); }
    public void doubleClick (By locator) { actions.doubleClick(findElement(locator)).perform();}
    public void chooseAllSongsList() { click(allSongsList); }
    public boolean isSongPlaying() { return findElement(soundBarVisualizer).isDisplayed();}



}
