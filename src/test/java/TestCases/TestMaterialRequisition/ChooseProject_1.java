package TestCases.TestMaterialRequisition;

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

public class ChooseProject_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("849340732878618624");
outputtextList.get(dr).setTestCaseDesc("");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="waitingForClickOrInput:选择点击项目",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="所属项目";
String byString= pageObject3.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择点击项目");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="KeyboardOperation:点击搜索",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="项目名称输入框";
String byString= pageObject3.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：点击搜索");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="waitingForClickOrInput:选择第一行",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step6(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="选择项目单选项目第一行";
String byString= pageObject3.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择第一行");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step6",timeOut = 180000,description="ScrollIntoView:点击确认",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step7(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="选择项目弹框确认按钮";
String byString= pageObject3.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("ScrollIntoView：点击确认");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.ScrollIntoView(dr,byString,testData);
}
@Test(dependsOnMethods ="Step7",timeOut = 180000,description="waitingForClickOrInput:点击确认",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step8(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="选择项目弹框确认按钮";
String byString= pageObject3.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确认");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
}
