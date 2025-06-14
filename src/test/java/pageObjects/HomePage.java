package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass {
   
    public HomePage(WebDriver driver)
    {
    	super(driver);
    }
    
@FindBy(xpath="//span[text()='My Account']")
WebElement linkAccount;

@FindBy(xpath="//ul/li/a[text()='Register']")
WebElement registerAccount;

@FindBy(xpath="//a[text()='Login']")  //Login link added in step 5
WebElement loginAccount;

public void clickMyAccount() {
	linkAccount.click();
}
public void registerAccount() {
	registerAccount.click();
}

public void loginClick() {
	loginAccount.click();
	}
}
