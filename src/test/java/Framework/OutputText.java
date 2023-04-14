package Framework;

/*
This class stores all param in the txt file
 */

import java.io.*;

public class OutputText {
    protected String expectedResult;
    protected String actualResult;
    protected String methodResult;
    protected String testName;
    protected String testCaseID;
    protected String testCaseDesc;
    protected String testCaseFailedStep="";
    protected String testCaseFailedSummary="";
    protected String testCaseFailedDetail="";
    protected String fileCreateTime;
    protected String TestSTime;
    protected String TestETime;
    protected String SuiteSTime;
    protected String SuiteETime;
    protected String beginAt;
    protected String endAt;
    protected String methodName;
    protected String imgLocation = "";
    protected String imgLocationOld = "";
    protected String methodSTime;
    protected String methodETime;
    protected String fileName;
    protected String suiteName;
    protected String testResult;
    protected String stepErrorMsg;
    //protected int testCaseId;
    protected int testStepId;
    protected String suiteTime;
    protected int failTestCount = 0;
    protected int passTestCount = 0;
    protected StringBuilder suiteReportHead = new StringBuilder();

    protected StringBuilder suiteReportBody = new StringBuilder();
    protected StringBuilder suiteReportBody_failed = new StringBuilder();

    protected StringBuilder suiteReportFoot = new StringBuilder();

    protected String TCStepsResultsJson="";

    protected boolean screenshot = true;

    protected int skipOtherTCsWhenFailed = 0; //开启当用例失败的时候，跳过剩下的用例，0表示禁用，1表示开启

    protected boolean skipOtherTCs = false; //跳过剩下的用例，默认为false，不跳过；true表示跳过

    public int getSkipOtherTCsWhenFailed() {
        return skipOtherTCsWhenFailed;
    }

    public void setSkipOtherTCsWhenFailed(int skipOtherTCsWhenFailed) {
        this.skipOtherTCsWhenFailed = skipOtherTCsWhenFailed;
    }

    public boolean isSkipOtherTCs() {
        return skipOtherTCs;
    }

    public void setSkipOtherTCs(boolean skipOtherTCs) {
        this.skipOtherTCs = skipOtherTCs;
    }

    public boolean isScreenshot() {
        return screenshot;
    }

    public void setScreenshot(boolean screenshot) {
        this.screenshot = screenshot;
    }

    public String getFileName() {
        return (suiteName + "_" + testName + "_" + fileCreateTime);
    }
    /*
    protected  void createFile() throws IOException{
        File f =  new File("TestReport/tmpfile/"+suiteTime+"/"+ suiteName+"/"+getFileName()+".txt");
        f.createNewFile();
    }*/

    public void writeFile(String fileName, String writecontext) throws IOException {
        // FileWriter fw = new FileWriter(fileName,true);
        BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8"));
        fw.write(writecontext + "\r\n");
        fw.close();
    }

    public void setExpectedResult(String a) {
        expectedResult = a;
    }

    public void setActualResult(String a) {
        actualResult = a;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public String getMethodResult() {
        return methodResult;
    }

    public void setMethodResult(String a) {
        methodResult = a;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String a) {
        testName = a;
    }
    public String getTestCaseID() {
        return testCaseID;
    }

    public void setTestCaseID(String a) {
        testCaseID = a;
    }

    public String getTestCaseDesc() {
        return testCaseDesc;
    }

    public void setTestCaseDesc(String a) {
        testCaseDesc = a;
    }

    public String getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(String a) {
        fileCreateTime = a;
    }

    public String getTestSTime() {
        return TestSTime;
    }

    public void setTestSTime(String a) {
        TestSTime = a;
    }

    public String getSuiteETime() {
        return SuiteETime;
    }

    public void setSuiteETime(String a) {
        SuiteETime = a;
    }

    public String getSuiteSTime() {
        return SuiteSTime;
    }

    public void setSuiteSTime(String a) {
        SuiteSTime = a;
    }

    public String getBeginAt() {
        return beginAt;
    }

    public void setBeginAt(String a) {
        beginAt = a;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String a) {
        endAt = a;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String a) {
        methodName = a;
    }

    public String getTestETime() {
        return TestETime;
    }

    public void setTestETime(String a) {
        TestETime = a;
    }

    public String getImgLocation() {
        return imgLocation;
    }

    public String getImgLocationOld() {
        return imgLocationOld;
    }

    public void setImgLocationOld(String a) {
        imgLocationOld = a;
    }

    public String getMethodSTime() {
        return methodSTime;
    }

    public void setMethodSTime(String a) {
        methodSTime = a;
    }

    public String getMethodETime() {
        return methodETime;
    }

    public void setMethodETime(String a) {
        methodETime = a;
    }

    public void setSuiteName(String a) {
        suiteName = a;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setTestResult(String a) {
        testResult = a;
    }

    public void setImgLocation(String a) {
        imgLocation = a;
    }

    public String getTestResult() {
        return testResult;
    }
/*
    public void setTestCaseID() {
        testCaseId = testStepId + 1;
    }
    public void setTestCaseID(int a) {
        testCaseId = a;
    }

    public int getTestCaseID() {
        //testCaseId++;
        return testCaseId;
    }*/

    public void setTestStepID(int a) {
        testStepId = a;
    }

    public String getTestStepID() {
        //testStepId++;
        return String.format("%02d", testStepId);
    }

    public void setStepErrorMsg(String a) {
        stepErrorMsg = a;
    }

    public String getStepErrorMsg() {
        return actualResult;
    }

    public String getSuiteTime() {
        return suiteTime;
    }

    public void setSuiteTime(String a) {
        suiteTime = a;
    }

    public int getPassTestCaseCount() {
        return passTestCount;
    }

    public void setPassTestCaseCount(int a) {
        passTestCount = a;
    }

    public void setPassTestCaseCount() {
        passTestCount = passTestCount + 1;
    }

    public int getFailTestCount() {
        return failTestCount;
    }

    public void setFailTestCaseCount(int a) {
        failTestCount = a;
    }

    public void setFailTestCaseCount() {
        failTestCount = failTestCount + 1;
    }

    public String getTestCaseFailedSummary() {
        return testCaseFailedSummary;
    }

    public void setTestCaseFailedSummary(String a) {

        if(a.contains("-")){
            testCaseFailedSummary = a.split("-")[0].length()>30?a.split("-")[0].substring(0,30):a.split("-")[0];
            testCaseFailedSummary=testCaseFailedSummary.replaceAll("[\\\"{}\\[\\]\\\\\t\n]","");

        }else{
            testCaseFailedSummary = a.length()>30?a.substring(0,30):a;
        }
    }
    public String getTestCaseFailedStep() {
        return testCaseFailedStep;
    }

    public void setTestCaseFailedStep(String a) {
        testCaseFailedStep = a;
    }
    public String getTestCaseFailedDetail() {
        return testCaseFailedDetail;
    }

    public void setTCStepsResultsJson(String a) {

        if(TCStepsResultsJson.equals("")){
            TCStepsResultsJson = a;
        }else{
            TCStepsResultsJson=TCStepsResultsJson+","+a;
        }
    }

    public String getTCStepsResultsJson() {
        return TCStepsResultsJson;
    }

    public void resetTCStepsResultsJson() {
        TCStepsResultsJson="";
    }

    public void setTestCaseFailedDetail(String a) {

            testCaseFailedDetail = a.replaceAll("[\\\"{}\\[\\]\\\\\t\n]","");


    }
}
