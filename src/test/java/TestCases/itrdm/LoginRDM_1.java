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

public class LoginRDM_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("852575301094342656");
outputtextList.get(dr).setTestCaseDesc("");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="OpenURL:打开系统界面并登陆",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step0(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="http://10.16.40.197:8888";
outputtextList.get(dr).setMethodName("OpenURL：打开系统界面并登陆");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.OpenURL(dr,byString,testData);
}
@Test(dependsOnMethods ="Step0",timeOut = 180000,description="Input:输入用户名",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="账号";
String byString= pageObject18.POString.get(testObject);
String testData ="admin";
outputtextList.get(dr).setMethodName("Input：输入用户名");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Input(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="Input:输入密码",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="密码";
String byString= pageObject18.POString.get(testObject);
String testData ="ver";
outputtextList.get(dr).setMethodName("Input：输入密码");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Input(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="Click:点击登录按钮",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="登录";
String byString= pageObject18.POString.get(testObject);
String testData ="";
outputtextList.get(dr).setMethodName("Click：点击登录按钮");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Click(dr,byString,testData);
}
}
