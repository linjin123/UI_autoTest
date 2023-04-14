package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject20 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("缺陷描述iframe","cssSelector;;label[for=REMARK]+div iframe");
POString.put("缺陷描述输入","xpath;;//body[@id='tinymce']");
POString.put("缺陷登记框iframe","xpath;;//iframe[@name='shortcut']");
}
}
