package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClassObject;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.nn.ss");
		 * Date dt = new Date();
		 * String currentdatetimestamp=df.format(dt);
		 *  */
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName); //specify location of the report
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report"); //Title of the report
		sparkReporter.config().setReportName("opencart Functional Testing"); //Name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		//Some common information into the report using setSystemInfo
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		//Capturing data of os and browser from the xml file
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		//In some cases groups may be present or absent, so checking if it is present and getting the data
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //To display groups in report
		test.log(Status.PASS, result.getName()+ "got successfully executed");
		
	}
	//On Failing need to capture screenshot hence using the captureScreen in try catch block
	public void onTestFailure(ITestResult result) {
		
		//gets the class and the name and creates a test 
		test = extent.createTest(result.getTestClass().getName());
		//gets the method and the groups and assign it based on the category
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+ "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClassObject().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ "got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testContext) {
		//This finalises the report
		extent.flush();
		
		//The below syntaxes is used to make the report open automatically once the execution is done
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		//Try catch block is used to throw error if in case the report is not generated or not found
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//This try catch scenario is to send the report via mail automatically
		  
		/*   try{
		       URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		       
		       //Create and email message
		       ImageHtmlEmail email = new ImageHtmlEmail();
		       
		       email.setDataSourceResolver(new DataSourceUrlResolver(url));
		       //The host name only works for gmail
		       email.setHostName("smtp.googlemail.com");
		       email.setSmtpPort(465);
		       //Sender email id and password
		       email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
		       email.setSSLOnConnect(true);
		       email.setFrom("pavanoltraining@gmail.com"); //Sender
		       email.setSubject("Test Result");
		       email.setMsg("Please find the Attached Report");
		       email.addTo("surya21899@gmail.com"); //Receiver
		       email.attach(url, "extend report", "please check report...");
		       email.send(); //send the email
		       }
		       catch(Exception e){
		           e.printStackTrace();
		           }
		       */ 
		 
	}

}
