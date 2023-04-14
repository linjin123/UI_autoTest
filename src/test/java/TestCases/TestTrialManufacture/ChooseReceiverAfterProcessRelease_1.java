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

public class ChooseReceiverAfterProcessRelease_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("849340783835217920");
outputtextList.get(dr).setTestCaseDesc("");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="MouseAction:双击流程发布后接收者",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step0(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="流程发布后接收者";
String byString= pageObject11.POString.get(testObject);
String testData ="DoubleClick";
outputtextList.get(dr).setMethodName("MouseAction：双击流程发布后接收者");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MouseAction(dr,byString,testData);
}
@Test(dependsOnMethods ="Step0",timeOut = 180000,description="waitingForClickOrInput:输入人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="人员搜索框";
String byString= pageObject11.POString.get(testObject);
String testData ="liuww1";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="waitingForClickOrInput:点击搜索",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="人员搜索框放大镜";
String byString= pageObject11.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击搜索");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="waitForAttributeContains:等待加载提示消失",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载页";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待加载提示消失");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="waitingForClickOrInput:选择人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="选择人员";
String byString= pageObject11.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="ScrollIntoView:滚动到确定按钮",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step5(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="确定";
String byString= pageObject11.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("ScrollIntoView：滚动到确定按钮");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.ScrollIntoView(dr,byString,testData);
}
@Test(dependsOnMethods ="Step5",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step6(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="确定";
String byString= pageObject11.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step6",timeOut = 180000,description="WaitOn:等待2秒",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step7(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="2000";
outputtextList.get(dr).setMethodName("WaitOn：等待2秒");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.WaitOn(dr,byString,testData);
}
}
