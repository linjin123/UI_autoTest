package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject32 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("产品详情_新建版本","xpath;;//button//span[contains(text(),'新建版本')]");
POString.put("产品详情_版本","xpath;;//div[@role='tablist']//div[contains(text(),'版本')]");
}
}
