package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	public static WebDriver driver;
	public Properties prop;
	public static Logger log;
	String path;

	public WebDriver driverintialisation() throws IOException {
		
		log = LogManager.getLogger();
		 path = System.getProperty("user.dir");
		prop = new Properties();
		FileInputStream file = new FileInputStream(path + "\\src\\main\\java\\resources\\data.properties");
		prop.load(file);
		String browser = prop.getProperty("browser");

		if (browser.equals("chrome")) {

			System.setProperty("WebDriver.chrome.driver", path + "\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", path + "\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else {
			System.setProperty("webdriver.edge.driver", path + "\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		log.info("Driver is initialized");
		return driver;
	}
	
	public void getScreenshot(String result) throws IOException {
		File Source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Source, new File(path+"\\test-output\\Screenshots\\"+result+"screenshot.png"));
		}
	
	
}
