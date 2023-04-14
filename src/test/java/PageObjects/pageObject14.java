package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject14 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("审批通过弹框确认按钮","cssSelector;;div[aria-label=审批通过].el-dialog button.el-button--primary");
POString.put("审核通过弹框确认按钮","cssSelector;;div[aria-label=审核通过].el-dialog button.el-button--primary");
POString.put("带颜色的审核按钮","cssSelector;;button[id^=transitionBtn_].el-button--primary");
POString.put("打开立项流程链接","cssSelector;;.contextmenu li:first-child span");
POString.put("立项搜索栏","cssSelector;;thead.has-gutter  th:nth-child(3) input[type=text]");
POString.put("立项流程链接","cssSelector;;a[title^=UI自动化测试项目]");
}
}
