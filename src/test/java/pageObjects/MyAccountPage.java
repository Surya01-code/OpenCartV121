package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BaseClass{
    public MyAccountPage(WebDriver driver) {
    	super(driver);
    }
    
    @FindBy(xpath="//h2[text()='My Account']")  //My account page heading
    WebElement headerText;
    
    @FindBy(xpath = "//div[@id='account-account']//aside[@id='column-right']//div[@class='list-group']//a[text()='Logout']") // Logout Link added in Step 6
    WebElement logOutLink;
    
    //Not a validation just checking whether the page is visible or not
    //The below command will not provide any result, throws exception if the page is not visible
    
    public boolean isMyAccountPageVisible() {
    	try {
    	return (headerText.isDisplayed());
    	}
    	catch(Exception e) {
    		return false;
    	}
    }
    
    public void clickLogOut() {
    	logOutLink.click();
    }
}
