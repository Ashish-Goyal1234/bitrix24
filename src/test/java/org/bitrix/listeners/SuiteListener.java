package org.bitrix.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.bitrix.utility.ApplicationConfiguration;
import org.bitrix.utility.BitrixLogger;
import org.bitrix.utility.BrowserFactory;
import org.bitrix.utility.SendEmailableReportTest;
import org.bitrix.utility.SendReportOnSlackChannel;
import org.bitrix.utility.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SuiteListener implements ISuiteListener {

	public static WebDriver    webDriverInstance;
	public static ISuite suite;
    static int                 suitCount = 0;
    private ExtentReports      extent;
    private ExtentHtmlReporter htmlReporter;
   
	
	/**
     * This method is invoked before the SuiteRunner starts.
     */
    
	public void onStart(ISuite suite){
	    performStartupActivities(suite);
	}
	
	
	 /**
     * This method perform activities necessary before starting the test.
     * 
     * @param suite Suite Instance.
     */
    private void performStartupActivities(ISuite suite) {
        ApplicationConfiguration config = readConfigurationFile(suite);
        loadBrowserDriverInstance(suite, config);
        String filePath = System.getProperty("user.dir") + File.separator
                + config.getextentReportsPath();
        filePath += new SimpleDateFormat("dd-MM-yyyy")
                .format(new Date());
        try {
            Files.createDirectories(Paths.get(filePath));
        } catch (IOException e) {
            BitrixLogger.debug(
                    "Error while creating  extent file report directory", e);
        }
        htmlReporter = new ExtentHtmlReporter(filePath + File.separator +  "Extent_"+ new SimpleDateFormat("E-dd-MMM-yyyy").format(new Date()) + ".html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Extent reports");
        htmlReporter.config().setReportName("Automation Test Result");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        suite.setAttribute("extentReport", extent);  // Setting extent value in extentReport


        extent.setSystemInfo("Automation Tester", "Ashish Goyal");
        extent.setSystemInfo("Organization", "Ashish Automation Labs");
        extent.setSystemInfo("Build No", "WA-11122");
    }
    
    
    
    
    /**
     * This method reads the properties file.
     * 
     * @param suite Suite Instance.
     * @return config ApplicationConfiguration Instance.
     */
    private ApplicationConfiguration readConfigurationFile(ISuite suite) {
        ApplicationConfiguration config = new ApplicationConfiguration();
        config.loadConfiguration();
        this.suite = suite;
        suite.setAttribute("configurations", config);
        return config;
    }

    
    
    /**
     * This method initiates the browser driver loading process.
     * 
     * @param suite Suite Instance
     * @param config ApplicationConfiguration Instance
     */
    private void loadBrowserDriverInstance(ISuite suite,
            ApplicationConfiguration config) {
        BrowserFactory browserFactory = new BrowserFactory();
        if (config.getLocalOrRemote().equalsIgnoreCase("local")) {
            if (suitCount == 0) {
                webDriverInstance = (WebDriver) suite
                        .getAttribute("driverForBrowser");
                	   webDriverInstance = browserFactory
                               .getLocalBrowser(config.getBrowserName());
                }
             
                setTimeouts(config, webDriverInstance.manage());
                webDriverInstance.manage().window().maximize();
                suitCount++;
            if (webDriverInstance != null) {
                suite.setAttribute("driverForBrowser", webDriverInstance);
                webDriverInstance.get(config.getUrl());
            }
        } else if (config.getLocalOrRemote().equalsIgnoreCase("remote")) {
            try {
                if (suitCount == 0) {
                    webDriverInstance = (WebDriver) suite
                            .getAttribute("driverForBrowser");
                    webDriverInstance = browserFactory
                            .getRemoteBrowser(config.getBrowserName());
                    setTimeouts(config, webDriverInstance.manage());
                    webDriverInstance.manage().window().maximize();
                    suitCount++;
                }
                if (webDriverInstance != null) {
                    suite.setAttribute("driverForBrowser", webDriverInstance);
                    webDriverInstance.get(config.getUrl());
                }
            } catch (Exception e) {
                BitrixLogger.error("Failed to load remote browser", e);
            }
        }
    }
    
    
    
    /**
     * This method sets the various time-outs for browser.
     * 
     * @param config ApplicationConfiguration Instance
     * @param optionsInstance Options Instance
     */
    private void setTimeouts(ApplicationConfiguration config,
            Options optionsInstance) {
        setImplicitTimeOutForBrowser(config, optionsInstance);
        setPageLoadTimeOutForBrowser(config, optionsInstance);
        setScriptTimeOutForBrowser(config, optionsInstance);
        BitrixLogger.info("Initialized Timeout settings.");
    }
    
    /**
     * This method sets the implicit time-out for browser.
     * 
     * @param config ApplicationConfiguration Instance
     * @param optionsInstance Options Instance
     */
    private void setImplicitTimeOutForBrowser(ApplicationConfiguration config,
            Options optionsInstance) {
        optionsInstance.timeouts().implicitlyWait(config.getImplicitTimeout(),
                TimeUnit.SECONDS);
    }

    /**
     * This method sets the pageload time-out for browser.
     * 
     * @param config ApplicationConfiguration Instance
     * @param optionsInstance Options Instance
     */
    private void setPageLoadTimeOutForBrowser(ApplicationConfiguration config,
            Options optionsInstance) {
        optionsInstance.timeouts().pageLoadTimeout(config.getPageLoadTimeout(),
                TimeUnit.SECONDS);
    }

    /**
     * This method sets the script time-out for browser.
     * 
     * @param config ApplicationConfiguration Instance
     * @param optionsInstance Options Instance
     */
    private void setScriptTimeOutForBrowser(ApplicationConfiguration config,
            Options optionsInstance) {
        optionsInstance.timeouts().setScriptTimeout(config.getScriptTimeout(),
                TimeUnit.SECONDS);
    }
    
    
    /**
     * This method is invoked after the SuiteRunner has run all the test suites.
     */
    public void onFinish(ISuite suite) {
        performCleanupActivities(suite);
    }

    /**
     * This method removes ApplicationConfiguration Instance from test context.
     * 
     * @param suite Suite Instance
     */
    private void performCleanupActivities(ISuite suite) {
        extent.flush();
    }
    
    public void loadEmailReportInstance( ) {
        ApplicationConfiguration config = readConfigurationFile(suite);
        
        SendEmailableReportTest emailRpt = new SendEmailableReportTest();
   
        String filePath = System.getProperty("user.dir") + "\\"
                + config.getextentReportsPath();
        filePath += new SimpleDateFormat("dd-MM-yyyy")
                .format(new Date());
       
        String reportPath = filePath+ "\\";
                
            emailRpt.testSendEmailableReportTest(config.getExcelSheetPath(), config.getEmailAddressSheetName(), reportPath, 
                    config.getEmailAddressHostName(), config.getEmailAddressUserName(), config.getEmailAddressPassword(),
                    config.getEmailFromAddress(), config.getEmailFromName(), config.getEmailSubject(), config.getEmailMessage());
    }
    
    
    public void loadSlackReportInstance( ) throws Exception {
    	 ApplicationConfiguration config = readConfigurationFile(suite);
    	 String filePath = System.getProperty("user.dir") +  File.separator
                 + config.getextentReportsPath();
         filePath += new SimpleDateFormat("dd-MM-yyyy")
                 .format(new Date());
         
         Utilities.tempMethodForThreadSleep(5000);
                 
         String reportPath =filePath + File.separator +"Extent_"+ new SimpleDateFormat("E-dd-MMM-yyyy").format(new Date()) + ".html";
     
         System.out.println("****" + reportPath);
         
         SendReportOnSlackChannel ss = new SendReportOnSlackChannel();
         ss.sendTestExecutionStatusToSlack("Report generated by ashish");
     	ss.sendTestExecutionReportToSlack(reportPath);
     	
         
    }

    
	

	
}
