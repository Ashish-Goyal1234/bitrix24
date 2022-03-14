package org.bitrix24.logout;

import org.bitrix.pom.LogoutPage;
import org.bitrix.utility.BitrixLogger;
import org.bitrix.utility.Utilities;
import org.bitrix24.skeleton.BaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogoutTest extends BaseClass{

		LogoutPage            logoutPage;
	    private WebDriverWait waitForReload;
	    
	    
	    /**
	     * This method initializes required instances for test.
	     */
	    @BeforeClass
	    public void initializeResources() {
	        logoutPage = new LogoutPage(browserDriver);
	        waitForReload = new WebDriverWait(browserDriver, 20);
	        BitrixLogger.info("Initialized the POM object for Login page.");
	    }

	    /**
	     * This method Click on logout and logout the page .
	     * 
	     */
	    
	    @Test
	    public void performLogout(){
	    	try{
	    	
	    	logoutPage.clickOnUsername(waitForReload);
	    	Utilities.tempMethodForThreadSleep(2000);
	    	logoutPage.clkOnLogoutBtn(waitForReload);
	    	BitrixLogger.info("Successfully Logged out of the system");
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    		Assert.fail("Failed to perform Logout" + e);
	    	}
	    }
	
}
