package TestCases.TestTrialManufacture;

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

public class EnterTrialManufactureFlow2_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("849340788008550400");
outputtextList.get(dr).setTestCaseDesc("");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="MouseAction:点击立项流程链接",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step0(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="立项流程链接";
String byString= pageObject12.POString.get(testObject);
String testData ="RightClick";
outputtextList.get(dr).setMethodName("MouseAction：点击立项流程链接");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MouseAction(dr,byString,testData);
}
@Test(dependsOnMethods ="Step0",timeOut = 180000,description="waitingForClickOrInput:打开点击立项流程链接",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="打开立项流程链接";
String byString= pageObject12.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：打开点击立项流程链接");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="waitForAttributeContains:等待加载提示消失",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载页";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待加载提示消失");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
}
