package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject1 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("密码","cssSelector;;#password");
POString.put("登录","xpath;;//*[@class='login-button']");
POString.put("账号","cssSelector;;#username");
}
}
