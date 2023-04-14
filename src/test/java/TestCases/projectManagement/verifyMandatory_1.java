package TestCases.projectManagement;

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

public class verifyMandatory_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("887658266425819136");
outputtextList.get(dr).setTestCaseDesc("验证必填项");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="VerifyElementExistence:验证必填项",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step0(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="必填项校验";
String byString= pageObject25.POString.get(testObject);
String testData ="true";
outputtextList.get(dr).setMethodName("VerifyElementExistence：验证必填项");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.VerifyElementExistence(dr,byString,testData);
}
@Test(dependsOnMethods ="Step0",timeOut = 180000,description="inputTimestamp:输入项目名称",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑页标题0";
String byString= pageObject6.POString.get(testObject);
String testData ="自动化项目新建";
outputtextList.get(dr).setMethodName("inputTimestamp：输入项目名称");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.inputTimestamp(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="BufferInputValue:设置项目名称变量",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑页标题0";
String byString= pageObject6.POString.get(testObject);
String testData ="ProjectName";
outputtextList.get(dr).setMethodName("BufferInputValue：设置项目名称变量");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.BufferInputValue(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="waitingForClickOrInput:输入项目等级",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑页点击空4";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目等级");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="waitingForClickOrInput:输入项目等级",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉输入";
String byString= pageObject6.POString.get(testObject);
String testData ="T";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目等级");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="waitingForClickOrInput:输入项目等级",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step5(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉点击";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目等级");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step5",timeOut = 180000,description="waitingForClickOrInput:输入计划模板",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step6(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑页点击空5";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入计划模板");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step6",timeOut = 180000,description="waitingForClickOrInput:输入计划模板",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step7(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉输入";
String byString= pageObject6.POString.get(testObject);
String testData ="技术与平台开发_通用项目管理计划模板_T类";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入计划模板");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step7",timeOut = 180000,description="waitingForClickOrInput:输入计划模板",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step8(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下拉点击";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入计划模板");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step8",timeOut = 180000,description="waitingForClickOrInput:输入项目背景与目的",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step9(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="富文本编辑1";
String byString= pageObject6.POString.get(testObject);
String testData ="项目背景与目的";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目背景与目的");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step9",timeOut = 180000,description="waitingForClickOrInput:输入项目范围",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step10(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="富文本编辑2";
String byString= pageObject6.POString.get(testObject);
String testData ="项目范围";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目范围");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step10",timeOut = 180000,description="ScrollIntoView:滚动到里程碑M1",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step11(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="里程碑M1";
String byString= pageObject25.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("ScrollIntoView：滚动到里程碑M1");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.ScrollIntoView(dr,byString,testData);
}
@Test(dependsOnMethods ="Step11",timeOut = 180000,description="waitingForClickOrInput:点击M1:项目立项",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step12(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="里程碑M1";
String byString= pageObject25.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击M1:项目立项");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step12",timeOut = 180000,description="waitingForClickOrInput:选择下一个月",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step13(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="日期选择_月";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择下一个月");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step13",timeOut = 180000,description="waitingForClickOrInput:选择日期",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step14(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="日期选择_天";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择日期");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step14",timeOut = 180000,description="waitingForClickOrInput:点击M2:计划阶段评审",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step15(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="里程碑M2";
String byString= pageObject25.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击M2:计划阶段评审");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step15",timeOut = 180000,description="waitingForClickOrInput:选择下一个月",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step16(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="日期选择_月";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择下一个月");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step16",timeOut = 180000,description="waitingForClickOrInput:选择日期",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step17(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="日期选择_天";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择日期");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step17",timeOut = 180000,description="waitingForClickOrInput:点击M3:验收阶段评审",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step18(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="里程碑M3";
String byString= pageObject25.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击M3:验收阶段评审");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step18",timeOut = 180000,description="waitingForClickOrInput:选择下一个月",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step19(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="日期选择_月";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择下一个月");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step19",timeOut = 180000,description="waitingForClickOrInput:选择日期",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step20(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="日期选择_天";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择日期");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step20",timeOut = 180000,description="waitingForClickOrInput:点击M4:项目结项评审",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step21(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="里程碑M4";
String byString= pageObject25.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击M4:项目结项评审");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step21",timeOut = 180000,description="waitingForClickOrInput:选择下一个月",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step22(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="日期选择_月";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择下一个月");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step22",timeOut = 180000,description="waitingForClickOrInput:选择日期",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step23(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="日期选择_天";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：选择日期");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step23",timeOut = 180000,description="waitingForClickOrInput:输入项目经理",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step24(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="项目经理成员";
String byString= pageObject25.POString.get(testObject);
String testData ="kongjx";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目经理");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step24",timeOut = 180000,description="waitingForClickOrInput:输入项目经理",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step25(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目经理");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step25",timeOut = 180000,description="waitingForClickOrInput:输入结构开发工程师",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step26(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="结构开发工程师";
String byString= pageObject25.POString.get(testObject);
String testData ="caiyj4";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入结构开发工程师");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step26",timeOut = 180000,description="waitingForClickOrInput:输入结构开发工程师",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step27(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入结构开发工程师");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step27",timeOut = 180000,description="waitingForClickOrInput:输入硬件开发工程师",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step28(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="硬件开发工程师";
String byString= pageObject25.POString.get(testObject);
String testData ="xuyi16";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入硬件开发工程师");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step28",timeOut = 180000,description="waitingForClickOrInput:输入硬件开发工程师",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step29(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入硬件开发工程师");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step29",timeOut = 180000,description="waitingForClickOrInput:输入项目管理工程师",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step30(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="项目管理工程师";
String byString= pageObject25.POString.get(testObject);
String testData ="xudan18";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目管理工程师");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step30",timeOut = 180000,description="waitingForClickOrInput:输入项目管理工程师",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step31(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目管理工程师");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step31",timeOut = 180000,description="waitingForClickOrInput:输入项目成员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step32(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="项目成员";
String byString= pageObject25.POString.get(testObject);
String testData ="chuxj2";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目成员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step32",timeOut = 180000,description="waitingForClickOrInput:输入项目成员",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step33(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目成员");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step33",timeOut = 180000,description="waitingForClickOrInput:输入品质测试代表",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step34(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="品质测试代表";
String byString= pageObject25.POString.get(testObject);
String testData ="chimy1";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入品质测试代表");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step34",timeOut = 180000,description="waitingForClickOrInput:输入品质测试代表",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step35(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入品质测试代表");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step35",timeOut = 180000,description="waitingForClickOrInput:输入软件开发工程师",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step36(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="软件开发工程师";
String byString= pageObject25.POString.get(testObject);
String testData ="chuxj2";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入软件开发工程师");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step36",timeOut = 180000,description="waitingForClickOrInput:输入软件开发工程师",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step37(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入软件开发工程师");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step37",timeOut = 180000,description="waitingForClickOrInput:点击下一步",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step38(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="下一步按钮2";
String byString= pageObject25.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击下一步");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step38",timeOut = 180000,description="waitingForClickOrInput:输入项目经理审批",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step39(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="流程节点审批1";
String byString= pageObject6.POString.get(testObject);
String testData ="kongjx";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目经理审批");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step39",timeOut = 180000,description="waitingForClickOrInput:输入项目经理审批",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step40(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入项目经理审批");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step40",timeOut = 180000,description="waitingForClickOrInput:输入PMO审批",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step41(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="流程节点审批2";
String byString= pageObject6.POString.get(testObject);
String testData ="chimy1";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入PMO审批");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step41",timeOut = 180000,description="waitingForClickOrInput:输入PMO审批",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step42(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入PMO审批");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step42",timeOut = 180000,description="waitingForClickOrInput:输入部门长",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step43(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="流程节点审批3";
String byString= pageObject6.POString.get(testObject);
String testData ="kongjx";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入部门长");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step43",timeOut = 180000,description="waitingForClickOrInput:输入部门长",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step44(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入部门长");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step44",timeOut = 180000,description="waitingForClickOrInput:输入副总裁",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step45(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="流程节点审批4";
String byString= pageObject6.POString.get(testObject);
String testData ="xuyi16";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入副总裁");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step45",timeOut = 180000,description="waitingForClickOrInput:输入副总裁",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step46(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入副总裁");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step46",timeOut = 180000,description="waitingForClickOrInput:输入总裁",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step47(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="流程节点审批5";
String byString= pageObject6.POString.get(testObject);
String testData ="chuxj2";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入总裁");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step47",timeOut = 180000,description="waitingForClickOrInput:输入总裁",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step48(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="弹出选中";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：输入总裁");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step48",timeOut = 180000,description="waitingForClickOrInput:点击提交",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step49(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="提交按钮";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击提交");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
@Test(dependsOnMethods ="Step49",timeOut = 180000,description="waitingForClickOrInput:点击确认按钮",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step50(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="提交确定按钮";
String byString= pageObject6.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("waitingForClickOrInput：点击确认按钮");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitingForClickOrInput(dr,byString,testData);
}
}
