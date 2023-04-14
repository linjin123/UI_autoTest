package TestCases.TestProjectCreation;

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

public class EntercreateProjectPage_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("849340760133206016");
outputtextList.get(dr).setTestCaseDesc("");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="MouseAction:点击项目管理菜单",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step0(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="项目管理";
String byString= pageObject2.POString.get(testObject);
String testData ="MoveToElement";
outputtextList.get(dr).setMethodName("MouseAction：点击项目管理菜单");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MouseAction(dr,byString,testData);
}
@Test(dependsOnMethods ="Step0",timeOut = 180000,description="waitingForClickOrInput:点击立项管理菜单",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="立项管理";
String byString= pageObject2.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击立项管理菜单");
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
