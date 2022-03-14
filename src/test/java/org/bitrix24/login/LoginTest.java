package org.bitrix24.login;

import org.bitrix.datadriven.DataProviderPage;
import org.bitrix.pom.LoginPage;
import org.bitrix.utility.BitrixLogger;
import org.bitrix24.skeleton.BaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LoginTest extends BaseClass{
	 private LoginPage loginPage;
	 private WebDriverWait waitForReload;
	 
	 /**
	     * This method initializes required instances for test.
	     * 
	     * @author Ashish-HP
	     */
	    @BeforeClass
	    public void initializeResources() {
	        loginPage = new LoginPage(browserDriver);
	        waitForReload = new WebDriverWait(browserDriver, 180);
	        BitrixLogger.info("Initialized the POM object for Login page.");
	    }

	    /**
	     * This method test the login credentials supplied to it via DataProvider
	     * method 'CredentailsTestData'. This method also depends on method
	     * 'verifyPresenceOfElements'
	     * 
	     * @param username String Username to test.
	     * @param password String password to test.
	     * @param result String Expected result of the login attempt.
	     * @throws InterruptedException 
	     */

	    
	    @Test( priority = 1, dataProviderClass = DataProviderPage.class, dataProvider = "CredentailsTestData12")
	    public void testLogin(String userName, String password) {
	    	try{
	        loginPage.enterUsername(userName);
	        loginPage.enterPassword(password);
	        loginPage.clkNextBtn();
	        BitrixLogger.info("Successfully Logged into the System.");
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    		Assert.fail("Failed to perform login" + e);
	    	}
	    }

	 /*   *//**
	     * This method checks if elements required for login are present or not.
	     *//*
	    @Test(priority = 0)
	    public void verifyPresenceOfElements() {
	        int elementCount = 0;
	        elementCount += (loginPage.getHomeLink() == null ? 0 : 1);
	        elementCount += (loginPage.getSignUpLink() == null ? 0 : 1);
	        elementCount += (loginPage.getPricingLink() == null ? 0 : 1);
	        elementCount += (loginPage.getFeaturesLink() == null ? 0 : 1);
	        elementCount += (loginPage.getCustomersLink() == null ? 0 : 1);
	        elementCount += (loginPage.getContactsLink() == null ? 0 : 1);
	        elementCount += (loginPage.getBtnLoginButton() == null ? 0 : 1);
	        elementCount += (loginPage.getTxtPassword() == null ? 0 : 1);
	        elementCount += (loginPage.getTxtUsername() == null ? 0 : 1);
	        if (elementCount != 9) {
	            Assert.fail("Elements not present");
	            CybageLogger.error("Login elements are not present.");
	        }
	        CybageLogger.info("Login elements are present.");
	    }
*/
}
