package org.bitrix24.CRMPage;

import java.util.ResourceBundle.Control;

import org.bitrix.utility.BitrixLogger;
import org.bitrix.utility.FrameLocators;
import org.bitrix.utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactsPage {

	WebDriver paramDriver;
	FrameLocators iframeLocatorsInstance;

	public ContactsPage(WebDriver paramDriver) {
		this.paramDriver = paramDriver;
		PageFactory.initElements(paramDriver, this);
		iframeLocatorsInstance = new FrameLocators(paramDriver);
		BitrixLogger.info("Initialized Contacts page POM elements.");
	}

	@FindBy(xpath = "//span[text()='CRM']")
	private WebElement lnkCRM;

	@FindBy(xpath = "//span[text()='Contacts']")
	private WebElement lnkContacts;

	@FindBy(xpath = "//a[text()='Add']")
	private WebElement btnAdd;

	@FindBy(xpath = "//input[@id = 'last_name_text']")
	private WebElement txtLastname;

	@FindBy(xpath = "//input[@id = 'name_text']")
	private WebElement txtFirstname;

	@FindBy(id = "second_name_text")
	private WebElement txtSecondname;

	@FindBy(id = "mfi-photo_uploader-editor")
	private WebElement btnAddImage;

	@FindBy(xpath = "//span[text()='File']")
	private WebElement fileTab;

	@FindBy(className = "main-file-input-upload-link")
	private WebElement uploadBtn;

	@FindBy(xpath = "//input[@name='BIRTHDATE']")
	private WebElement dob;

	@FindBy(linkText = "March")
	private WebElement monthField;

	@FindBy(linkText = "2022")
	private WebElement yearField;

	@FindBy(className = "bx-calendar-year-input")
	private WebElement yearInputField;
	
	@FindBy(id = "post_text")
	private WebElement position;
	
	@FindBy(xpath="//div[@class='side-panel-label-icon side-panel-label-icon-close']")
	private WebElement sidePaneLabel;
	
	@FindBy(id = "CRM_CONTACT_LIST_V12_check_all")
	private WebElement chkBoxSelectAll;
	
	@FindBy(id = "grid_remove_button_control")
	private WebElement btnDelete;
	
	@FindBy(xpath="//span[text()='Continue']")
	private WebElement btnContinue;
	

	public WebElement getlnkCRM() {
		return lnkCRM;
	}

	public WebElement getContactsLnk() {
		return lnkContacts;
	}

	public WebElement getBtnAdd() {
		return btnAdd;
	}

	public WebElement getTxtContactsLastName() {
		return txtLastname;
	}

	public WebElement getTxtContactsFirstname() {
		return txtFirstname;
	}

	public WebElement getTxtContactsSecondname() {
		return txtSecondname;
	}

	public WebElement getBtnAddImage() {
		return btnAddImage;
	}

	public WebElement getFileTab() {
		return fileTab;
	}

	public WebElement getUploadBtn() {
		return uploadBtn;
	}

	public WebElement getFieldDOB() {
		return dob;
	}

	public WebElement getMonthField() {
		return monthField;
	}

	public WebElement getYearField() {
		return yearField;
	}

	public WebElement getYearInputField() {
		return yearInputField;
	}
	
	public WebElement getPosition() {
		return position;
	}
	
	public WebElement getSidePaneLabel() {
		return sidePaneLabel;
	}
	
	public WebElement getSelectAllCheckbox() {
		return chkBoxSelectAll;
	}
	
	public WebElement getDeleteBtn() {
		return btnDelete;
	}
	
	public WebElement getBtnContinue() {
		return btnContinue;
	}
	
	

	public void clkOnCRMlnk(WebDriverWait waitForReload) {
		waitForReload.until(ExpectedConditions.elementToBeClickable(getlnkCRM())).click();
		BitrixLogger.info("Clicked on CRM link.");
	}

	public void clkOnContacts(WebDriverWait waitForReload) {
		waitForReload.until(ExpectedConditions.elementToBeClickable(getContactsLnk())).click();
		BitrixLogger.info("Clicked on Contacts link.");
	}

	public void clkOnAddBtn(WebDriverWait waitForReload) {
		waitForReload.until(ExpectedConditions.elementToBeClickable(getBtnAdd())).click();
		BitrixLogger.info("Clicked on Add button.");
	}
	
	public void clkSidePaneLabel(WebDriverWait waitForReload) {
		waitForReload.until(ExpectedConditions.elementToBeClickable(getSidePaneLabel())).click();
	}
	
	
	

	public void createNewContact(WebDriverWait waitForReload, String lastname, String firstname, String secondname,
			String dobMonth, String birthYear, String birthDate, String getposition, String filePath) {
		Utilities.tempMethodForThreadSleep(2000);
		iframeLocatorsInstance.traverseToSidePanelFrame(waitForReload);
		waitForReload.until(ExpectedConditions.elementToBeClickable(getTxtContactsLastName())).sendKeys(lastname);
		waitForReload.until(ExpectedConditions.elementToBeClickable(getTxtContactsFirstname())).sendKeys(firstname);
		waitForReload.until(ExpectedConditions.elementToBeClickable(getTxtContactsSecondname())).sendKeys(secondname);

		// Below code for add image not working right now
		/*
		 * Utilities.tempMethodForThreadSleep(2000);
		 * Utilities.javaScriptClick(paramDriver, getBtnAddImage());
		 * Utilities.tempMethodForThreadSleep(2000);
		 * waitForReload.until(ExpectedConditions.elementToBeClickable(
		 * getFileTab())).click(); Utilities.tempMethodForThreadSleep(2000);
		 * Utilities.uploadAnImageUsingJavascript(paramDriver,getUploadBtn(),
		 * filePath);
		 */

		waitForReload.until(ExpectedConditions.elementToBeClickable(getFieldDOB())).click();
		waitForReload.until(ExpectedConditions.elementToBeClickable(getMonthField())).click();
		waitForReload.until(ExpectedConditions
				.elementToBeClickable(paramDriver.findElement(By.xpath("//span[contains(text(),'" + dobMonth + "')]"))))
				.click();
		waitForReload.until(ExpectedConditions.elementToBeClickable(getYearField())).click();

		waitForReload.until(ExpectedConditions.elementToBeClickable(getYearInputField())).sendKeys(birthYear);
		Utilities.tempMethodForThreadSleep(1000);
		waitForReload.until(ExpectedConditions.elementToBeClickable(paramDriver.findElement(By.linkText(birthDate))))
				.click();
		waitForReload.until(ExpectedConditions.elementToBeClickable(getPosition())).sendKeys(getposition);
        WebElement currentElement  = paramDriver.switchTo().activeElement();
        currentElement.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));
        iframeLocatorsInstance.switchToDefaultFrame(paramDriver);
        Utilities.tempMethodForThreadSleep(2000);
        clkSidePaneLabel(waitForReload);
    	Utilities.tempMethodForThreadSleep(3000);
    	BitrixLogger.info("Create new contact" + firstname + " " +secondname);
	}
	
	
	public void deleteAllContacts(WebDriverWait waitForReload) {
		waitForReload.until(ExpectedConditions.elementToBeClickable(getSelectAllCheckbox())).click();
		waitForReload.until(ExpectedConditions.elementToBeClickable(getDeleteBtn())).click();
		waitForReload.until(ExpectedConditions.elementToBeClickable(getBtnContinue())).click();
		Utilities.tempMethodForThreadSleep(2000);
		BitrixLogger.info("Clicked on Delete button");

		
		
	}

}
