package Framework;

import org.apache.poi.ss.formula.functions.T;
import org.testng.ISuite;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import io.restassured.response.Response;

public class GlobalVariable {
    public static Map<String, String> GV = new ConcurrentHashMap<String, String>();

    //主要是保存http接口的响应内容提取多个值为list
    public static Map<String, List<String>> GVList = new ConcurrentHashMap<String,List<String>>();

    public static Map<String, OutputText> outputtextList = new ConcurrentHashMap<String, OutputText>();

    static {
        outputtextList.put("common", new OutputText());
        GV.put("ChromeUserData", "");
    }

    public static Map<String, List<String>> DBResults = new ConcurrentHashMap<String, List<String>>();

    // public static Map<String, String> report_suiteSubFolder = new ConcurrentHashMap<String, String>();

    public static String overallReportFolder = "None";
    public static String runStartTime = "";

    public static int implicitlyWait = 10;

    public static Map<String, String> suiteStartTime = new ConcurrentHashMap<String, String>();

    public static String record = "否";
    public static String productName = "";
    public static String projectName = "";
    public static String reportFolder = "";
    public static String taskId = "";
    public static String recordId = "";
    public static String stepRerun = "false";
    public static String caseRerun = "false";
    public static String suiteRerun = "false";
    public static int maxRetry = 1;
    public static String globalParamLabel = "";


    public static List<ISuite> runSuites = new ArrayList<ISuite>();

    public static Response response;
    public static String resString;
}
