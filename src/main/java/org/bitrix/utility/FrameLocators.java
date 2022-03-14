package org.bitrix.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class FrameLocators {
	 private WebDriver            browserDriverInstance;
	
    public FrameLocators(WebDriver paramDriver) {
        this.browserDriverInstance = paramDriver;
        PageFactory.initElements(browserDriverInstance, this);
    }


	@FindBy(xpath = "//iframe[contains(@id,'iframe_')]")
	private WebElement sidePanelFrame;

	public WebElement getSidePanelFrame() {
		return sidePanelFrame;
	}

	public void traverseToSidePanelFrame(WebDriverWait waitForReload) {
		waitForReload.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getSidePanelFrame()));
	}
	
	public void switchToDefaultFrame(WebDriver paramDriver) {
		paramDriver.switchTo().defaultContent();
	}

}
