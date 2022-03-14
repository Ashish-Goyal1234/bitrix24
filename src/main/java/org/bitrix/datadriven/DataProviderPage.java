package org.bitrix.datadriven;


import org.bitrix24.skeleton.BaseClass;
import org.testng.annotations.DataProvider;

public class DataProviderPage extends BaseClass{
    
    private static String LoginSheetName                                              = "Logintest";
    private static String CreateContactsSheetName                                              = "createContacts";
    
    @DataProvider(name = "CredentailsTestData12")
    public static Object[][] getCredentailsTestData() {
            return DataDrivenScript.readSheetData("TestData//Login.xlsx", LoginSheetName);
    }
    
    @DataProvider(name = "CreteContactsTestData")
    public static Object[][] createContactsTestData() {
            return DataDrivenScript.readSheetData("TestData//TestData.xlsx", CreateContactsSheetName);
    }
    
    
    
}
