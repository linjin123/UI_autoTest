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

public class addTestcase_public_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("900314807100506112");
outputtextList.get(dr).setTestCaseDesc("新建用例_公共用例");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="inputTimestamp:输入名称",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑页标题0";
String byString= pageObject6.POString.get(testObject);
String testData ="自动化新增用例";
outputtextList.get(dr).setMethodName("inputTimestamp：输入名称");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.inputTimestamp(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="BufferInputValue:设置用例名称变量",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑页标题0";
String byString= pageObject6.POString.get(testObject);
String testData ="TestcaseNamePub";
outputtextList.get(dr).setMethodName("BufferInputValue：设置用例名称变量");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.BufferInputValue(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="waitingForClickOrInput:点击保存",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="保存";
String byString= pageObject28.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击保存");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="waitForObjectAppear:等待成功提示出现",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="保存成功提示";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待成功提示出现");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="VerifyElementExistence:验证成功提示",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step5(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="保存成功提示";
String byString= pageObject31.POString.get(testObject);
String testData ="true";
outputtextList.get(dr).setMethodName("VerifyElementExistence：验证成功提示");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.VerifyElementExistence(dr,byString,testData);
}
@Test(dependsOnMethods ="Step5",timeOut = 180000,description="waitForAttributeContains:等待可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step6(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step6",timeOut = 180000,description="ScrollLeft:生命阶段可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step7(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表";
String byString= pageObject6.POString.get(testObject);
String testData ="2000";
outputtextList.get(dr).setMethodName("ScrollLeft：生命阶段可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.ScrollLeft(dr,byString,testData);
}
@Test(dependsOnMethods ="Step7",timeOut = 180000,description="MouseAction:点击生命阶段",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step8(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库列表_生命阶段";
String byString= pageObject31.POString.get(testObject);
String testData ="LeftClick";
outputtextList.get(dr).setMethodName("MouseAction：点击生命阶段");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MouseAction(dr,byString,testData);
}
@Test(dependsOnMethods ="Step8",timeOut = 180000,description="waitingForClickOrInput:输入生命阶段",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step9(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉输入";
String byString= pageObject6.POString.get(testObject);
String testData ="草稿";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入生命阶段");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step9",timeOut = 180000,description="waitingForClickOrInput:选中生命阶段",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step10(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉点击";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选中生命阶段");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step10",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step11(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="生命阶段确定按钮";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step11",timeOut = 180000,description="ScrollLeft:名称框可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step12(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表";
String byString= pageObject6.POString.get(testObject);
String testData ="0";
outputtextList.get(dr).setMethodName("ScrollLeft：名称框可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.ScrollLeft(dr,byString,testData);
}
@Test(dependsOnMethods ="Step12",timeOut = 180000,description="filterByTitle:输入用例名称查询",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step13(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表标题";
String byString= pageObject6.POString.get(testObject);
String testData ="{{TestcaseNamePub}};名称;.el-table__header th:not(.is-hidden):nth-child";
outputtextList.get(dr).setMethodName("filterByTitle：输入用例名称查询");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.filterByTitle(dr,byString,testData);
}
@Test(dependsOnMethods ="Step13",timeOut = 180000,description="waitForAttributeContains:等待可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step14(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step14",timeOut = 180000,description="waitingForClickOrInput:进入用例详情",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step15(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_用例名称列";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：进入用例详情");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step15",timeOut = 180000,description="waitingForClickOrInput:点击用例类型",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step16(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑页点击空2";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击用例类型");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step16",timeOut = 180000,description="waitingForClickOrInput:输入用例类型",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step17(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉输入";
String byString= pageObject6.POString.get(testObject);
String testData ="功能测试";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入用例类型");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step17",timeOut = 180000,description="waitingForClickOrInput:选择用例类型",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step18(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉点击";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择用例类型");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step18",timeOut = 180000,description="waitingForClickOrInput:点击归属产品",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step19(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑页点击空3";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击归属产品");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step19",timeOut = 180000,description="waitingForClickOrInput:输入产品名称",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step20(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_新建_归属产品_产品名称输入框";
String byString= pageObject31.POString.get(testObject);
String testData ="{{proName}}";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入产品名称");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step20",timeOut = 180000,description="KeyboardOperation:回车",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step21(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_新建_归属产品_产品名称输入框";
String byString= pageObject31.POString.get(testObject);
String testData ="Enter";
outputtextList.get(dr).setMethodName("KeyboardOperation：回车");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step21",timeOut = 180000,description="KeyboardOperation:点击tab",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step22(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_新建_归属产品_产品名称输入框";
String byString= pageObject31.POString.get(testObject);
String testData ="Tab";
outputtextList.get(dr).setMethodName("KeyboardOperation：点击tab");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.KeyboardOperation(dr,byString,testData);
}
@Test(dependsOnMethods ="Step22",timeOut = 180000,description="waitingForClickOrInput:选择归属产品",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step23(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库_归属产品弹窗第一条";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择归属产品");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step23",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step24(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="新增用例_归属产品_确定";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step24",timeOut = 180000,description="waitingForClickOrInput:输入功能描述",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step25(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="新增用例_功能描述";
String byString= pageObject31.POString.get(testObject);
String testData ="功能描述";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入功能描述");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step25",timeOut = 180000,description="waitingForClickOrInput:输入前置条件",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step26(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="新增用例_前置条件";
String byString= pageObject31.POString.get(testObject);
String testData ="前置条件";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入前置条件");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step26",timeOut = 180000,description="WaitOn:等待1秒",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step27(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="1000";
outputtextList.get(dr).setMethodName("WaitOn：等待1秒");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.WaitOn(dr,byString,testData);
}
@Test(dependsOnMethods ="Step27",timeOut = 180000,description="Input:输入步骤描述",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step28(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="新增用例_步骤描述";
String byString= pageObject31.POString.get(testObject);
String testData ="步骤描述";
outputtextList.get(dr).setMethodName("Input：输入步骤描述");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Input(dr,byString,testData);
}
@Test(dependsOnMethods ="Step28",timeOut = 180000,description="waitingForClickOrInput:输入预期结果",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step29(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="新增用例_预期结果";
String byString= pageObject31.POString.get(testObject);
String testData ="预期结果";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入预期结果");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step29",timeOut = 180000,description="waitingForClickOrInput:点击优先级",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step30(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="新增用例_优先级";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击优先级");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step30",timeOut = 180000,description="waitingForClickOrInput:选择优先级",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step31(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="新增个人用例_优先级第一条";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择优先级");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step31",timeOut = 180000,description="waitingForClickOrInput:点击发布",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step32(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="发布3";
String byString= pageObject28.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击发布");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step32",timeOut = 180000,description="waitForObjectAppear:等待跳转",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step33(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="管理员";
String byString= pageObject22.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待跳转");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step33",timeOut = 180000,description="waitForAttributeContains:等待加载",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step34(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待加载");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step34",timeOut = 180000,description="ScrollLeft:生命阶段可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step35(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表";
String byString= pageObject6.POString.get(testObject);
String testData ="2000";
outputtextList.get(dr).setMethodName("ScrollLeft：生命阶段可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.ScrollLeft(dr,byString,testData);
}
@Test(dependsOnMethods ="Step35",timeOut = 180000,description="MouseAction:点击生命阶段",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step36(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="用例库列表_生命阶段";
String byString= pageObject31.POString.get(testObject);
String testData ="LeftClick";
outputtextList.get(dr).setMethodName("MouseAction：点击生命阶段");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.MouseAction(dr,byString,testData);
}
@Test(dependsOnMethods ="Step36",timeOut = 180000,description="waitingForClickOrInput:输入生命阶段",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step37(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉输入";
String byString= pageObject6.POString.get(testObject);
String testData ="草稿";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入生命阶段");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step37",timeOut = 180000,description="waitingForClickOrInput:选中生命阶段",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step38(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉点击";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选中生命阶段");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step38",timeOut = 180000,description="waitingForClickOrInput:点击确定",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step39(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="生命阶段确定按钮";
String byString= pageObject31.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确定");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step39",timeOut = 180000,description="WaitOn:等待2秒",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step40(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="2000";
outputtextList.get(dr).setMethodName("WaitOn：等待2秒");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.WaitOn(dr,byString,testData);
}
@Test(dependsOnMethods ="Step40",timeOut = 180000,description="Verify_Contain:校验文本",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step41(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表第一条";
String byString= pageObject6.POString.get(testObject);
String testData ="发布";
outputtextList.get(dr).setMethodName("Verify_Contain：校验文本");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Verify_Contain(dr,byString,testData);
}
}
