package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClassObject;
import utilities.DataProviders;

public class TC_0003_LoginDDT extends BaseClassObject{
	
	/*To Invoke data provider inside this class, mention the dataprovider name same as from DataProviders.java in @Test annotation
	 * if the data provider is present in different packagae, mention dataProviderClass as well
	 * if the data provider is from the same class, the dataProviderClass key value is not needed */
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")
	public void verify_loginDDT(String email, String pwd, String exp) { //Fetching data as string from excel using email, pwd, exp variable
		//Adding Logger information
		logger.info("*****TC_0003_LoginTest*****");
		//Home Page
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.loginClick();
		
		//Login
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//My Account page
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageVisible();
		
		/* 1)Data is valid: login success - Test pass -- logout
		 * 2)Data is valid: login failed - test fail
		 * 
		 *  3)Data is invalid: login success - Test fail -- logout
		 *  4)Data is invalid: login failed - test pass */
		
		//1)
		if(exp.equalsIgnoreCase("Valid")) { //EqualsIgnoreCase used hence it is case insensitive
			if(targetPage==true) {
				map.clickLogOut();
				Assert.assertTrue(true);
				//NOTE: After assertion no statement will be executed hence the statements are put before assertion
			}
			//2)
			else {
				Assert.assertTrue(false);
			}
		}
		//3)
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				map.clickLogOut();
				Assert.assertTrue(false);
			}
			//4)
			else {
				Assert.assertTrue(true);
			}
		}
		Thread.sleep(3000);
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*****TC_0003_Test Case Completed*****");
		
	}
	

}
