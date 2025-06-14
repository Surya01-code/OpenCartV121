package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationTest;
import pageObjects.HomePage;
import testBase.BaseClassObject;

public class TC_0001_AccountRegistrationTest extends BaseClassObject {
    //MOSTLY KEEP ALL THE METHODS AS PUBLIC AS THOSE CAN BE ACCESSED FROM ANYWHERE
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		//To access it from the HomePage, create a object for it with the reference to the driver
		
		//Implementing logger		
		logger.info("***** Starting of the Account Registration Test *****");
		
		//Putting the steps into the try catch method if it gets any error
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Click on Account"); //Click on account info log
		hp.registerAccount();
		logger.info("Register Account"); //Register account info log
		
		//To access it from the AccountRegistrationPage, create a object for it with the reference to the driver
		AccountRegistrationTest apt = new AccountRegistrationTest(driver);
		
		logger.info("Input details in registration page");  //Input details info log
		apt.setFirstName(randomeString().toUpperCase());
		apt.setLastName(randomeString().toUpperCase());
		apt.setEmail(randomeString()+"@gmail.com");
		apt.setTelephone(randomeNumber());
		
		String passWord = randomeStringNumber();
		apt.setPassword(passWord);
		apt.setConfirmPassword1(passWord);
		//Here if we use randomeStringNumber() in password and confirm password it will generate two different password hence storing it in a 
		///string variable and using it 
		
		//click on check box and continue
		apt.checkBox();
		apt.continueButton(); 
		
		//validating confirmation message
		logger.info("Validating Confirmation message"); //validating message info log
		String confirmMessage = apt.getConfirmationMsg();
		Assert.assertEquals(confirmMessage, "Your Account Has Been Created!");
		}
		catch(Exception e) {
		    logger.error("Test failed...");  //Test failed error log
		    logger.debug("Debug logs");  //Debug logs
		    Assert.fail();
		}
		
		logger.info("***** Starting of the Account Registration Test *****");
		
		
		
	}
	
}
