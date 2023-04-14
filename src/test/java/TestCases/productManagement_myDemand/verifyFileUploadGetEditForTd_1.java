package TestCases.productManagement_myDemand;

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

public class verifyFileUploadGetEditForTd_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("887658240052035584");
outputtextList.get(dr).setTestCaseDesc("需求管理_校验跳转技术文档编辑页");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="SwitchToNumWindow:",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="2";
outputtextList.get(dr).setMethodName("SwitchToNumWindow：");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.SwitchToNumWindow(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="Verify_Contain:跳转编辑页",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑页内容";
String byString= pageObject23.POString.get(testObject);
String testData ="IT项目Git管理规程";
outputtextList.get(dr).setMethodName("Verify_Contain：跳转编辑页");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Verify_Contain(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="SwitchToFrame:切换iframe",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="切换文件编辑iframe";
String byString= pageObject23.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("SwitchToFrame：切换iframe");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.SwitchToFrame(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="waitForObjectAppear:等待编辑主题元素加载完成",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="编辑主题2";
String byString= pageObject23.POString.get(testObject);
String testData ="1";
outputtextList.get(dr).setMethodName("waitForObjectAppear：等待编辑主题元素加载完成");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForObjectAppear(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="Click:编辑文件",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step5(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="清除格式";
String byString= pageObject23.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("Click：编辑文件");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Click(dr,byString,testData);
}
@Test(dependsOnMethods ="Step5",timeOut = 180000,description="SwitchOutOfFrame:跳出iframe",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step6(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="";
outputtextList.get(dr).setMethodName("SwitchOutOfFrame：跳出iframe");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.SwitchOutOfFrame(dr,byString,testData);
}
@Test(dependsOnMethods ="Step6",timeOut = 180000,description="Click:保存",alwaysRun = true)
@Parameters({"driverID","paramGroup"})
public void Step7(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="保存按钮";
String byString= pageObject23.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("Click：保存");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Click(dr,byString,testData);
}
}
