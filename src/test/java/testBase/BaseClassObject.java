package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger; //Log4j

public class BaseClassObject {
		
	public static WebDriver driver; 
	/*Note: Making the webdriver as static hence already there is a object created
	 * for webdriver used in the selection of chrome, edge, or firefox
	 * 
	 * In screeshot, we create object of driver there as well hence it will 
	 * get conflict
	 * 
	 * So making the webdriver as static will resolve this issue*/
	public Logger logger;  //log4j2
	public Properties p;
	
	
	@BeforeClass(groups= {"Sanity", "Regression", "Master"})
	//Receiving the parameters for launching different kind of browser
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		//Loading configuration data from config.properties file
		//FileInputStream or FileReader can be used
		FileReader file = new FileReader("./src//test//resources//config.properties"); // "./" navigates to the current project location then we can navigate to config through src/test/resources
		p=new Properties(); //initialize property
		p.load(file); //loading file 
		
		logger=LogManager.getLogger(this.getClass());
		
		//Put the if condition if execution_env is remote to execute this script
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("Windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching OS");
				return;
			}
			//For browser we can use switch case statement dynamicallay
			switch (br.toLowerCase()) {
			case "chrome": 
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("No matching browsers found");
				return;
			}
			//webdriver used to launch the browser
			driver=new RemoteWebDriver(new URL("http://192.168.95.183:4444/wb/hub"),capabilities);
			
			
			
		}
		//if the environment is local
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		//driver = new ChromeDriver();
		switch(br.toLowerCase()) {
		case "chrome": driver=new ChromeDriver();
		break;
		case "edge": driver=new EdgeDriver();
		break;
		case "firefox": driver=new FirefoxDriver();
		break;
		default:
			System.out.println("Invalid Browser Name");
			return;
		}
		}
		driver.manage().deleteAllCookies(); //deletes existing cookies
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://tutorialsninja.com/demo/");
		//Getting URL from the config properties
		driver.get(p.getProperty("appURL")); //SYNTAX TO GET DATA FROM CONFIG.PROPERTY FILE (p.getProperty(""))
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "Master"})
	public void tearDown() {
		driver.quit();
	}
	//To generate name, password, mail, telephone, password we cause use RandomStringUtils package
		//To generate string 
		public String randomeString() {
			String generatedString = RandomStringUtils.randomAlphabetic(5);
			return generatedString;
			
		}
		//To generate number
		public String randomeNumber() {
			String generatedNumber = RandomStringUtils.randomNumeric(10);
			return generatedNumber;
		}
		//To generate alphanumeric
		public String randomeStringNumber() {
			String generatedString = RandomStringUtils.randomAlphabetic(3);
			String generatedNumber = RandomStringUtils.randomNumeric(3);
			return(generatedString+"@"+generatedNumber);
		}
		public String captureScreen(String tname) throws IOException {
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile = new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			
			return targetFilePath;
			
		}		
}
