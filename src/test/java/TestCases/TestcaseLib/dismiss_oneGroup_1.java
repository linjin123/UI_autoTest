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

public class dismiss_oneGroup_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("900314796891570176");
outputtextList.get(dr).setTestCaseDesc("解散某个分组");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="waitingForClickOrInput:点击箭头",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step0(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_分组箭头";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击箭头");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step0",timeOut = 180000,description="MoveToElement:鼠标移动到新增分组1",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_分组1";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("MoveToElement：鼠标移动到新增分组1");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MoveToElement(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="WaitOn:等待2秒",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_分组1";
String byString= pageObject31.POString.get(testObject);
String testData ="2000";
outputtextList.get(dr).setMethodName("WaitOn：等待2秒");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.WaitOn(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="MoveToElement:鼠标移动到…",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_新建同级分组菜单";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("MoveToElement：鼠标移动到…");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MoveToElement(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="waitForObjectAppear:等待解散该分组可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_解散该分组";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待解散该分组可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="waitingForClickOrInput:点击解散该分组",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step5(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_解散该分组";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击解散该分组");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step5",timeOut = 180000,description="waitForAttributeContains:等待加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step6(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step6",timeOut = 180000,description="VerifyElementExistence:校验文本",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step7(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_分组1";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("VerifyElementExistence：校验文本");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.VerifyElementExistence(dr,byString,testData);
}
}
