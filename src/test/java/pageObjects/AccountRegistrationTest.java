package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationTest extends BaseClass{
	public AccountRegistrationTest(WebDriver driver) {
		super(driver);
	}
@FindBy(xpath="//input[@name='firstname']")
WebElement txtFirstName;

@FindBy(xpath="//input[@name='lastname']")
WebElement txtLastName;

@FindBy(xpath="//input[@name='email']")
WebElement txtEmail;

@FindBy(xpath="//input[@name='telephone']")
WebElement txtTelephone;

@FindBy(xpath="//input[@name='password']")
WebElement txtPassword;

@FindBy(xpath="//input[@name='confirm']")
WebElement txtConfirmPassword;

@FindBy(xpath="//input[@name='agree']")
WebElement txtConfirmCheckBox;

@FindBy(xpath="//input[@value='Continue']")
WebElement txtContinueButton;

@FindBy(xpath="//div//h1[text()='Your Account Has Been Created!']")
WebElement confirmationText;

@FindBy(xpath="//div[@class='pull-right']//a[text()='Continue']")
WebElement continueButton;

//Methods created to put data into fields
public void setFirstName(String fname) {
	txtFirstName.sendKeys(fname);
}
public void setLastName(String lname) {
	txtLastName.sendKeys(lname);
}
public void setEmail(String email) {
	txtEmail.sendKeys(email);
}
public void setTelephone(String telephone) {
	txtTelephone.sendKeys(telephone);
}
public void setPassword(String password) {
	txtPassword.sendKeys(password);
}
public void setConfirmPassword1(String password) {
	txtConfirmPassword.sendKeys(password);
}
public void checkBox() {
	txtConfirmCheckBox.click();
}
public void continueButton() {
	//Solution 1
	txtContinueButton.click();
	//Solution 2
	//txtContinueButton.submit();
	//Solution 3
	//Actions ac = new Actions(driver);
	//ac.moveToElement(txtContinueButton).click().perform();
	//Solution 4
	//JavaScriptExecutor js = (JavaScriptExecutor) driver;
	//js.executeScript("arguments[0].click();",txtContinueButton);
	//Solution 5
	//txtContinueButton.sendKeys(Keys.RETURN);
	//Solution 6
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//myWait.until(ExpectedConditions.elementToBeClickable(txtContinueButton)).click();
}

//Here we are using the try catch method just to get the confirmation text not for the validation, validations to be done only in test case part
public String getConfirmationMsg() {
	try {
		return(confirmationText.getText());
	}
	catch (Exception e) {
		return (e.getMessage());
	}
}

}
