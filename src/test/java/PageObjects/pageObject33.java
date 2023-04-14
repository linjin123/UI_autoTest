package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject33 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("保存按钮","cssSelector;;[gppm-ui=save]");
POString.put("提数管理权限框","xpath;;//*[contains(text(),'提数管理')]/preceding-sibling::span");
POString.put("角色输入框","cssSelector;;[gppm-ui=searchInput]");
}
}
