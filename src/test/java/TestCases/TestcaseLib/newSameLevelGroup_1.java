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

public class newSameLevelGroup_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("900314798787395584");
outputtextList.get(dr).setTestCaseDesc("新增同级分组");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="waitingForClickOrInput:点击产品",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_产品输入框";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击产品");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="waitingForClickOrInput:输入产品名称",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_新建_归属产品_产品名称输入框";
String byString= pageObject31.POString.get(testObject);
String testData ="{{proName}}";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入产品名称");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_新建_归属产品_产品名称输入框";
String byString= pageObject31.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="waitingForClickOrInput:选择归属产品",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_归属产品弹窗第一条";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择归属产品");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step5(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="产品筛选_确定";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step5",timeOut = 180000,description="waitingForClickOrInput:点击箭头",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step6(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_分组箭头";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击箭头");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step6",timeOut = 180000,description="MoveToElement:鼠标移动到分组1",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step7(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_分组1菜单";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("MoveToElement：鼠标移动到分组1");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MoveToElement(dr,byString,testData);
}
@Test(dependsOnMethods ="Step7",timeOut = 180000,description="WaitOn:等待2秒",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step8(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="2000";
outputtextList.get(dr).setMethodName("WaitOn：等待2秒");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.WaitOn(dr,byString,testData);
}
@Test(dependsOnMethods ="Step8",timeOut = 180000,description="MoveToElement:鼠标移动到…",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step9(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_新建同级分组菜单";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("MoveToElement：鼠标移动到…");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MoveToElement(dr,byString,testData);
}
@Test(dependsOnMethods ="Step9",timeOut = 180000,description="waitForObjectAppear:等待新增同级分组可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step10(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_新增同级分组";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待新增同级分组可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step10",timeOut = 180000,description="waitingForClickOrInput:点击同级新增分组",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step11(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_新增同级分组";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击同级新增分组");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step11",timeOut = 180000,description="waitingForClickOrInput:输入分组名称",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step12(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_新增分组输入框";
String byString= pageObject31.POString.get(testObject);
String testData ="分组2";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入分组名称");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step12",timeOut = 180000,description="waitingForClickOrInput:点击全部",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step13(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_全部模块";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击全部");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step13",timeOut = 180000,description="waitForAttributeContains:等待加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step14(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step14",timeOut = 180000,description="VerifyElementExistence:校验文本",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step15(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_分组2";
String byString= pageObject31.POString.get(testObject);
String testData ="true";
outputtextList.get(dr).setMethodName("VerifyElementExistence：校验文本");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.VerifyElementExistence(dr,byString,testData);
}
}
