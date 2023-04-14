package TestCases.TestMaterialRequisition;

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

public class TitleOfMaterialRequisition_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("849340729338626048");
outputtextList.get(dr).setTestCaseDesc("");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="Input_RegExp:输入标题",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step0(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="标题";
String byString= pageObject3.POString.get(testObject);
String testData ="UI自动化测试新建领用单test[1-9]{5}";
outputtextList.get(dr).setMethodName("Input_RegExp：输入标题");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Input_RegExp(dr,byString,testData);
}
@Test(dependsOnMethods ="Step0",timeOut = 180000,description="BufferInputValue:保存标题",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="标题";
String byString= pageObject3.POString.get(testObject);
String testData ="TitleName";
outputtextList.get(dr).setMethodName("BufferInputValue：保存标题");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.BufferInputValue(dr,byString,testData);
}
}
