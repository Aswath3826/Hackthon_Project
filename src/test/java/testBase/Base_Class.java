package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pageObject.BookShelves_Page;
import pageObject.GiftCards_Page;
import pageObject.Home_Page;

public class Base_Class {
	public static WebDriver driver;
	public static FileInputStream file;
	public static Properties p = new Properties();
	public static Logger logger;
	public static Home_Page hp;
	public static BookShelves_Page bp;
	public static GiftCards_Page gp;
	@BeforeTest
	@Parameters({"os","Browser"})
	public void setup(String os , String br) throws MalformedURLException
	{
		//loading properties file
		try
		{
			file = new FileInputStream("C:\\Users\\2318646\\eclipse-workspace\\Hackathon_Project\\src\\test\\resources\\config.properties");
			p.load(file);
		}
		catch(Exception e)
		{
			System.out.println("File not Found");
		}
		//loading log4j 
	    logger=LogManager.getLogger(this.getClass());//Log4j
	    logger.info("-----------Driver setup done with "+br+" browser------------------");
	    
	    if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	 	{
	    	DesiredCapabilities capabilities=new DesiredCapabilities();
		
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os..");
				return;
			}
		
			//browser
			if(br.equalsIgnoreCase("chrome"))
			{
				capabilities.setBrowserName("chrome");
			}
			else if(br.equalsIgnoreCase("edge"))
			{
				capabilities.setBrowserName("MicrosoftEdge");
			}
		
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
       }
	   else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	   {
		   //launching browser based on condition - locally
		   if(br.equalsIgnoreCase("chrome"))
			{
				driver = new ChromeDriver();
			}
			else if(br.equalsIgnoreCase("edge"))
			{
				driver = new EdgeDriver();
			}
	   }
	   hp = new Home_Page(driver);
	   bp = new BookShelves_Page(driver);
	   gp = new GiftCards_Page(driver);
	   driver.get(p.getProperty("baseURL"));
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	@AfterTest
	public void close()
	{
		logger.info("----------Driver Closed---------------");
		driver.quit();
	}
	
	//Screensot
	public String captureScreen(String tname) throws IOException 
	{				
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);		
		String targetFilePath=System.getProperty("user.dir")+"\\Error_Message_Screeshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);		
		FileUtils.copyFile(sourceFile, targetFile);			
		sourceFile.renameTo(targetFile);			
		return targetFilePath;
	}
	//Navigate
	public void navigate_to()
	{
		driver.navigate().to(p.getProperty("baseURL"));
	}
}
