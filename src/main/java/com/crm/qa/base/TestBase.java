package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static Properties prop;
	
	static ThreadLocal<WebDriver> webdriver = new ThreadLocal<WebDriver>();
	
	
	//public static WebDriver driver;
	public TestBase() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\crm\\qa\\config\\Config.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			webdriver.set(new ChromeDriver());
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			webdriver.set(new FirefoxDriver());
		}

		webdriver.get().manage().window().maximize();
		webdriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.Imlicitlywait));
		webdriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PageLoadTimeout));
		webdriver.get().manage().deleteAllCookies();
		webdriver.get().get(prop.getProperty("url"));
	}
public static WebDriver getdriver()
{
	return webdriver.get();
}
public static String takeScreenshotOnFailure(String testCaseName) {
File source = ((TakesScreenshot)getdriver()).getScreenshotAs(OutputType.FILE);
String target_folder = System.getProperty("user.dir")+"\\report\\"+testCaseName+".png";
try {
	FileUtils.copyFile(source, new File(target_folder));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return target_folder;
}
public void unload()
{
webdriver.remove();	
}
}
