package org.bitrix24.crm;

import org.bitrix.datadriven.DataProviderPage;
import org.bitrix.utility.BitrixLogger;
import org.bitrix24.CRMPage.ContactsPage;
import org.bitrix24.skeleton.BaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContactsTest extends BaseClass {

	private ContactsPage contactspage;
	private WebDriverWait waitForReload;

	@BeforeClass
	public void initialization() {
		contactspage = new ContactsPage(browserDriver);
		waitForReload = new WebDriverWait(browserDriver, 30);
		BitrixLogger.info("Initialized the POM object for Contacts page.");
	}

	@Test(dataProviderClass = DataProviderPage.class, dataProvider = "CreteContactsTestData")
	public void createContacts(String lastname, String firstname, String secondname, String dobMonth, String birthYear,
			String birthDate, String position, String filename) {
		try {
			contactspage.clkOnCRMlnk(waitForReload);
			contactspage.clkOnContacts(waitForReload);
			contactspage.clkOnAddBtn(waitForReload);
			contactspage.createNewContact(waitForReload, lastname, firstname, secondname,  dobMonth, birthYear,
					birthDate, position,  (imagePath + filename));
			contactspage.deleteAllContacts(waitForReload);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to create contacts" + e);
		}
	}
}
