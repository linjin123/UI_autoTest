package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject18 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("密码","xpath;;//input[@*='userPassword']");
POString.put("登录","xpath;;//button[text()='登录']");
POString.put("账号","xpath;;//input[@*='userName']");
}
}
