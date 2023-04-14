package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject21 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("密码输入框","cssSelector;;[placeholder=请输入密码]");
POString.put("登录按钮","cssSelector;;button");
POString.put("账号输入框","cssSelector;;[placeholder=请输入用户名]");
}
}
