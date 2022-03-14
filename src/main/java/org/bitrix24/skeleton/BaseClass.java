package org.bitrix24.skeleton;


import java.io.File;

import org.bitrix.datadriven.DataDrivenScript;
import org.bitrix.utility.ApplicationConfiguration;
import org.bitrix.utility.BitrixLogger;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	protected WebDriver                browserDriver;
    protected DataDrivenScript         DrivenScript;
    protected ApplicationConfiguration config;
    public static String path;
    public static String imagePath;
    
    static{
    	path = System.getProperty("user.dir");
    	imagePath = path + File.separator + "TestData" + File.separator + "images";
    }
    
    
    /**
     * This method perform operations required before the test begins.
     * 
     * @param context Context Instance
     */
    @BeforeClass
    public void preProcessor(ITestContext context) {
        ISuite suite = context.getSuite();
        browserDriver = (WebDriver) suite.getAttribute("driverForBrowser");
        DrivenScript = new DataDrivenScript();
        config = (ApplicationConfiguration) suite
                .getAttribute("configurations");
        BitrixLogger.setClass(this);
        BitrixLogger.startTestCase(this.getClass().getSimpleName());
    }

    /**
     * This method perform operations required after the test of a class have
     * ended.
     */
    @AfterClass
    public void postProcessor() {
    	BitrixLogger.endTestCase(this.getClass().getSimpleName());
    }

    /**
     * Returns the webdriver instance.
     * 
     * @return browserDriver instance
     */
    public WebDriver getBrowserDriver() {
        return browserDriver;
    }

    /**
     * Returns the ApplicationConfiguration Instance.
     * 
     * @return config ApplicationConfiguration
     */
    public ApplicationConfiguration getConfig() {
        return config;
    }
    
    
    /**
     * This is returns the data driven class to read the data from the xlsx file
     * 
     * @return object of data driven
     */
    public DataDrivenScript getExcelDataDriven() {
        return DrivenScript;
    }
}
