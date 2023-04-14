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

public class verifyAddProject_1 extends TestSetup{
private String TCParam="";
@BeforeTest(alwaysRun = true)
@Parameters({"driverID","skipOtherTCsWhenFailed"})
public void beforeTest(String dr,String skipOtherTCsWhenFailed) {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
outputtextList.get(dr).setTestCaseID("887658271186354176");
outputtextList.get(dr).setTestCaseDesc("数据校验");
outputtextList.get(dr).setSkipOtherTCsWhenFailed(Integer.parseInt(skipOtherTCsWhenFailed));
}
@Test(timeOut = 180000,description="waitForAttributeContains:等待可见",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step1(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="加载框";
String byString= pageObject6.POString.get(testObject);
String testData ="style;display: none";
outputtextList.get(dr).setMethodName("waitForAttributeContains：等待可见");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.waitForAttributeContains(dr,byString,testData);
}
@Test(dependsOnMethods ="Step1",timeOut = 180000,description="WaitOn:等待5s",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step2(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="5000";
outputtextList.get(dr).setMethodName("WaitOn：等待5s");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.WaitOn(dr,byString,testData);
}
@Test(dependsOnMethods ="Step2",timeOut = 180000,description="CurrentPageRefresh:刷新页面",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step3(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="";
outputtextList.get(dr).setMethodName("CurrentPageRefresh：刷新页面");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.CurrentPageRefresh(dr,byString,testData);
}
@Test(dependsOnMethods ="Step3",timeOut = 180000,description="WaitOn:等待5s",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step4(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String byString="";
String testData ="5000";
outputtextList.get(dr).setMethodName("WaitOn：等待5s");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.WaitOn(dr,byString,testData);
}
@Test(dependsOnMethods ="Step4",timeOut = 180000,description="Verify_AnyContain:校验文本",alwaysRun = false)
@Parameters({"driverID","paramGroup"})
public void Step5(String dr,String paramGroup) throws Exception {
dr= Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
String testObject="列表";
String byString= pageObject6.POString.get(testObject);
String testData ="{{ProjectName}};审核";
outputtextList.get(dr).setMethodName("Verify_AnyContain：校验文本");
outputtextList.get(dr).setScreenshot(true);
ApplicationActions.Verify_AnyContain(dr,byString,testData);
}
}
