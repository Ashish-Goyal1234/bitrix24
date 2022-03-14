package org.bitrix.utility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.testng.Assert;

public class SendEmailableReportTest {
    EmailAttachment attachment;
    static String recipientEmailAddress = "";
    private org.bitrix.datadriven.Xls_Reader reader;
    private MultiPartEmail email;

    public void testSendEmailableReportTest(String excelPath, String sheetName, String reportPath, String getEmailAddressHostName,
            String getEmailAddressUserName, String getEmailAddressPassword, String getEmailFromAddress, String getEmailFromName, 
            String getEmailSubject, String getEmailMessage) {
        
            try {
                    reader = new org.bitrix.datadriven.Xls_Reader(excelPath,sheetName);
                    for (int rowNum = 2; rowNum <= reader.getRowCount(sheetName); rowNum++) {
                            attachment = new EmailAttachment();
                         //  attachment.setPath(getLastModified(reportPath).toString());
                            attachment.setPath(reportPath);    // Temp line need to remove
                            attachment.setDisposition(EmailAttachment.ATTACHMENT);
                          // attachment.setName(getLastModified(reportPath).getName());
                            attachment.setName("AutomationReport.oog");  // Temp Line need to remove
                            email = new MultiPartEmail();
                            email.setHostName(getEmailAddressHostName);
                            email.setSmtpPort(465);
                            email.setAuthenticator(
                                            new DefaultAuthenticator(getEmailAddressUserName, getEmailAddressPassword));
                            email.setSSLOnConnect(true);
                            recipientEmailAddress = reader.getCellData(sheetName, "Email Address", rowNum);
                            email.setFrom(getEmailFromAddress, getEmailFromName);
                            email.setSubject(getEmailSubject);
                            email.setMsg(getEmailMessage);
                            email.addTo(recipientEmailAddress);
                            email.attach(attachment);
                            email.send();
                            BitrixLogger.info("Email send Succesfully to recepients : " + recipientEmailAddress);
                    }
            } catch (Exception e) {
                e.printStackTrace();
                    Assert.fail("Failed to send an Email :" + e);
                    BitrixLogger.error("Failed to send an email :" + e);
               
            }
    }

   /* private static File getLastModified(String directoryFilePath) {
            File directory = new File(directoryFilePath);
            File[] files = directory.listFiles(File::isFile);
            long lastModifiedTime = Long.MIN_VALUE;
            File chosenFile = null;

            if (files != null) {
                    for (File file : files) {
                            if (file.lastModified() > lastModifiedTime) {
                                    chosenFile = file;
                                    lastModifiedTime = file.lastModified();
                            }
                    }
            }
            return chosenFile;
    }*/
}