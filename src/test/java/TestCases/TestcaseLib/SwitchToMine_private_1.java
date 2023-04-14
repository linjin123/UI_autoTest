package TestCases.TestcaseLib;

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

public class SwitchToMine_private_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("900314803615039488");
outputtextList.get(dr).setTestCaseDesc("切换到我创建的_个人用例");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="Buffer:保存用户名",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="当前用户名div";
String byString= pageObject30.POString.get(testObject);
String testData ="username";
outputtextList.get(dr).setMethodName("Buffer：保存用户名");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Buffer(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="waitingForClickOrInput:点击我创建的",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_我创建的";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击我创建的");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="waitForAttributeContains:等待可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="Verify_Contain:验证创建人为登录人",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库列表_创建人";
String byString= pageObject31.POString.get(testObject);
String testData ="title;{{username}}";
outputtextList.get(dr).setMethodName("Verify_Contain：验证创建人为登录人");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Verify_Contain(dr,byString,testData);
}
}
