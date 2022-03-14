package org.bitrix.pom;

import org.bitrix.utility.BitrixLogger;
import org.bitrix.utility.FrameLocators;
import org.bitrix.utility.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {
	
	WebDriver paramDriver;

	/**
     * Parameterized constructor. This method sets the browser driver instance
     * for this page.
     * 
     * @param paramDriver
     */
    public LogoutPage(WebDriver paramDriver) {
    	this.paramDriver = paramDriver;
        PageFactory.initElements(paramDriver, this);
        BitrixLogger.info("Initialized Login page POM elements.");
    }

	@FindBy(className = "user-name")
	private WebElement username;

	@FindBy(className = "system-auth-form__item-link-all")
	private WebElement logoutBtn;

	public WebElement getCurrentUsername() {
		return username;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}

	public void clickOnUsername(WebDriverWait waitForReload){
		Utilities.tempMethodForThreadSleep(2000);
		Utilities.javaScriptClick(paramDriver, getCurrentUsername());
    	BitrixLogger.info("Clicked on username");
    }

	public void clkOnLogoutBtn(WebDriverWait waitForReload) {
		waitForReload.until(ExpectedConditions.elementToBeClickable(getLogoutBtn())).click();
		BitrixLogger.info("Clicked on Logout button");
	}

}
