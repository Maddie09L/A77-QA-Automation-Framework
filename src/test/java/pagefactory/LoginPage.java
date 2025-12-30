package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends BasePage {


    @FindBy(xpath = "//input[@type='email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitBtn;
    public LoginPage (WebDriver givenDriver) { super (givenDriver);}

    public LoginPage provideEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }
    public LoginPage providePassword (String password) {
        passwordField.sendKeys(password);
        return this;
    }
    public LoginPage clickSubmit() {
        submitBtn.click();
        return this;
    }
    public void login (){
        provideEmail("madeleiny.mason@testpro.io");
        providePassword("FU2nVt8d");
        clickSubmit();
    }
}

