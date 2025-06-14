package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClassObject;

public class TC_0002_LoginTest extends BaseClassObject{  //extends from BaseClassObject as it has the webdriver launch details
       @Test(groups= {"Sanity", "Master"})
       public void verify_Login() {
    	   //Using logger info
    	   logger.info("***** Starting TC_0002_LoginTest *****");
    	   
    	   
    	   //From HomePage
    	   try{
    		   HomePage hp = new HomePage(driver);
    	   
    	   hp.clickMyAccount();  //Clicking on My Account
    	   hp.loginClick(); // Clicking on Login
    	   
    	   //From LoginPage
    	   LoginPage lp = new LoginPage(driver);
    	   lp.setEmail(p.getProperty("email"));
    	   lp.setPassword(p.getProperty("password"));
    	   lp.clickLogin();
    	   
    	   //Now to check in My Account Page
    	   MyAccountPage mp = new MyAccountPage(driver);
    	   //store the object created for verification 
    	   boolean targetPage = mp.isMyAccountPageVisible();
    	   
    	   Assert.assertEquals(targetPage, true, "Login Failed"); //also use Assert.assertTrue(targetPage)
    	   }
    	   catch(Exception e) {
    		   Assert.fail();
    	   }
    	   //Putting it in try catch block as it may throw exception in any of the state
    	   
    	   //logger info
    	   logger.info("***** TC_0002_LoginTest_Finished *****");
    	   
    	   
       }
}
