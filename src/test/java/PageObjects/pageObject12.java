package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject12 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("发布弹框确认按钮","cssSelector;;div[aria-label=发布].el-dialog button.el-button--primary");
POString.put("发布按钮","cssSelector;;div.gppm-margin button.el-button--primary");
POString.put("审核通过弹框确认按钮","cssSelector;;div[aria-label=审核通过].el-dialog button.el-button--primary");
POString.put("带颜色的审核按钮","cssSelector;;button[id^=transitionBtn_].el-button--primary");
POString.put("打开立项流程链接","cssSelector;;.contextmenu li:first-child span");
POString.put("立项搜索栏","cssSelector;;thead.has-gutter  th:nth-child(3) input[type=text]");
POString.put("立项流程链接","cssSelector;;a[ui-title^=UI自动化测试试制单]");
}
}
