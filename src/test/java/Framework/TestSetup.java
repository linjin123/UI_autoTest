package Framework;

import java.io.IOException;
import java.net.MalformedURLException;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

import static Framework.GlobalVariable.*;
import static Framework.Keywords.*;
import static Framework.MyListener.PreviousTestStatus;


public class TestSetup {

    public static Map<String, WebDriver> drivers = new ConcurrentHashMap<String, WebDriver>();
    public static Map<String, ChromeDriverService> chromeservices = new ConcurrentHashMap<String, ChromeDriverService>();
    public static int startCount;
    public static int insCount = 1;
    public static int firstGenerated = 0;
    public static int isGenerated = 0;
    public static String lastExpPro = "";
    public static int Run_Count = 0;


    public static boolean DeleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = DeleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }


    @BeforeSuite
    @Parameters({"driverID","implicitlyWait","browser"})
    public void Setup(String dr,String implicitlyWaitStr,String br) throws Exception {
        dr = Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
        GV.put("browser",br);
        implicitlyWait = Integer.parseInt(implicitlyWaitStr);

        Keywords.OpenBrowser(dr, "", "");
    }

    @AfterSuite
    @Parameters({"driverID","closeBrowserFlag"})
    public void TearDown(String dr,String closeBrowserFlag) throws Exception {
        dr = Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
        DB_CloseConnection(dr, "", "");
        if (closeBrowserFlag.equals("true")) {
            drivers.get(dr).quit();
        }
    }


    @BeforeTest
    @Parameters({"driverID", "skipOtherTCsWhenFailed"})
    public void beforeTest(String dr, String skipOtherTCsWhenFailed) throws MalformedURLException {
        dr = Reporter.getCurrentTestResult().getTestContext().getSuite().getName();
        if (!PreviousTestStatus.equals("Pass")) {
            while (isAlertPresent(dr)) {
                drivers.get(dr).switchTo().alert().accept();
            }

        }
    }

    @AfterTest
    @Parameters({"driverID"})
    public void afterTest(String dr) {


    }

    @BeforeMethod
    @Parameters({"driverID"})
    public void beforemethod(String dr) {


    }
}
