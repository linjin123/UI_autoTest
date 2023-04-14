package Framework;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.util.List;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static Framework.GlobalVariable.GV;
import static Framework.GlobalVariable.outputtextList;
import static org.testng.Assert.assertTrue;
import org.apache.commons.lang.RandomStringUtils;

import org.apache.commons.lang.StringUtils;

import org.apache.poi.hssf.usermodel.HSSFCell;

import org.apache.poi.hssf.usermodel.HSSFRow;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.tools.ant.Project;

import org.apache.tools.ant.taskdefs.SQLExec;

import org.apache.tools.ant.types.EnumeratedAttribute;

import org.openqa.selenium.*;

import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;

import java.util.List;

import java.awt.*;

// import java.awt.List;
import java.awt.datatransfer.*;

import java.awt.event.KeyEvent;

import java.sql.*;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.*;

import java.util.Date;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import static Framework.GlobalVariable.GV;

import static Framework.GlobalVariable.outputtextList;

import static org.testng.Assert.assertTrue;
public class ApplicationActions extends Keywords {
public static void SpecialInput(String dr, String byString, String data) {
By by = getByObject(byString);
    String[] testData;
    data = getDataFromVariable(data);
    testData = data.split(",");
    // outputtextList.get(dr).setExpectedResult(ExpectedResult);
    try {
        MouseAction(dr, byString, "MoveToElement");
        // drivers.get(dr).findElement(by).click();
        for (int i = 0; i < testData.length; i++) {
            drivers.get(dr).findElement(by).sendKeys(testData[i]);
            new Actions(drivers.get(dr)).sendKeys(Keys.ENTER).perform();
        }
        assertTrue(true);
    } catch (NullPointerException e1) {
        outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
        assertTrue(false);
    } catch (ElementNotVisibleException e2) {
        outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
        assertTrue(false);
    } catch (NoSuchElementException e3) {
        outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
        assertTrue(false);
    } catch (UnhandledAlertException e) {
        outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
        assertTrue(false);
    } catch (WebDriverException e) {
        outputtextList.get(dr).setActualResult(e.toString());
        assertTrue(false);
    }
}
public static void inputReadonlyDate(String dr, String byString, String data) {
By by = getByObject(byString);
    SimpleDateFormat dateFormat;
    JavascriptExecutor js = (JavascriptExecutor) drivers.get(dr);
    // js.executeScript( "$('input[id=needCompleteTime-inputEl]').attr('readonly',false)");// 4.jQuery，设置为空（同3）
    js.executeScript("document.getElementById('needCompleteTime-inputEl').removeAttribute('readonly')");
    // "document.getElementById('txtBeginDate').removeAttribute('readonly')"  # 1.原生js，移除属性
    // "$('input[id=txtBeginDate]').removeAttr('readonly')"  # 2.jQuery，移除属性
    // "$('input[id=txtBeginDate]').attr('readonly',false)"  # 3.jQuery，设置为false
    // js = "$('input[id=txtBeginDate]').attr('readonly','')"  # 4.jQuery，设置为空（同3）
    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    if (data.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
        drivers.get(dr).findElement(by).sendKeys(data);
    } else {
        drivers.get(dr).findElement(by).sendKeys(inputdate.get(data));
    }
}
public static void inputTimestamp(String dr, String byString, String data) {
By by = getByObject(byString);
        try {
            loading(dr,byString,data);
            MouseAction(dr, byString, "MoveToElement");
            drivers.get(dr).findElement(by).clear();
            drivers.get(dr).findElement(by).sendKeys(data+Long.toString(System.currentTimeMillis()));
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
            outputtextList.get(dr).setActualResult("浏览器异常 - 可能发生了页面突然卡死或浏览器崩溃了，请在该步骤之前添加刷新页面操作!!!WebDriverException" + e.toString());
            assertTrue(false);
        }
}
public static void ClickIfExite(String dr, String byString, String data) {
loading(dr,byString,data);
By by = getByObject(byString);
        String[] testData = data.split(";");
        String tempValue = drivers.get(dr).findElement(by).getAttribute(testData[0]);

        try {
            if (tempValue.equals(testData[1])){
                clickElement(drivers.get(dr).findElement(by));
                outputtextList.get(dr).setActualResult("点击成功");
                assertTrue(true);
            }

            //drivers.get(dr).findElement(by).click();


        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
}
public static void Verify_AnyContain(String dr, String byString, String data) {
loading(dr,byString,data);
By by = getByObject(byString);
        data = getDataFromVariable(data);
        String[] testData = data.split(";");
        boolean b = true;
        try {
            String tempValue = drivers.get(dr).findElement(by).getText();
            for (int i=0;i<5;i++) {
                for (String temp : testData) {
                    temp = temp.trim();
                    if (tempValue.contains(temp) == false) {
                        b = false;
                        outputtextList.get(dr).setActualResult("数据错误- 属性值验证失败：" + testData[0] + "期望值是" + testData[1] + ";" + "实际值是： " + tempValue + " .");
                        assertTrue(false);
                        break;
                    }
                }
                if (b) {
                    outputtextList.get(dr).setActualResult("属性值验证成功：" + testData[0] + "期望值是" + testData[1] + ";" + "实际值是： " + tempValue + " .");
                    assertTrue(true);
                    break;
                }
                Thread.sleep(2000);
                CurrentPageRefresh(dr, byString, data);
            }


        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
            assertTrue(false);
        } catch (InterruptedException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
}
public static void filterByTitle(String dr, String byString, String data) {
loading(dr,byString,data);
    try {By by = getByObject(byString);
        data = getDataFromVariable(data);
        List<WebElement> elements = drivers.get(dr).findElements(by);
        String[] testData = data.split(";");
        int i = 0;
        for (WebElement e : elements) {
            i++;
            if (!e.getText().isEmpty()) {
                if (testData[1].contains(e.getText())) {
//                    System.out.println(e.getText());
                    break;
                }
            }
        }
        testData[2] = testData[2] + "(" + i + ") input" ;
        String value= "cssSelector;;"+testData[2];
        loading2(dr,value,data);
        drivers.get(dr).findElement(By.cssSelector(testData[2])).clear();
        drivers.get(dr).findElement(By.cssSelector(testData[2])).sendKeys(Keys.CONTROL, "a");
        drivers.get(dr).findElement(By.cssSelector(testData[2])).sendKeys(testData[0]);
        Actions actions = new Actions(drivers.get(dr));
        actions.sendKeys(drivers.get(dr).findElement(By.cssSelector(testData[2])), Keys.ENTER).perform();
    } catch (NullPointerException e1) {
        outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
        assertTrue(false);
    } catch (ElementNotVisibleException e2) {
        outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
        assertTrue(false);
    } catch (NoSuchElementException e3) {
        outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
        assertTrue(false);
    } catch (UnhandledAlertException e) {
        outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
        assertTrue(false);
    } catch (StaleElementReferenceException e) {
        filterByTitle(dr,byString,data);
    } catch (WebDriverException e) {
        outputtextList.get(dr).setActualResult(e.toString());
        assertTrue(false);
    }
}
public static void clickByTitle(String dr, String byString, String data) {
loading(dr,byString,data);
    try {
        By by = getByObject(byString);
        List<WebElement> elements = drivers.get(dr).findElements(by);
        String[] testData = data.split(";");
        int i = 0;
        for (WebElement e : elements) {
            i++;
            if (!e.getText().isEmpty()) {
                if (testData[0].contains(e.getText())) {
//                    System.out.println(e.getText());
                    break;
                }
            }
        }
        testData[1] = testData[1] + "(" + i + ")";
        String value= "cssSelector;;"+testData[1];
        clickElementCycle(dr,value,data);
    } catch (NullPointerException e1) {
        outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
        assertTrue(false);
    } catch (ElementNotVisibleException e2) {
        outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
        assertTrue(false);
    } catch (NoSuchElementException e3) {
        outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
        assertTrue(false);
    } catch (UnhandledAlertException e) {
        outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
        assertTrue(false);
    } catch (StaleElementReferenceException e) {
        clickByTitle(dr,byString,data);
    } catch (WebDriverException e) {
        outputtextList.get(dr).setActualResult(e.toString());
        assertTrue(false);
    }
}
public static void waitingForClickOrInput(String dr, String byString, String data) {
loading(dr,byString,data);
By by = getByObject(byString);
        try {
            // MouseAction(dr, byString, "MoveToElement");
            data = getDataFromVariable(data);
            if (data!="") {

                    drivers.get(dr).findElement(by).clear();
                    drivers.get(dr).findElement(by).sendKeys(data);
                    outputtextList.get(dr).setActualResult("输入成功");
                    assertTrue(true);


            } else {
                    //clickElementCycle(dr,byString,data);
                    String result = clickElement(drivers.get(dr).findElement(by));


                    //drivers.get(dr).findElement(by).click();
                    if (result.equals("pass")) {

                        outputtextList.get(dr).setActualResult("点击成功");
                        assertTrue(true);
                    } else {
                        outputtextList.get(dr).setActualResult("点击失败:" + result);
                        assertTrue(false);
                    }

                }


        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
            assertTrue(false);
        } catch (StaleElementReferenceException e) {
            waitingForClickOrInput(dr,byString,data);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
}
public static void loading(String dr, String byString, String data) {
try {
            // MouseAction(dr, byString, "MoveToElement");
            WebDriver driver = drivers.get(dr);
            WebDriverWait webDriverWait = new WebDriverWait(driver, 180);
            webDriverWait.until(ExpectedConditions.attributeContains(getByObject("cssSelector;;[class='el-loading-mask is-fullscreen']"), "style", "display: none"));

        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("loading:"+e.toString());
            assertTrue(false);
        }
}
public static void verifyElementNum(String dr, String byString, String data) {
waitForObjectAppear(dr, byString, "60");
        By by = getByObject(byString);
        try {
            String testData = getDataFromVariable(data);
            int tempValue = drivers.get(dr).findElements(by).size();
            String S1 = ""+tempValue;
            if (S1.equals(testData)) {
                outputtextList.get(dr).setActualResult("属性数值验证成功：" + testData + "期望值是" + testData + ";" + "实际值是： " + tempValue + " .");
                assertTrue(true);
            }
            else {
                outputtextList.get(dr).setActualResult("属性数值验证失败：" + testData + "期望值是" + testData + ";" + "实际值是： " + tempValue + " .");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
}
public static void clickIfAppear(String dr, String byString, String data) {
loading(dr,byString,data);
    By by = getByObject(byString);
    boolean present = isElementPresent(dr, by);
    if (present) {
        Click(dr, byString, data);
    }else{
        outputtextList.get(dr).setActualResult("无需点击跳过");
        assertTrue(true);
    }
}
public static void ScrollIntoViewByTitle(String dr, String byString, String data) {
loading(dr,byString,data);
        By by = getByObject(byString);
        try {
            List<WebElement> elements = drivers.get(dr).findElements(by);
            String[] testData = data.split(";");
            int i = 0;
            for (WebElement e : elements) {
                i++;
                if (!e.getText().isEmpty()) {
                    if (testData[0].contains(e.getText())) {
//                    System.out.println(e.getText());
                        break;
                    }
                }
            }
            testData[1] = testData[1] + "(" + i + ")";
            ScrollIntoView(dr,"cssSelector;;"+testData[1],data);
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
}
public static void clickOrInputIfExist(String dr, String byString, String data) {
By by = getByObject(byString);
        boolean result=isElementPresent(dr, by);
        try {
            if (data!=""){
                if (result){
                    drivers.get(dr).findElement(by).clear();
                    drivers.get(dr).findElement(by).sendKeys(Keys.CONTROL, "a");
                    drivers.get(dr).findElement(by).sendKeys(data);
                    outputtextList.get(dr).setActualResult("输入成功");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("没有元素忽略");
                    assertTrue(true);
                }
            } else {
                if (result) {
                    clickElementCycle(dr, byString, data);
                    outputtextList.get(dr).setActualResult("点击成功");
                    assertTrue(true);
                } else {
                    outputtextList.get(dr).setActualResult("没有元素忽略不点");
                    assertTrue(true);
                }
            }
            //drivers.get(dr).findElement(by).click();


        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
}
public static void loading2(String dr, String byString, String data) {
try {
            By by = getByObject(byString);
            List<WebElement> elements = drivers.get(dr).findElements(by);
            if (elements.size() > 0) {
                WebDriver driver = drivers.get(dr);
                WebDriverWait webDriverWait = new WebDriverWait(driver, 120);
                webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
            } else {
                outputtextList.get(dr).setActualResult("没有找到元素！！！");
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
            assertTrue(false);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult("loading2:"+e.toString());
            System.out.println(e.toString());
            assertTrue(false);
        }
}
public static void clickElementCycle(String dr, String byString, String data) {
By by = getByObject(byString);
    String status = "";
    int i = 0;
    for (; i < 8 & !status.equals("pass"); i++) {
        WebElement elmt =drivers.get(dr).findElement(by);
        status = itemClickable(elmt);
        if (status.equals("pass")) {
            outputtextList.get(dr).setActualResult("第"+i+"点击成功");
            assertTrue(true);
        }else {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
            }
        }
    }
}
public static void clickByTitle1(String dr, String byString, String data) {
try {
            By by = getByObject(byString);
            List<WebElement> elements = drivers.get(dr).findElements(by);
            String[] testData = data.split(";");
            String title = testData[0];
            boolean flag = false;
            int i = 0;
            for (WebElement e : elements) {
                i++;
                String elementText = e.getText();
                WaitOn(dr, byString, "1000");
                if (!elementText.isEmpty()) {
                    if (elementText.contains(title)) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {
                String value = testData[1] + "(" + i + ")";
                drivers.get(dr).findElement(By.cssSelector(value)).click();
                outputtextList.get(dr).setActualResult("点击成功！！！");
                assertTrue(true);
            } else {
                outputtextList.get(dr).setActualResult("没有找到与<" + title + ">匹配的标题！！！");
                assertTrue(false);
            }
        } catch (NullPointerException e1) {
            outputtextList.get(dr).setActualResult("数据不对，有null值:" + e1.toString());
            assertTrue(false);
        } catch (ElementNotVisibleException e2) {
            outputtextList.get(dr).setActualResult("元素不可见：" + e2.toString());
            assertTrue(false);
        } catch (NoSuchElementException e3) {
            outputtextList.get(dr).setActualResult("没有找到该元素：" + e3.toString());
            assertTrue(false);
        } catch (UnhandledAlertException e) {
            outputtextList.get(dr).setActualResult("有不预见的alert提示弹出来：" + e.toString());
            assertTrue(false);
        } catch (StaleElementReferenceException e) {
            clickByTitle(dr, byString, data);
        } catch (WebDriverException e) {
            outputtextList.get(dr).setActualResult(e.toString());
            assertTrue(false);
        }
}
}
