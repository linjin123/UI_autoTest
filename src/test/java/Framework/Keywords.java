
package Framework;

import com.mifmif.common.regex.Generex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tools.ant.*;
import org.apache.tools.ant.taskdefs.*;
import org.apache.tools.ant.types.*;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import io.restassured.path.json.JsonPath;
import javax.imageio.ImageIO;

import static Framework.GlobalVariable.GV;
import static Framework.GlobalVariable.implicitlyWait;
import static Framework.GlobalVariable.outputtextList;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.testng.Assert.assertTrue;
import static org.testng.internal.Utils.copyFile;


public class Keywords extends TestSetup {
    private static Connection connection;
    private static SQLExec sqlExec = new SQLExec();
    protected static HSSFWorkbook workbook = null;

    protected static String suiteName;
    protected static String testName;
    protected static String testCaseDesc;
    protected static String stepNo;
    protected static String methodName;
    protected static String methodSTime;
    protected static String methodETime;

    private static String rootPath;

    //获取项目所在的根目录
    static public String getRootPath() {

        File file = new File("");
        rootPath = file.getAbsolutePath();
        return rootPath;
    }

    //根据给定的日期和天数计算出并返回新日期
    public static Date setDate(Date date, int days) {
        long time = date.getTime() / 1000 + 60 * 60 * 24 * days;
        Date newDate = new Date();
        newDate.setTime(time * 1000);
        return newDate;
    }

    //判断一个页面元素是否存在
    public static boolean isElementPresent(String dr, By by) {

        try {
            drivers.get(dr).manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            drivers.get(dr).findElement(by);
            drivers.get(dr).manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //判断有没有Alert弹出
    public static boolean isAlertPresent(String dr) {
        try {
            drivers.get(dr).switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    //判断元素是否可点击状态的
    public static String clickElement(WebElement elmt) {
        String status = "";
        int i = 0;
        for (; i < 8 & !status.equals("pass"); i++) {
            status = itemClickable(elmt);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
            }
        }
        if (i != 8) {
            status = "pass";
        }
        return status;
    }

    public static String itemClickable(WebElement elmt) {
        try {
            elmt.click();
            return "pass";
        } catch (WebDriverException e) {
            return e.toString();
        }
    }

    public static boolean itemClickable(String dr, By by) {
        try {
            drivers.get(dr).findElement(by).click();
            return true;
        } catch (ElementNotVisibleException e2) {
            return false;
        } catch (NoSuchElementException e3) {
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        } catch (ElementClickInterceptedException e) {
            return false;
        } catch (NotFoundException e) {
            return false;
        }
    }

    //判断input标签是否可以编辑并清除内容
    public static boolean itemEditable(WebElement elmt) {
        try {
            elmt.clear();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }


    //根据给定的日期格式返回当前时间
    public static String getCurrentTime(String dateFormat) {
        SimpleDateFormat formater = new SimpleDateFormat(dateFormat);
        Calendar calender = Calendar.getInstance();
        String currentTime = formater.format(calender.getTime());
        return currentTime;
    }

    //将某个字符串转化为指定格式的日期
    public Date StringToDateTime(String str, String dateFormat) {
        SimpleDateFormat defDateFormate = new SimpleDateFormat(dateFormat);
        Date tempDate = null;
        try {
            tempDate = defDateFormate.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tempDate;
    }

    //删除指定目录下的子文件夹和文件，包括该目录文件夹
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

    public static By getByObject(String byString) {
        try {
            byString = getDataFromVariable(byString);
            String byType = "";
            String byTypeValue = "";
            Map<String, By> byList = new HashMap<String, By>();

            if (byString.split(";;").length == 2) {
                byType = byString.split(";;")[0];
                byTypeValue = byString.split(";;")[1];

                byList.clear();
                byList.put("xpath", By.xpath(byTypeValue));
                byList.put("cssSelector", By.cssSelector(byTypeValue));
                byList.put("id", By.id(byTypeValue));
                byList.put("className", By.className(byTypeValue));
                byList.put("linkText", By.linkText(byTypeValue));
                byList.put("tagName", By.tagName(byTypeValue));
                byList.put("partialLinkText", By.partialLinkText(byTypeValue));

                return byList.get(byType);
            }
        } catch (WebDriverException e) {

        }
        return By.xpath(byString);
    }

    public static String filePath(String dr, String data) {
        data = getDataFromVariable(data);
        String filePath = "";
        try {
            String uploadFileDir = getRootPath() + "/TestData/uploadFile/";
            File file = new File(uploadFileDir);
            File[] files = file.listFiles();
            for (File f : files) {
                String temp = f.getAbsolutePath();
                int length = temp.split("/").length;
                temp = temp.split("/")[length - 1];
                if (temp.contains(data)) {
                    filePath = f.getAbsolutePath();
                    break;
                }
            }
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("获取文件路径异常" + e.toString());
            assertTrue(false);
            e.printStackTrace();
        }
        return filePath;
    }

    //鼠标移到指定元素
    public static void MoveToElement(String dr, String byString, String data) {
        String locateMode = byString.split(";;")[0];
        if ("AI图像识别".equals(locateMode)) {
            waitForObjectAppear1(dr, byString, String.valueOf(implicitlyWait));
            String imagePath = byString.split(";;")[2];
            File file = new File(imagePath + "\\1.png");
            if (file.exists()) {
                awtMouseMove(dr, imagePath + "\\1.png");
            } else {
                outputtextList.get(dr).setActualResult("图片不存在！！！");
                assertTrue(false);
            }
        } else {
            By by = getByObject(byString);
            try {
                new Actions(drivers.get(dr)).moveToElement(drivers.get(dr).findElement(by)).perform();
                outputtextList.get(dr).setActualResult("成功移到指定页面元素");
                assertTrue(true);
            } catch (WebDriverException e) {
                WaitOn(dr, "", data);
                new Actions(drivers.get(dr)).moveToElement(drivers.get(dr).findElement(by)).perform();
            }
        }
    }


    public static void OpenURL(String dr, String byString, String url) {
        try {
            url = getDataFromVariable(url);
            drivers.get(dr).get(url);
            outputtextList.get(dr).setActualResult("打开成功，URL地址为：" + url);
            assertTrue(true);
        } catch (NotFoundException e1) {
            outputtextList.get(dr).setActualResult("打开失败 - 请检URL格式是否正确!!!URL地址为：" + url + "，NotFoundException " + e1.toString());
            assertTrue(false);
        } catch (NullPointerException e2) {
            outputtextList.get(dr).setActualResult("url is null - 请检查是否填写URL地址!!!NullPointerException.");
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!UnhandledAlertException" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            if (e.toString().contains("cannot determine loading status from unknown error: unhandled inspector error: { code :-32000, message : Inspected target navigated or closed }")) {
                outputtextList.get(dr).setActualResult("打开成功，URL地址为：" + url);
                assertTrue(true);
            }
            outputtextList.get(dr).setActualResult("打开失败，URL地址为：" + url + "WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void NavigateURL(String dr, String byString, String url) {
        try {
            url = getDataFromVariable(url);
            drivers.get(dr).navigate().to(url);
            outputtextList.get(dr).setActualResult("打开成功");
            assertTrue(true);
        } catch (NotFoundException e1) {
            outputtextList.get(dr).setActualResult("打开失败 - 请检URL格式是否正确!!!NotFoundException " + e1.toString());
            assertTrue(false);
        } catch (NullPointerException e2) {
            outputtextList.get(dr).setActualResult("url is null - 请检查是否填写URL地址!!!NullPointerException.");
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!UnhandledAlertException" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            if (e.toString().contains("cannot determine loading status from unknown error: unhandled inspector error: { code :-32000, message : Inspected target navigated or closed }")) {
                outputtextList.get(dr).setActualResult("打开成功");
                assertTrue(true);
            }
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void SetCookie(String dr, String byString, String data) {
        try {

            Cookie c1 = new Cookie("KP_SECURITY_COOKIE_KEY", "03155b365e304bdb2f459053b55e087c3bb081ac4e72c57c4f90174040e0dfa775ba4e93fcdcb646c74dce33d6582450", "kunp.midea.com", "/", null);
            //Cookie c2 = new Cookie("_pk_ses.2.b07a", "*");

            drivers.get(dr).manage().addCookie(c1);
            //drivers.get(dr).manage().addCookie(c2);
            //drivers.get(dr).navigate().to(url);
            outputtextList.get(dr).setActualResult("打开成功");
            assertTrue(true);
        } catch (NotFoundException e1) {
            outputtextList.get(dr).setActualResult("打开失败:" + e1.toString());
            assertTrue(false);
        } catch (NullPointerException e2) {
            outputtextList.get(dr).setActualResult("url is null!");
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }

    }

    /**
     * 验证文本值是否相等
     *
     * @param dr
     * @param byString
     * @param data
     */
    public static void Verify_Text_Equal(String dr, String byString, String data) {
        By by = getByObject(byString);

        try {
            data = getDataFromVariable(data);
            MouseAction(dr, byString, "MoveToElement");
            String tempValue = drivers.get(dr).findElement(by).getText();
            if (tempValue.equals(data)) {
                outputtextList.get(dr).setActualResult("文本验证成功：期望值是：" + data + ";" + "实际值是： " + tempValue + " .");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("数据错误- 文本验证失败：期望值是：" + data + ";" + "实际值是: " + tempValue + " .");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Verify_Text_NotEqual(String dr, String byString, String data) {
        By by = getByObject(byString);

        try {

            data = getDataFromVariable(data);

            MouseAction(dr, byString, "MoveToElement");
            String tempValue = drivers.get(dr).findElement(by).getText();
            if (!tempValue.equals(data)) {
                outputtextList.get(dr).setActualResult("文本验证成功，结果为不相等：期望值是：" + data + ";" + "实际值是： " + tempValue + " .");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("数据错误- 文本验证失败，结果为相等：：期望值是：" + data + ";" + "实际值是: " + tempValue + " .");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    /**
     * 验证元素是否可用
     *
     * @param dr
     * @param byString
     * @param data
     */
    public static void Verify_Enabled(String dr, String byString, String data) {
        By by = getByObject(byString);

        try {

            if (drivers.get(dr).findElement(by).isEnabled() == Boolean.parseBoolean(data)) {

                outputtextList.get(dr).setActualResult("状态验证成功：Enabled期望值是：" + data + ";实际值是：" + drivers.get(dr).findElement(by).isEnabled() + ".");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("页面异常- 元素状态不对：Enabled期望值是：" + data + ";实际值是：" + drivers.get(dr).findElement(by).isEnabled() + ".");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void VerifyElementExistence(String dr, String byString, String data) {
        try {
            String locteMode = byString.split(";;")[0];
            if ("AI图像识别".equals(locteMode)) {
                String imagePath = byString.split(";;")[2];
                File file = new File(imagePath + "\\1.png");
                if (file.exists()) {
                    File src = awtFullScreetshot(dr);
                    Double y = getElementLeftCorePoint(dr, src.getPath(), imagePath + "\\1.png");
                    boolean flag = y > 100;
                    if (flag == Boolean.parseBoolean(data)) {
                        outputtextList.get(dr).setActualResult("状态验证成功：existence期望值是：" + data + ";实际值是：" + flag + ".");
                        assertTrue(true);
                    } else {
                        outputtextList.get(dr).setActualResult("状态验证失败：existence期望值是：" + data + ";实际值是：" + flag + ".");
                        assertTrue(false);
                    }
                } else {
                    outputtextList.get(dr).setActualResult("图片未上传，请检查！！！");
                    assertTrue(false);
                }
            } else {
                By by = getByObject(byString);
                if (isElementPresent(dr, by) == Boolean.parseBoolean(data)) {
                    outputtextList.get(dr).setActualResult("状态验证成功：existence期望值是：" + data + ";实际值是：" + isElementPresent(dr, by) + ".");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("页面异常- 元素状态不对：existence期望值是：" + data + ";实际值是：" + isElementPresent(dr, by) + ".");
                    assertTrue(false);
                }
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    /**
     * 验证元素是否存在
     *
     * @param dr
     * @param byString
     * @param data
     */
    public static void Verify_Displayed(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {

            if (drivers.get(dr).findElement(by).isDisplayed() == Boolean.parseBoolean(data)) {
                outputtextList.get(dr).setActualResult("可见性验证成功：Displayed期望值是：" + data + ";实际值是：" + drivers.get(dr).findElement(by).isDisplayed() + ".");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("页面异常- 元素状态不对：Displayed期望值是：" + data + ";实际值是：" + drivers.get(dr).findElement(by).isDisplayed() + ".");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    /**
     * 验证元素是否可选
     *
     * @param dr
     * @param byString
     * @param data
     */
    public static void Verify_Selected(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            MouseAction(dr, byString, "MoveToElement");
            if (drivers.get(dr).findElement(by).isSelected() == Boolean.parseBoolean(data)) {
                outputtextList.get(dr).setActualResult("选中状态验证成功：selected期望值是：" + data + " .");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("页面异常- 元素状态不对：Selected期望值是：" + data + ";实际值是：" + drivers.get(dr).findElement(by).isSelected() + ".");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    /**
     * 验证属性是否正确
     *
     * @param dr
     * @param byString
     * @param data
     */
    public static void Verify_Propery(String dr, String byString, String data) {
        By by = getByObject(byString);
        String[] testData = data.split(";");
        try {
            MouseAction(dr, byString, "MoveToElement");
            String tempValue = drivers.get(dr).findElement(by).getAttribute(testData[0]);
            if (tempValue.equals(testData[1])) {
                outputtextList.get(dr).setActualResult("属性值验证成功：" + testData[0] + "期望值是" + testData[1] + ";" + "实际值是： " + tempValue + " .");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("数据错误- 属性值验证失败：" + testData[0] + "期望值是" + testData[1] + ";" + "实际值是： " + tempValue + " .");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Verify_Equal(String dr, String byString, String data) {
        By by = getByObject(byString);

        String[] testData = data.split(";");
        try {
            MouseAction(dr, byString, "MoveToElement");
            if (testData.length == 2) {
                testData[1] = getDataFromVariable(testData[1]);

                if (testData[0].equals("Enabled")) {
                    if (drivers.get(dr).findElement(by).isEnabled() == Boolean.parseBoolean(testData[1])) {
                        outputtextList.get(dr).setActualResult("状态验证成功：Enabled期望值是：" + testData[1] + " .");
                        assertTrue(true);
                    } else {
                        outputtextList.get(dr).setActualResult("页面异常- 元素状态不对：Enabled期望值是：" + data + ";实际值是：" + drivers.get(dr).findElement(by).isEnabled() + ".");
                        assertTrue(false);
                    }
                } else if (testData[0].equals("Displayed")) {
                    if (drivers.get(dr).findElement(by).isDisplayed() == Boolean.parseBoolean(testData[1])) {
                        outputtextList.get(dr).setActualResult("可见性验证成功：Displayed期望值是：" + testData[1] + " .");
                        assertTrue(true);
                    } else {
                        outputtextList.get(dr).setActualResult("页面异常- 元素状态不对：Displayed期望值是：" + data + ";实际值是：" + drivers.get(dr).findElement(by).isDisplayed() + ".");
                        assertTrue(false);
                    }
                } else if (testData[0].equals("Selected")) {
                    if (drivers.get(dr).findElement(by).isSelected() == Boolean.parseBoolean(testData[1])) {
                        outputtextList.get(dr).setActualResult("选中状态验证成功：selected期望值是：" + testData[1] + " .");
                        assertTrue(true);
                    } else {
                        outputtextList.get(dr).setActualResult("页面异常- 元素状态不对：Selected期望值是：" + data + ";实际值是：" + drivers.get(dr).findElement(by).isSelected() + ".");
                        assertTrue(false);
                    }
                } else {

                    String tempValue = drivers.get(dr).findElement(by).getAttribute(testData[0]);
                    if (tempValue.equals(testData[1])) {
                        outputtextList.get(dr).setActualResult("属性值验证成功：" + testData[0] + "期望值是" + testData[1] + ";" + "实际值是： " + tempValue + " .");
                        assertTrue(true);
                    } else {
                        outputtextList.get(dr).setActualResult("数据错误- 属性值验证失败：" + testData[0] + "期望值是" + testData[1] + ";" + "实际值是： " + tempValue + " .");
                        assertTrue(false);
                    }
                }
            } else {
                testData[0] = getDataFromVariable(testData[0]);

                String tempValue = drivers.get(dr).findElement(by).getText();
                tempValue = tempValue.replaceAll("\n", "");

                if (tempValue.equals(testData[0])) {
                    outputtextList.get(dr).setActualResult("文本验证成功：期望值是：" + testData[0] + ";" + "实际值是： " + tempValue + " .");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("数据错误- 文本验证失败：期望值是：" + testData[0] + ";" + "实际值是: " + tempValue + " .");
                    assertTrue(false);
                }
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Verify_TagType(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {

            String tempValue = drivers.get(dr).findElement(by).getTagName();

            if (tempValue.equals(data)) {
                outputtextList.get(dr).setActualResult("验证成功：期望值是：" + data + ";" + "实际值是： " + tempValue + " .");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("数据错误- 验证失败：期望值是：" + data + ";" + "实际值是: " + tempValue + ".");
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void Verify_Contain(String dr, String byString, String data) {
        By by = getByObject(byString);
        String[] testData = data.split(";");
        try {
            MouseAction(dr, byString, "MoveToElement");
            if (testData.length == 2) {
                testData[1] = getDataFromVariable(testData[1]);

                String tempValue = drivers.get(dr).findElement(by).getAttribute(testData[0]);
                if (tempValue.contains(testData[1])) {
                    outputtextList.get(dr).setActualResult("属性值验证成功：" + testData[0] + "期望值是" + testData[1] + ";" + "实际值是： " + tempValue + " .");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("数据错误- 属性值验证失败：" + testData[0] + "期望值是" + testData[1] + ";" + "实际值是： " + tempValue + " .");
                    assertTrue(false);
                }

            } else {
                testData[0] = getDataFromVariable(testData[0]);

                String tempValue = drivers.get(dr).findElement(by).getText();

                if (tempValue.contains(testData[0])) {
                    outputtextList.get(dr).setActualResult("验证成功：期望值是：" + testData[0] + ";" + "实际值是： " + tempValue + " .");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("数据错误- 验证失败：期望值是：" + testData[0] + ";" + "实际值是： " + tempValue + " .");
                    assertTrue(false);
                }
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Verify_Match(String dr, String byString, String data) {
        By by = getByObject(byString);
        String[] testData = data.split(";");
        try {
            MouseAction(dr, byString, "MoveToElement");

            if (testData.length == 2) {
                testData[1] = getDataFromVariable(testData[1]);
                String tempValue = drivers.get(dr).findElement(by).getAttribute(testData[0]);
                if (tempValue.matches(testData[1])) {
                    outputtextList.get(dr).setActualResult("属性值验证成功：" + testData[0] + "期望值是" + testData[1] + ";" + "实际值是： " + tempValue + " .");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("数据错误- 属性值验证失败：" + testData[0] + "期望值是" + testData[1] + ";" + "实际值是： " + tempValue + " .");
                    assertTrue(false);

                }

            } else {
                testData[0] = getDataFromVariable(testData[0]);
                String tempValue = drivers.get(dr).findElement(by).getText();
                if (tempValue.matches(testData[0])) {
                    outputtextList.get(dr).setActualResult("验证成功：期望值是：" + testData[0] + ";" + "实际值是： " + tempValue + " .");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("数据错误- 验证失败：期望值是：" + testData[0] + ";" + "实际值是： " + tempValue + " .");
                    assertTrue(false);
                }
            }


        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void VerifyList_Equal(String dr, String byString, String data) {
        By by = getByObject(byString);
        int i = 1;
        Boolean status = true;
        String failedResult = "数据错误- ";
        String testData;
        testData = getDataFromVariable(data);

        try {
            //MouseAction(dr,by,"MoveToElement");
            for (WebElement item : drivers.get(dr).findElements(by)) {
                if (item.getText().equals(testData)) {
                    status = status && true;
                } else {
                    status = status && false;
                    failedResult = failedResult + "\n The result of item #" + i + " is not matched: acutal result is: " + item.getText() + ".";
                }
                i++;
            }
            if (status) {
                outputtextList.get(dr).setActualResult("验证成功");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult(failedResult);
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void VerifyList_EQOrLargeThan(String dr, String byString, String data) {
        By by = getByObject(byString);
        int i = 1;
        Boolean status = true;
        String failedResult = "数据错误- ";
        String testData;
        testData = getDataFromVariable(data);

        try {
            //MouseAction(dr,by,"MoveToElement");
            for (WebElement item : drivers.get(dr).findElements(by)) {
                if (item.getText().compareTo(testData) >= 0) {
                    status = status && true;
                } else {
                    status = status && false;
                    failedResult = failedResult + "\n The result of item #" + i + " is not matched: acutal result is: " + item.getText() + ".";
                }
                i++;
            }
            if (status) {
                outputtextList.get(dr).setActualResult("验证成功");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult(failedResult);
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void VerifyList_EQOrLessThan(String dr, String byString, String data) {
        By by = getByObject(byString);
        int i = 1;
        Boolean status = true;
        String failedResult = "数据错误- ";
        String testData;
        testData = getDataFromVariable(data);

        try {
            //MouseAction(dr,by,"MoveToElement");
            for (WebElement item : drivers.get(dr).findElements(by)) {
                if (item.getText().compareTo(testData) <= 0) {
                    status = status && true;
                } else {
                    status = status && false;
                    failedResult = failedResult + "\n The result of item #" + i + " is not matched: acutal result is: " + item.getText() + ".";
                }
                i++;
            }
            if (status) {
                outputtextList.get(dr).setActualResult("验证成功");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult(failedResult);
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void VerifyList_Contain(String dr, String byString, String data) {
        By by = getByObject(byString);
        int i = 1;
        Boolean status = true;
        String failedResult = "数据错误- ";
        String testData;
        testData = getDataFromVariable(data);
        String tempValue = "";

        try {
            // MouseAction(dr,by,"MoveToElement");

            for (WebElement item : drivers.get(dr).findElements(by)) {
                if (item.getText().contains(testData)) {
                    status = status && true;
                    // tempValue=tempValue+"\n"+item.getText();
                } else {
                    status = status && false;
                    failedResult = failedResult + "\n The result of item #" + i + " is not matched: acutal result is: " + item.getText() + ".";
                }
                i++;
            }
            if (status) {
                outputtextList.get(dr).setActualResult("验证成功\n" + tempValue);
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult(failedResult);
                assertTrue(false);

            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void VerifyList_match(String dr, String byString, String data) {
        By by = getByObject(byString);
        int i = 1;
        Boolean status = true;
        String failedResult = "数据错误";
        String testData;
        testData = getDataFromVariable(data);

        try {

            for (WebElement item : drivers.get(dr).findElements(by)) {
                if (item.getText().matches(testData)) {
                    status = status && true;
                } else {
                    status = status && false;
                    failedResult = failedResult + "\n The result of item #" + i + " is not matched: acutal result is: " + item.getText() + ".";
                }
                i++;
            }
            if (status) {
                outputtextList.get(dr).setActualResult("验证成功");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult(failedResult);
                assertTrue(false);

            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void Input(String dr, String byString, String data) {
        String locateMode = byString.split(";;")[0];
        if ("AI图像识别".equals(locateMode)) {
            waitForObjectAppear(dr, byString, String.valueOf(implicitlyWait));
            if (data.isEmpty()) {
                outputtextList.get(dr).setActualResult("传入的测试数据值不能为空");
                assertTrue(false);
            }
            try {
                String imagePath = byString.split(";;")[2];
                File file = new File(imagePath + "\\1.png");
                if (file.exists()) {
                    awtClick(dr, imagePath + "\\1.png");
                    new Actions(drivers.get(dr)).sendKeys(data).perform();
                    outputtextList.get(dr).setActualResult("输入成功");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("图片不存在！！！");
                    assertTrue(false);
                }
            } catch (Exception e) {
                outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e.toString());
                assertTrue(false);
            }
        } else {
            By by = getByObject(byString);
            String testData;
            testData = getDataFromVariable(data);
            try {
                MouseAction(dr, byString, "MoveToElement");
                drivers.get(dr).findElement(by).clear();
                drivers.get(dr).findElement(by).sendKeys(testData);
                outputtextList.get(dr).setActualResult("输入成功");
                assertTrue(true);
            } catch (NullPointerException e1) {
                outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
                assertTrue(false);
            } catch (ElementNotVisibleException e2) {
                outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
                assertTrue(false);
            } catch (NoSuchElementException e3) {
                outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
                assertTrue(false);
            } catch (UnhandledAlertException e) {
                outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
                assertTrue(false);
            } catch (WebDriverException e) {
                outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
                assertTrue(false);
            }
        }

    }


    public static void Input_RegExp(String dr, String byString, String data) {
        By by = getByObject(byString);
        String testData;
        Generex generex = new Generex(data);
        testData = generex.random();

        try {
            MouseAction(dr, byString, "MoveToElement");
/*
            Actions actions = new Actions(drivers.get(dr));
            actions.sendKeys(drivers.get(dr).findElement(by), Keys.CONTROL, "a").sendKeys(Keys.DELETE);
            actions.sendKeys(Keys.NULL);
            //actions.keyUp(drivers.get(dr).findElement(by),Keys.CONTROL).perform();
            actions.sendKeys(drivers.get(dr).findElement(by), testData).perform();
            */
            drivers.get(dr).findElement(by).clear();
            drivers.get(dr).findElement(by).sendKeys(testData);
            outputtextList.get(dr).setActualResult("输入成功");
            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void SelectSingleOption(String dr, String byString, String data) {
        By by = getByObject(byString);
        String testData;
        testData = getDataFromVariable(data);

        //outputtextList.get(dr).setExpectedResult(ExpectedResult);
        try {
            MouseAction(dr, byString, "MoveToElement");
            Select select = new Select(drivers.get(dr).findElement(by));
            if (testData.matches("^[0-9]{1,2}$")) {

                select.selectByIndex(Integer.parseInt(testData));
                outputtextList.get(dr).setActualResult("选择成功");
                assertTrue(true);
            } else {

                select.selectByVisibleText(testData);
                outputtextList.get(dr).setActualResult("选择成功");
                assertTrue(true);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void SelectMultipleOption(String dr, String byString, String data) {
        By by = getByObject(byString);
        String[] testData;
        data = getDataFromVariable(data);
        testData = data.split(",");


        //outputtextList.get(dr).setExpectedResult(ExpectedResult);
        try {
            MouseAction(dr, byString, "MoveToElement");
            Select select = new Select(drivers.get(dr).findElement(by));
            select.deselectAll();
            for (int i = 0; i < testData.length; i++) {
                if (testData[i].matches("^[0-9]{1,2}$")) {
                    select.selectByIndex(Integer.parseInt(testData[i]));
                    outputtextList.get(dr).setActualResult("选择成功");
                    assertTrue(true);
                } else {
                    select.selectByValue(testData[i]);
                    outputtextList.get(dr).setActualResult("选择成功");
                    assertTrue(true);
                }
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void DeselectAllOptions(String dr, String byString, String data) {
        By by = getByObject(byString);

        //outputtextList.get(dr).setExpectedResult(ExpectedResult);
        try {
            MouseAction(dr, byString, "MoveToElement");
            Select select = new Select(drivers.get(dr).findElement(by));
            select.deselectAll();
            outputtextList.get(dr).setActualResult("反选成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void SelectFromQuickSearchList(String dr, String byString, String data) {
        By by = getByObject(byString);
        Actions actions = new Actions(drivers.get(dr));

        String testData;
        testData = getDataFromVariable(data);

        try {
            drivers.get(dr).findElement(by).clear();//}
            if (!testData.equals("")) {

                if (testData.matches("^[1-9]{1}$")) {
                    int times = Integer.parseInt(testData);
                    drivers.get(dr).findElement(by).click();
                    // 点击回车 Enter
                    //actions.sendKeys(Keys.ENTER).perform();
                    // actions.sendKeys(Keys.NULL).perform();
                    actions.sendKeys(drivers.get(dr).findElement(by), Keys.ENTER).perform();
                    for (int i = 0; i < times; i++) {
                        actions.sendKeys(Keys.ARROW_DOWN).perform();
                        actions.sendKeys(Keys.NULL).perform();
                    }
                    actions.sendKeys(Keys.ENTER).perform();
                    actions.sendKeys(Keys.NULL).perform();

                } else {
                    MouseAction(dr, byString, "MoveToElement");
                    actions.moveToElement(drivers.get(dr).findElement(by)).sendKeys(drivers.get(dr).findElement(by), data).perform();
                    actions.sendKeys(drivers.get(dr).findElement(by), Keys.ENTER).perform();

                    Thread.sleep(6000);
                }
                //actions.sendKeys(Keys.ENTER).perform();
                //actions.sendKeys(Keys.NULL).perform();

                outputtextList.get(dr).setActualResult("选择成功");
                assertTrue(true);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        } catch (InterruptedException e) {
            outputtextList.get(dr).setActualResult("InterruptedException - Driver进程被中断了!!!" + e.toString());
            assertTrue(false);
        }
    }

    public static void Click(String dr, String byString, String data) {
        String locateMode = byString.split(";;")[0];
        if ("AI图像识别".equals(locateMode)) {
            waitForObjectAppear(dr, byString, String.valueOf(implicitlyWait));
            String imagePath = byString.split(";;")[2];
            File file = new File(imagePath + "\\1.png");
            if (file.exists()) {
                awtClick(dr, imagePath + "\\1.png");
            } else {
                outputtextList.get(dr).setActualResult("图片不存在！！！");
                assertTrue(false);
            }
        } else {
            By by = getByObject(byString);
            try {
                String result = clickElement(drivers.get(dr).findElement(by));
                if (result.equals("pass")) {
                    outputtextList.get(dr).setActualResult("点击成功");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("页面异常- 点击失败:" + result);
                    assertTrue(false);
                }
                assertTrue(true);
            } catch (NullPointerException e1) {
                outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
                assertTrue(false);
            } catch (ElementNotVisibleException e2) {
                outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
                assertTrue(false);
            } catch (NoSuchElementException e3) {
                outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
                assertTrue(false);
            } catch (UnhandledAlertException e) {
                outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
                assertTrue(false);
            } catch (WebDriverException e) {
                outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
                assertTrue(false);
            }
        }
    }

    public static void Loop_ClickRefresh(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            while (!itemClickable(dr, by)) {
                drivers.get(dr).navigate().refresh();
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //outputtextList.get(dr).setActualResult("点击成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("Exception: " + e.toString());
            assertTrue(false);
        }
    }


    public static void ClickIfContains(String dr, String byString, String data) {
        By by = getByObject(byString + "[contains(text(),'" + data + "')]");
        try {
            MouseAction(dr, byString, "MoveToElement");

            //drivers.get(dr).findElement(by).click();
            clickElement(drivers.get(dr).findElement(by));

            outputtextList.get(dr).setActualResult("点击成功");
            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void JavascriptClick(String dr, String byString, String data) {
        waitForObjectAppear(dr, byString, "60");
        By by = getByObject(byString);
        String testData;
        testData = getDataFromVariable(data);

        try {
            JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
            js.executeScript("arguments[0].click();", drivers.get(dr).findElement(by));
            outputtextList.get(dr).setActualResult("点击成功");
            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void VerifyInputValue_Equal(String dr, String byString, String data) {
        By by = getByObject(byString);

        data = getDataFromVariable(data);

        try {
            if (byString.split(";;")[0].equals("cssSelector")) {
                JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
                injectjQueryIfNeeded(js);

                String tempValue = (String) js.executeScript("return $(\"" + byString.split(";;")[1] + "\").val();");
                if (tempValue.equals(data)) {
                    outputtextList.get(dr).setActualResult("验证成功，期望值是：" + data + ";" + "实际值是: " + tempValue + ".");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("数据错误- 验证失败，期望值是：" + data + ";" + "实际值是: " + tempValue + ".");
                    assertTrue(false);
                }
            } else {
                outputtextList.get(dr).setActualResult("用例设计问题- 传过来的ByType必须是cssSelector。");
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void BufferClass_Pattern(String dr, String byString, String data) {
        By by = getByObject(byString);
        String[] buffer = data.split(";");

        try {
            String tempValue = drivers.get(dr).findElement(by).getAttribute("class");
            Pattern pattern = Pattern.compile(buffer[1]);
            Matcher matcher = pattern.matcher(tempValue);
            if (matcher.find()) {
                buffer[1] = matcher.group();
            } else {
                buffer[1] = tempValue;
            }
            GV.put(buffer[0], buffer[1]);
            outputtextList.get(dr).setActualResult("缓存成功：变量"+buffer[0]+"的值为"+buffer[1]);
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void BufferInputValue(String dr, String byString, String data) {
        By by = getByObject(byString);
        String[] buffer = new String[2];
        data = getDataFromVariable(data);
        buffer[0] = data;

        try {
            if (byString.split(";;")[0].equals("cssSelector")) {
                JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
                injectjQueryIfNeeded(js);

                // String tempValue = (String) js.executeScript("return $(\""+byString.split(";;")[1]+"\").val();");
                String tempValue = (String) js.executeScript("return document.querySelector(\"" + byString.split(";;")[1] + "\").value;");
                //String tempValue = (String) js.executeScript("return arguments[0].value;",drivers.get(dr).findElement(by));
                buffer[1] = tempValue;
                GV.put(buffer[0], buffer[1]);

                outputtextList.get(dr).setActualResult("缓存成功");
                ;
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("用例设计问题- 传过来的ByType必须是cssSelector。");
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Buffer(String dr, String byString, String data) {
        By by = getByObject(byString);
        String[] buffer = new String[2];
        data = getDataFromVariable(data);
        buffer[0] = data;
        //outputtextList.get(dr).setExpectedResult(ExpectedResult);

        try {

            buffer[1] = drivers.get(dr).findElement(by).getText();
            GV.put(buffer[0], buffer[1]);
            outputtextList.get(dr).setActualResult("缓存成功");
            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (StaleElementReferenceException e) {
            WaitOn(dr, byString, "2000");
            buffer[1] = drivers.get(dr).findElement(by).getText();
            GV.put(buffer[0], buffer[1]);
            outputtextList.get(dr).setActualResult("缓存成功");
            assertTrue(true);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Buffer_Property(String dr, String byString, String data) {
        By by = getByObject(byString);
        String[] buffer = new String[2];
        data = getDataFromVariable(data);
        buffer[0] = data.split(";")[1];
        //outputtextList.get(dr).setExpectedResult(ExpectedResult);

        try {

            buffer[1] = drivers.get(dr).findElement(by).getAttribute(data.split(";")[0]);
            GV.put(buffer[0], buffer[1]);
            outputtextList.get(dr).setActualResult("缓存成功");

            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void Buffer_Replace(String dr, String byString, String data) {
        By by = getByObject(byString);
        String[] buffer = data.split(";");
        //outputtextList.get(dr).setExpectedResult(ExpectedResult);

        try {

            String tempValue = drivers.get(dr).findElement(by).getText().replaceAll(buffer[1], "");
            buffer[1] = tempValue;
            GV.put(buffer[0], buffer[1]);
            outputtextList.get(dr).setActualResult("缓存成功");

            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Buffer_Pattern(String dr, String byString, String data) {
        By by = getByObject(byString);
        String[] buffer = data.split(";");

        try {
            String tempValue = drivers.get(dr).findElement(by).getText();
            Pattern pattern = Pattern.compile(buffer[1]);
            Matcher matcher = pattern.matcher(tempValue);
            if (matcher.find()) {
                buffer[1] = matcher.group();
            } else {
                buffer[1] = tempValue;
            }
            GV.put(buffer[0], buffer[1]);
            outputtextList.get(dr).setActualResult("缓存成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void Compare_Equal(String dr, String byString, String data) {
        //outputtextList.get(dr).setExpectedResult(ExpectedResult);
        String[] testData = new String[2];

        testData[0] = getDataFromVariable(data.split(";")[0]);

        testData[1] = getDataFromVariable(data.split(";")[1]);

        try {
            if (testData[0].equals(testData[1])) {
                outputtextList.get(dr).setActualResult("对比成功：值1是" + testData[0] + ";值2是" + testData[1]);
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("数据错误- 两个值不相等：值1是" + testData[0] + ";值2是" + testData[1]);
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Compare_Contain(String dr, String value1, String value2) {
        //outputtextList.get(dr).setExpectedResult(ExpectedResult);
        String[] testData = new String[2];

        testData[0] = getDataFromVariable(value1);

        testData[1] =getDataFromVariable(value2);

        try {
            if (testData[1].contains(testData[0])) {
                outputtextList.get(dr).setActualResult("对比成功：值1是"+testData[0]+";值2是"+testData[1]);
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("两个值不相等：值1是"+testData[0]+";值2是"+testData[1]);
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Compare_LargerThan(String dr, String byString, String data) {
        String value1 = getDataFromVariable(data.split(";")[0]);
        String value2 = getDataFromVariable(data.split(";")[1]);

        try {
            if (Integer.parseInt(value1) > Integer.parseInt(value2)) {
                outputtextList.get(dr).setActualResult("比较成功：值1:" + value1 + "大于值2：" + value2);
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("两个值大小比较不是预期的结果：值1:" + value1 + "没有大于值2：" + value2);
                assertTrue(false);
            }

        } catch (NullPointerException e) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e.toString());
            assertTrue(false);
        }
    }

    public static void ExportFileAndCopyToExpectedPath(String dr, String byString, String data) {
        // 使用该方法前提是需要将系统默认导出的路径配置在Config.xlsx里面
        //该方法是点击导出按钮，系统自动保存文件在默认路径，然后将导出的文件拷贝到指定的位置：TestData文件夹

        By by = getByObject(byString);
        String testData;
        testData = getDataFromVariable(data);
        String sourcePath = "";
        String destPath = "";

        try {
            sourcePath = ExcelUtils.getConfigData("Overview", 19, 1) + "/" + data;
            destPath = getRootPath() + "/TestData/" + data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drivers.get(dr).findElement(by).click();
            copyFileUsingApacheCommonsIO(sourcePath, destPath);

            outputtextList.get(dr).setActualResult("文件导出成功");
            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        } catch (IOException e) {
            outputtextList.get(dr).setActualResult("IOException - 请检查待导出的文件是否被打开!!!" + e.toString());
            assertTrue(false);
        }
    }


    public static void SelectFileToUpload(String dr, String byString, String data) {
        By by = getByObject(byString);
        // 获取文件上传文半框位置，传入文件上传地址。
        //by页面元素定位到input 标签 且type属性等于file
        String testData;
        testData = getDataFromVariable(data);

        //outputtextList.get(dr).setExpectedResult(ExpectedResult);
        //testData = getRootPath() + "\\TestData\\" + testData;
        testData = getRootPath() + testData;

        try {

            File file = new File(testData);
            drivers.get(dr).findElement(by).sendKeys(file.getAbsolutePath());
            outputtextList.get(dr).setActualResult("文件上传成功");
            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void OpenFileSelectionThenSelectAFile(String dr, String byString, String data) {
        By by = getByObject(byString);
        //by页面元素定位到可以打开选择文件框的按钮或链接
        String testData;
        testData = getDataFromVariable(data);
        //testData = getRootPath() + "\\TestData\\" + testData;
        testData = getRootPath() +  testData;

        try {

            File file = new File(testData);
            String filePath = file.getAbsolutePath();
            StringSelection selection = new StringSelection(filePath);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);

            drivers.get(dr).findElement(by).click();

            //KeyboardOperation(dr,by,"Paste");

            // 点击回车 Enter
            //KeyboardOperation(dr,by,"Enter");

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);


            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            outputtextList.get(dr).setActualResult("文件上传成功");
            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        } catch (AWTException e) {
            outputtextList.get(dr).setActualResult("AWTException - 请检查系统是否有模拟键盘设备!!!" + e.toString());
            assertTrue(false);
        }
    }

    public static void AutoIt_SelectFileToUpload(String dr, String byString, String data) {
        By by = getByObject(byString);
        //by页面元素定位到可以打开选择文件框的按钮或链接
        String testData;
        testData = getDataFromVariable(data);
        //testData = getRootPath() + "\\TestData\\" + testData;
        testData = getRootPath()  + testData;

        try {

            File file = new File(testData);
            String filePath = file.getAbsolutePath();
            String browser = "";
            if (Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("browser") == null) {
                browser = "chrome";
            } else if (!Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("browser").equals("")) {
                browser = Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("browser").toLowerCase();
            } else {
                browser = "chrome";
            }


            String executeFile = getRootPath() + "/TestData/AutoIt_uploadfile.exe"; //定义了upload.exe文件的路径

            String cmd = "\"" + executeFile + "\"" + " " + "\"" + browser + "\"" + " " + "\"" + filePath + "\"";
            System.out.println(cmd);
            Runtime.getRuntime().exec(cmd);
            //Process p = Runtime.getRuntime().exec(cmd);
            //p.waitFor();
            outputtextList.get(dr).setActualResult("文件上传成功");
            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        } catch (IOException e) {
            outputtextList.get(dr).setActualResult("IOException - 读写文件失败，请检查文件是否被打开!!!" + e.toString());
            assertTrue(false);
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("Exception - " + e.toString());
            assertTrue(false);
        }
    }


    public static void AutoIt_WindowsLogin(String dr, String byString, String data) {
        By by = getByObject(byString);
        //by页面元素定位到可以打开选择文件框的按钮或链接
        String testData;
        testData = getDataFromVariable(data);
        //testData = getRootPath() + "\\TestData\\" + testData;
        String username = testData.split(";")[0];
        String password = testData.split(";")[1];

        try {
            String browser = "";
            if (Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("browser") == null) {
                browser = "chrome";
            } else if (!Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("browser").equals("")) {
                browser = Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("browser").toLowerCase();
            } else {
                browser = "chrome";
            }
            if (browser.equals("ie")) {
                String executeFile = "TestData/WindowsLogin.exe"; //定义了exe文件的路径
                String cmd = "\"" + executeFile + "\"" + " " + "\"" + username + "\"" + " " + "\"" + password + "\"";
                Process p = Runtime.getRuntime().exec(cmd);
                p.waitFor();
                Thread.sleep(4000);
            } else if (browser.equals("chrome") || browser.equals("firefox")) {
                StringSelection selection = new StringSelection(username);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
                KeyboardOperation(dr, "", "Paste");
                WaitOn(dr, "", "1000");

                KeyboardOperation(dr, "", "Tab");

                selection = new StringSelection(password);
                clipboard.setContents(selection, selection);
                KeyboardOperation(dr, "", "Paste");
                WaitOn(dr, "", "1000");

            }

            outputtextList.get(dr).setActualResult("登录成功");
            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        } catch (InterruptedException e) {
            outputtextList.get(dr).setActualResult("InterruptedException - Driver进程被中断了!!!" + e.toString());
            assertTrue(false);
        } catch (IOException e) {
            outputtextList.get(dr).setActualResult("IOException - 读写文件失败，请检查文件是否被打开!!!" + e.toString());
            assertTrue(false);
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("Exception- " + e.toString());
            assertTrue(false);
        }
    }

    public static void VerifyList_Sorting(String dr, String byString, String data) {
        By by = getByObject(byString);
        String testData;
        testData = getDataFromVariable(data);
        //outputtextList.get(dr).setExpectedResult(ExpectedResult);
        int i = 0;
        Boolean status = true;
        String[] Values;
        String previousValue;
        String nextValue;

        try {
            int len = drivers.get(dr).findElements(by).size();
            Values = new String[len];

            for (WebElement item : drivers.get(dr).findElements(by)) {
                Values[i] = item.getText();
                i++;
            }

            if (len < 2) {
                outputtextList.get(dr).setActualResult("cannot verify the data is sorted in expected order due to lack of data.");
                assertTrue(true);
            } else {

                for (int j = 0; j < len - 1; j++) {
                    previousValue = Values[j];
                    nextValue = Values[j + 1];
                    if (testData.equals("Ascending")) {
                        if (previousValue.compareTo(nextValue) <= 0) {
                            status = status && true;
                        } else {
                            status = status && false;
                        }
                    } else {
                        if (previousValue.compareTo(nextValue) >= 0) {
                            status = status && true;
                        } else {
                            status = status && false;
                        }
                    }
                }
            }
            if (status) {
                outputtextList.get(dr).setActualResult("排序正确");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("排序不正确");
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void WaitOn(String dr, String byString, String data) {
        long ms = 0;
        try {
            ms = Long.parseLong(data);
        } catch (Exception e) {
            ms = 1;
        }

        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        outputtextList.get(dr).setActualResult("等待" + data + "毫秒");
    }

    public static void ScrollLeft(String dr, String byString, String data) {
        By by = getByObject(byString);
        int px = Integer.parseInt(data);

        JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
        injectjQueryIfNeeded(js);
        if (byString.split(";;")[0].equals("cssSelector")) {
            String jsString = "document.querySelector(\"" + byString.split(";;")[1] + "\").scrollLeft=" + px;
            js.executeScript(jsString);
            outputtextList.get(dr).setActualResult("滚动成功");
            assertTrue(true);
        } else {
            outputtextList.get(dr).setActualResult("用例设计问题- 传过来的ByType必须是cssSelector。");
            assertTrue(false);
        }
    }


    public static void ScrollTop(String dr, String byString, String data) {

        By by = getByObject(byString);
        int px = Integer.parseInt(data);

        JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
        injectjQueryIfNeeded(js);
        if (byString.split(";;")[0].equals("cssSelector")) {
            String jsString = "document.querySelector(\"" + byString.split(";;")[1] + "\").scrollTop=" + px;
            js.executeScript(jsString);
            outputtextList.get(dr).setActualResult("滚动成功");
            assertTrue(true);
        } else {
            outputtextList.get(dr).setActualResult("用例设计问题- 传过来的ByType必须是cssSelector。");
            assertTrue(false);
        }
    }

    public static void ScrollIntoView(String dr, String byString, String data) {
        By by = getByObject(byString);
        //int px = Integer.parseInt(data);

        JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
        //js.executeScript("$(\""+byXpath+"\").scrollTop(" + px + ")");
        //js.executeScript("document.getElementByXpath(\""+byXpath+"\").scrollTop(" + px + ")");
        //js.executeScript("document.documentElement.scrollTop=" + px);

        WebElement target = drivers.get(dr).findElement(by);
        js.executeScript("arguments[0].scrollIntoView(true);", target);//拖动到可见的元素去
        //js.executeScript("document.getElementsByClassName(\"react-scrollbar-default\")[0].scrollTop=1000");//拖动到可见的元素去

        outputtextList.get(dr).setActualResult("滚动成功");
        assertTrue(true);

    }

    public static void Browser_ScrollLeft(String dr, String byString, String data) {
        //  By by=getByObject(byString);

        int px = Integer.parseInt(data);
        JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
        js.executeScript("document.scrollingElement.scrollLeft=" + px);
        outputtextList.get(dr).setActualResult("滚动成功");
        assertTrue(true);
    }


    public static void Browser_ScrollTop(String dr, String byString, String data) {
        // By by=getByObject(byString);

        int px = Integer.parseInt(data);
        JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
        js.executeScript("document.scrollingElement.scrollTop=" + px);
        outputtextList.get(dr).setActualResult("滚动成功");
        assertTrue(true);
    }

    public static void CloseBrowserThenOpenBrowser(String dr, String byString, String data) {
        By by = getByObject(byString);
        drivers.get(dr).quit();

        try {
            OpenBrowser(dr, "", "");
            outputtextList.get(dr).setActualResult("关闭已有浏览器，并打开新浏览器成功");
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void OpenBrowser(String dr, String byString, String data) throws Exception {
        By by = getByObject(byString);
        //*************************Initializing for phone or browsers**********************
        String seleniumGridIP = "localhost";

        String browser = "";
        if (Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("browser") == null) {
            browser = "chrome";
        } else if (!Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("browser").equals("")) {
            browser = Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("browser");
        } else {
            browser = "chrome";
        }
        String execSide = "";
        String usingOpenChrome = Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("usingOpenChrome");
        String remotedebugport = Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("remotedebugport");

        //for firefox
        if (browser.equalsIgnoreCase("Firefox")) {
            drivers.put(dr, new FirefoxDriver());
            drivers.get(dr).manage().window().maximize();

        } else if (browser.equalsIgnoreCase("IE")) {
            //for ie browser
            if (execSide.equals("网格")) {
                DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
                capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capability.setBrowserName("internet explorer");
                //capability.setVersion(browser);
                capability.setPlatform(Platform.WINDOWS);
                drivers.put(dr, new RemoteWebDriver(new URL("http://" + seleniumGridIP + ":4444/wd/hub"), capability));
                drivers.get(dr).manage().window().maximize();
            } else {
                // System.setProperty("webdriver.ie.driver", "./libs/" + browserPath.get(browser) + "/IEDriverServer.exe");
                DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
                capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                // capability.setCapability("ignoreProtectedModeSettings", false);
                drivers.put(dr, new InternetExplorerDriver(capability));
                drivers.get(dr).manage().window().maximize();
            }
        } else if (browser.equalsIgnoreCase("Edge")) {
            drivers.put(dr, new EdgeDriver());
            drivers.get(dr).manage().window().maximize();
        } else {
            //for Chrome
            if (execSide.equals("网格")) {
                DesiredCapabilities capability = DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setVersion(browser);
                capability.setPlatform(Platform.WINDOWS);
                drivers.put(dr, new RemoteWebDriver(new URL("http://" + seleniumGridIP + ":4444/wd/hub"), capability));
                drivers.get(dr).manage().window().maximize();
            } else {
                //  String webDriverPath = "./libs/" + browserPath.get(browser) + "/chromedriver.exe";
                //  System.setProperty("webdriver.chrome.driver", "./libs/" + browserPath.get(browser) + "/chromedriver.exe");
                ChromeOptions driverOptions = new ChromeOptions();
                //driverOptions.addArguments("disable-gpu");
                //driverOptions.addArguments("start-maximized");
                if (!GlobalVariable.GV.get("ChromeUserData").equals("")) {
                    driverOptions.addArguments("user-data-dir=" + GlobalVariable.GV.get("ChromeUserData"));
                }
                if (usingOpenChrome.equals("true")) {
                    remotedebugport = remotedebugport.equals("") ? "9222" : remotedebugport;
                    driverOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:" + remotedebugport);
                }
                drivers.put(dr, new ChromeDriver(driverOptions));
                if (!usingOpenChrome.equals("true")) {
                    drivers.get(dr).manage().window().maximize();
                }
            }
        }

        drivers.get(dr).manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        //drivers.get(dr).manage().window().maximize();
        outputtextList.get(dr).setActualResult("打开浏览器成功");
        assertTrue(true);
    }


    /**
     * 拖动页面元素，将元素A拖动到元素B的位置
     *
     * @param dr
     * @param byXpath
     * @param data
     * @throws InterruptedException
     */
    public static void DragAndDropElement(String dr, String byXpath, String data) throws InterruptedException {
        if (StringUtils.isBlank(byXpath)) {
            outputtextList.get(dr).setActualResult("内容不能为空");
            assertTrue(false);
        }
        String[] elements = byXpath.split(";");
        String element = elements[0];
        String element1 = elements[1];

        Actions actions = new Actions(drivers.get(dr));
        WebElement target = null;
        WebElement target1 = null;
        try {
            target = drivers.get(dr).findElement(By.xpath(element));
            target1 = drivers.get(dr).findElement(By.xpath(element1));
        } catch (NoSuchElementException e) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e.toString());
            assertTrue(false);
        }
        actions.dragAndDrop(target, target1).perform();
        outputtextList.get(dr).setActualResult("拖动成功");
        assertTrue(true);
    }

    public static void MouseAction(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            if (data.equals("DoubleClick")) {
                new Actions(drivers.get(dr)).doubleClick(drivers.get(dr).findElement(by)).perform();
                outputtextList.get(dr).setActualResult("鼠标操作左键双击成功");
                assertTrue(true);
            } else if (data.equals("RightClick")) {
                new Actions(drivers.get(dr)).contextClick(drivers.get(dr).findElement(by)).perform();
                outputtextList.get(dr).setActualResult("鼠标操作右击成功");

                assertTrue(true);
            } else if (data.equals("MoveToElement")) {
                new Actions(drivers.get(dr)).moveToElement(drivers.get(dr).findElement(by)).perform();
                outputtextList.get(dr).setActualResult("鼠标操作成功移动到指定页面元素");
                assertTrue(true);
            } else if (data.startsWith("MoveByOffset")) {
                String[] tempData = data.split(";");
                int xValue = 0;
                int yValue = 0;
                xValue = (tempData[1].charAt(0) == '-') ? -Integer.parseInt(tempData[1].replaceAll("-", "")) : Integer.parseInt(tempData[1]);
                yValue = (tempData[2].charAt(0) == '-') ? -Integer.parseInt(tempData[2].replaceAll("-", "")) : Integer.parseInt(tempData[2]);
                new Actions(drivers.get(dr)).moveByOffset(xValue, yValue);
                outputtextList.get(dr).setActualResult("鼠标操作移动成功");
                assertTrue(true);
            } else if (data.startsWith("DragAndDrop")) {
                String[] tempData = data.split(";");
                int xValue = 0;
                int yValue = 0;
                xValue = (tempData[1].charAt(0) == '-') ? -Integer.parseInt(tempData[1].replaceAll("-", "")) : Integer.parseInt(tempData[1]);
                yValue = (tempData[2].charAt(0) == '-') ? -Integer.parseInt(tempData[2].replaceAll("-", "")) : Integer.parseInt(tempData[2]);
                // new Actions(drivers.get(dr)).dragAndDropBy(drivers.get(dr).findElement(by), xValue, yValue).perform();

                new Actions(drivers.get(dr)).clickAndHold(drivers.get(dr).findElement(by)).moveByOffset(xValue, yValue).release().build().perform();

                //Thread.sleep(3000);
                outputtextList.get(dr).setActualResult("鼠标操作拖动成功");
                assertTrue(true);
            } else if (data.equals("LeftClick")) {
                new Actions(drivers.get(dr)).click(drivers.get(dr).findElement(by)).perform();
                // Thread.sleep(3000);
                outputtextList.get(dr).setActualResult("鼠标操作左键单击成功");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("用例设计问题- 没有该鼠标操作");
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void InputDate_DeleteValueFisrt(String dr, String byString, String data) {
        By by = getByObject(byString);
        Actions actions = new Actions(drivers.get(dr));
        String testData;
        testData = getDataFromVariable(data);

        String[] dateValue = testData.split(";");
        SimpleDateFormat dateFormat;

        try {

            if (dateValue.length == 2) {
                dateValue[0] = transferDate(dateValue[0], dateValue[1]);

            } else {
                dateValue[0] = transferDate(dateValue[0], "");
            }
            actions.sendKeys(drivers.get(dr).findElement(by), Keys.CONTROL, "a").sendKeys(Keys.DELETE).perform();
            actions.sendKeys(Keys.NULL);

            //drivers.get(dr).findElement(by).sendKeys(dateValue[0]);
            actions.sendKeys(drivers.get(dr).findElement(by), dateValue[0]).perform();

            outputtextList.get(dr).setActualResult("输入成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void InputDate(String dr, String byString, String data) {
        By by = getByObject(byString);
        Actions actions = new Actions(drivers.get(dr));
        String testData;
        testData = getDataFromVariable(data);

        String[] dateValue = testData.split(";");
        SimpleDateFormat dateFormat;

        try {

            if (dateValue.length == 2) {
                dateValue[0] = transferDate(dateValue[0], dateValue[1]);

            } else {
                dateValue[0] = transferDate(dateValue[0], "");
            }

            drivers.get(dr).findElement(by).clear();
            drivers.get(dr).findElement(by).sendKeys(dateValue[0]);

            outputtextList.get(dr).setActualResult("输入成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void InputDate_readonly(String dr, String byString, String data) {
        By by = getByObject(byString);
        Actions actions = new Actions(drivers.get(dr));
        String testData;
        testData = getDataFromVariable(data);

        String[] dateValue = testData.split(";");
        SimpleDateFormat dateFormat;


        try {
            if (byString.split(";;")[0].equals("cssSelector")) {
                JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
                js.executeScript("document.querySelector(\"" + byString.replace("cssSelector;;", "") + "\").removeAttribute('readonly');");

                if (dateValue.length == 2) {
                    dateValue[0] = transferDate(dateValue[0], dateValue[1]);

                } else {
                    dateValue[0] = transferDate(dateValue[0], "");
                }

                drivers.get(dr).findElement(by).clear();
                drivers.get(dr).findElement(by).sendKeys(dateValue[0]);

                outputtextList.get(dr).setActualResult("输入成功");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("用例设计问题- 传过来的ByType必须是cssSelector。");
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void Verify_Date(String dr, String byString, String data) {
        By by = getByObject(byString);
        String testData;
        testData = getDataFromVariable(data);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date getDate = new Date();
        Map<String, String> inputdate = new HashMap<String, String>();
        inputdate.put("today", dateFormat.format(getDate));

        try {
            if (drivers.get(dr).findElement(by).getText().replaceAll(" ", "").equals(inputdate.get(testData))) {
                outputtextList.get(dr).setActualResult("期望值是：" + inputdate.get(testData) + ";" + "实际值是: " + drivers.get(dr).findElement(by).getText() + " .");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("数据错误- 期望值是：" + inputdate.get(testData) + ";" + "实际值是: " + drivers.get(dr).findElement(by).getText() + " .");
                assertTrue(false);

            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void KeyboardOperation(String dr, String byString, String data) {
        By by = getByObject(byString);
        String keyOperation = data;
        Actions actions = new Actions(drivers.get(dr));

        Map<String, Keys> keyCode = new HashMap<String, Keys>();

        keyCode.put("Enter", Keys.ENTER);
        keyCode.put("Delete", Keys.DELETE);
        keyCode.put("Backspace", Keys.BACK_SPACE);
        keyCode.put("Tab", Keys.TAB);
        keyCode.put("Shift", Keys.SHIFT);
        keyCode.put("Ctrl", Keys.CONTROL);
        keyCode.put("Alt", Keys.ALT);
        keyCode.put("Esc", Keys.ESCAPE);
        keyCode.put("F2", Keys.F2);
        if (byString.equals("")) {

            if (keyOperation.equals("Copy")) {
                actions.sendKeys(Keys.CONTROL, "C").perform();
                actions.sendKeys(Keys.NULL).perform();
            } else if (keyOperation.equals("Paste")) {
                actions.sendKeys(Keys.CONTROL, "V").perform();
                actions.sendKeys(Keys.NULL).perform();
            } else if (keyOperation.equals("SelectAll")) {
                actions.sendKeys(Keys.CONTROL, "a").perform();
                actions.sendKeys(Keys.NULL).perform();
            } else if (keyOperation.equals("2020-03-08")) {
                actions.sendKeys("2020-03-08").perform();
            } else {
                actions.sendKeys(keyCode.get(keyOperation)).perform();
                actions.sendKeys(Keys.NULL).perform();
            }
        } else {
            if (keyOperation.equals("Copy")) {
                actions.sendKeys(drivers.get(dr).findElement(by), Keys.CONTROL, "C").perform();
                actions.sendKeys(Keys.NULL).perform();
            } else if (keyOperation.equals("Paste")) {
                actions.sendKeys(drivers.get(dr).findElement(by), Keys.CONTROL, "V").perform();
                actions.sendKeys(Keys.NULL).perform();
            } else if (keyOperation.equals("SelectAll")) {
                actions.sendKeys(drivers.get(dr).findElement(by), Keys.CONTROL, "a").perform();
                actions.sendKeys(Keys.NULL).perform();
            } else {
                actions.sendKeys(drivers.get(dr).findElement(by), keyCode.get(keyOperation)).perform();
                actions.sendKeys(Keys.NULL).perform();
            }
        }

        outputtextList.get(dr).setActualResult("键盘操作成功");
        assertTrue(true);

    }

    public static void SwitchToFrame(String dr, String byString, String data) {
        By by = getByObject(byString);

        try {
            drivers.get(dr).switchTo().frame(drivers.get(dr).findElement(by));

            outputtextList.get(dr).setActualResult("跳入Frame成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void SwitchOutOfFrame(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            drivers.get(dr).switchTo().defaultContent();

            outputtextList.get(dr).setActualResult("跳出Frame成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    //返回最近的窗口  added by plm
    public static void SwitchToNearestWindow(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            //int x = Integer.parseInt(data);
            Thread.sleep(3000);
            //将页面上所有的windowshandle放在入set集合当中
            Set<String> handles = drivers.get(dr).getWindowHandles();
            List<String> it = new ArrayList<String>(handles);

            for (int i = 1; i < 5; i++) {
                drivers.get(dr).switchTo().window(it.get(it.size() - 1));
            }
            outputtextList.get(dr).setActualResult("转入最新的window窗口成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        } catch (InterruptedException e) {
            outputtextList.get(dr).setActualResult("InterruptedException - Driver进程被中断了!!!" + e.toString());
            assertTrue(false);
        }
    }

    //返回窗口 added by plm
    public static Boolean SwitchToNumWindow(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            int x = Integer.parseInt(data);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            //将页面上所有的windowshandle放在入set集合当中
            Set<String> handles = drivers.get(dr).getWindowHandles();
            List<String> it = new ArrayList<String>(handles);

            if (it != null) {
                for (int i = 1; i < 5; i++) {
                    drivers.get(dr).switchTo().window(it.get(x - 1));
                }
            }
            return true;
            //drivers.get(dr).manage().window().maximize();
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
            return false;
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
            return false;
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
            return false;
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
            return false;
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
            return false;
        }
    }

    public static void ConfirmIfConfirmationPopup(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            if (drivers.get(dr).findElements(by).size() > 0) {
                for (WebElement item : drivers.get(dr).findElements(by)) {
                    clickElement(item);
                }
            }
            outputtextList.get(dr).setActualResult("确认成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void AcceptIfAlertPopup(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            while (isAlertPresent(dr)) {
                drivers.get(dr).switchTo().alert().accept();
            }
            outputtextList.get(dr).setActualResult("确认提示成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void AcceptAlert(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {

            drivers.get(dr).switchTo().alert().accept();
            outputtextList.get(dr).setActualResult("确认提示成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void DismissAlert(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {

            drivers.get(dr).switchTo().alert().dismiss();
            outputtextList.get(dr).setActualResult("确认提示成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void VerifyAlertText(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {

            String alerttext = drivers.get(dr).switchTo().alert().getText();

            Compare_Equal(dr, alerttext, data);

            outputtextList.get(dr).setActualResult("Alert文本验证成功：期望值是" + data + "；实际值是" + alerttext + ".");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void OpenNewTabWithAURL(String dr, String byString, String data) {
        By by = getByObject(byString);
        String url = data;
        try {
            Actions actions = new Actions(drivers.get(dr));


            //第一种实现方法
            /*
        //actions.keyDown(Keys.CONTROL).sendKeys("n").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
        //actions.sendKeys(Keys.CONTROL, "t").perform();

            drivers.get(dr).switchTo().window("新标签页");

        drivers.get(dr).get(url);
*/
            //第二种实现方法

            JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
            String jsString = "window.open(\"" + url + "\")";
            js.executeScript(jsString);

            SwitchToWindowByURL(dr, "", url);

            Thread.sleep(2000);
            outputtextList.get(dr).setActualResult("打开新窗口并输入url成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        } catch (InterruptedException e) {
            outputtextList.get(dr).setActualResult("InterruptedException - Driver进程被中断了!!!" + e.toString());
            assertTrue(false);
        }
    }

    //转到指定title的浏览器窗口
    public static void SwitchToWindowByTitle(String dr, String byString, String data) {
        By by = getByObject(byString);
        data = getDataFromVariable(data);
        try {
            //将页面上所有的windowshandle放在入set集合当中
            Set<String> handles = drivers.get(dr).getWindowHandles();
            List<String> it = new ArrayList<String>(handles); // 将set集合存入list对象

            for (int i = it.size(); (i > 0) & (!drivers.get(dr).getTitle().equals(data)); i--) {
                drivers.get(dr).switchTo().window(it.get(i - 1));
                /*
                if (drivers.get(dr).getTitle().equals(data)) {
                    assertTrue(true);
                    break;
                }*/
            }

            outputtextList.get(dr).setActualResult("跳入windows窗口成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    //转到以指定地址开头的url的浏览器窗口
    public static void SwitchToWindowByURL(String dr, String byString, String data) {
        By by = getByObject(byString);
        data = getDataFromVariable(data);
        try {

            //将页面上所有的windowshandle放在入set集合当中
            Set<String> handles = drivers.get(dr).getWindowHandles();
            List<String> it = new ArrayList<String>(handles); // 将set集合存入list对象
            for (int i = it.size(); (i > 0) & (!drivers.get(dr).getCurrentUrl().startsWith(data)); i--) {

                drivers.get(dr).switchTo().window(it.get(i - 1));

/*
                if (drivers.get(dr).getCurrentUrl().startsWith(data)) {
                    System.out.println(drivers.get(dr).getCurrentUrl());
                    assertTrue(true);
                    break;
                }*/
            }

            outputtextList.get(dr).setActualResult("跳入windows窗口成功");
            assertTrue(true);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    //适用于正常表格结构：table\tbody\tr\td
    public static void TableCell_PerformAction_Common(String dr, String byString, String data) {
        By by = getByObject(byString);

        Actions actions = new Actions(drivers.get(dr));
        String[] tempValue = data.split(";");
        int rowNo = Integer.parseInt(tempValue[0]);
        int colNo = Integer.parseInt(tempValue[1]);
        String action = tempValue[2];
        String dataValue = "";
        if (tempValue.length >= 4) {
            dataValue = tempValue[3];
        }


        dataValue = getDataFromVariable(dataValue);

        String row;
        String cell;
        if (byString.split(";;")[0].equals("xpath")) {
            row = byString.split(";;")[1] + "//tbody//tr[" + rowNo + "]";
            cell = byString.split(";;")[1] + "//tbody//tr[" + rowNo + "]//td[" + colNo + "]";

            try {

                if (action.equals("ClickLink")) {
                    drivers.get(dr).findElement(By.xpath(cell + "//a")).click();
                    assertTrue(true);

                } else if (action.equals("SelectRow")) {
                    actions.moveToElement(drivers.get(dr).findElement(By.xpath(cell))).perform();
                    actions.click(drivers.get(dr).findElement(By.xpath(cell))).perform();
                    assertTrue(true);
                } else if (action.equals("VerifyEqual")) {
                    Verify_Equal(dr, byString.split(";;")[0] + ";" + cell, dataValue);
                } else if (action.equals("VerifyContains")) {
                    Verify_Contain(dr, byString.split(";;")[0] + ";" + cell, dataValue);
                } else if (action.equals("VerifyMatch")) {
                    Verify_Match(dr, byString.split(";;")[0] + ";" + cell, dataValue);
                } else {
                    outputtextList.get(dr).setActualResult("用例设计问题- 传递的action关键字不对。");
                    assertTrue(false);

                }

                outputtextList.get(dr).setActualResult("操作成功");
                assertTrue(true);
            } catch (NullPointerException e1) {
                outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
                assertTrue(false);
            } catch (ElementNotVisibleException e2) {
                outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
                assertTrue(false);
            } catch (NoSuchElementException e3) {
                outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
                assertTrue(false);
            } catch (UnhandledAlertException e) {
                outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
                assertTrue(false);
            } catch (WebDriverException e) {
                outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
                assertTrue(false);
            }
        }
    }


    public static void waitForObjectAppear(String dr, String byString, String data) {
        try {
            String locateMode = byString.split(";;")[0];
            if ("AI图像识别".equals(locateMode)) {
                String imagePath = byString.split(";;")[2];
                File file = new File(imagePath + "\\1.png");
                long timeout = Long.parseLong(data);
                if (timeout >= 10) {
                    timeout = timeout * 1000;
                } else {
                    timeout = Long.parseLong(data) * 60 * 1000;
                }
                if (file.exists()) {
                    boolean flag = true;
                    long startTime = new Date().getTime();
                    long endTime = 0;
                    while (flag) {
                        File src = awtFullScreetshot(dr);
                        Double y = getElementLeftCorePoint(dr, src.getPath(), imagePath + "\\1.png");
//                        System.out.println(y);
                        if (y > 100) {
                            flag = false;
                            break;
                        }
                        WaitOn(dr, byString, "3000");
                        endTime = new Date().getTime();
                        if (endTime - startTime > timeout) {
                            outputtextList.get(dr).setActualResult("超时异常- 等待" + data + "分钟" + "图片未出现");
                            assertTrue(false);
                        }
                    }
                    if (!flag) {
                        outputtextList.get(dr).setActualResult("图片成功出现");
                        assertTrue(true);
                    }
                } else {
                    outputtextList.get(dr).setActualResult("图片不存在！！！");
                    assertTrue(false);
                }
            } else {
                By by = getByObject(byString);
                WebDriver driver = drivers.get(dr);
                if (StringUtils.isBlank(data)) {

                    WebDriverWait webDriverWait = new WebDriverWait(driver, 60);

                    WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));

                    if (webElement.isDisplayed()) {
                        outputtextList.get(dr).setActualResult("元素成功出现");
                        assertTrue(true);
                    } else {
                        outputtextList.get(dr).setActualResult("超时异常- 等待1分钟" + "元素未出现");
                        assertTrue(false);
                    }
                } else {
                    long timeout = Long.parseLong(data) * 60;
                    WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
                    WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
                    if (webElement.isDisplayed()) {
                        outputtextList.get(dr).setActualResult("元素成功出现");
                        assertTrue(true);
                    } else {
                        outputtextList.get(dr).setActualResult("超时异常- 等待" + data + "分钟后" + "元素未出现");
                        assertTrue(false);
                    }
                }
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForObjectAppear1(String dr, String byString, String data) {
        try {
            String locateMode = byString.split(";;")[0];
            if ("AI图像识别".equals(locateMode)) {
                String imagePath = byString.split(";;")[2];
                File file = new File(imagePath + "\\1.png");
                long timeout = Long.parseLong(data);
                if (timeout >= 10) {
                    timeout = timeout * 1000;
                } else {
                    timeout = Long.parseLong(data) * 60 * 1000;
                }
                if (file.exists()) {
                    boolean flag = true;
                    long startTime = new Date().getTime();
                    long endTime = 0;
                    while (flag) {
                        File src = awtFullScreetshot(dr);
                        Double y = getElementLeftCorePoint(dr, src.getPath(), imagePath + "\\1.png");
                        System.out.println(y);
                        if (y > 100) {
                            flag = false;
                            break;
                        }
                        WaitOn(dr, byString, "3000");
                        endTime = new Date().getTime();
                        if (endTime - startTime > timeout) {
                            outputtextList.get(dr).setActualResult("超时异常- 等待" + data + "分钟" + "图片未出现");
                            assertTrue(false);
                        }
                    }
                    if (!flag) {
                        outputtextList.get(dr).setActualResult("图片成功出现");
                        assertTrue(true);
                    }
                } else {
                    outputtextList.get(dr).setActualResult("图片不存在！！！");
                    assertTrue(false);
                }
            } else {
                By by = getByObject(byString);
                WebDriver driver = drivers.get(dr);
                if (StringUtils.isBlank(data)) {

                    WebDriverWait webDriverWait = new WebDriverWait(driver, 60);

                    WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));

                    if (webElement.isDisplayed()) {
                        outputtextList.get(dr).setActualResult("元素成功出现");
                        assertTrue(true);
                    } else {
                        outputtextList.get(dr).setActualResult("超时异常- 等待1分钟" + "元素未出现");
                        assertTrue(false);
                    }
                } else {
                    long timeout = Long.parseLong(data) * 60;
                    WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
                    WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
                    if (webElement.isDisplayed()) {
                        outputtextList.get(dr).setActualResult("元素成功出现");
                        assertTrue(true);
                    } else {
                        outputtextList.get(dr).setActualResult("超时异常- 等待" + data + "分钟后" + "元素未出现");
                        assertTrue(false);
                    }
                }
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForObjectADisappear(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {

            WebDriver driver = drivers.get(dr);
            if (StringUtils.isBlank(data)) {

                WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
                Boolean invisable = webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));

                if (invisable) {
                    outputtextList.get(dr).setActualResult("元素成功消失");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("超时异常- 等待1分钟" + "元素未消失");
                    assertTrue(false);
                }
            } else {
                long timeout = Long.parseLong(data) * 60;
                WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
                Boolean invisable = webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
                if (invisable) {
                    outputtextList.get(dr).setActualResult("元素成功消失");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("超时异常- 等待" + data + "分钟后" + "元素未消失");
                    assertTrue(false);
                }
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForTextToBe(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);

            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            Boolean tempValue = webDriverWait.until(ExpectedConditions.textToBe(by, data));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("文本成功出现");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "文本未出现");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForTextContains(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);

            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            Boolean tempValue = webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(by, data));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("文本成功出现");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "文本未出现");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForTextMatches(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);

            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            Pattern regexString = Pattern.compile(data);
            Boolean tempValue = webDriverWait.until(ExpectedConditions.textMatches(by, regexString));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("文本成功出现");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "文本未出现");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForValueAttributeContains(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);

            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            Boolean tempValue = webDriverWait.until(ExpectedConditions.textToBePresentInElementValue(by, data));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("期望的value属性值成功出现。");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "期望的value属性值未出现");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForAttributeToBe(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);

            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            String attr = data.split(";")[0];
            String value = data.split(";")[1];
            Boolean tempValue = webDriverWait.until(ExpectedConditions.attributeToBe(by, attr, value));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("属性值成功出现");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "属性值未出现");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForAttributeContains(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);

            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            String attr = data.split(";")[0];
            String value = data.split(";")[1];
            Boolean tempValue = webDriverWait.until(ExpectedConditions.attributeContains(by, attr, value));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("属性值成功出现");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "属性值未出现");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForWindowsTitleIs(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);

            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            Boolean tempValue = webDriverWait.until(ExpectedConditions.titleIs(data));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("窗口的标题成功出现");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "窗口的标题未出现");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForWindowsTitleContains(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);

            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            Boolean tempValue = webDriverWait.until(ExpectedConditions.titleContains(data));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("窗口的标题成功出现");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "窗口的标题未出现");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForNumOfWindowsToBe(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);
            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            Boolean tempValue = webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(Integer.parseInt(data)));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("窗口数量正确");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "窗口数量不正确");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForElementToBeClickable(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);
            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            WebElement we = webDriverWait.until(ExpectedConditions.elementToBeClickable(drivers.get(dr).findElement(by)));
            if (we.isDisplayed() && we.isEnabled()) {
                outputtextList.get(dr).setActualResult("元素可以点击");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "元素不可以点击");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForURLIs(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);

            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            Boolean tempValue = webDriverWait.until(ExpectedConditions.urlToBe(data));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("窗口url成功出现");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "窗口url未出现");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForURLContains(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            WebDriver driver = drivers.get(dr);

            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            Boolean tempValue = webDriverWait.until(ExpectedConditions.urlContains(data));
            if (tempValue == true) {
                outputtextList.get(dr).setActualResult("窗口url成功出现");
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 等待" + 180 + "s" + "窗口url未出现");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Input_WithExcelValue(String dr, String byString, String data) {
        By by = getByObject(byString);

        String[] excelInfo = data.split(";");
        String excelFile = getRootPath() + "/TestData/" + excelInfo[0];
        String excelSheet = excelInfo[1];
        String row = excelInfo[2];
        String col = excelInfo[3];
        String testData = "";
        try {
            testData = ExcelUtils.getCellData(excelFile, excelSheet, row, col);
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("用例设计问题- 读取不到数据：" + e.toString());
            assertTrue(false);
        }

        try {
            drivers.get(dr).findElement(by).clear();
            drivers.get(dr).findElement(by).sendKeys(testData);
            outputtextList.get(dr).setActualResult("输入成功");

            assertTrue(true);

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void Verify_Text_Equal_WithExcelValue(String dr, String byString, String data) {
        By by = getByObject(byString);

        String[] excelInfo = data.split(";");
        String excelFile = getRootPath() + "/TestData/" + excelInfo[0];
        String excelSheet = excelInfo[1];
        String row = excelInfo[2];
        String col = excelInfo[3];
        String testData = "";

        try {
            testData = ExcelUtils.getCellData(excelFile, excelSheet, row, col);
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("用例设计问题- 读取不到数据：" + e.toString());
            assertTrue(false);
        }

        try {

            String tempValue = drivers.get(dr).findElement(by).getText();
            if (tempValue.equals(testData)) {
                outputtextList.get(dr).setActualResult("文本验证成功：期望值是：" + testData + ";" + "实际值是： " + tempValue + " .");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("数据错误- 文本验证失败：期望值是：" + testData + ";" + "实际值是: " + tempValue + " .");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    public static void SetChromeFlashEnabled(String dr, String byString, String data) {
        // By by=getByObject(byString);
        try {
            drivers.get(dr).navigate().to("chrome://settings/content/flash");

            WaitOn(dr, "", "3000");

            JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);

            injectjQueryIfNeeded(js);

            String jsString = "document.querySelector(\"settings-ui\").shadowRoot.querySelector(\"div settings-main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"div#advancedPage settings-section settings-privacy-page\").shadowRoot.querySelector(\"category-setting-exceptions\").shadowRoot.querySelector(\"site-list[category-header='允许']\").shadowRoot.querySelector(\"div#category paper-button#addSite\").click()";

            js.executeScript(jsString);

            jsString = "return document.querySelector(\"settings-ui\").shadowRoot.querySelector(\"div settings-main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"div#advancedPage settings-section settings-privacy-page\").shadowRoot.querySelector(\"category-setting-exceptions\").shadowRoot.querySelector(\"site-list[category-header='允许']\").shadowRoot.querySelector(\"add-site-dialog\").shadowRoot.querySelector(\"dialog paper-input\").shadowRoot.querySelector(\"paper-input-container input\")";

            WebElement inputSite = ExpandShadowElement(js, jsString);
            inputSite.sendKeys(data);

            jsString = "return document.querySelector(\"settings-ui\").shadowRoot.querySelector(\"div settings-main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"div#advancedPage settings-section settings-privacy-page\").shadowRoot.querySelector(\"category-setting-exceptions\").shadowRoot.querySelector(\"site-list[category-header='允许']\").shadowRoot.querySelector(\"add-site-dialog\").shadowRoot.querySelector(\"paper-button.action-button\")";

            WebElement addButton = ExpandShadowElement(js, jsString);

            addButton.click();


            drivers.get(dr).navigate().refresh();


        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static WebElement ExpandShadowElement(JavascriptExecutor js, String execjs) {

        return (WebElement) js.executeScript(execjs);
    }


    //当前页面刷新
    public static void CurrentPageRefresh(String dr, String byString, String data) {
        // By by=getByObject(byString);
        try {
            drivers.get(dr).navigate().refresh();
            outputtextList.get(dr).setActualResult("刷新成功！");
            assertTrue(true);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("页面异常- 刷新失败：" + e.toString());
            assertTrue(false);
        }
    }

    //关闭当前窗口
    public static void CurrentWindowClose(String dr, String byString, String data) {
        // By by=getByObject(byString);
        try {
            drivers.get(dr).close();
            outputtextList.get(dr).setActualResult("关闭成功！");
            assertTrue(true);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("浏览器异常- 关闭失败：" + e.toString());
            assertTrue(false);
        }
    }

    //逐一点击所有找到的页面元素
    public static void ClickAll(String dr, String byString, String data) {
        By by = getByObject(byString);
        try {
            Actions actions = new Actions(drivers.get(dr));
            for (WebElement item : drivers.get(dr).findElements(by)) {
                actions.moveToElement(item);
                item.click();
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void waitForTextAppear(String dr, String byString, String data) {
        By by = getByObject(byString);
        data = getDataFromVariable(data);
        try {
            WebDriver driver = drivers.get(dr);

            WebElement webElement = drivers.get(dr).findElement(by);
            // WebDriverWait webDriverWait = new WebDriverWait(driver, 240);
            for (int seconds = 0; ; seconds++) {
                if (seconds >= 150) {
                    Boolean isPresent = ExpectedConditions.textToBePresentInElementValue(webElement, data).apply(driver);

                    if (isPresent == true) {
                        outputtextList.get(dr).setActualResult("元素成功出现：" + webElement.getText().toString());
                        break;
                    } else {
                        driver.navigate().refresh();
                    }
                    Thread.sleep(4000);
                }
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        } catch (InterruptedException e) {
            outputtextList.get(dr).setActualResult("InterruptedException - Driver进程被中断了!!!" + e.toString());
            assertTrue(false);
        }
    }

    //added by plm,used to wait for the text being changed to expected value after manually refresh
    public static void WaitForStatusChange(String dr, String byString, String data) {
        By by = getByObject(byString);
        Boolean exist = false;
        String iframeXpath = "";

        data = getDataFromVariable(data);

        try {
            MouseAction(dr, byString, "MoveToElement");
            WebDriverWait webDriverWait = new WebDriverWait(drivers.get(dr), 55);
            exist = webDriverWait.until(ExpectedConditions.textToBe(by, data));
            int i = 1;

            while (!exist && i < 3) {
                i++;
                drivers.get(dr).navigate().refresh();
                //drivers.get(dr).switchTo().frame(drivers.get(dr).findElement(By.xpath(iframeXpath)));
                exist = webDriverWait.until(ExpectedConditions.textToBe(by, data));
            }

            if (exist) {
                outputtextList.get(dr).setActualResult("成功等到文本出现");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("超时异常- 没有等到文本出现");
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }

    public static void SetGlobalVariable(String dr, String byString, String data) {
        By by = getByObject(byString);
        data = getDataFromVariable(data);

        try {
            if (data.split(";").length == 2) {
                GV.put(data.split(";")[0], data.split(";")[1]);

                outputtextList.get(dr).setActualResult("设置成功");
                assertTrue(true);

            } else {
                outputtextList.get(dr).setActualResult("用例设计问题- 数据使用不对，格式应该是《变量名》;《变量值》");
                assertTrue(false);
            }

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("页面异常，元素不可见 - 请检查元素是否显示在当前页面上，如果没有，则在当前步骤之前增加滚动操作，推荐使用ScrollIntoView关键字!!!" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("元素找不到 - 元素定位值不正确，请重新定位元素，注意存在多个值的情况，需要定位到该元素的唯一值!!!" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("页面异常，有不预见的alert提示弹出来 - 请在该步骤之前添加处理alert提示框的操作!!!" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("WebDriverException" + e.toString());
            assertTrue(false);
        }
    }


    ///////////////////////////////////////////////加数据库的方法//////////////////////////////////////////////////////////////
    public static void DB_Connecton(String dr, String byString, String data) throws Exception {

        //Connection connect = null;
        //Map<String, String> DBDRType = new HashMap<String, String>();
        //DBDRType.put("Oracle", "oracle.jdbc.OracleDriver");

        String dbdriver = getStringFromJson("database", "databaseList." + data + ".databaseType");
        String url = getStringFromJson("database", "databaseList." + data + ".connectionString");
        String username = getStringFromJson("database", "databaseList." + data + ".username");
        String password = getStringFromJson("database", "databaseList." + data + ".password");


        try {
            //Class.forName(DBDRType.get(DBType));

            Class.forName(dbdriver);

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully!");

            outputtextList.get(dr).setActualResult("数据库连接成功");
            assertTrue(true);

        } catch (ClassNotFoundException e) {
            outputtextList.get(dr).setActualResult("数据库连接失败 - 请检查对应的数据库工具包是否在maven本地仓库中" + e.toString());
            assertTrue(false);
        } catch (SQLException e) {
            outputtextList.get(dr).setActualResult("数据库连接失败 - 数据库连接信息不正确，请检查账号、密码、和URL!!!" + e.toString());
            assertTrue(false);
        }

    }


    public static void DB_CloseConnection(String suiteName, String byString, String data) throws ClassNotFoundException {
        try {
            if (connection != null) connection.close();
            outputtextList.get(suiteName).setActualResult("数据库断开成功");
            assertTrue(true);
        } catch (SQLException e) {
            outputtextList.get(suiteName).setActualResult("数据关闭异常 - 请检查是否存在未执行完的SQL语句!!!" + e.toString());
            assertTrue(false);
        }
    }

    public static void DB_RunSQL(String dr, String byString, String sqlString) throws ClassNotFoundException {
        sqlString = getDataFromVariable(sqlString);
        ResultSet resultSet;
        Statement statement = null;
        Boolean result;

        try {
            if (connection == null) {
                if ("" == getStringFromJson("database", "databaseList")) {
                    System.out.println("请检查是否添加数据库信息！");
                } else {
                    String dataName = getStringFromJson("database", "databaseList");
                    dataName = dataName.split("=")[0].substring(1);
                    DB_Connecton(dr, byString, dataName);
                }
//                DB_Connecton(dr, byString, getStringFromJson("database","databaseList.defaultDB"));
            }
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sqlString);

            if (resultSet.next()) {
                //将指针指向第一行之前
                resultSet.beforeFirst();

                if (resultSet.next()) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        String key = resultSet.getMetaData().getColumnLabel(i);
                        String value = resultSet.getString(i);
                        GV.put(key, value);
                    }
                }

                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("No result found!");
                assertTrue(false);
            }

        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("sql执行异常 - 1.请检查当前连接的库中是否存在该表；2.请检查语句中的表字段是否正确；3.请检查SQL语法是否正确!!!" + e.toString());
            assertTrue(false);

        }

    }


    public static void DB_RunSQL_Query(String dr, String byString, String sqlFile) throws ClassNotFoundException {
        //String[] buffer = data.split(";");
        Statement statement = null;
        ResultSet resultSet;
        String sqlStatement = readFileToString(sqlFile);
        sqlStatement = getDataFromVariable(sqlStatement);

        try {
            if (connection == null) {
                if ("" == getStringFromJson("database", "databaseList")) {
                    System.out.println("请检查是否添加数据库信息！");
                } else {
                    String dataName = getStringFromJson("database", "databaseList");
                    dataName = dataName.split("=")[0].substring(1);
                    DB_Connecton(dr, byString, dataName);
                }
//                DB_Connecton(dr, byString, "default");
            }

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sqlStatement);


            if (resultSet.next()) {
                //将指针指向第一行之前
                resultSet.beforeFirst();

                if (resultSet.next()) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        String key = resultSet.getMetaData().getColumnLabel(i);
                        String value = resultSet.getString(i);
                        GV.put(key, value);
                    }
                }

                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("No result found!");
                assertTrue(false);
            }
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("sql执行异常 - 1.请检查当前连接的库中是否存在该表；2.请检查语句中的表字段是否正确；3.请检查SQL语法是否正确!!!" + e.toString());
            // System.out.println(sqlStatement);
            assertTrue(false);

        }

    }

    public static void DB_RunSQL_Buffer(String suiteName, String byString, String sqlString) {
        ResultSet resultSet;
        Statement statement = null;
        String[] buffer = sqlString.split(";");
        String sql = buffer[0];
        String[] params = buffer[1].split(",");
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append("缓存的数据为：");
        try {
            if (connection == null) {
                if ("" == getStringFromJson("database", "databaseList")) {
                    System.out.println("请检查是否添加数据库信息！");
                } else {
                    String dataName = getStringFromJson("database", "databaseList");
                    dataName = dataName.split("=")[0].substring(1);
                    DB_Connecton(suiteName, byString, dataName);
                }
            }
            for (int i = 0; i < params.length; i++) {
                sql = sql.replace("P" + (i + 1), getDataFromVariable(params[i]));
            }
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                //将指针指向第一行之前
                resultSet.beforeFirst();
                if (resultSet.next()) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        String key = resultSet.getMetaData().getColumnLabel(i);
                        String value = resultSet.getString(i);
                        GV.put(key, value);
                        dataBuilder.append(key + "," + value + ";");
                    }
                }
                outputtextList.get(suiteName).setActualResult(dataBuilder.toString());
                assertTrue(true);
            } else {
                outputtextList.get(suiteName).setActualResult("查询结果为空");
                assertTrue(false);
            }
        } catch (Exception e) {
            outputtextList.get(suiteName).setActualResult("sql执行异常 - 1.请检查当前连接的库中是否存在该表；2.请检查语句中的表字段是否正确；3.请检查SQL语法是否正确!!!" + e.toString());
            assertTrue(false);
        }
    }

    //OMS2.0 取脚本参数，执行更新SQL
    public static void DB_RunSQL_Buffer1(String dr, String byString, String data) throws ClassNotFoundException {
        String[] buffer = data.split(";");
        ArrayList<String> list = new ArrayList<String>();
        int index = 0;
        for (int i = 1; i < buffer.length; i+=2) {
            list.add(GV.get(buffer[i]));
        }
        for (int i = 1; i < buffer.length; i+=2) {
            buffer[i] = list.get(index);
            index++;
        }
        data = "";
        for (int i = 0; i < buffer.length; i++) {
            data += buffer[i];
        }
        ResultSet resultSet;
        Statement statement = null;

        try {
            if (connection == null) {
                if ("" == getStringFromJson("database", "databaseList")) {
                    System.out.println("请检查是否添加数据库信息！");
                } else {
                    String dataName = getStringFromJson("database", "databaseList");
                    dataName = dataName.split("=")[0].substring(1);
                    DB_Connecton(dr, byString, dataName);
                }
            }
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(data);


        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("sql执行异常 - 1.请检查当前连接的库中是否存在该表；2.请检查语句中的表字段是否正确；3.请检查SQL语法是否正确!!!" + e.toString());
            assertTrue(false);
        }
    }

    public static void DB_RunSQL_Update_Param(String dr, String byString, String data) {
        Statement statement = null;
        String[] buffer = data.split(";");
        String sql = buffer[0];
        String[] params = buffer[1].split(",");
        try {
            if (connection == null) {
                if ("" == getStringFromJson("database", "databaseList")) {
                    System.out.println("请检查是否添加数据库信息！");
                } else {
                    String dataName = getStringFromJson("database", "databaseList");
                    dataName = dataName.split("=")[0].substring(1);
                    DB_Connecton(dr, byString, dataName);
                }
            }
            for (int i = 0; i < params.length; i++) {
                sql = sql.replace("P" + (i + 1), getDataFromVariable(params[i]));
            }
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            int i = statement.executeUpdate(sql);
            if (i >= 1) {
                outputtextList.get(dr).setActualResult("执行sql成功");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("执行sql失败");
                assertTrue(false);
            }
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("sql执行异常 - 1.请检查当前连接的库中是否存在该表；2.请检查语句中的表字段是否正确；3.请检查SQL语法是否正确!!!" + e.toString());
            assertTrue(false);
        }
    }

    //对数据库进行insert，delete或者update
    public static void DB_RunSQL_Update(String dr, String byString, String data) throws ClassNotFoundException {

        Statement statement = null;
        int resultCount;
        if (data.endsWith(".sql")) {
            data = readFileToString(data);
        }
        data = getDataFromVariable(data);
        //System.out.println(sqlString);

        try {
            if (connection == null) {
                if ("" == getStringFromJson("database", "databaseList")) {
                    System.out.println("请检查是否添加数据库信息！");
                } else {
                    String dataName = getStringFromJson("database", "databaseList");
                    dataName = dataName.split("=")[0].substring(1);
                    DB_Connecton(dr, byString, dataName);
                }
                DB_Connecton(dr, byString, "default");
            }

            int times = data.split(";").length;
            for (int i = 0; i < times; i++) {
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                resultCount = statement.executeUpdate(data.split(";")[i]);
                statement.close();
            }

        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("sql执行异常 - 1.请检查当前连接的库中是否存在该表；2.请检查语句中的表字段是否正确；3.请检查SQL语法是否正确!!!" + e.toString());
            assertTrue(false);

        }
    }

    //执行sql文件
    public static void DB_runSQLfile(String dr, String byString, String sqlfile) throws Exception {
//数据库名称和sql文件名称不需要一样
        String dbdriver = getStringFromJson("database", "databaseList." + new File(sqlfile).getName() + ".databaseType");
        String url = getStringFromJson("database", "databaseList." + new File(sqlfile).getName() + ".connectionString");
        String username = getStringFromJson("database", "databaseList." + new File(sqlfile).getName() + ".username");
        String password = getStringFromJson("database", "databaseList." + new File(sqlfile).getName() + ".password");
        try {
            SQLExec sqlExec = new SQLExec();
//设置数据库参数
            sqlExec.setDriver(dbdriver);
            sqlExec.setUrl(url);
            sqlExec.setUserid(username);
            sqlExec.setPassword(password);
//要执行的脚本
            sqlExec.setSrc(new File(sqlfile));
//有出错的语句该如何处理
            sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(
                    SQLExec.OnError.class, "abort")));
            sqlExec.setPrint(true); //设置是否输出
//输出到文件 sql.out 中；不设置该属性，默认输出到控制台
            sqlExec.setOutput(new File("TestData/sql/sql.out"));
            sqlExec.setProject(new Project()); // 要指定这个属性，不然会出错
            sqlExec.execute();

        } catch (Exception e) {
            System.out.println("sql执行异常 - 1.请检查当前连接的库中是否存在该表；2.请检查语句中的表字段是否正确；3.请检查SQL语法是否正确!!!" + e.toString());
        }

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String readFileToString(String fileName) {
        //String encoding = "ISO-8859-1";
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public static void injectjQueryIfNeeded(JavascriptExecutor jse) {
        if (!jQueryLoaded(jse))
            injectjQuery(jse);
    }

    // 判断是已加载jQuery
    public static Boolean jQueryLoaded(JavascriptExecutor jse) {
        Boolean loaded;
        try {
            loaded = (Boolean) jse.executeScript("return " + "jQuery()!=null");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    // 通过注入jQuery
    public static void injectjQuery(JavascriptExecutor jse) {
        jse.executeScript(" var headID = "

                + "document.getElementsByTagName(\"head\")[0];"
                + "var newScript = document.createElement('script');"
                + "newScript.type = 'text/javascript';" + "newScript.src = "
                + "'https://code.jquery.com/jquery-3.2.1.min.js';"
                + "headID.appendChild(newScript);");

    }

    /**
     * 判断文件是否存在
     *
     * @param fileDir 文件路径
     * @return
     */
    public static boolean fileExist(String fileDir) {
        boolean flag = false;
        File file = new File(fileDir);
        flag = file.exists();
        return flag;
    }

    /**
     * 判断文件的sheet（工作表）是否存在
     *
     * @param fileDir   文件路径
     * @param sheetName 表格索引名
     * @return
     * @throws Exception
     */
    public static boolean sheetExist(String fileDir, String sheetName) throws Exception {
        boolean flag = false;
        File file = new File(fileDir);
        if (file.exists()) {  //文件存在
            //创建workbook
            try {
                workbook = new HSSFWorkbook(new FileInputStream(file));
                //添加worksheet（不添加sheet时生成的Excel文件打开时会报错）
                HSSFSheet sheet = workbook.getSheet(sheetName);
                if (sheet != null) { //sheet(工作表)存在
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {  //文件不存在
            flag = false;
        }
        return flag;
    }

    /**
     * 创建新Excel
     *
     * @param fileDir   Excel的路径
     * @param sheetName 要创建的表格索引
     * @param titleRow  Excel的第一行即表头
     * @throws Exception
     */
    public static void createExcel(String fileDir, String sheetName, String[] titleRow) throws Exception {
        //创建workbook
        workbook = new HSSFWorkbook();
        //添加worksheet（不添加sheet时生成的Excel文件打开时会报错
        HSSFSheet sheet1 = workbook.createSheet(sheetName);
        //新建文件
        FileOutputStream out = null;
        try {
            //添加表头
            HSSFRow row = workbook.getSheet(sheetName).createRow(0); //创建第一行
            for (int i = 0; i < titleRow.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
            }
            out = new FileOutputStream(fileDir);
            workbook.write(out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除文件
     *
     * @param fileDir 文件路径
     * @return
     */
    public static boolean deleteExcel(String fileDir) {
        boolean flag = false;
        File file = new File(fileDir);
        //判断目录或文件是否存在
        if (!file.exists()) {  //不存在返回 false
            return flag;
        } else {
            //判断是否为文件
            if (file.isFile()) {  //为文件时，删除文件
                file.delete();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 往Excel表格写入（已存在的数据无法写入）
     *
     * @param fileDir   文件路径
     * @param sheetName 表格索引
     * @param mapList
     * @throws Exception
     */
    public static void writeToExcel(String fileDir, String sheetName, List<Map> mapList) throws Exception {
        //创建workbook
        File file = new File(fileDir);
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //流
        FileOutputStream out = null;
        HSSFSheet sheet = workbook.getSheet(sheetName);
        //获得表头的列数
        int columnCount = sheet.getRow(0).getLastCellNum();
        try {
            //获取表头的行对象
            HSSFRow titleRow = sheet.getRow(0);
            if (titleRow != null) {
                for (int rowId = 0; rowId < mapList.size(); rowId++) {
                    Map map = mapList.get(rowId);
                    HSSFRow newRow = sheet.createRow(rowId + 1);
                    for (short columnIndex = 0; columnIndex < columnCount; columnIndex++) {  //遍历表头
                        String mapKey = titleRow.getCell(columnIndex).toString().trim().toString().trim();
                        HSSFCell cell = newRow.createCell(columnIndex);
                        cell.setCellValue(map.get(mapKey) == null ? null : map.get(mapKey).toString());
                    }
                }
            }
            out = new FileOutputStream(fileDir);
            workbook.write(out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取该路径下所有的文件路径
     *
     * @param path
     * @return
     */
    public static List<String> getFilePathList(String path) {
        List<String> list = new ArrayList<String>();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] dirFile = file.listFiles();
            for (File f : dirFile) {
                if (f.isDirectory()) {
                    File[] dirFile1 = f.listFiles();
                    for (File f1 : dirFile1) {
                        if (f1.isDirectory()) {
                            getFilePathList(f1.getAbsolutePath());
                        } else {
                            String str = f1.getAbsolutePath().split("\\\\")[4];
                            System.out.println(f1.getAbsolutePath());
                            if (str.split("_")[0].equals("GV")) {
                                list.add(f1.getAbsolutePath());
                            }
                        }
                    }
                }
            }
        }
        return list;
    }


    public static void copyFileUsingApacheCommonsIO(String sourcePath, String destPath)
            throws IOException {
        File source = new File(sourcePath);
        File dest = new File(destPath);
        FileUtils.copyFile(source, dest);
    }

    public static String getSubString(String data, String start, String end) {
        if (end.equals("")) {
            return data.substring(data.indexOf(start) + start.length());
        } else {
            return data.substring(data.indexOf(start) + start.length(), data.indexOf(end));
        }
    }

    public static String getDataFromVariable(String data) {
        String testData = data;
        if (data.startsWith("GV:")) {
            testData = GV.get(data.replaceAll("GV:", ""));
        } else if (testData.contains("{{") && testData.contains("}}")) {
            while (testData.contains("{{") && testData.contains("}}")) {
                String temp = getSubString(testData, "{{", "}}");
                testData = testData.replaceAll("(\\{){2}(" + temp + ")(\\}){2}", GV.get(temp));
            }
        } else {
            testData = data;
        }
        return testData;
    }

    public static String transferDate(String data, String format) {
        SimpleDateFormat dateFormat;

        if (format.equals("")) {
            dateFormat = new SimpleDateFormat("yyyyMMdd");
        } else {
            dateFormat = new SimpleDateFormat(format);
        }
        //Calendar c = Calendar.getInstance();
        //int year = c.get(Calendar.YEAR);
        //int month = c.get(Calendar.MONTH);
        //int date = c.get(Calendar.DATE);
        Date getDate = new Date();
        Map<String, String> inputdate = new HashMap<String, String>();
        inputdate.put("today", dateFormat.format(getDate));
        inputdate.put("nextMonth", dateFormat.format(setDate(getDate, 30)));
        inputdate.put("lastMonth", dateFormat.format(setDate(getDate, -30)));
        inputdate.put("yesterday", dateFormat.format(setDate(getDate, -1)));
        inputdate.put("tomorrow", dateFormat.format(setDate(getDate, 1)));
        inputdate.put("nextWeek", dateFormat.format(setDate(getDate, 7)));
        inputdate.put("lastWeek", dateFormat.format(setDate(getDate, -7)));
        inputdate.put("nextYear", dateFormat.format(setDate(getDate, 365)));
        inputdate.put("lastYear", dateFormat.format(setDate(getDate, -365)));

        try {

            if (inputdate.get(data) == null) {
                if (data.matches("^[0-9]{8}$||^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                    data = data;
                } else {
                    int tempValue = 0;
                    tempValue = (data.charAt(0) == '-') ? -Integer.parseInt(data.replaceAll("-", "")) : Integer.parseInt(data);
                    data = dateFormat.format(setDate(getDate, tempValue));
                }
            } else {
                data = inputdate.get(data);
            }
            return data;

        } catch (NullPointerException e1) {
            return "";
        }
    }

    public static String getStringFromJson(String type, String jsonPath) {
        try {
            File json = type.equals("GlobalVariable") || type.equals("database") ? new File("configData.json") : new File("stepSetParam.json");
            return JsonPath.from(new InputStreamReader(new FileInputStream(json),"UTF-8")).get(jsonPath).toString();
        } catch (Exception e) {
            return "";
        }

    }


    public static float getFloatFromJson(String type, String jsonPath) {
        try {
            File json = type.equals("GlobalVariable") ? new File("configData.json") : new File("stepSetParam.json");
            return JsonPath.from(json).getInt(jsonPath);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean getBooleanFromJson(String type, String jsonPath) {
        try {
            File json = type.equals("GlobalVariable") ? new File("configData.json") : new File("stepSetParam.json");
            return JsonPath.from(json).getBoolean(jsonPath);
        } catch (Exception e) {
            return true;
        }
    }

    public static String getTCParam(String json, String jsonPath) {
        try {
            return JsonPath.from(json).getString(jsonPath);
        } catch (Exception e) {
            return "";
        }

    }

    public static void actionsClick(String dr, String byString, String data) {

        String sourcePath = "image/source.png";
        WebDriver driver = drivers.get(dr);
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            copyFile(screenshotAs, new File(sourcePath));
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
        List<Double> list = getElementCenterPoint(dr, sourcePath, data);
        double x = list.get(0);
        double y = list.get(1);
        int x1 = new Double(x).intValue();
        int y1 = new Double(y).intValue();
        new Actions(driver).moveByOffset(x1, y1).click().perform();
        outputtextList.get(dr).setActualResult("actions点击成功");
        assertTrue(true);
        String.valueOf(1);
    }

    public static void actionsInput(String dr, String byString, String data) {
        try {
            String[] strings = data.split(";");
            awtClick(dr, strings[0]);
            new Actions(drivers.get(dr)).sendKeys(strings[1]).perform();
            outputtextList.get(dr).setActualResult("点击成功");
            assertTrue(true);
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
    }

    public static void awtClick(String dr, String data) {
        File file = awtFullScreetshot(dr);
        List<Double> list = getElementCenterPoint(dr, file.getPath(), data);
        double x = list.get(0);
        double y = list.get(1);
        try {
            Robot robot = new Robot();
            robot.mouseMove((new Double(x).intValue()), (new Double(y).intValue()));
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            outputtextList.get(dr).setActualResult("awt点击成功");
            assertTrue(true);
        } catch (AWTException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
    }

    public static void clickIndex(String dr, String byString, String data) {
        String locateMode = byString.split(";;")[0];
        if ("AI图像识别".equals(locateMode)) {
            if (data.isEmpty()) {
                outputtextList.get(dr).setActualResult("传入的测试数据值不能为空");
                assertTrue(false);
            }
            try {
                String imagePath = byString.split(";;")[2];
                File file = new File(imagePath + "\\1.png");
                if (file.exists()) {
                    awtClickOfIndex(dr, imagePath + "\\1.png", data);
                    outputtextList.get(dr).setActualResult("点击成功");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("图片不存在！！！");
                    assertTrue(false);
                }
            } catch (Exception e) {
                outputtextList.get(dr).setActualResult("数据错误，有null值 - 请检查是否填写数据!!!" + e.toString());
                assertTrue(false);
            }
        } else {
            outputtextList.get(dr).setActualResult("元素定位方式必须是：“AI图像识别类型”");
            assertTrue(false);
        }
    }
    public static void awtClickOfIndex(String dr, String templatePath, String data) {
        File file = awtFullScreetshot(dr);
        int index = Integer.parseInt(data);
        List templatePointList = getTemplatePointList(dr, file.getPath(), templatePath);
        List<Integer> list = (List<Integer>)templatePointList.get(index);
        int x = list.get(0);
        int y = list.get(1);
        System.out.println("x=" + x + ";y=" + y);
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            outputtextList.get(dr).setActualResult("根据索引点击成功");
            assertTrue(true);
        } catch (AWTException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
    }

    public static void awtMouseMove(String dr, String templatePath) {

        File file = awtFullScreetshot(dr);
        List<Double> list = getElementCenterPoint(dr, file.getPath(), templatePath);
        double x = list.get(0);
        double y = list.get(1);
        try {
            Robot robot = new Robot();
            robot.mouseMove((new Double(x).intValue()), (new Double(y).intValue()));
            outputtextList.get(dr).setActualResult("鼠标移动到指定位置");
            assertTrue(true);
        } catch (AWTException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
    }

    public static List<Double> getTemplatePointList(String dr, String sourcePath, String templatePath) {
        String javaHome = System.getenv("JAVA_HOME");
        try {
            System.load(javaHome + "\\\\bin\\\\opencv_java453.dll");
        } catch (UnsatisfiedLinkError e) {
            outputtextList.get(dr).setActualResult("找不到系统变量JAVA_HOME,请配置！！！");
            assertTrue(false);
        }
        List list = new ArrayList();
        try {
            Mat source, template;
            source = imread(sourcePath);
            if (source.empty()) {
                outputtextList.get(dr).setActualResult("页面全屏截图图片读取失败！！！");
                assertTrue(false);
            }
//            template = imread(templatePath); 如果读取的图像文件路径存在中文，imread会报错
            //所以改成用流的方式读取
            File file = new File(templatePath);
            if (file.exists()) {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                inputStream.read(bytes);
                template = Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_COLOR);
                if (template.empty()) {
                    outputtextList.get(dr).setActualResult("页面元素中上传图片读取失败！！！");
                    assertTrue(false);
                }
                int width = source.cols() - template.cols() + 1;
                int height = source.rows() - template.rows() + 1;
                Mat mat = new Mat(width, height, CvType.CV_32FC1);
                //标准相关匹配,这类方法将模版对其均值的相对值与图像对其均值的相关值进行匹配,1表示完美匹配,-1表示糟糕的匹配,0表示没有任何相关性
                Imgproc.matchTemplate(source, template, mat, Imgproc.TM_CCOEFF_NORMED);
                Core.normalize(mat, mat, 0, 1, Core.NORM_MINMAX, -1, new Mat());
                double limit = 0.99;
                for (int i = 0; i < mat.rows(); i++) {
                    for (int j = 0; j < mat.cols(); j++) {
                        double matchValue = mat.get(i, j)[0];
                        if (matchValue >= limit) {
                            int x = j + template.cols() / 2;
                            int y = i + template.rows() / 2;
                            list.add(Arrays.asList(x, y));
                        }
                    }
                }
                if (list.size() == 0) {
                    outputtextList.get(dr).setActualResult("页面上没有匹配的图片！！！");
                    assertTrue(false);
                }
            } else {
                outputtextList.get(dr).setActualResult("没有找到页面元素中上传的图片路径，请检查页面元素图片是否上传");
                assertTrue(false);
            }

        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("获取图片坐标点发生异常," + e.toString());
            assertTrue(false);
        }
        return list;
    }

    public static File awtFullScreetshot(String dr) {
        File base = new File("basePath");
        if (!base.exists()) {
            base.mkdir();
        }
        File file = new File("basePath/source.png");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            BufferedImage screenCapture = (new Robot()).createScreenCapture(new Rectangle(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight()));
            ImageIO.write(screenCapture, "png", file);
        } catch (AWTException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        } catch (IOException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
        return file;
    }

    /**
     * 获取元素在屏幕上左上角的坐标点
     * @param dr
     * @param sourcePath
     * @param templatePath
     * @return
     */
    public static Double getElementLeftCorePoint(String dr, String sourcePath, String templatePath) {
        String javaHome = System.getenv("JAVA_HOME");
        try {
            System.load(javaHome + "\\\\bin\\\\opencv_java453.dll");
        } catch (UnsatisfiedLinkError e) {
            outputtextList.get(dr).setActualResult("找不到系统变量JAVA_HOME,请配置！！！");
            assertTrue(false);
        }
        double y = 0;
        try {
            Mat source, template;
            source = imread(sourcePath, 1);
            if (source.empty()) {
                outputtextList.get(dr).setActualResult("页面全屏截图图片读取失败！！！");
                assertTrue(false);
            }
//            template = imread(templatePath); 如果读取的图像文件路径存在中文，imread会报错
            //所以改成用流的方式读取
            File file = new File(templatePath);
            if (file.exists()) {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                inputStream.read(bytes);
                template = Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_COLOR);
                if (template.empty()) {
                    outputtextList.get(dr).setActualResult("页面元素中上传图片读取失败！！！");
                    assertTrue(false);
                }
                int result_rows = source.rows() - template.rows() + 1;
                int result_cols = source.cols() - template.cols() + 1;
                Mat result = new Mat(result_cols, result_rows, CvType.CV_32FC1);
                //TM_CCOEFF_NORMED：标准相关匹配,这类方法将模版对其均值的相对值与图像对其均值的相关值进行匹配,1表示完美匹配,-1表示糟糕的匹配,0表示没有任何相关性(随机序列).
                Imgproc.matchTemplate(source, template, result, Imgproc.TM_CCOEFF_NORMED);
                Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
                Core.MinMaxLocResult mlr = Core.minMaxLoc(result);
                Point mathLoc = mlr.maxLoc;
                y = mathLoc.y;
            } else {
                outputtextList.get(dr).setActualResult("没有找到页面元素中上传的图片路径，请检查页面元素图片是否上传");
                assertTrue(false);
            }
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("获取图片坐标点发生异常," + e.toString());
            assertTrue(false);
        }
//        Imgproc.rectangle(source, mathLoc, new Point(x + template.width(), y + template.height()), new Scalar(0, 0, 225), 2, Imgproc.LINE_AA);
//        HighGui.imshow("模板匹配", source);
//        HighGui.waitKey(0);
        return y;
    }

    public static List<Double> getElementCenterPoint(String dr, String sourcePath, String templatePath) {
        String javaHome = System.getenv("JAVA_HOME");
        try {
            System.load(javaHome + "\\\\bin\\\\opencv_java453.dll");
        } catch (UnsatisfiedLinkError e) {
            outputtextList.get(dr).setActualResult("找不到系统变量JAVA_HOME,请配置！！！");
            assertTrue(false);
        }
        List<Double> list = new ArrayList<>();
        double x = 0;
        double y = 0;
        try {
            Mat source, template;
            source = imread(sourcePath, 1);
            if (source.empty()) {
                outputtextList.get(dr).setActualResult("页面全屏截图图片读取失败！！！");
                assertTrue(false);
            }
//            template = imread(templatePath); 如果读取的图像文件路径存在中文，imread会报错
            //所以改成用流的方式读取
            File file = new File(templatePath);
            if (file.exists()) {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                inputStream.read(bytes);
                template = Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_COLOR);
                if (template.empty()) {
                    outputtextList.get(dr).setActualResult("页面元素中上传图片读取失败！！！");
                    assertTrue(false);
                }
                int result_rows = source.rows() - template.rows() + 1;
                int result_cols = source.cols() - template.cols() + 1;
                Mat result = new Mat(result_cols, result_rows, CvType.CV_32FC1);
                //TM_CCOEFF_NORMED：标准相关匹配,这类方法将模版对其均值的相对值与图像对其均值的相关值进行匹配,1表示完美匹配,-1表示糟糕的匹配,0表示没有任何相关性(随机序列).
                Imgproc.matchTemplate(source, template, result, Imgproc.TM_CCOEFF_NORMED);
                Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
                Core.MinMaxLocResult mlr = Core.minMaxLoc(result);
                Point mathLoc = mlr.maxLoc;
                x = mathLoc.x + template.width() / 2;
                y = mathLoc.y + template.height() / 2;
            } else {
                outputtextList.get(dr).setActualResult("没有找到页面元素中上传的图片路径，请检查页面元素图片是否上传");
                assertTrue(false);
            }
        } catch (Exception e) {
            outputtextList.get(dr).setActualResult("获取图片坐标点发生异常," + e.toString());
            assertTrue(false);
        }
//        Imgproc.rectangle(source, mathLoc, new Point(x + template.width(), y + template.height()), new Scalar(0, 0, 225), 2, Imgproc.LINE_AA);
//        HighGui.imshow("模板匹配", source);
//        HighGui.waitKey(0);
        list.add(x);
        list.add(y);
        return list;
    }
}
