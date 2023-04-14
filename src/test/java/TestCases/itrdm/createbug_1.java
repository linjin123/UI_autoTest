package TestCases.itrdm;

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

public class createbug_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("852575300809129984");
outputtextList.get(dr).setTestCaseDesc("");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="waitForElementToBeClickable:",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷登记按钮";
String byString= pageObject19.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForElementToBeClickable：");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForElementToBeClickable(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="Click:",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷登记按钮";
String byString= pageObject19.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("Click：");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Click(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="WaitOn:",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="3000";
outputtextList.get(dr).setMethodName("WaitOn：");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.WaitOn(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="SwitchToWindowByTitle:切换到缺陷登记窗口",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="缺陷登记";
outputtextList.get(dr).setMethodName("SwitchToWindowByTitle：切换到缺陷登记窗口");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.SwitchToWindowByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="SwitchToFrame:",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step5(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷描述iframe";
String byString= pageObject20.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("SwitchToFrame：");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.SwitchToFrame(dr,byString,testData);
}
@Test(dependsOnMethods ="Step5",timeOut = 180000,description="WaitOn:等待3秒",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step6(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="3000";
outputtextList.get(dr).setMethodName("WaitOn：等待3秒");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.WaitOn(dr,byString,testData);
}
@Test(dependsOnMethods ="Step6",timeOut = 180000,description="Input:",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step7(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷描述输入";
String byString= pageObject20.POString.get(testObject);
String testData ="test2222222222";
outputtextList.get(dr).setMethodName("Input：");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Input(dr,byString,testData);
}
@Test(dependsOnMethods ="Step7",timeOut = 180000,description="Input:",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step8(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷描述输入";
String byString= pageObject20.POString.get(testObject);
String testData ="okkokkkkkkk";
outputtextList.get(dr).setMethodName("Input：");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Input(dr,byString,testData);
}
}
