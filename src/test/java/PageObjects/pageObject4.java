package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject4 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("审批通过弹框确认按钮","cssSelector;;div[aria-label=审批通过].el-dialog button.el-button--primary");
POString.put("审核意见","cssSelector;;div[aria-label=审核通过].el-dialog div#undefined_FLD_A_00003  textarea");
POString.put("审核通过弹框确认按钮","cssSelector;;div[aria-label=审核通过] div.dialog-footer  button.el-button--primary");
POString.put("带颜色的审核按钮","cssSelector;;button[id^=transitionBtn_].el-button--primary");
POString.put("打开领用单流程链接","cssSelector;;.contextmenu li:first-child span");
POString.put("领用单搜索栏","cssSelector;;thead.has-gutter th:nth-child(2) input[type=text]");
POString.put("领用单流程链接","cssSelector;;a[ui-title^=UI自动化测试新建领用单]");
}
}
