package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject30 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("工作台","xpath;;//div[contains(text(),'工作台')]");
POString.put("当前用户名div","cssSelector;;div.header-user__name");
}
}
