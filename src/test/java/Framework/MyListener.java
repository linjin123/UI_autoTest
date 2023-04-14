package Framework;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.LookupTranslator;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.*;

import java.io.*;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import static Framework.GlobalVariable.*;
import static Framework.Keywords.*;

import org.testng.annotations.Parameters;
import org.testng.xml.XmlSuite;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    ExcelUtils excelUtils;
    public static String stepName;
    public static String PreviousTestStatus = "Pass";
    public static Connection executionConnection;
    String stepArray[][];
    public static int retryDone = 0;

    // This belongs to ISuiteListener and will execute before the Suite start
    @Override
    public void onStart(ISuite suite) {
        try {
            record = suite.getParameter("record").equals("true") ? "是" : "否";

            System.out.println("recordflag:" + record);

            productName = suite.getParameter("productName");
            projectName = suite.getParameter("projectName");
            taskId = suite.getParameter("taskId");
            recordId = suite.getParameter("recordId");
            stepRerun = suite.getParameter("stepRerun");
            caseRerun = suite.getParameter("caseRerun");
            suiteRerun = suite.getParameter("suiteRerun");
			globalParamLabel = suite.getParameter("globalParamLabel");
            maxRetry = Integer.parseInt(suite.getParameter("maxRetry"));

        } catch (Exception e) {
            System.out.println(e.toString());

        }


        String suiteName = suite.getName();

        outputtextList.put(suiteName, new OutputText());

        final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
        System.setProperty(ESCAPE_PROPERTY, "false");
        String c = Keywords.getCurrentTime("yyMMdd");
        //outputtextList.get(suite.getName()).setTestCaseID(Integer.parseInt(c)*1000);
        //outputtextList.get(suiteName).setTestCaseID(0);
        outputtextList.get(suiteName).setPassTestCaseCount(0);
        outputtextList.get(suiteName).setFailTestCaseCount(0);

        outputtextList.get(suiteName).setSuiteName(suiteName);

        String suitetime = Keywords.getCurrentTime("yyyyMMddHHmmss");
        if (overallReportFolder.equals("None")) {
            runStartTime=Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss");
            if (record.equals("否") && !suite.getParameter("suiteId").equals("")) {
                overallReportFolder = suite.getParameter("suiteId");
                DeleteDir(new File("TestReport/" + overallReportFolder));
            } else if (record.equals("否") && !suite.getParameter("taskId").equals("")) {
                overallReportFolder = suite.getParameter("taskId");
                DeleteDir(new File("TestReport/" + overallReportFolder));
            } else {
                overallReportFolder = Keywords.getCurrentTime("yyyyMMddHHmmss");
            }

            if (fileExist("OverviewReport.html")) {
                deleteExcel("OverviewReport.html");
            }

            reportFolder = getMD5(productName + projectName + overallReportFolder);


            File testReportDir = new File("TestReport");

            if (suite.getParameter("delHistoryReport").equals("true")) {
                String[] fileList = testReportDir.list();

                for (int i = 0; i < fileList.length; i++) {
                    try {
                        DeleteDir(new File(testReportDir, fileList[i]));
                    } catch (ExceptionInInitializerError e) {
                        System.out.println(e.toString());
                    }
                }
            }
        }

        // runSuites.add(suite);

        suiteStartTime.put(suite.getName(), Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss"));
        outputtextList.get(suiteName).setSuiteSTime(Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss"));
        outputtextList.get(suiteName).setSuiteTime(suitetime);

        String tempExecLog = Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss") + "： 套件Suite：" + suite.getName() + "开始执行；";
        System.out.println(tempExecLog);

        if (record.equals("是")) {
            System.out.println("插入suite的running记录");
            insertExecutionResult(GV.get("job_name"), GV.get("build_number"), GV.get("build_url"), "suite", overallReportFolder, productName, projectName, suiteName, "","", "", outputtextList.get(suiteName).getSuiteSTime(), outputtextList.get(suiteName).getSuiteSTime(), "", "Running", 0, 0, 0, "", "", "");
        }


        PreviousTestStatus = "Pass";

        //File dir = new File("TestReport/tmpfile/" + overallReportFolder + "/" + suiteName);
       /* File dir = new File("TestReport/tmpfile/" + overallReportFolder + "/" + suite.getName());
        if (!dir.exists()) {
            dir.mkdirs();
        }*/

        //File dirreport = new File("TestReport/" + overallReportFolder + "/" + suiteName);
        File dirreport = new File("TestReport/" + overallReportFolder + "/" + suite.getName());
        if (!dirreport.exists()) {
            dirreport.mkdirs();
        }
        // String htmlFile = "TestReport/" + overallReportFolder + "/" + suiteName + "/" + "index.html";
        String htmlFile = "TestReport/" + overallReportFolder + "/" + suite.getName() + "/" + "index.html";
        generateSuiteReport_head(htmlFile, outputtextList.get(suiteName));
    }

    // This belongs to ISuiteListener and will execute, once the Suite is finished

    @Override
    public void onFinish(ISuite suite) {

        String suiteName = suite.getName();
        if (outputtextList.get(suiteName).getFailTestCount() != 0 && retryDone < maxRetry && suiteRerun.equalsIgnoreCase("true")) {
            retryDone = retryDone + 1;
            outputtextList.get(suiteName).suiteReportHead.setLength(0);
            outputtextList.get(suiteName).suiteReportBody_failed.setLength(0);
            outputtextList.get(suiteName).suiteReportBody.setLength(0);
            outputtextList.get(suiteName).suiteReportFoot.setLength(0);
            suite.run();
        } else {
            //String suiteName = suite.getParameter("driverID");//need to revise in the future
            outputtextList.get(suiteName).setSuiteETime(Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss"));
            //String htmlFile = "TestReport/" + overallReportFolder + "/" + suiteName + "/" + "index.html";
            String htmlFile = "TestReport/" + overallReportFolder + "/" + suite.getName() + "/" + "index.html";
            String htmlFileFailed = "TestReport/" + overallReportFolder + "/" + suite.getName() + "/" + "index-failed.html";

            retryDone = 0;
            runSuites.add(suite);
            generateSuitReport_foot(htmlFile, htmlFileFailed, outputtextList.get(suiteName));

            String suiteStatus = outputtextList.get(suiteName).getFailTestCount() == 0 ? "Pass" : "Fail";
            String tempExecLog = Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss") + "： 套件Suite：" + suite.getName() + "执行完:结果是" + suiteStatus + ",总用例数=" + (outputtextList.get(suiteName).getPassTestCaseCount() + outputtextList.get(suiteName).getFailTestCount()) + "，通过用例数=" + outputtextList.get(suiteName).getPassTestCaseCount() + "，失败用例数=" + outputtextList.get(suiteName).getFailTestCount() + "。";
            System.out.println(tempExecLog);

            if (record.equals("是")) {
                System.out.println("插入suite的结束记录");
                insertExecutionResult(GV.get("job_name"), GV.get("build_number"), GV.get("build_url"), "suite", overallReportFolder, productName, projectName, suiteName, "","", "", outputtextList.get(suiteName).getSuiteSTime(), outputtextList.get(suiteName).getSuiteETime(), "", suiteStatus, (outputtextList.get(suiteName).getPassTestCaseCount() + outputtextList.get(suiteName).getFailTestCount()), outputtextList.get(suiteName).getPassTestCaseCount(), outputtextList.get(suiteName).getFailTestCount(), "", "", "");
            }
        }


    }

    // This belongs to ITestListener and will execute before starting of Test set/batch

    public void onStart(ITestContext testContext) {

        String suiteName = testContext.getSuite().getName();
        //String suiteName = testContext.getSuite().getParameter("driverID");//need to revise in the future
        outputtextList.get(suiteName).setTestName(testContext.getName());
        outputtextList.get(suiteName).setSuiteName(testContext.getSuite().getName());
        String fileTime = Keywords.getCurrentTime("yyMMddHHmmss");
        outputtextList.get(suiteName).setFileCreateTime(fileTime);

        outputtextList.get(suiteName).setTestCaseFailedStep("");
        outputtextList.get(suiteName).setTestCaseFailedSummary("");
        outputtextList.get(suiteName).setTestCaseFailedDetail("");

        //migraded from TestSetup
        String currentTime = Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss");
        outputtextList.get(suiteName).setTestSTime(currentTime);
        outputtextList.get(suiteName).setTestResult("Skipped");
        //outputtextList.get(suiteName).setTestCaseID();
        outputtextList.get(suiteName).setTestStepID(0);

        String htmlFile = "TestReport/" + overallReportFolder + "/" + testContext.getSuite().getName() + "/" + outputtextList.get(suiteName).getSuiteName() + "_" + outputtextList.get(suiteName).getTestName() + "_" + outputtextList.get(suiteName).getFileCreateTime() + ".html";
        generateTestReport_head(htmlFile, outputtextList.get(suiteName));


        String tempExecLog = outputtextList.get(suiteName).getTestSTime() + "： 套件：" + suiteName + "的用例：" + outputtextList.get(suiteName).getTestName() + " 正在执行中。。。";

        System.out.println(tempExecLog);
        /*
        if (record.equals("是")) {
            insertExecutionResult(GV.get("job_name"), GV.get("build_number"), GV.get("build_url"), "testcase", overallReportFolder, productName, projectName, suiteName, outputtextList.get(suiteName).testName, outputtextList.get(suiteName).testCaseID, outputtextList.get(suiteName).testCaseDesc, outputtextList.get(suiteName).getTestSTime(), outputtextList.get(suiteName).getTestSTime(), "", "Running", 0, 0, 0, "", "", "");
        }*/
    }

    // This belongs to ITestListener and will execute, once the Test set/batch is finished

    public void onFinish(ITestContext testContext) {

        String suiteName = testContext.getSuite().getName();

        //String suiteName = testContext.getSuite().getParameter("driverID");//need to revise in the future

        outputtextList.get(suiteName).setTestETime(Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss"));
        if (outputtextList.get(suiteName).getTestResult().equals("Pass")) {
            outputtextList.get(suiteName).setPassTestCaseCount();
        } else {
            outputtextList.get(suiteName).setFailTestCaseCount();
        }


        try {

            String tempExecLog = "\n" + outputtextList.get(suiteName).getTestETime() + "： 套件：" + suiteName + "的用例：" + outputtextList.get(suiteName).getTestName() + " 已经执行完，结果是" + outputtextList.get(suiteName).getTestResult();

            System.out.println(tempExecLog);
            if (record.equals("是")) {
                System.out.println("插入testcase的记录");

                insertExecutionResult(GV.get("job_name"), GV.get("build_number"), GV.get("build_url"), "testcase", overallReportFolder, productName, projectName, suiteName, outputtextList.get(suiteName).testName, outputtextList.get(suiteName).testCaseID, outputtextList.get(suiteName).testCaseDesc, outputtextList.get(suiteName).getTestSTime(), outputtextList.get(suiteName).getTestETime(), "", outputtextList.get(suiteName).getTestResult(), 0, 0, 0, outputtextList.get(suiteName).getTestCaseFailedSummary(), outputtextList.get(suiteName).getTestCaseFailedStep(), outputtextList.get(suiteName).getTestCaseFailedDetail());
            }

            String htmlFile = "TestReport/" + overallReportFolder + "/" + testContext.getSuite().getName() + "/" + outputtextList.get(suiteName).getSuiteName() + "_" + outputtextList.get(suiteName).getTestName() + "_" + outputtextList.get(suiteName).getFileCreateTime() + ".html";
            generateTestReport_foot(htmlFile, outputtextList.get(suiteName));

            String htmlFile2 = "TestReport/" + overallReportFolder + "/" + testContext.getSuite().getName() + "/" + "index.html";
            generateSuiteReport_TCDetail(htmlFile2, outputtextList.get(suiteName));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String handleImgname(String imgname){
        Pattern pattern = Pattern.compile("[\\\\/：:*?\"<>|]");
        Matcher matcher = pattern.matcher(imgname);
        String s = matcher.replaceAll("").trim();
        imgname = s.replace('：', '_');
        imgname = imgname.replace(':', '_');
        return imgname;
    }

    // This belongs to ITestListener and will execute only when the test is pass

    public void onTestSuccess(ITestResult tr) {
        String suiteName = tr.getTestContext().getSuite().getName();
        //String suiteName = tr.getTestContext().getSuite().getParameter("driverID");//need to revise in the future
        stepName = "";
        stepArray = null;

        WebDriver driver = TestSetup.drivers.get(suiteName);
        //System.out.println(tr.getTestContext().getSuite().getName()+":"+tr.getTestContext().getName()+":"+driver+":"+tr.getTestContext().getSuite().getParameter("driverID"));
        if (outputtextList.get(suiteName).isSkipOtherTCs()) {
            tr.setStatus(3);
            onTestSkipped(tr);
        } else {
            String methodResult = convertTestStatus(tr.getStatus());

            stepName = outputtextList.get(suiteName).getMethodName();
            File scrFile = new File("");


            outputtextList.get(suiteName).setMethodETime(Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss"));
            outputtextList.get(suiteName).setMethodResult(methodResult);
            if (outputtextList.get(suiteName).getTestResult().equals("Fail")) {
                outputtextList.get(suiteName).setTestResult("Fail");
            } else {
                outputtextList.get(suiteName).setTestResult("Pass");
            }
            //outputtextList.get(suiteName).setActualResult(outputtextList.get(suiteName).getExpectedResult());
            PreviousTestStatus = "Pass";


            if (outputtextList.get(suiteName).isScreenshot()) {
                try {
                    scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                } catch (UnhandledAlertException e) {
                    //scrFile = ((TakesScreenshot) driver.switchTo().alert()).getScreenshotAs(OutputType.FILE);
                } catch (NoSuchWindowException e) {
                    if (SwitchToNumWindow(suiteName, "", "1")) {
                        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    }
                } catch (NoSuchSessionException e) {
                    scrFile = null;
                } catch (WebDriverException e) {
                    if (SwitchToNumWindow(suiteName, "", "1")) {
                        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    }
                } catch (RuntimeException e) {
                    if (SwitchToNumWindow(suiteName, "", "1")) {
                        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    }
                }
            } else {
                scrFile = null;
            }
            //String imgname = Keywords.getCurrentTime("dd_MM_yy_HH_mm_ss") + "screenshot.png";
            String imgname = outputtextList.get(suiteName).getTestStepID() + "_" + stepName.replaceAll("\"|\\\\"," ") + "_" + "screenshot.png";
            try {
                //FileUtils.copyFile(scrFile, new File("TestReport\\new\\" + overallReportFolder + "\\" + outputtextList.get(suiteName).getSuiteName() + "\\screenshot\\" + outputtextList.get(suiteName).getTestName() + "\\" + imgname));
                if (null != scrFile) {
                    imgname = handleImgname(imgname);
                    FileUtils.copyFile(scrFile, new File("TestReport/" + overallReportFolder + "/" + tr.getTestContext().getSuite().getName() + "/screenshot/" + outputtextList.get(suiteName).getTestName() + "/" + imgname));
                }

                outputtextList.get(suiteName).setImgLocation("screenshot/" + outputtextList.get(suiteName).getTestName() + "/" + imgname);
            } catch (FileNotFoundException e) {
                outputtextList.get(suiteName).setImgLocation("");

            } catch (IOException el) {
                //el.printStackTrace();
            }
            //outputtextList.get(suiteName).setImgLocation("screenshot/" + outputtextList.get(suiteName).getTestName() + "/" + imgname);//screenshot\\"..\\..\\new\\"+ OutputText.suiteName + "_"+OutputText.suitetime +"\\


            // String fileN = "TestReport/tmpfile/" + overallReportFolder + "/" + tr.getTestContext().getSuite().getName()+ "/" + outputtextList.get(suiteName).getSuiteName() + "_" + outputtextList.get(suiteName).getTestName() + "_" + outputtextList.get(suiteName).getFileCreateTime() + ".txt";

            try {

                String htmlFile = "TestReport/" + overallReportFolder + "/" + tr.getTestContext().getSuite().getName() + "/" + outputtextList.get(suiteName).getSuiteName() + "_" + outputtextList.get(suiteName).getTestName() + "_" + outputtextList.get(suiteName).getFileCreateTime() + ".html";
                generateTestReport_stepDetail(htmlFile, outputtextList.get(suiteName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // This belongs to ITestListener and will execute only on the event of fail test

    public void onTestFailure(ITestResult tr) {

        String suiteName = tr.getTestContext().getSuite().getName();

        //String suiteName = tr.getTestContext().getSuite().getParameter("driverID");//need to revise in the future
        WebDriver driver = TestSetup.drivers.get(suiteName);

        if (outputtextList.get(suiteName).isSkipOtherTCs()) {
            tr.setStatus(3);
            onTestSkipped(tr);
        } else {
            stepName = "";
            stepArray = null;

            String methodResult = convertTestStatus(tr.getStatus());

            stepName = outputtextList.get(suiteName).getMethodName();

            File scrFile = new File("");
            outputtextList.get(suiteName).setMethodETime(Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss"));
            outputtextList.get(suiteName).setMethodResult("Fail");
            outputtextList.get(suiteName).setTestResult("Fail");


            if (outputtextList.get(suiteName).isScreenshot()) {
                try {
                    scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                } catch (UnhandledAlertException e) {
                    //scrFile = ((TakesScreenshot) driver.switchTo().alert()).getScreenshotAs(OutputType.FILE);
                } catch (NoSuchWindowException e) {
                    if (SwitchToNumWindow(suiteName, "", "1")) {
                        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    }
                } catch (NoSuchSessionException e) {
                    scrFile = null;
                } catch (WebDriverException e) {
                    if (SwitchToNumWindow(suiteName, "", "1")) {
                        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    }
                } catch (RuntimeException e) {
                    if (SwitchToNumWindow(suiteName, "", "1")) {
                        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    }
                }
            } else {
                scrFile = null;
            }
            //String imgname = Keywords.getCurrentTime("dd_MM_yy_HH_mm_ss") + "screenshot.png";
            String imgname = outputtextList.get(suiteName).getTestStepID() + "_" + stepName.replaceAll("\"|\\\\"," ") + "_" + "screenshot.png";
            try {
                if (null != scrFile) {
                    imgname = handleImgname(imgname);
                    FileUtils.copyFile(scrFile, new File("TestReport/" + overallReportFolder + "/" + tr.getTestContext().getSuite().getName() + "/screenshot/" + outputtextList.get(suiteName).getTestName() + "/" + imgname));//
                    outputtextList.get(suiteName).setImgLocation("screenshot/" + outputtextList.get(suiteName).getTestName() + "/" + imgname);
                }
            } catch (FileNotFoundException e) {
                outputtextList.get(suiteName).setImgLocation("");

            } catch (IOException el) {
                //el.printStackTrace();
            }

            try {

                String htmlFile = "TestReport/" + overallReportFolder + "/" + tr.getTestContext().getSuite().getName() + "/" + outputtextList.get(suiteName).getSuiteName() + "_" + outputtextList.get(suiteName).getTestName() + "_" + outputtextList.get(suiteName).getFileCreateTime() + ".html";
                generateTestReport_stepDetail(htmlFile, outputtextList.get(suiteName));

                if (outputtextList.get(suiteName).testStepId == 1 || PreviousTestStatus.equals("Pass")) {
                    outputtextList.get(suiteName).setTestCaseFailedStep(outputtextList.get(suiteName).getMethodName());
                    outputtextList.get(suiteName).setTestCaseFailedSummary(outputtextList.get(suiteName).getActualResult());
                    outputtextList.get(suiteName).setTestCaseFailedDetail(outputtextList.get(suiteName).getActualResult());

                }
                PreviousTestStatus = "Fail";

            } catch (Exception e) {
                if (outputtextList.get(suiteName).testStepId == 1 || PreviousTestStatus.equals("Pass")) {
                    outputtextList.get(suiteName).setTestCaseFailedStep(outputtextList.get(suiteName).getMethodName());
                    outputtextList.get(suiteName).setTestCaseFailedSummary(outputtextList.get(suiteName).getActualResult());
                    outputtextList.get(suiteName).setTestCaseFailedDetail(outputtextList.get(suiteName).getActualResult());

                }
                PreviousTestStatus = "Fail";
            }
            if (outputtextList.get(suiteName).getSkipOtherTCsWhenFailed() == 1) {
                outputtextList.get(suiteName).setSkipOtherTCs(true);
                if (null != driver) {
                    RemoteWebDriver webDriver = (RemoteWebDriver) driver;
                    if (null != webDriver.getSessionId()) {
                        driver.quit();
                    }
                }
            }
        }
    }

    // This belongs to ITestListener and will execute before the main test start (@Test)

    public void onTestStart(ITestResult tr) {

        String suiteName = tr.getTestContext().getSuite().getName();
        //String suiteName = tr.getTestContext().getSuite().getParameter("driverID");//need to revise in the future

        outputtextList.get(suiteName).setActualResult("");
        outputtextList.get(suiteName).setExpectedResult("");
        outputtextList.get(suiteName).setImgLocation("");
        outputtextList.get(suiteName).setMethodETime("");
        outputtextList.get(suiteName).setMethodResult("");
        outputtextList.get(suiteName).setStepErrorMsg("");
        outputtextList.get(suiteName).setMethodSTime(Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss"));

        outputtextList.get(suiteName).setMethodName(tr.getName());
        outputtextList.get(suiteName).setTestStepID(Integer.parseInt(outputtextList.get(suiteName).getTestStepID()) + 1);

    }

    // This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped

    public void onTestSkipped(ITestResult tr) {
        String suiteName = tr.getTestContext().getSuite().getName();

        if (tr.getAttribute("skipReport") == null) {


            // String suiteName = tr.getTestContext().getSuite().getParameter("driverID");//need to revise in the future
            stepName = "";
            stepArray = null;

            String methodResult = convertTestStatus(tr.getStatus());

            outputtextList.get(suiteName).setMethodResult(methodResult);
            outputtextList.get(suiteName).setMethodName(tr.getMethod().getDescription());
//            outputtextList.get(suiteName).setMethodName(tr.getName());
            outputtextList.get(suiteName).setMethodSTime(Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss"));
            outputtextList.get(suiteName).setMethodETime(Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss"));
            outputtextList.get(suiteName).setExpectedResult("由于上一步失败，此步骤不执行，无期望结果。");
            outputtextList.get(suiteName).setActualResult("由于上一步失败，此步骤不执行，无期望结果。");
            outputtextList.get(suiteName).setImgLocation("");
            outputtextList.get(suiteName).setStepErrorMsg("");
//            outputtextList.get(suiteName).setTestResult("Fail");
            PreviousTestStatus = "Fail";
            //outputtextList.get(suiteName).setTestStepID(Integer.parseInt(outputtextList.get(suiteName).getTestStepID()) + 1);


            stepName = outputtextList.get(suiteName).getMethodName();//No.160219

            //String fileN = "TestReport/tmpfile/" + overallReportFolder + "/" + tr.getTestContext().getSuite().getName() + "/" + suiteName + "_" + outputtextList.get(suiteName).getTestName() + "_" + outputtextList.get(suiteName).getFileCreateTime() + ".txt";

            try {

                String htmlFile = "TestReport/" + overallReportFolder + "/" + tr.getTestContext().getSuite().getName() + "/" + outputtextList.get(suiteName).getSuiteName() + "_" + outputtextList.get(suiteName).getTestName() + "_" + outputtextList.get(suiteName).getFileCreateTime() + ".html";
                generateTestReport_stepDetail(htmlFile, outputtextList.get(suiteName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            outputtextList.get(suiteName).setTestStepID(Integer.parseInt(outputtextList.get(suiteName).getTestStepID()) - 1);
        }

    }

    // This is just a piece of shit, ignore this

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

    }

    // This is the method which will be executed in case of test pass or fail

    // This will provide the information on the test

    private void printTestResults(ITestResult result) {

    }

    // This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test

    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {


    }

    // This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test

    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {

    }

    // This will return method names to the calling function

    private String returnMethodName(ITestNGMethod method) {
        return method.getMethodName();
    }

    public static String convertTestStatus(int i) {
        String testStatus = null;

        switch (i) {
            case 0:
                testStatus = "Notrun";
                break;
            case ITestResult.SUCCESS:
                testStatus = "Pass";
                break;
            case ITestResult.FAILURE:
                testStatus = "Fail";
                break;
            case ITestResult.SKIP:
                testStatus = "Skipped";
                break;
            default:
                testStatus = "Notrun";
                break;
        }
        return testStatus;
    }

    //generage each test case's report  - report head
    private void generateTestReport_head(String fileName, OutputText ot) {

        try {
            ot.writeFile(fileName, "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML l.0 Transitional//EN\"\"https://www.w3.org/TR/xhtml1/DTD/xhtml-transtional.dtd\" >\r\n ");
            ot.writeFile(fileName, "<html>\r\n");
            ot.writeFile(fileName, "<head>\r\n");
            ot.writeFile(fileName, "<meta http-equiv=\"Content-Type\"  content=\"text/html;charset=utf-8\"/>\r\n");
            ot.writeFile(fileName, "<style type=\"text/css\">\r\n");
            ot.writeFile(fileName, ".Pass{color:green}\r\n");
            ot.writeFile(fileName, ".Fail{color: red}\r\n");
            ot.writeFile(fileName, ".Skipped{color:grey}\r\n");
            ot.writeFile(fileName, ".Notrun{color:grey}\r\n");
            ot.writeFile(fileName, "</style>\r\n");
            ot.writeFile(fileName, "</head>\r\n");
            ot.writeFile(fileName, "<body>\r\n");
            ot.writeFile(fileName, "<font face='arial' size='3'>\r\n");//No.02
            ot.writeFile(fileName, "<h2><center>测试执行详细报告</center></h2>\r\n");//Test Execution Detailed Report
            ot.writeFile(fileName, "<h3><a href=\"index.html\">返回全部用例列表</a></h3>\r\n");//Test Execution Detailed Report
            ot.writeFile(fileName, "<h2><center><font size='3'>测试名称：" + ot.getTestName() + "</font></center></h2>\r\n");//Test Case Name:
            ot.writeFile(fileName, "<table id=\"workDemo\" border='0' width='95%'  height ='70' align='center' style='table-layout:fixed'>\r\n");
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<td align ='left' valign = 'center' width='4%'  bgcolor='#CCCCFF'><b><font color='#000000' font-family='courier' face='arial'  size='2'>步骤ID</font></b></td>\r\n");//Step ID
            ot.writeFile(fileName, "<td align ='left' valign = 'center' width='20%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>步骤名称</font></b></td>\r\n");//Step Name //12%
            //ot.writeFile(fileName, "<td align ='left' valign = 'center' width='25%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>期望结果</font></b></td>\r\n");//Expected Result
            ot.writeFile(fileName, "<td align ='left' valign = 'center' width='38%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>实际结果</font></b></td>\r\n");//Actual Result
            ot.writeFile(fileName, "<td align ='left' valign = 'center' width='7%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>开始时间</font></b></td>\r\n");//Start Date & Time
            ot.writeFile(fileName, "<td align ='left' valign = 'center' width='7%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>结束时间</font></b></td>\r\n");//End Date & Time
            ot.writeFile(fileName, "<td align ='left' valign = 'center' width='4%'  bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>结果</font></b></td>\r\n");//Status
            ot.writeFile(fileName, "<td align ='left' valign = 'center' width='20%'  bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>截图</font></b></td>\r\n");//Screenshot
            ot.writeFile(fileName, "</tr>\r\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //generage each test case's report  - test step detail line
    private void generateTestReport_stepDetail(String fileName, OutputText ot) {

        try {
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<td class='" + ot.getMethodResult() + "' align ='left' width='4%'  valign = 'center' bgcolor='#FFFFDC'><b><font font-family='courier face='arial' size='2'>" + ot.getTestStepID() + "</font></b></td>\r\n");//No.02
            ot.writeFile(fileName, "<td class='" + ot.getMethodResult() + "' align ='left' width='20%' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + ot.getMethodName() + "</font></b></td>\r\n");//No.02
            //ot.writeFile(fileName, "<td class='" + ot.getMethodResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + ot.getExpectedResult() + "</font></b></td>\r\n");//No.02
            ot.writeFile(fileName, "<td class='" + ot.getMethodResult() + "' align ='left' width='38%' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + ot.getActualResult().replaceAll("<","&lt;").replaceAll(">","&gt;") + "</font></b></td>\r\n");//No.02
            ot.writeFile(fileName, "<td class='" + ot.getMethodResult() + "' align ='left'  width='7%' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + ot.getMethodSTime() + "</font></b></td>\r\n");//No.02
            ot.writeFile(fileName, "<td class='" + ot.getMethodResult() + "' align ='left' width='7%'  valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + ot.getMethodETime() + "</font></b></td>\r\n");//No.02
            ot.writeFile(fileName, "<td class='" + ot.getMethodResult() + "' align ='left'  width='4%' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + ot.getMethodResult() + "</font></b></td>\r\n");//No.02

            // if (ot.getMethodResult().equals("Pass") || ot.getMethodResult().equals("Fail")) {
            if (!ot.getImgLocation().equals("")) {
                ot.writeFile(fileName, "<td align = 'left'  width='20%' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>\r\n" + "<a href='" + ot.getImgLocation() + "'target='_blank'>" + "<img width ='200'  height='360'  border='no'  alt='Click to maximize' src='" + ot.getImgLocation() + "'></a>\r\n" + "</font></b></td>\r\n");//No.02

            } else {
                ot.writeFile(fileName, "<td align ='left' width='20%'  valign='center'  bgcolor='#FFFFDC' align=' center'><b><font font-family='courier' face='arial' size='2'></font></b></td>\r\n");//No.02
            }
            ot.writeFile(fileName, "</tr>\r\n");

            String newScrenshotPaht=ot.getImgLocation().equals("")?"":reportFolder+"/"+ot.getSuiteName()+"/"+ot.getImgLocation();
            String tempActualResult=ot.getActualResult().length()>=2000?ot.getActualResult().substring(0,1999):ot.getActualResult();

            ot.setTCStepsResultsJson("{\n" +
                    "        \"recordId\": \""+recordId+"\",\n" +
                    "        \"taskId\": \""+taskId+"\",\n" +
                    "        \"jobNumber\": \""+overallReportFolder+"\",\n" +
                    "        \"suiteName\": \""+ot.getSuiteName()+"\",\n" +
                    "        \"testcaseName\": \""+ot.getTestName()+"\",\n" +
                    "        \"stepSort\": "+ot.testStepId+",\n" +
                    "        \"stepId\": \""+ot.getTestStepID()+"\",\n" +
                    "        \"stepName\": \""+ot.getMethodName().replaceAll("\"|\\\\"," ")+"\",\n" +
                    "        \"actualResult\": \""+tempActualResult.replaceAll("\"|\\\\"," ")+"\",\n" +
                    "        \"runStarttime\": \""+ot.getMethodSTime()+"\",\n" +
                    "        \"runEndtime\": \""+ot.getMethodETime()+"\",\n" +
                    "        \"status\": \""+ot.getMethodResult()+"\",\n" +
                    "        \"screenshot\": \""+newScrenshotPaht+"\"\n" +
                    "    }");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //generage each test case's report  - report head
    private void generateTestReport_foot(String fileName, OutputText ot) {

        try {
            ot.writeFile(fileName, "</table>\r\n");
            ot.writeFile(fileName, "<table border='0' width='50%' align = center>\r\n");
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<td colspan=2 width='15%'  bgcolor='#FFFFFF' valign='top' align='center'><b><font face='arial' size='2' color='Black'>总结 </font></b></td>\r\n");//Summary
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<td width='15%' bgcolor='#C3FDB8' valign='top' align='center'><b><font face='arial' size='2' color='Black'>开始时间</font></b></td>\r\n");//Start Date & Time
            ot.writeFile(fileName, "<td width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial' size='2' color='Green'>" + ot.getTestSTime() + "</font></b></td>\r\n");//No.02
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<td width='15%' bgcolor='#C3FDB8' valign='top' align='center'><b><font face='arial' size='2' color=' Black'>结束时间</font></b></td>\r\n");//End Date & Time
            ot.writeFile(fileName, "<td width='15%' bgcolor='#E8FFE8' valign= top' align='center'><b><font face='arial' size='2' color='Green'>" + ot.getTestETime() + "</font></b></td>\r\n");//No.02
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<td width='15%' bgcolor='#C3FDB8' valign='top' align='center'><b><font face='arial' size='2' color='Black'>持续时间</font></b></td>\r\n");//Duration
            ot.writeFile(fileName, "<td width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial' size='2' color='Green'>" + subStractString(ot.getTestSTime(), ot.getTestETime()) + "</font></b></td>\r\n");//No.02
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "<td width='15%' bgcolor='#C3FDB8' valign='top'  align ='center'><b><font face='arial'  size='2'  color='Black'>最终结果</font></b></td>\r\n");//No.02
            ot.writeFile(fileName, "<td class=" + ot.getTestResult() + " width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial' size='2'>" + ot.getTestResult() + "</font></b></td>\r\n");//No.02
            ot.writeFile(fileName, "<tr>\r\n");
            ot.writeFile(fileName, "</table>\r\n");
            ot.writeFile(fileName, "<script type=text/javascript>\r\n");
            ot.writeFile(fileName, "<Function ImgShow(evt){\r\n");
            ot.writeFile(fileName, " var ImgTag=(window.event)?event.srcElement:evt.target;\r\n");
            ot.writeFile(fileName, " var ImgPath=ImgTag.src.replace(/\\_\\d\\./,\"_4.\");\r\n");
            ot.writeFile(fileName, " var tagTop=Math.max(document.documentElement.scrollTop,document.bodyscrollTop);\r\n");
            ot.writeFile(fileName, " var tag=document.createElement(\"div\");\r\n");
            ot.writeFile(fileName, " tag.style.cssText=\"width:100%;height:\"+Math.max(document.body.clientHeight,document.body.offsetHeight,document.documentElement.clientHeight)+\"px;position:absolute;background:black;top:O;filter:Alpha(Opacity=80);Opacity:0.8;\";\r\n");

            ot.writeFile(fileName, " tag.ondblclick=closes;\r\n");
            ot.writeFile(fileName, " var tagImg=document.createElement(\"div\");\r\n");
            ot.writeFile(fileName, " tagImg.style.cssText=\"font:12px/18px verdana;overflow:auto;text-align:center;position:absolute;width:200px;border:5px solid white;background:white;color:white;left:\"+(parseInt(document.body.offsetWidth)/2-100)+\"px;top:\"+(document.documentElement.clientHeight/3+tagTop)+\"px;\"\r\n");
            ot.writeFile(fileName, " tagImg.innerHTML=\" <div style='padding:10px;background:#cccccc;border:1px solid white'><img src='Ioading.gif/><br/><br/>/<b style='color:#999999;font-weight:normal'>Image loading...</b><br/></div>\";\r\n");
            ot.writeFile(fileName, " tagImg.oncontextmenu=fuction(){var clsOK=confirm(\"Are you sure to close the picture?\"} ;if(clsOK){closes();}; return false};\r\n");
            ot.writeFile(fileName, " var closeTag=document.createElement(\"div\");\r\n");
            ot.writeFile(fileName, " closeTagstyle.cssText=\"display:none;position:absolute;left:10px:top:10px;color:black;\";\r\n");
            ot.writeFile(fileName, " var closesHtml=\"<b style='background:red;border:lpx solid white;filter:Alpha(Opacity=50);Opacity:0.5;cursor:pointer;'>&nbsp;close&nbsp;</b>\";\r\n");
            ot.writeFile(fileName, " closeTag.innerHTML=closesHtml+\" double click to zoom in\";\r\n");
            ot.writeFile(fileName, " closeTag.onclick=closes;\r\n");
            ot.writeFile(fileName, " document.body.appendChild(tag);\r\n");
            ot.writeFile(fileName, " document.body.appendChild(tagImg);\r\n");
            ot.writeFile(fileName, " var img=new Image();\r\n");
            ot.writeFile(fileName, " img.src=imgPath;\r\n");
            ot.writeFile(fileName, " img.style.cssText=\" border:1px solid #cccccc;filter:Alpha(Opacity=0);Opacity:O;cursor:pointer\";\r\n");
            ot.writeFile(fileName, " var barShow,imgTime;\r\n");
            ot.writeFile(fileName, " img.complete?ImgOK():img.onload=ImgOK;\r\n");
            ot.writeFile(fileName, " function ImgOK(){\r\n");
            ot.writeFile(fileName, " var Stopl=false,Stop2=false,temp=0;\r\n");
            ot.writeFile(fileName, " var tx=tagImg.offsetWidth,ty=tagImg.offsetHeight;\r\n");
            ot.writeFile(fileName, " var ix=img.width,iy=img.height;\r\n");
            ot.writeFile(fileName, " var sx=document.documentElement.clientWidth,sy=window.innerHeight||document.documentElement.clientHeight;\r\n");
            ot.writeFile(fileName, " if(iy>sy||ix>sx){\r\n");
            ot.writeFile(fileName, " var yy=sy-100;\r\n");
            ot.writeFile(fileName, " var xx=(ix/iy)*yy;\r\n");
            ot.writeFile(fileName, " }else{\r\n");
            ot.writeFile(fileName, " var xx=ix+4;\r\n");
            ot.writeFile(fileName, " var yy=iy+3;\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " img.style.width=xx-4+'px';\r\n");
            ot.writeFile(fileName, " img.style.height=yy-3+'px';\r\n");
            ot.writeFile(fileName, " if(ix<sx&&iy<sy){tagImg.alt=\"\";closeTag.innerHTML=closesHtml;};\r\n");
            ot.writeFile(fileName, " var maxTime=setInterval(funaion(){\r\n");
            ot.writeFile(fileName, " temp+=35;\r\n");
            ot.writeFile(fileName, " if(tx+temp<xx){\r\n");
            ot.writeFile(fileName, " tagImgstyle.width=(tx+temp)+\"px\";\r\n");
            ot.writeFile(fileName, " tagImgstyle.left=(sx-(tx+temp)/2+\"px\";\r\n");
            ot.writeFile(fileName, " }else{\r\n");
            ot.writeFile(fileName, " Stop1=true;\r\n");
            ot.writeFile(fileName, " tagImgstyle.width=tx+\"px\";\r\n");
            ot.writeFile(fileName, " tagImgstyle.left=(sx-tx)/2+\"px\";\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " if((ty+temp)<yy){\r\n");
            ot.writeFile(fileName, " tagImgstyle.height=(ty+temp)+\"px\";\r\n");
            ot.writeFile(fileName, " tagImgstyle.top=(tagTop+((sy-(ty+temp))/2))+\"px\";\r\n");
            ot.writeFile(fileName, " }else{\r\n");
            ot.writeFile(fileName, " Stop2=true;");
            ot.writeFile(fileName, " tagImg.style.height=yy+\"px\"';\r\n");
            ot.writeFile(fileName, " tagImg.style.top=(tagTop+((sy-yy)/2))+\"px\";\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " if(Stopl&&Stop2){\r\n");
            ot.writeFile(fileName, " clearInterval(maxTime);\r\n");
            ot.writeFile(fileName, " tagImg.appendChild(img);\r\n");
            ot.writeFile(fileName, " temp=0;\r\n");
            ot.writeFile(fileName, " imgOPacity();\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " },1)\r\n");

            ot.writeFile(fileName, " function imgOPacity(){\r\n");
            ot.writeFile(fileName, " temp+=1O;\r\n");
            ot.writeFile(fileName, " img.style.filter=\"alpha(opacity=\"+temp+\")\";\r\n");
            ot.writeFile(fileName, " img.style.opacity=temp/1OO;\r\n");
            ot.writeFile(fileName, " imgTime=setTimeout(imgOPacity,1)\r\n");
            ot.writeFile(fileName, " if(temp>100)clearTimeout(imgTime);\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " tagImg.innerHTML=\"\";\r\n");
            ot.writeFile(fileName, " tagImg.appendChild(closeTag);\r\n");
            ot.writeFile(fileName, " \r\n");
            ot.writeFile(fileName, " if(ix>xx||iy>W){\r\n");
            ot.writeFile(fileName, " img.alt=\" Drag with left button,double click to zoom in\";\r\n");
            ot.writeFile(fileName, " img.ondblclick=function(){\r\n");
            ot.writeFile(fileName, " if(tagImg.offsetWidth<img.offsetWidth||tagImg.offsetHeight<img.offsetHeight){\r\n");
            ot.writeFile(fileName, " img.style.width=\"auto\";\r\n");
            ot.writeFile(fileName, " img.style.height=\"100%\";\r\n");
            ot.writeFile(fileName, " img.alt=\"double click to zoom in\";\r\n");
            ot.writeFile(fileName, " img.onmousedown=null;\r\n");
            ot.writeFile(fileName, " closeTag.style.top=\"10px\";\r\n");
            ot.writeFile(fileName, " closeTag.style.left=\"10px\";\r\n");
            ot.writeFile(fileName, " tagImgstyle.overf10w=\"vsible\";\r\n");
            ot.writeFile(fileName, " tagImgstyle.width =img.offsetWidth+\"px\";\r\n");
            ot.writeFile(fileName, " tagImgstyle.left=((sx-img.offsetWidth)/2)+\"px\";\r\n");
            ot.writeFile(fileName, " }else{\r\n");
            ot.writeFile(fileName, " img.style.width=ix+\"px\";\r\n");
            ot.writeFile(fileName, " img.style.height=iy+\"px\";\r\n");
            ot.writeFile(fileName, " img.alt=\"double click to zoom in\";\r\n");
            ot.writeFile(fileName, " img.onmousedown=dragDown;\r\n");
            ot.writeFile(fileName, " tagImg.style.overf10w=\"auto\";\r\n");
            ot.writeFile(fileName, " tagImg.style.width=(ix<sx-100?ix+20:sx-100)+\"px\";\r\n");
            ot.writeFile(fileName, " tagImg.style.left=(sx-(ix<sx-100?ix+20:sx-100》/2)+\"px\";\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " img.onmousedown=dragDown;\r\n");
            ot.writeFile(fileName, " tagImg.onmousemove=barHidden;\r\n");
            ot.writeFile(fileName, " tagImg.onmouseout=moveStop;\r\n");
            ot.writeFile(fileName, " document.onmouseup=moveStop;\r\n");
            ot.writeFile(fileName, " }else{\r\n");
            ot.writeFile(fileName, " tagimg.style.ovef10w=\"visible\";\r\n");
            ot.writeFile(fileName, " tagImg.onmousemove=barHidden\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " function dragDown(a){\r\n");
            ot.writeFile(fileName, " img.style.cursor=\"move\";\r\n");
            ot.writeFile(fileName, " var evts=allwindow.event;\r\n");
            ot.writeFile(fileName, " var onx=evts.clientX,ony=evts.clientY;\r\n");
            ot.writeFile(fileName, " var sox=tagImg.scrollLeft,soy=tagImg.scrollTop;\r\n");
            ot.writeFile(fileName, " var sow=img.width+2-tagImg.clientWidth,soh=img.height+2-tagImg.clientHeight;\r\n");
            ot.writeFile(fileName, " var xxleft,yyTop;\r\n");
            ot.writeFile(fileName, " document.onmousemove=function(e){\r\n");
            ot.writeFile(fileName, " var evt=e||window.event;\r\n");
            ot.writeFile(fileName, " xxleft=sox-(evt.clientX_onx)<O?\"O\"sox-(evt.clientX-onx)>sow?sow:sox-(evt.clientX-onx);\r\n");
            ot.writeFile(fileName, " yytop=soy-(evt.clientY-ony)<O?\"O\":soy-(evt.clientY-ony)>soh?soh:soy-(evt.clientY-ony);\r\n");
            ot.writeFile(fileName, " tagImg.scrollTop=yytop;\r\n");
            ot.writeFile(fileName, " tagImg scrollLeft=xxleft;\r\n");
            ot.writeFile(fileName, " closeTag.style.top=(yytop+10)+\"px\";r\n");
            ot.writeFile(fileName, " closeTag.style.left=xxleft+10)+\"px\";\r\n");
            ot.writeFile(fileName, " return false;\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " return false;\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " function barHidden(){\r\n");
            ot.writeFile(fileName, " clearTimeOut(barShow);\r\n");
            ot.writeFile(fileName, " closeTag.style.top=(tagImg.scrollTop+ 10)+\"px\";\r\n");
            ot.writeFile(fileName, " closeTag.style.left=(tagImg.scrollLeft +10)+\"px\";\r\n");
            ot.writeFile(fileName, " if(closeTag.style.display==\"none\")closeTag.style.display=\" block\";\r\n");
            ot.writeFile(fileName, " barShow=setTimeout(function(){closeTag.style.dsplay=\"none\";},lOOO);\r\n ");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " function closes(){\r\n");
            ot.writeFile(fileName, " document.body.removeChild(tag);\r\n");
            ot.writeFile(fileName, " document.body.removeChild(tagImg);\r\n");
            ot.writeFile(fileName, " clearTimeout(barShow);\r\n");
            ot.writeFile(fileName, " clearTimeout(imgTime);\r\n");
            ot.writeFile(fileName, " document.onmouseup=null;\r\n");
            ot.writeFile(fileName, " tag=img=tagImg=closeTag=null;\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " funtion moveStop(){\r\n");
            ot.writeFile(fileName, " document.onmousemove=null;\r\n");
            ot.writeFile(fileName, " tagImg.onmousemove=barHidden;\r\n");
            ot.writeFile(fileName, " imgstyle.cursor=\"pointery\";\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " }\r\n");
            ot.writeFile(fileName, " ／／事件+绑定部分\r\n");

            ot.writeFile(fileName, " if(document.getElementById(\"workDemo\")){\r\n");
            ot.writeFile(fileName, " var workTag=document.getElementById(\"workDemo\");\r\n");
            ot.writeFile(fileName, " var workImg=workTag.getElementsByTagName(\"img\"); \r\n");
            ot.writeFile(fileName, " var worka =workTag.getElementsByTagName(\"a\");\r\n");
            ot.writeFile(fileName, " for(var i=0;i<workImg.length; i++){workImg[i].onclick=ImgShow;worka[i].href=\"##\"}\r\n");
            ot.writeFile(fileName, " </script>\r\n");
            ot.writeFile(fileName, " </body>\r\n");
            ot.writeFile(fileName, " </html>\r\n");

            insertTCStepDetails(ot.getTCStepsResultsJson());
            ot.resetTCStepsResultsJson();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    //generage each suite case's report  - report head
    private void generateSuiteReport_head(String fileName, OutputText ot) {

        try {
            ot.suiteReportHead.setLength(0);
            ot.suiteReportHead.append("<html>\r\n");
            ot.suiteReportHead.append("<title>Selenium  UI test report</title>\r\n");
            ot.suiteReportHead.append("<head>\r\n");
            ot.suiteReportHead.append("<meta content=\"text/html;charset=utf-8\" http-equiv=\"content-type\">\r\n");
            ot.suiteReportHead.append("<style type=\"text/css\">\n" +
                    ".Pass {color:green}\n" +
                    ".Fail {color:red}\n" +
                    "td.active\n" +
                    "{\n" +
                    "outline-style:outset;\n" +
                    "outline-color:#FF0000;\n" +
                    "}\n" +
                    "</style>\n");

            ot.suiteReportHead.append("<script type=\"text/JavaScript\">\r\n");
            ot.suiteReportHead.append("function timedRefresh(timeoutPeriod){ \r\n");
            ot.suiteReportHead.append("setTimeout('10cation. reload(true); :  (timeoutPeriod);')\r\n");
            ot.suiteReportHead.append("}\r\n");
            ot.suiteReportHead.append("timedRefresh(10000);\r\n");
            ot.suiteReportHead.append("function Sort(){\n" +
                    "\tvar table=document.getElementById(\"Sortable\");\n" +
                    "    //var headers=table.getElementsByTagName(\"th\");\n" +
                    "\n" +
                    "            var flag=table.getElementsByTagName(\"th\")[5].className;\n" +
                    "\n" +
                    "                // sortrows(table,n);\n" +
                    "\n" +
                    "                var tbody=table.getElementsByTagName(\"tbody\")[0];//第一个<tbody>\n" +
                    "                var rows=tbody.getElementsByTagName(\"tr\");//tbody中的所有行\n" +
                    "                rows=Array.prototype.slice.call(rows,0);//真实数组中的快照\n" +
                    "\n" +
                    "                //基于第n个<td>元素的值对行排序\n" +
                    "                rows.sort(function(row1,row2){\n" +
                    "                    var cell1=row1.getElementsByTagName(\"td\")[5];//获得第n个单元格\n" +
                    "                    var cell2=row2.getElementsByTagName(\"td\")[5];\n" +
                    "                    var val1=cell1.textContent||cell1.innerText;//获得文本内容\n" +
                    "                    var val2=cell2.textContent||cell2.innerText;\n" +
                    "\n" +
                    "                    if(val1<val2){\n" +
                    "                        return -1;\n" +
                    "                    }else if(val1>val2){\n" +
                    "                        return 1;\n" +
                    "                    }else{\n" +
                    "                        return 0;\n" +
                    "                    }\n" +
                    "                });\n" +
                    "                if(flag==\"as\"){\n" +
                    "                    rows.reverse();\n" +
                    "\t\t\t\t\ttable.getElementsByTagName(\"th\")[5].setAttribute(\"class\",\"desc\");\n" +
                    "                }else{\n" +
                    "\t\t\t\ttable.getElementsByTagName(\"th\")[5].setAttribute(\"class\",\"as\");\n" +
                    "\t\t\t\t}\n" +
                    "                //在tbody中按它们的顺序把行添加到最后\n" +
                    "                //这将自动把它们从当前位置移走，故没必要预先删除它们\n" +
                    "                //如果<tbody>还包含了除了<tr>的任何其他元素，这些节点将会悬浮到顶部位置\n" +
                    "                for(var i=0;i<rows.length;i++){\n" +
                    "                    tbody.appendChild(rows[i]);\n" +
                    "                }\n" +
                    "\n" +
                    "                           \n" +
                    "\t\t\t}");
            ot.suiteReportHead.append("</script>\r\n");
            ot.suiteReportHead.append("</head>\r\n");
            ot.suiteReportHead.append("<body>\r\n");
            ot.suiteReportHead.append("<font face='arial'size='3'>\r\n");//No.02
            ot.suiteReportHead.append("<h2><center><center>自动化测试报告 - suite: " + ot.getSuiteName() + "</h2>\r\n");//Selenium report
            ot.suiteReportHead.append("<font face='arial'size='2'>\r\n");
            ot.suiteReportHead.append("<h2><center>执行于：" + ot.getSuiteTime() + "<center></h2>\r\n<div id=\"body\">\r\n");//As of:

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //generage each suite's report  - test case detail line
    private void generateSuiteReport_TCDetail(String fileName, OutputText ot) {

        try {

           /* if(record.equals("是")) {

                DB_insertExecutionRecord(overallReportFolder, "testcase", productName, projectName,ot.getSuiteName(), ot.getTestName(), ot.getTestCaseDesc(), ot.getTestSTime(), ot.getTestETime(), subStractString(ot.getTestSTime(), ot.getTestETime()), ot.getTestResult(), 0, 0, 0);
            }*/
            ot.suiteReportBody.append("<tr>\r\n");
            //ot.writeFile(fileName, "<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC'><b><font font-family='courier' face='arial' size='2'>" + ot.getTestCaseID() + "</font></b></td >\r\n ");//No.0 2
            ot.suiteReportBody.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC'><b><font font-family='courier' face='arial' size='2'>" + ot.getTestName() + "</font></b></td>\r\n");//No.02
            ot.suiteReportBody.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC'><font font-family='courier' face='arial' size='2'>" + ot.getTestCaseDesc() + "</font></td>\r\n");//No.02
            ot.suiteReportBody.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + ot.getTestSTime() + "</font ></b></td>\r\n ");//No.02
            ot.suiteReportBody.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + ot.getTestETime() + "</font></b></td>\r\n");//No.02
            ot.suiteReportBody.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + subStractString(ot.getTestSTime(), ot.getTestETime()) + "</font></b></td>\r\n");//No.02
            ot.suiteReportBody.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'><a href='" + ot.getSuiteName() + "_" + ot.getTestName() + "_" + ot.getFileCreateTime() + ".html'>" + ot.getTestResult() + "</a></font></b></td>\r\n");//OutputText.getFileCreateTime()
            ot.suiteReportBody.append("</tr>");

            if (ot.getTestResult().equals("Fail")) {
                ot.suiteReportBody_failed.append("<tr>\r\n");
                //ot.writeFile(fileName, "<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC'><b><font font-family='courier' face='arial' size='2'>" + ot.getTestCaseID() + "</font></b></td >\r\n ");//No.0 2
                ot.suiteReportBody_failed.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC'><b><font font-family='courier' face='arial' size='2'>" + ot.getTestName() + "</font></b></td>\r\n");//No.02
                ot.suiteReportBody_failed.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC'><font font-family='courier' face='arial' size='2'>" + ot.getTestCaseDesc() + "</font></td>\r\n");//No.02
                ot.suiteReportBody_failed.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + ot.getTestSTime() + "</font ></b></td>\r\n ");//No.02
                ot.suiteReportBody_failed.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + ot.getTestETime() + "</font></b></td>\r\n");//No.02
                ot.suiteReportBody_failed.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + subStractString(ot.getTestSTime(), ot.getTestETime()) + "</font></b></td>\r\n");//No.02
                ot.suiteReportBody_failed.append("<td class='" + ot.getTestResult() + "' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'><a href='" + ot.getSuiteName() + "_" + ot.getTestName() + "_" + ot.getFileCreateTime() + ".html'>" + ot.getTestResult() + "</a></font></b></td>\r\n");//OutputText.getFileCreateTime()
                ot.suiteReportBody_failed.append("</tr>");

            }
        } catch (Exception e) {

        }
    }

    //generage each suite's report  - report head
    private void generateSuitReport_foot(String fileName, String failedfileName, OutputText ot) {

        try {
            String suiteResult = ot.getFailTestCount() == 0 && ot.getPassTestCaseCount() != 0 ? "Pass" : "Fail";

            /*if(record.equals("是")) {

                DB_insertExecutionRecord(overallReportFolder, "suite", productName, projectName,ot.getSuiteName(), "", "", ot.getSuiteSTime(), ot.getSuiteETime(), subStractString(ot.getSuiteSTime(), ot.getSuiteETime()), suiteResult, (ot.getPassTestCaseCount() + ot.getFailTestCount()), ot.getPassTestCaseCount(), ot.getFailTestCount());
                 CloseConnectionToExecutionDB(executionConnection);
            }*/

            ot.suiteReportHead.append("<table border='0' width='50%' align =center style=\"margin-bottom: 20px;\">\r\n");
            ot.suiteReportHead.append("<tr>\r\n");
            ot.suiteReportHead.append("<td colspan=2 width='15%' bgcolor='#FFFFFF' valign='top' align='center'><b><font face='arial' size='4' color='Blue'>总结</font></b></td>\r\n");//Summary
            ot.suiteReportHead.append("</tr>\r\n");
            ot.suiteReportHead.append("<tr>\r\n");
            ot.suiteReportHead.append("<td width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial' size='2' color='Green'>通过用例数：</font></b></td>\r\n");//Total Test Cases Passed:
            ot.suiteReportHead.append("<td width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial' size='2' color='Green' id= 'tdIntPass'>" + (ot.getPassTestCaseCount() + " (" + ((float) ot.getPassTestCaseCount() / ((float) ot.getPassTestCaseCount() + (float) ot.getFailTestCount()) * 100)) + "%)</font></b></td>\r\n");//No.02
            ot.suiteReportHead.append("</tr>\r\n");
            ot.suiteReportHead.append("<tr>\r\n");
            ot.suiteReportHead.append("<td width='15%' bgcolor='#FFE6FF' valign='top' align='center'><b><font face='arial'  size='2' color='Red' >失败用例数：</font></b></td>\r\n");//Total Test Cases Failed:
            ot.suiteReportHead.append("<td width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial'  size='2' color='Green'  id='intFail'>" + (ot.getFailTestCount() + " (" + ((float) ot.getFailTestCount() / ((float) ot.getPassTestCaseCount() + (float) ot.getFailTestCount()) * 100)) + "%)</font></bx/td>\r\n");//No.02
            ot.suiteReportHead.append("</tr>\r\n");
            ot.suiteReportHead.append("<tr>\r\n");
            ot.suiteReportHead.append("<td width='15%'  bgcolor='#C3FOB8' valign='top' align='center'><b><font face='arial' size='2' color='Black'>测试用例总数：</font></b></td>\r\n");//Total Test Case:
            ot.suiteReportHead.append("<td width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial' size='2' color='Green' id='intTotal'>" + (ot.getPassTestCaseCount() + ot.getFailTestCount()) + "</font></b></td>\r\n");//No.02
            ot.suiteReportHead.append("</tr>\r\n");
            ot.suiteReportHead.append("</table>\n");
            ot.writeFile(failedfileName, ot.suiteReportHead.toString());
            StringBuilder suiteReportHead_failed = new StringBuilder();
            ot.suiteReportHead.append("<table border='0' width='20%' align ='left' style=\"margin-bottom: 2px;\">\n" +
                    "<tr>\n" +
                    "<td width='15%'  bgcolor='#E8FFE8' valign='top' align='center' class='active'><b><font face='arial' size='2' color='#4169E1'><a href=\"index.html\" color='#FFFFFF' >全部用例</a></font></b></td>\n" +
                    "<td width='15%' bgcolor='#E8FFE8' valign='top' align='center' ><b><font face='arial' size='2' color='Green' id='intTotal'><a href=\"index-failed.html\" >失败用例</a></font></b></td>\n" +
                    "</tr>\n" +
                    "</table>\n");
            ot.suiteReportHead.append("<table id=\"Sortable\" border='0' width='95%'  height='70' align ='left'>\n" +
                    "<caption><b>全部用例</b></caption>\n" +
                    "<thead>");
            suiteReportHead_failed.append("<table border='0' width='20%' align ='left' style=\"margin-bottom: 2px;\">\n" +
                    "<tr>\n" +
                    "<td width='15%'  bgcolor='#E8FFE8' valign='top' align='center'  ><b><font face='arial' size='2' color='#4169E1'><a href=\"index.html\" color='#FFFFFF' >全部用例</a></font></b></td>\n" +
                    "<td width='15%' bgcolor='#E8FFE8' valign='top' align='center' class='active' ><b><font face='arial' size='2' color='Green' id='intTotal'><a href=\"index-failed.html\" >失败用例</a></font></b></td>\n" +
                    "</tr>\n" +
                    "</table>\n");
            suiteReportHead_failed.append("<table id=\"Sortable\" border='0' width='95%'  height='70' align ='left'>\n" +
                    "<caption><b>失败用例</b></caption>\n" +
                    "<thead>");
            ot.suiteReportHead.append("<tr>\r\n");
            // ot.writeFile(fileName, "<td align ='left' valign = 'center' width='10%' bgcolor='#CCCCFF'><b><font color='#000000' font-family='courier' face='arial' size='2'>用例ID</font></b></td>\r\n");//Test Case ID
            ot.suiteReportHead.append("<th align ='left' valign = 'center' width='15%' bgcolor='#CCCCFF'><b><font color='#000000' font-family='courier' face='arial' size='2'>用例名</font></b></td>\r\n");//Test Case Name
            ot.suiteReportHead.append("<th align ='left' valign = 'center' bgcolor='#CCCCFF'><b><font color='#000000' font-family='courier' face='arial' size='2'>用例描述</font></b></td>\r\n");//Test Case Desc
            ot.suiteReportHead.append("<th align ='left' valign = 'center' width='12%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>开始时间</font></b></td>\r\n");//Start Date & Time
            ot.suiteReportHead.append("<th align ='left' valign = 'center' width='12%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>结束时间</font></b></td>\r\n");//End Date & Time
            ot.suiteReportHead.append("<th align ='left' valign = 'center' width='12%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>持续时间</font></b></td>\r\n");//Duration
            ot.suiteReportHead.append("<th align ='left' valign = 'center' width='5%'  bgcolor='#CCCCFF' align='center'><b><font co1or='#000000' font-family='courier' face='arial' size='2' onclick=\"Sort()\" class=\"as\" style=\"cursor:pointer\">结果</font></b></td>\r\n");//Status
            ot.suiteReportHead.append("</tr>\n" +
                    "            </thead>\n" +
                    "            <tbody>\r\n");
            suiteReportHead_failed.append("<tr>\r\n");
            // ot.writeFile(fileName, "<td align ='left' valign = 'center' width='10%' bgcolor='#CCCCFF'><b><font color='#000000' font-family='courier' face='arial' size='2'>用例ID</font></b></td>\r\n");//Test Case ID
            suiteReportHead_failed.append("<th align ='left' valign = 'center' width='15%' bgcolor='#CCCCFF'><b><font color='#000000' font-family='courier' face='arial' size='2'>用例名</font></b></td>\r\n");//Test Case Name
            suiteReportHead_failed.append("<th align ='left' valign = 'center' bgcolor='#CCCCFF'><b><font color='#000000' font-family='courier' face='arial' size='2'>用例描述</font></b></td>\r\n");//Test Case Desc
            suiteReportHead_failed.append("<th align ='left' valign = 'center' width='12%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>开始时间</font></b></td>\r\n");//Start Date & Time
            suiteReportHead_failed.append("<th align ='left' valign = 'center' width='12%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>结束时间</font></b></td>\r\n");//End Date & Time
            suiteReportHead_failed.append("<th align ='left' valign = 'center' width='12%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>持续时间</font></b></td>\r\n");//Duration
            suiteReportHead_failed.append("<th align ='left' valign = 'center' width='5%'  bgcolor='#CCCCFF' align='center'><b><font co1or='#000000' font-family='courier' face='arial' size='2' onclick=\"Sort()\" class=\"as\" style=\"cursor:pointer\">结果</font></b></td>\r\n");//Status
            suiteReportHead_failed.append("</tr>\n" +
                    "            </thead>\n" +
                    "            <tbody>\r\n");
            ot.suiteReportFoot.append("</tbody>\n" +
                    "        </table>\r\n");
            ot.suiteReportFoot.append("\t\t</div>\r\n");
            ot.suiteReportFoot.append("</font>\r\n");
            ot.suiteReportFoot.append("</body>\r\n");
            ot.suiteReportFoot.append("</html>\r\n");

            ot.writeFile(fileName, ot.suiteReportHead.toString());
            ot.writeFile(fileName, ot.suiteReportBody.toString());
            ot.writeFile(fileName, ot.suiteReportFoot.toString());
            ot.writeFile(failedfileName, suiteReportHead_failed.toString());
            ot.writeFile(failedfileName, ot.suiteReportBody_failed.toString());
            ot.writeFile(failedfileName, ot.suiteReportFoot.toString());
        } catch (Exception e) {

        }

    }

    public static String subStractString(String stime, String etime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date datel = sdf.parse(stime);
        Date date2 = sdf.parse(etime);
        long subStractTime = (date2.getTime() - datel.getTime());
        long hour = subStractTime / (60 * 60 * 1000);
        long minute = (subStractTime - hour * 60 * 60 * 1000) / (60 * 1000);
        long second = (subStractTime - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
        if (second >= 60) {
            second = second % 60;
            minute += second / 60;
        }
        if (minute >= 60) {
            minute = minute % 60;
            hour += minute / 60;
        }
        String conertedTime = hour + "Hr " + minute + " Min " + second + " Sec";
        return conertedTime;
    }

    public static void ConnectToExecutionDB() {

        String dbdriver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://xxxxxx/uiat?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String username = "";
        String password = "";

        try {

            Class.forName(dbdriver);
            executionConnection = DriverManager.getConnection(url, username, password);
            System.out.println("Connect to executionrecod db successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("failed to connect to executionrecod db!");
            System.out.println(e.toString());
        } catch (SQLException e) {
            System.out.println("failed to connect to executionrecod db!");
            System.out.println(e.toString());
        }
    }

    public static void CloseConnectionToExecutionDB(Connection cn) throws Exception {

        try {
            cn.close();
            System.out.println("close connection to executionrecod db successfully!");
        } catch (SQLException e) {
            System.out.println("failed to close connection to executionrecod db!");
            System.out.println(e.toString());
        }

    }

    //对数据库进行insert，delete或者update
    public static void DB_insertExecutionRecord(String job_number, String record_type, String product_name, String project_name, String suite_name, String testcase_name, String testcase_desc, String run_starttime, String run_endtime, String duration, String status, int total_testcase_number, int passed_testcase_number, int failed_testcase_number) throws ClassNotFoundException {

        Statement statement = null;
        int resultCount;

        String sqlString = "insert into uiat.ui_at_executionrecords (\n" +
                "    duration,\n" +
                "    failed_testcase_number,\n" +
                "    job_number,\n" +
                "    passed_testcase_number,\n" +
                "    product_name,\n" +
                "    project_name,\n" +
                "    record_type,\n" +
                "    run_endtime,\n" +
                "    run_starttime,\n" +
                "    status,\n" +
                "    suite_name,\n" +
                "    testcase_desc,\n" +
                "    testcase_name,\n" +
                "    total_testcase_number,\n" +
                "    createdby)\n" +
                "values (\n" +
                "'" + duration + "',\n" +
                failed_testcase_number + ",\n" +
                "'" + job_number + "',\n" +
                passed_testcase_number + ",\n" +
                "'" + product_name + "',\n" +
                "'" + project_name + "',\n" +
                "'" + record_type + "',\n" +
                "'" + run_endtime + "',\n" +
                "'" + run_starttime + "',\n" +
                "'" + status + "',\n" +
                "'" + suite_name + "',\n" +
                "'" + testcase_desc + "',\n" +
                "'" + testcase_name + "',\n" +
                "'" + total_testcase_number + "',\n" +
                "user());";

        try {
            //executionConnection.prepareStatement("")
            statement = executionConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            resultCount = statement.executeUpdate(sqlString);

            statement.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    private String escapeStrWithoutCN(String str){
        return new LookupTranslator(
                new String[][]{
                        {"\"", "\\\""},
                        {"\\", "\\\\"},
                }).with(
                new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE())
        ).translate(str);
    }

    private void insertExecutionResult(String job_name, String build_number, String build_url, String record_type, String job_number, String product_name, String project_name, String suite_name, String testcase_name, String testcase_ID,String testcase_desc, String run_starttime, String run_endtime, String duration, String status, int total_testcase_number, int passed_testcase_number, int failed_testcase_number, String failure_summary, String failure_step, String failure_detail) {
        //创建一个httpClient的实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个httpGet请求实例
        HttpPut httpput = new HttpPut("http://10.18.38.41:9887/uitest/record/add");//production
        //HttpPut httpput = new HttpPut("http://10.16.85.150/uitest/record/add");//UAT
        // 使用httpClient实例发送刚创建的get请求，并用httpResponse将反馈接收
        CloseableHttpResponse httpResponse = null;
        try {
            System.out.println("调用insertExecutionResult");
            run_starttime = run_starttime.replaceAll("/", "-");
            run_endtime = run_endtime.replaceAll("/", "-");
            httpput.setHeader("Content-Type", "application/json");
            testcase_desc = escapeStrWithoutCN(testcase_desc);
            failure_step = escapeStrWithoutCN(failure_step);
            //获取请求参数
            String paramsJson = " { \n" +
                    "     \"jobNumber\": \"" + job_number + "\", \n" +
                    "     \"recordType\": \"" + record_type + "\", \n" +
                    "     \"productName\": \"" + product_name + "\", \n" +
                    "     \"projectName\": \"" + project_name + "\", \n" +
                    "     \"suiteName\": \"" + suite_name + "\", \n" +
                    "     \"testcaseName\": \"" + testcase_name + "\", \n" +
                    "     \"testcaseId\": \"" + testcase_ID + "\", \n" +
                    "     \"testcaseDesc\": \"" + testcase_desc + "\", \n" +
                    "     \"runStarttime\": \"" + run_starttime + "\", \n" +
                    "     \"runEndtime\": \"" + run_endtime + "\", \n" +
                    "     \"duration\": \"" + duration + "\", \n" +
                    "     \"status\": \"" + status + "\", \n" +
                    "     \"totalTestcaseNumber\": " + total_testcase_number + ", \n" +
                    "     \"passedTestcaseNumber\": " + passed_testcase_number + ", \n" +
                    "     \"failedTestcaseNumber\": " + failed_testcase_number + ", \n" +
                    "     \"jobName\":\"" + job_name + "\", \n" +
                    "     \"buildNumber\": \"" + build_number + "\", \n" +
                    "     \"buildUrl\":\"" + build_url + "\" , \n" +
                    "     \"failureSummary\":\"" + failure_summary + "\", \n" +
                    "     \"failureStep\": \"" + failure_step + "\", \n" +
                    "     \"failureDetail\":\"" + failure_detail + "\" , \n" +
                    "     \"taskId\": \"" + taskId + "\", \n" +
                    "     \"recordId\":\"" + recordId + "\" \n" +
                    " }";
            //组织请求参数
            StringEntity stringEntity = new StringEntity(paramsJson, "utf-8");

            httpput.setEntity(stringEntity);
            httpResponse = httpClient.execute(httpput);
            //从反馈中提取出状态码
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            //从反馈中提取出反馈主体
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

            System.out.println("插入请求的响应码："+Integer.toString(responseCode)+",响应内容是："+responseBody);

            httpClient.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void insertTCStepDetails(String TCStepResults) {
        //创建一个httpClient的实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个httpGet请求实例
        HttpPut httpput = new HttpPut("http://10.18.38.41:9887/uitest/record/batchAddRecordDetailForExample");//production
        //HttpPut httpput = new HttpPut("http://10.16.85.150/uitest/record/batchAddRecordDetailForExample");//UAT
        // 使用httpClient实例发送刚创建的get请求，并用httpResponse将反馈接收
        CloseableHttpResponse httpResponse = null;
        try {
            httpput.setHeader("Content-Type", "application/json");
            //获取请求参数
            String paramsJson = "["+TCStepResults+"]";
            //组织请求参数
            StringEntity stringEntity = new StringEntity(paramsJson, "utf-8");

            httpput.setEntity(stringEntity);
            httpResponse = httpClient.execute(httpput);
            //从反馈中提取出状态码
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            //从反馈中提取出反馈主体
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            httpClient.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFile(String fileName) {
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

    /***
     * MD5加码 生成32位md5码
     */
    public static String getMD5(String st) {

        StringBuffer buf = new StringBuffer("");

        try {

            MessageDigest md = null;

            md = MessageDigest.getInstance("MD5");

            try {
                md.update(st.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            byte b[] = md.digest();


            int i;

            for (int offset = 0; offset < b.length; offset++) {
                //System.out.println(buf.toString());
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");

                }
                buf.append(Integer.toHexString(i));
            }
            //System.out.println("原始：" + st);
            //  System.out.println("原始b：" + st.getBytes());
            //System.out.println("MD5后：" + buf.toString());


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return buf.toString();

    }


}
