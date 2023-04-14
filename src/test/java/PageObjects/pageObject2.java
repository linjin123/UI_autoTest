package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject2 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("成品物料领用","cssSelector;;.navigation-sub__item span[title=成品物料领用]");
POString.put("支撑管理","cssSelector;;span[title=支撑管理]");
POString.put("立项审批流程","cssSelector;;span[title=立项审批流程]");
POString.put("立项管理","cssSelector;;.navigation-sub__item span[title=立项管理]");
POString.put("项目管理","cssSelector;;span[title=项目管理]");
POString.put("试制管理","cssSelector;;.navigation-sub__item [title=试制管理]");
POString.put("试产管理","cssSelector;;.navigation-sub__item span[title=试产管理]");
POString.put("任务中心","cssSelector;;span[title=任务中心]");
POString.put("我的主页","cssSelector;;span[title=我的主页]");
POString.put("提数管理","cssSelector;;span[title=提数管理]");
POString.put("用户角色配置","cssSelector;;span[title=用户角色配置]");
POString.put("系统管理","cssSelector;;span[title=系统管理]");
POString.put("角色管理","cssSelector;;span[title=角色管理]");
}
}
