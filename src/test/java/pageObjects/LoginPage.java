package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseClass{ 
    public LoginPage(WebDriver driver) {
    	super(driver);
    }
    
    @FindBy(xpath="//input[@name='email']")
    WebElement txtemail;
    
    @FindBy(xpath="//input[@name='password']")
    WebElement txtpassword;
    
    @FindBy(xpath="//input[@value='Login']")
    WebElement loginButton;
    
    public void setEmail(String email) {
    	txtemail.sendKeys(email);
    }
    public void setPassword(String password) {
    	txtpassword.sendKeys(password);
    }
    public void clickLogin() {
    	loginButton.click();
    }
}
