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

public class verifyFileUploadEdit_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("887658239125094400");
outputtextList.get(dr).setTestCaseDesc("需求管理_保存成功校验");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="Verify_Contain:编辑校验",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step0(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="保存校验";
String byString= pageObject23.POString.get(testObject);
String testData ="保存成功";
outputtextList.get(dr).setMethodName("Verify_Contain：编辑校验");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Verify_Contain(dr,byString,testData);
}
@Test(dependsOnMethods ="Step0",timeOut = 180000,description="CurrentWindowClose:关闭页面",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="";
outputtextList.get(dr).setMethodName("CurrentWindowClose：关闭页面");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.CurrentWindowClose(dr,byString,testData);
}
}
