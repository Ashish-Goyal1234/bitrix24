package org.bitrix.listeners;

import org.bitrix.utility.BitrixLogger;
import org.bitrix.utility.SendReportOnSlackChannel;

public class CleanUp extends SuiteListener{
    /**
     * Quits the browser driver window.
     */
    public void closeBrowserDriver() {
        webDriverInstance.quit();
        BitrixLogger.info("Closed browser window.");
    }
    
    
    public void sendEmailReport() {
        loadEmailReportInstance();
    }
    
    public void sendReportOnSlack() throws Exception {
    	loadSlackReportInstance();
    }
}
