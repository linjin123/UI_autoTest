package TestCases.defectManagement;

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

public class NotIotShelvingProcess_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("887720604717809664");
outputtextList.get(dr).setTestCaseDesc("非Iot搁置流程");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="MoveToElement:点击管理员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="管理员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("MoveToElement：点击管理员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MoveToElement(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="waitForObjectAppear:等待模拟登录可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待模拟登录可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="waitingForClickOrInput:点击模拟登录",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击模拟登录");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="waitingForClickOrInput:输入搜索人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="xinyn";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入搜索人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step5(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step5",timeOut = 180000,description="waitingForClickOrInput:选中人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step6(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_选中人员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选中人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step6",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step7(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="确定";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step7",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step8(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step8",timeOut = 180000,description="filterByTitle:输入标题搜索",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step9(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="{{defecttitle4}};标题;.el-table__header th:nth-child";
outputtextList.get(dr).setMethodName("filterByTitle：输入标题搜索");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.filterByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step9",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step10(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step10",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step11(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step11",timeOut = 180000,description="clickByTitle:点击标题",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step12(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="标题;.el-table__body  tr:first-child>td:nth-child";
outputtextList.get(dr).setMethodName("clickByTitle：点击标题");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.clickByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step12",timeOut = 180000,description="waitingForClickOrInput:点击指派",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step13(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_指派";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击指派");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step13",timeOut = 180000,description="waitingForClickOrInput:点击优先级",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step14(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑页点击空3";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击优先级");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step14",timeOut = 180000,description="waitingForClickOrInput:输入优先级",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step15(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉输入";
String byString= pageObject6.POString.get(testObject);
String testData ="高";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入优先级");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step15",timeOut = 180000,description="waitingForClickOrInput:选择优先级",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step16(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉点击";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择优先级");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step16",timeOut = 180000,description="waitingForClickOrInput:输入备注",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step17(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_备注文本域";
String byString= pageObject29.POString.get(testObject);
String testData ="备注";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入备注");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step17",timeOut = 180000,description="waitingForClickOrInput:输入解决人",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step18(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_解决人";
String byString= pageObject29.POString.get(testObject);
String testData ="yanql";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入解决人");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step18",timeOut = 180000,description="waitingForClickOrInput:选中解决人",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step19(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选中解决人");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step19",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step20(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_指派确定";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step20",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step21(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step21",timeOut = 180000,description="waitForObjectAppear:等待页面跳转",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step22(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷管理_列表页面";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待页面跳转");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step22",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step23(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step23",timeOut = 180000,description="Verify_Contain:验证提交成功",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step24(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷管理_流程状态2";
String byString= pageObject29.POString.get(testObject);
String testData ="解决中";
outputtextList.get(dr).setMethodName("Verify_Contain：验证提交成功");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Verify_Contain(dr,byString,testData);
}
@Test(dependsOnMethods ="Step24",timeOut = 180000,description="MoveToElement:点击管理员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step25(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="管理员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("MoveToElement：点击管理员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MoveToElement(dr,byString,testData);
}
@Test(dependsOnMethods ="Step25",timeOut = 180000,description="waitForObjectAppear:等待模拟登录可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step26(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待模拟登录可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step26",timeOut = 180000,description="waitingForClickOrInput:点击模拟登录",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step27(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击模拟登录");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step27",timeOut = 180000,description="waitingForClickOrInput:输入搜索人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step28(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="yanql";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入搜索人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step28",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step29(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step29",timeOut = 180000,description="waitingForClickOrInput:选中人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step30(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_选中人员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选中人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step30",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step31(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="确定";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step31",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step32(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step32",timeOut = 180000,description="filterByTitle:输入标题搜索",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step33(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="{{defecttitle4}};标题;.el-table__header th:nth-child";
outputtextList.get(dr).setMethodName("filterByTitle：输入标题搜索");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.filterByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step33",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step34(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step34",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step35(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step35",timeOut = 180000,description="clickByTitle:点击标题",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step36(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="标题;.el-table__body  tr:first-child>td:nth-child";
outputtextList.get(dr).setMethodName("clickByTitle：点击标题");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.clickByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step36",timeOut = 180000,description="waitingForClickOrInput:点击搁置",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step37(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_搁置";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击搁置");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step37",timeOut = 180000,description="waitingForClickOrInput:输入搁置说明",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step38(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_备注文本域";
String byString= pageObject29.POString.get(testObject);
String testData ="搁置";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入搁置说明");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step38",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step39(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_搁置确定";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step39",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step40(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step40",timeOut = 180000,description="waitForObjectAppear:等待页面跳转",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step41(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷管理_列表页面";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待页面跳转");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step41",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step42(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step42",timeOut = 180000,description="Verify_Contain:验证缺陷状态正确",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step43(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷管理_流程状态2";
String byString= pageObject29.POString.get(testObject);
String testData ="搁置确认";
outputtextList.get(dr).setMethodName("Verify_Contain：验证缺陷状态正确");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Verify_Contain(dr,byString,testData);
}
@Test(dependsOnMethods ="Step43",timeOut = 180000,description="MoveToElement:点击管理员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step44(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="管理员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("MoveToElement：点击管理员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MoveToElement(dr,byString,testData);
}
@Test(dependsOnMethods ="Step44",timeOut = 180000,description="waitForObjectAppear:等待模拟登录可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step45(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待模拟登录可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step45",timeOut = 180000,description="waitingForClickOrInput:点击模拟登录",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step46(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击模拟登录");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step46",timeOut = 180000,description="waitingForClickOrInput:输入搜索人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step47(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="liyan9";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入搜索人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step47",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step48(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step48",timeOut = 180000,description="waitingForClickOrInput:选中人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step49(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_选中人员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选中人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step49",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step50(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="确定";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step50",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step51(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step51",timeOut = 180000,description="filterByTitle:输入标题搜索",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step52(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="{{defecttitle4}};标题;.el-table__header th:nth-child";
outputtextList.get(dr).setMethodName("filterByTitle：输入标题搜索");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.filterByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step52",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step53(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step53",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step54(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step54",timeOut = 180000,description="clickByTitle:点击标题",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step55(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="标题;.el-table__body  tr:first-child>td:nth-child";
outputtextList.get(dr).setMethodName("clickByTitle：点击标题");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.clickByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step55",timeOut = 180000,description="waitingForClickOrInput:点击确认搁置",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step56(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_确认搁置";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确认搁置");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step56",timeOut = 180000,description="waitingForClickOrInput:输入备注",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step57(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_备注文本域";
String byString= pageObject29.POString.get(testObject);
String testData ="搁置";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入备注");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step57",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step58(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_确认搁置确定";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step58",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step59(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step59",timeOut = 180000,description="MoveToElement:点击管理员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step60(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="管理员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("MoveToElement：点击管理员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MoveToElement(dr,byString,testData);
}
@Test(dependsOnMethods ="Step60",timeOut = 180000,description="waitForObjectAppear:等待模拟登录可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step61(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待模拟登录可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step61",timeOut = 180000,description="waitingForClickOrInput:点击模拟登录",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step62(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击模拟登录");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step62",timeOut = 180000,description="waitingForClickOrInput:输入搜索人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step63(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="xinyn";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入搜索人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step63",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step64(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step64",timeOut = 180000,description="waitingForClickOrInput:选中人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step65(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_选中人员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选中人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step65",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step66(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="确定";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step66",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step67(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step67",timeOut = 180000,description="filterByTitle:输入标题搜索",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step68(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="{{defecttitle4}};标题;.el-table__header th:nth-child";
outputtextList.get(dr).setMethodName("filterByTitle：输入标题搜索");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.filterByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step68",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step69(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step69",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step70(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step70",timeOut = 180000,description="clickByTitle:点击标题",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step71(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="标题;.el-table__body  tr:first-child>td:nth-child";
outputtextList.get(dr).setMethodName("clickByTitle：点击标题");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.clickByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step71",timeOut = 180000,description="waitingForClickOrInput:点击确认搁置",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step72(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_确认搁置";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确认搁置");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step72",timeOut = 180000,description="waitingForClickOrInput:输入备注",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step73(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_备注文本域";
String byString= pageObject29.POString.get(testObject);
String testData ="搁置";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入备注");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step73",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step74(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_确认搁置确定";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step74",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step75(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step75",timeOut = 180000,description="MoveToElement:点击管理员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step76(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="管理员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("MoveToElement：点击管理员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MoveToElement(dr,byString,testData);
}
@Test(dependsOnMethods ="Step76",timeOut = 180000,description="waitForObjectAppear:等待模拟登录可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step77(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待模拟登录可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step77",timeOut = 180000,description="waitingForClickOrInput:点击模拟登录",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step78(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击模拟登录");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step78",timeOut = 180000,description="waitingForClickOrInput:输入搜索人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step79(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="zhangxw13";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入搜索人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step79",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step80(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step80",timeOut = 180000,description="waitingForClickOrInput:选中人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step81(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_选中人员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选中人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step81",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step82(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="确定";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step82",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step83(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step83",timeOut = 180000,description="filterByTitle:输入标题搜索",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step84(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="{{defecttitle4}};标题;.el-table__header th:nth-child";
outputtextList.get(dr).setMethodName("filterByTitle：输入标题搜索");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.filterByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step84",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step85(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step85",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step86(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step86",timeOut = 180000,description="clickByTitle:点击标题",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step87(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="标题;.el-table__body  tr:first-child>td:nth-child";
outputtextList.get(dr).setMethodName("clickByTitle：点击标题");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.clickByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step87",timeOut = 180000,description="waitingForClickOrInput:点击确认搁置",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step88(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_确认搁置";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确认搁置");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step88",timeOut = 180000,description="waitingForClickOrInput:输入备注",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step89(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_备注文本域";
String byString= pageObject29.POString.get(testObject);
String testData ="搁置";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入备注");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step89",timeOut = 180000,description="waitingForClickOrInput:点击预计解决版本",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step90(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_预计解决版本";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击预计解决版本");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step90",timeOut = 180000,description="waitingForClickOrInput:输入预计解决版本",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step91(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉输入";
String byString= pageObject6.POString.get(testObject);
String testData ="uat";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入预计解决版本");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step91",timeOut = 180000,description="waitingForClickOrInput:选中预计解决版本",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step92(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉点击";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选中预计解决版本");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step92",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step93(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_确认搁置确定";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step93",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step94(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step94",timeOut = 180000,description="waitForObjectAppear:等待页面跳转",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step95(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷管理_列表页面";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待页面跳转");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step95",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step96(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step96",timeOut = 180000,description="MoveToElement:点击管理员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step97(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="管理员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("MoveToElement：点击管理员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MoveToElement(dr,byString,testData);
}
@Test(dependsOnMethods ="Step97",timeOut = 180000,description="waitForObjectAppear:等待模拟登录可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step98(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待模拟登录可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step98",timeOut = 180000,description="waitingForClickOrInput:点击模拟登录",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step99(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击模拟登录");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step99",timeOut = 180000,description="waitingForClickOrInput:输入搜索人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step100(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="liyan9";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入搜索人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step100",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step101(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_搜索人员";
String byString= pageObject22.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step101",timeOut = 180000,description="waitingForClickOrInput:选中人员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step102(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="模拟登录_选中人员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选中人员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step102",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step103(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="确定";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step103",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step104(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step104",timeOut = 180000,description="filterByTitle:输入标题搜索",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step105(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="{{defecttitle4}};标题;.el-table__header th:nth-child";
outputtextList.get(dr).setMethodName("filterByTitle：输入标题搜索");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.filterByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step105",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step106(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step106",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step107(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step107",timeOut = 180000,description="Verify_Contain:验证缺陷状态正确",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step108(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷管理_流程状态2";
String byString= pageObject29.POString.get(testObject);
String testData ="搁置";
outputtextList.get(dr).setMethodName("Verify_Contain：验证缺陷状态正确");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Verify_Contain(dr,byString,testData);
}
@Test(dependsOnMethods ="Step108",timeOut = 180000,description="clickByTitle:点击标题",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step109(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="标题;.el-table__body  tr:first-child>td:nth-child";
outputtextList.get(dr).setMethodName("clickByTitle：点击标题");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.clickByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step109",timeOut = 180000,description="waitingForClickOrInput:点击重新打开",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step110(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_重新打开";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击重新打开");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step110",timeOut = 180000,description="waitingForClickOrInput:输入备注",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step111(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_备注文本域";
String byString= pageObject29.POString.get(testObject);
String testData ="重新打开";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入备注");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step111",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step112(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="验证缺陷_重新打开确定";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step112",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step113(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step113",timeOut = 180000,description="waitForObjectAppear:等待页面跳转",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step114(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷管理_列表页面";
String byString= pageObject29.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待页面跳转");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step114",timeOut = 180000,description="waitForAttributeContains:等待页面加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step115(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待页面加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step115",timeOut = 180000,description="Verify_Contain:验证缺陷状态正确",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step116(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="缺陷管理_流程状态2";
String byString= pageObject29.POString.get(testObject);
String testData ="解决中";
outputtextList.get(dr).setMethodName("Verify_Contain：验证缺陷状态正确");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Verify_Contain(dr,byString,testData);
}
}
