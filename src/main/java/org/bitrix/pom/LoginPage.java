package org.bitrix.pom;


import org.bitrix.utility.BitrixLogger;
import org.bitrix.utility.Utilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver paramDriver;
	public LoginPage(WebDriver paramDriver) {
		this.paramDriver = paramDriver;
        PageFactory.initElements(paramDriver, this);
        BitrixLogger.info("Initialized Login page POM elements.");
    }
	
	
	@FindBy(id = "login")
	private WebElement txtUserName;
	
	@FindBy(id = "password")
	private WebElement txtPassword;
	
	@FindBy(xpath="//button[text()='Next']")
	private WebElement nextBtn;
	
	
	  
    /**
     * Getter for txtUsername
     * 
     * @return WebElement
     */
	public WebElement getTxtUsername(){
		return txtUserName;
	}
	
	/**
     * Getter for txtPassword
     * 
     * @return WebElement
     */
	public WebElement getTxtPassword(){
		return txtPassword;
	}
	
	public WebElement getNextBtn(){
		return nextBtn;
	}
	
	
	
	/**
     * This method sets the provided username in the username text field.
     * 
     * @param username String value containing the username
     */
	public void enterUsername(String username){
		getTxtUsername().clear();
        getTxtUsername().sendKeys(username);
        Utilities.tempMethodForThreadSleep(1000);
        
        WebElement currentElement  = paramDriver.switchTo().activeElement();
        currentElement.sendKeys(Keys.RETURN);
        BitrixLogger.info("Entered username " + username);
	}
	
	
	
	public void enterPassword(String password) {
		txtPassword.clear();
		txtPassword.sendKeys(password);
	}
	
	
	public void clkNextBtn(){
		getNextBtn().click();
	}
	
	
	
}
