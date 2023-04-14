package Framework;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPException;
import ch.ethz.ssh2.SFTPv3Client;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static Framework.GlobalVariable.*;
import static Framework.Keywords.getRootPath;


public class MyNewReport implements IReporter {

    static int allSuites_passTCCounts = 0;
    static int allSuites_failTCCounts = 0;

    String ip = "EDZELZGLZ@E"; //production
    //String ip = "EDZELZGLZ@F"; //UAT

    int port = 22; //port端口
    String user = "\u0015\u0004\u0004\u0007";   //production
    //String user = "\u0010\u001B\u0017\u001F\u0011\u0006";  //UAT

    String passwd = "!.\"6$DS\u0010\f\u000FWK";  //production
    //String passwd = "!.\"6$DS\u0010\f\u000FWK";  //UAT

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        StringBuilder sb = new StringBuilder();
        StringBuilder ssb = new StringBuilder();
        PrintStream printStream = null;
        String overviewReportBasePath = "";
        String runEndTime = Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss");


        try {
/*
            String tempPath = ExcelUtils.getConfigData("Overview", 20, 1);
            if (!tempPath.equals("")) {
                overviewReportBasePath = tempPath + "/ws/TestReport/" + overallReportFolder + "/";
            }*/
            printStream = new PrintStream(new FileOutputStream("TestReport/" + overallReportFolder + "/index.html"), true, "utf-8");

        } catch (FileNotFoundException el) {

        } catch (UnsupportedEncodingException e) {

        } catch (Exception e) {

        }
        startHtmlPage(sb,runEndTime);

        for (ISuite suite : runSuites) {

            try {
                generateData(sb, outputtextList.get(suite.getName()), suite, overviewReportBasePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        endHtmIPage(sb);
        printStream.println(sb.toString());

        File indextHtml = new File("TestReport/" + overallReportFolder + "/index.html");
        File newFile = new File("OverviewReport.html");

        try {
            FileUtils.copyFile(indextHtml, newFile);
            String status = "Pass";
            if (allSuites_failTCCounts != 0) {
                status = "Fail";
            }
            String dur = "";
            try {
                dur = subStractString(runStartTime, runEndTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //String reportFolder = getMD5(productName + projectName + overallReportFolder);

            if (record.equals("是")) {
                insertExecutionResult(GV.get("job_name"), GV.get("build_number"), GV.get("build_url"), overallReportFolder, productName, projectName, runStartTime, runEndTime, dur, status, allSuites_passTCCounts + allSuites_failTCCounts, allSuites_passTCCounts, allSuites_failTCCounts);
                Connection con = new Connection(convertMD5(ip), port);

                try {
                    //连接
                    con.connect();
                    //远程服务器的用户名密码
                    boolean isAuth = con.authenticateWithPassword(convertMD5(user), convertMD5(passwd));

                    if (isAuth) {
                        // System.out.println("认证成功！");
                        //建立SCP客户端
                        SCPClient scpClient = con.createSCPClient();

                        //建立一个SFTP客户端        
                        SFTPv3Client sftpClient = new SFTPv3Client(con);

                        // production
                        uploadFile(scpClient, sftpClient, getRootPath() + "/TestReport/" + overallReportFolder, "/apps/svr/data/uitest/" + reportFolder);
                        // UAT
                       // uploadFile(scpClient, sftpClient, getRootPath() + "/TestReport/" + overallReportFolder, "/apps/data/uitest/" + reportFolder);

                    } else {
                        System.out.println("认证失败！");
                    }

                    con.close();
                } catch (IOException e) {
                    System.out.println("服务器连接异常，报告上传失败！");
                }
            }

            String tempExecLog = Keywords.getCurrentTime("yyyy/MM/dd HH:mm:ss") + "： 所有用例已执行完:结果是" + status + ",总用例数=" + (allSuites_passTCCounts + allSuites_failTCCounts) + "，通过用例数=" + allSuites_passTCCounts + "，失败用例数=" + allSuites_failTCCounts + "。";
            System.out.println(tempExecLog);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startHtmlPage(StringBuilder sb,String runEndTime) {
        sb.append("<html>\r\n");
        sb.append("<title>Selenium  UI test Overall report</title>\r\n");
        sb.append("<head>\r\n");
        sb.append("<meta content=\"text/html;charset=utf-8\" http-equiv=\"content-type\">\r\n");
        sb.append("<style type=\"text/css\">\r\n");
        sb.append(".Pass {color:green}\r\n");
        sb.append(".Fail {color:red}\r\n");
        sb.append("</style>\r\n");
        sb.append("<script type=\"text/JavaScript\">\r\n");
        sb.append("function timedRefresh(timeoutPeriod){ \r\n");
        sb.append("setTimeout('10cation. reload(true); :  timeoutPeriod);\r\n");
        sb.append("}\r\n");
        sb.append("timedRefresh(10000);\r\n");
        sb.append("</script>\r\n");
        sb.append("</head>\r\n");
        sb.append("<body>\r\n");
        sb.append("<font face='arial'size='3'>\r\n");//No.02
        sb.append("<h2><center>自动化测试报告 - Overall</center></h2>\r\n");//Selenium report
        sb.append("<font face='arial'size='2'>\r\n");
        sb.append("<h3><center>开始执行时间：" + runStartTime + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束执行时间："+runEndTime+"</center></h3>\r\n");//As of:
        sb.append("<p>summary needs to be replace</p>\r\n");
        sb.append("<br>\n"+
                "<table border='0' width='70%'  height='70' align ='center'>\n" +
                "<caption><b>用例集执行结果</b></caption>\n"+
                "<tr>\n" +
                "<td align ='left' valign = 'center' width='20%' bgcolor='#CCCCFF'><b><font color='#000000' font-family='courier' face='arial' size='2'>用例集名</font></b></td>\n" +
                "<td align ='left' valign = 'center' width='5%' bgcolor='#CCCCFF'><b><font color='#000000' font-family='courier' face='arial' size='2'>结果</font></b></td>\n" +
                "<td align ='left' valign = 'center' width='15%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>执行时间</font></b></td>\n" +
                "<td align ='left' valign = 'center' width='15%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>结束时间</font></b></td>\n" +
                "<td align ='left' valign = 'center' width='15%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>总耗时</font></b></td>\n" +
                "<td align ='left' valign = 'center' width='10%' bgcolor='#CCCCFF' align='center'><b><font color='#000000' font-family='courier' face='arial' size='2'>通过用例数</font></b></td>\n" +
                "<td align ='left' valign = 'center' width='10%'  bgcolor='#CCCCFF' align='center'><b><font co1or='#000000' font-family='courier' face='arial' size='2'>失败用例数</font></b></td>\n" +
                "<td align ='left' valign = 'center' width='10%'  bgcolor='#CCCCFF' align='center'><b><font co1or='#000000' font-family='courier' face='arial' size='2'>总用例数</font></b></td>\n" +
                //"<td align ='left' valign = 'center' width='10%'  bgcolor='#CCCCFF' align='center'><b><font co1or='#000000' font-family='courier' face='arial' size='2'>执行端</font></b></td>\n" +
                "</tr>");
    }

    private static void endHtmIPage(StringBuilder sb) {
        String summary="" +
                "\n" +
                "<table border='0' width='50%' align =center>\n" +
                "<tr>\n" +
                "<td colspan=2 width='15%' bgcolor='#FFFFFF' valign='top' align='center'><b><font face='arial' size='2' color='Black'>总结</font></b></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial' size='2' color='Green'>通过用例数：</font></b></td>\n" +
                "<td width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial' size='2' color='Green' id= 'tdIntPass'>" + allSuites_passTCCounts + " (" + ((float) allSuites_passTCCounts / ((float) allSuites_passTCCounts + (float) allSuites_failTCCounts) * 100) + "%)</font></b></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td width='15%' bgcolor='#FFE6FF' valign='top' align='center'><b><font face='arial'  size='2' color='Red' >失败用例数：</font></b></td>\n" +
                "<td width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial'  size='2' color='Green'  id='intFail'>" + allSuites_failTCCounts + " (" + ((float) allSuites_failTCCounts / ((float) allSuites_passTCCounts + (float) allSuites_failTCCounts) * 100) + "%)</font></b></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td width='15%'  bgcolor='#C3FOB8' valign='top' align='center'><b><font face='arial' size='2' color='Black'>测试用例总数：</font></b></td>\n" +
                "<td width='15%' bgcolor='#E8FFE8' valign='top' align='center'><b><font face='arial' size='2' color='Green' id='intTotal'>" + (allSuites_passTCCounts + allSuites_failTCCounts) + "</font></b></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</font>\n" +
                "";
        String indexString=sb.toString().replace("<p>summary needs to be replace</p>",summary)+
                "</table>\n" +
                "</font>\n" +
                "</body>\n" +
                "</html>\n";
        sb.setLength(0);
        sb.append(indexString);

    }

    private static void generateData(StringBuilder sb, OutputText ot, ISuite suite, String overviewReportBasePath) throws IOException {
        String suiteResult = "";
        int passCount = 0;
        int failCount = 0;
        int totalCount = 0;
        String host = "";
        String suiteSTime = "";
        String suiteETime = "";
        String duration = "";

        try {
            if (ot.getFailTestCount() == 0 && ot.getPassTestCaseCount() != 0) {
                suiteResult = "Pass";
            } else {
                suiteResult = "Fail";
            }

            suiteSTime = ot.getSuiteSTime();
            suiteETime = ot.getSuiteETime();
            if (runStartTime.equals("")) {
                runStartTime = suiteSTime;
            }
            duration = subStractString(suiteSTime, suiteETime);

            passCount = ot.getPassTestCaseCount();
            failCount = ot.getFailTestCount();
            totalCount = ot.getPassTestCaseCount() + ot.getFailTestCount();
            host = suite.getHost();


            sb.append("<tr>\n" +
                    "<td class='Pass' align ='left' valign = 'center' bgcolor='#FFFFDC'><b><font font-family='courier' face='arial' size='2'>" + suite.getName() + "</font></b></td >\n");

            if (suiteResult.equals("Pass") || suiteResult.equals("Fail")) {
                sb.append("<td class='Pass' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'><a href='" + overviewReportBasePath + suite.getName() + "\\" + "index-failed.html'>" + suiteResult + "</a></font></b></td> \n");
            } else {
                sb.append("<td class='Pass' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2' color='gray'>" + suiteResult + "</font></b></td> \n");
            }

            sb.append("<td class='Pass' align ='left' valign = 'center' bgcolor='#FFFFDC'><b><font font-family='courier' face='arial' size='2'>" + suiteSTime + "</font></b></td>\n" +
                    "<td class='Pass' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + suiteETime + "</font></b></td>\n" +
                    "<td class='Pass' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + duration + "</font></b></td>\n" +
                    "<td class='Pass' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + passCount + "</font></b></td>\n" +
                    "<td class='Pass' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + failCount + "</font></b></td>\n" +
                    "<td class='Pass' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>" + totalCount + "</font></b></td>\n" +
                    //"<td class='Pass' align ='left' valign = 'center' bgcolor='#FFFFDC' align='center'><b><font font-family='courier' face='arial' size='2'>"+host+"</font></b></td>\n" +
                    "</tr>");

        } catch (NullPointerException e) {
            suiteResult = "Notrun";
            passCount = 0;
            failCount = 0;
            totalCount = 0;
            host = "";

        } catch (ParseException e) {

        }

        allSuites_passTCCounts = allSuites_passTCCounts + passCount;
        allSuites_failTCCounts = allSuites_failTCCounts + failCount;

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


    /***
     * 上传文件到目录
     * @param filePath 本地路劲

     * @param path 上传服务器路径

     * @throws IOException
     */
    public void uploadFile(SCPClient scpClient, SFTPv3Client sftpClient, String filePath, String path) throws IOException {

        File file = new File(filePath);
        File LinuxPath = new File(path);

        if (!LinuxPath.exists()) {
            try {
                sftpClient.mkdir(path, 0707);
            } catch (SFTPException e) {

            }
        }

        if (file.isDirectory()) {
            File[] allFile = file.listFiles();

            for (File f : allFile) {

                String tempWpath = f.getAbsolutePath();
                String tempLpath = path;

                if (f.isDirectory()) {
                    tempLpath = path + "/" + f.getName();
                }
                uploadFile(scpClient, sftpClient, tempWpath, tempLpath);
            }

        } else if(file.getName().endsWith(".png")) {

            //scpClient.put(new String(filePath.getBytes("ISO8859-1"),"UTF-8"),new String(path.getBytes("ISO8859-1"),"UTF-8"));
            scpClient.put(filePath, path);
        }

    }


    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }



    private void insertExecutionResult(String job_name, String build_number, String build_url, String job_number, String product_name, String project_name, String run_starttime, String run_endtime, String duration, String status, int total_testcase_number, int passed_testcase_number, int failed_testcase_number) {
        //创建一个httpClient的实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个httpGet请求实例
        HttpPut httpput = new HttpPut("http://10.18.38.41:9887/uitest/record/add");//production
        //HttpPut httpput = new HttpPut("http://10.16.85.150/uitest/record/add");//UAT
        // 使用httpClient实例发送刚创建的get请求，并用httpResponse将反馈接收
        CloseableHttpResponse httpResponse = null;
        try {
            run_starttime = run_starttime.replaceAll("/", "-");
            run_endtime = run_endtime.replaceAll("/", "-");
            httpput.setHeader("Content-Type", "application/json");
            //获取请求参数
            String paramsJson = " { \n" +
                    "     \"jobNumber\": \"" + job_number + "\", \n" +
                    "     \"recordType\": \"overall\", \n" +
                    "     \"productName\": \"" + product_name + "\", \n" +
                    "     \"projectName\": \"" + project_name + "\", \n" +
                    "     \"suiteName\": \"all\", \n" +
                    "     \"testcaseName\": \"\", \n" +
                    "     \"testcaseId\": \"\", \n" +
                    "     \"testcaseDesc\": \"\", \n" +
                    "     \"runStarttime\": \"" + run_starttime + "\", \n" +
                    "     \"runEndtime\": \"" + run_endtime + "\", \n" +
                    "     \"duration\": \"" + duration + "\", \n" +
                    "     \"status\": \"" + status + "\", \n" +
                    "     \"totalTestcaseNumber\": " + total_testcase_number + ", \n" +
                    "     \"passedTestcaseNumber\": " + passed_testcase_number + ", \n" +
                    "     \"failedTestcaseNumber\": " + failed_testcase_number + ", \n" +
                    "     \"jobName\": \"" + job_name + "\", \n" +
                    "     \"buildNumber\": \"" + build_number + "\", \n" +
                    "     \"buildUrl\": \"" + build_url + "\" , \n" +
                    "     \"failureSummary\":\"\", \n" +
                    "     \"failureStep\": \"\", \n" +
                    "     \"failureDetail\":\"\" , \n" +
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //插入耗时统计数据
    public static void insertConsumingTimeData(String dr) {
        String taskId = GlobalVariable.taskId;
        String recordId = GlobalVariable.recordId;
        String suiteName = outputtextList.get(dr).getSuiteName();
        String testcaseName = outputtextList.get(dr).getTestName();
        String testcaseDesc = outputtextList.get(dr).getTestCaseDesc();
        int stepIndex = Integer.parseInt(outputtextList.get(dr).getTestStepID());
        String stepDesc = outputtextList.get(dr).getMethodName();
        String beginAt = outputtextList.get(dr).getBeginAt();
        String endAt = outputtextList.get(dr).getEndAt();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://ittmp.midea.com/uitest/during/batchAdd");
        HttpResponse httpResponse = null;
        try {
            httpPost.setHeader("Content-Type", "application/json");
            String paramsJson = "[{ \n" +
                    "     \"taskId\": \"" + taskId + "\", \n" +
                    "     \"recordId\": \"" + recordId + "\", \n" +
                    "     \"suiteName\": \"" + suiteName + "\", \n" +
                    "     \"testcaseName\": \"" + testcaseName + "\", \n" +
                    "     \"testcaseDesc\": \"" + testcaseDesc + "\", \n" +
                    "     \"stepIndex\": \"" + stepIndex + "\", \n" +
                    "     \"stepDesc\": \"" + stepDesc + "\", \n" +
                    "     \"beginAt\": \"" + beginAt + "\", \n" +
                    "     \"endAt\": \"" + endAt + "\", \n" +
                    " }]";
            StringEntity stringEntity = new StringEntity(paramsJson, "utf-8");
            httpPost.setEntity(stringEntity);
            httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
