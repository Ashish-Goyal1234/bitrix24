package org.bitrix.utility;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class is responsible for creating the requested browser instance.
 *
 */

public class BrowserFactory {

	/**
	 * This method accepts the browser name and created the appropriate driver
	 * instance and return it.
	 * 
	 * @param browserName
	 *            String
	 * @param browserVersion
	 *            String
	 * @return driver WebDriver Instance
	 */
	public WebDriver getLocalBrowser(String browserName) {
		WebDriver driver = null;
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		if (driver != null) {
			BitrixLogger.info("Loaded Browser Driver Instance.");
		} else {
			BitrixLogger.fatal("Failed to load Browser Driver Instance.");
		}
		return driver;
	}

	@SuppressWarnings("deprecation")
	public WebDriver getRemoteBrowser(String browserName) throws MalformedURLException {
		WebDriver remoteDriver = null;
		if (browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			// cap.setPlatform(Platform.MAC);
			cap.setBrowserName(BrowserType.CHROME);
			URL url = new URL("http://localhost:4444");
			remoteDriver = new RemoteWebDriver(url, cap);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			// cap.setPlatform(Platform.WIN8);
			cap.setBrowserName(BrowserType.FIREFOX);
			URL url = new URL("http://localhost:4545");
			remoteDriver = new RemoteWebDriver(url, cap);
		}
		return remoteDriver;
	}

}
