package org.bitrix.listeners;

import java.io.IOException;
import java.util.Arrays;



import org.bitrix.utility.ApplicationConfiguration;
import org.bitrix.utility.BitrixLogger;
import org.bitrix.utility.Utilities;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestListener implements ITestListener{
	 private ExtentReports      extentRpt;
	    private ExtentTest         extentTest;
	    ApplicationConfiguration config;
	    
	    
	    /**
	     * Invoked after the test class is instantiated and before any configuration
	     * method is called.
	     */
	    public void onStart(ITestContext context) {
	        ISuite suiteInstance = context.getSuite();
	        extentRpt = (ExtentReports) suiteInstance.getAttribute("extentReport");
	        config = (ApplicationConfiguration) suiteInstance.getAttribute("configurations");
	    }
	    
	    
	    
	    /**
	     * Invoked after all the tests have run and all their Configuration methods
	     * have been called.
	     */
	    public void onFinish(ITestContext context) {
	        
	        
	    }
	    
	    
	    
	    /**
	     * Invoked each time before a test will be invoked. The
	     * <code>ITestResult</code> is only partially filled with the references to
	     * class, method, start millis and status.
	     *
	     * @param result the partially filled <code>ITestResult</code>
	     * @see ITestResult#STARTED
	     */
	    public void onTestStart(ITestResult result) {
	        
	        extentTest  = extentRpt.createTest(result.getTestClass().getName()+"      @TestCase : " + result.getMethod().getMethodName());
	        extentTest.assignCategory(result.getTestClass().getName());  // This line is ued to add catagories.
	        extentTest.log(Status.INFO, result.getTestClass().getName()+"      @TestCase : " + result.getMethod().getMethodName() + "test is started");
	    }
	    
	    
	    
	    /**
	     * Invoked each time a test succeeds.
	     *
	     * @param result <code>ITestResult</code> containing information about the
	     *            run test
	     * @see ITestResult#SUCCESS
	     */
	    public void onTestSuccess(ITestResult result){
	        String methodName = result.getMethod().getMethodName();
	        String logText = "<b>" + "TEST CASE : - " + methodName.toUpperCase()
	                + " Passed" + "</b>";
	        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
	        extentTest.pass(m);
	    }
	    
	    
	    /**
	     * Invoked each time a test fails.
	     *
	     * @param result <code>ITestResult</code> containing information about the
	     *            run test
	     * @throws IOException
	     * @see ITestResult#FAILURE
	     */
	    public void onTestFailure(ITestResult result) {
	        String path = config.getScreenshotPath();
	        String screenshotPath =Utilities.captureScreenshotWithRobot(result.getName(), path);
	        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
	        extentTest.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured : Click to see"  + "</font>" + "</b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"  + "\n");
	        String failureLog = "TEST CASE FAILED";
	        Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
	        extentTest.log(Status.FAIL, m);
	        try {
	            extentTest.fail("<font color=" + "red>" + "Snapshot below: " + extentTest.addScreenCaptureFromPath(screenshotPath));
	            } catch (IOException e) {
	                BitrixLogger.debug("Failed to add scrxeenshot in extent report", e);
	                Assert.fail("Failed to add screenshot in extent report", e);
	            }

	    }
	    
	    
	    
	    /**
	     * Invoked each time a test is skipped.
	     *
	     * @param result <code>ITestResult</code> containing information about the
	     *            run test
	     * @see ITestResult#SKIP
	     */
	    public void onTestSkipped(ITestResult result) {
	        String methodName = result.getMethod().getMethodName();
	        String logText = "<b>" + "TEST CASE : - " + methodName.toUpperCase()
	                + " SKipped" + "</b>";
	        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
	        extentTest.skip(m);
	    }



		public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
			// TODO Auto-generated method stub
			
		}
	    

}
