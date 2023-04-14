package TestCases.TestTrialProduction;

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

public class RequirementInformation_2 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("849340813275037696");
outputtextList.get(dr).setTestCaseDesc("");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="ScrollIntoView:滚动至库存组织",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="库存组织";
String byString= pageObject13.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("ScrollIntoView：滚动至库存组织");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.ScrollIntoView(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="waitingForClickOrInput:点击库存组织",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="库存组织";
String byString= pageObject13.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击库存组织");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="waitingForClickOrInput:选择库存组织",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="库存组织选择";
String byString= pageObject13.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择库存组织");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="ScrollLeft:滚动至需求数",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="其他需求样机的用途去向";
String byString= pageObject10.POString.get(testObject);
String testData ="400";
outputtextList.get(dr).setMethodName("ScrollLeft：滚动至需求数");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.ScrollLeft(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="clickOrInputIfExist:输入测试需求数",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step6(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="测试需求";
String byString= pageObject13.POString.get(testObject);
String testData ="1";
outputtextList.get(dr).setMethodName("clickOrInputIfExist：输入测试需求数");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.clickOrInputIfExist(dr,byString,testData);
}
@Test(dependsOnMethods ="Step6",timeOut = 180000,description="clickOrInputIfExist:输入其他需求数",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step7(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="其他需求";
String byString= pageObject13.POString.get(testObject);
String testData ="1";
outputtextList.get(dr).setMethodName("clickOrInputIfExist：输入其他需求数");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.clickOrInputIfExist(dr,byString,testData);
}
@Test(dependsOnMethods ="Step7",timeOut = 180000,description="clickOrInputIfExist:输入需求样机",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step8(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="其他需求样机用途";
String byString= pageObject10.POString.get(testObject);
String testData ="随便用";
outputtextList.get(dr).setMethodName("clickOrInputIfExist：输入需求样机");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.clickOrInputIfExist(dr,byString,testData);
}
@Test(dependsOnMethods ="Step8",timeOut = 180000,description="clickOrInputIfExist:输入样机需求数量",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step9(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="样机需求数量";
String byString= pageObject10.POString.get(testObject);
String testData ="1";
outputtextList.get(dr).setMethodName("clickOrInputIfExist：输入样机需求数量");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.clickOrInputIfExist(dr,byString,testData);
}
@Test(dependsOnMethods ="Step9",timeOut = 180000,description="waitingForClickOrInput:输入样机需求用途",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step10(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="样机需求用途";
String byString= pageObject10.POString.get(testObject);
String testData ="测试";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入样机需求用途");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step10",timeOut = 180000,description="waitingForClickOrInput:输入样机需求人",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step11(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="样机需求人";
String byString= pageObject10.POString.get(testObject);
String testData ="linjin4";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入样机需求人");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step11",timeOut = 180000,description="waitingForClickOrInput:弹出选中",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step12(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：弹出选中");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
}
