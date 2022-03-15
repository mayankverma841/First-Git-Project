package Utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer {
	
	private int retryCount=0;
	private static int maxRetryCount=1;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (retryCount < RetryFailedTestCases.maxRetryCount) {
			TestListener.extent.removeTest(TestListener.extentTest.get());
			retryCount++;
            return true;
        }
        return false;
	}

}
