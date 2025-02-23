package org.petsFirst.api.utilts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class HTMLReport {
	 static ExtentReports extent;
	    static ExtentSparkReporter reporter;

	    public static ExtentReports getReporterObject() {
	        extent = new ExtentReports();
	        String reportsPath = System.getProperty("user.dir") + "/reports/testCasesResult";
	        reporter = new ExtentSparkReporter(reportsPath);
	        reporter.config().setReportName("YouCater Test Report");
	        reporter.config().setDocumentTitle("YouCater Test Report");
	        extent.attachReporter(reporter);
	        return extent;
	    }
}
