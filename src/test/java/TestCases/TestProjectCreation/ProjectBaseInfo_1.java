package TestCases.TestProjectCreation;

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

public class ProjectBaseInfo_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("849340763501232128");
outputtextList.get(dr).setTestCaseDesc("");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="Input_RegExp:输入项目名称",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step0(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="项目名称";
String byString= pageObject7.POString.get(testObject);
String testData ="UI自动化测试项目test[1-9]{5}";
outputtextList.get(dr).setMethodName("Input_RegExp：输入项目名称");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Input_RegExp(dr,byString,testData);
}
@Test(dependsOnMethods ="Step0",timeOut = 180000,description="Buffer_Property:保存项目名称",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="项目名称";
String byString= pageObject7.POString.get(testObject);
String testData ="value;ProjectName";
outputtextList.get(dr).setMethodName("Buffer_Property：保存项目名称");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Buffer_Property(dr,byString,testData);
}
}
