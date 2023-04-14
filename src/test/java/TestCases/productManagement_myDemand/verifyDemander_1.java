package TestCases.productManagement_myDemand;

import Framework.ApplicationActions;
import Framework.Keywords;
import Framework.TestSetup;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import PageObjects.*;
import org.testng.annotations.*;
import static Framework.GlobalVariable.*;
import org.testng.Reporter;
import Framework.ExcelUtils;

public class verifyDemander_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("887658255755509760");
outputtextList.get(dr).setTestCaseDesc("需求管理_校验需求方");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="Verify_Equal:校验需求方",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step0(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="需求方";
String byString= pageObject23.POString.get(testObject);
String testData ="value;IoT";
outputtextList.get(dr).setMethodName("Verify_Equal：校验需求方");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Verify_Equal(dr,byString,testData);
}
}
