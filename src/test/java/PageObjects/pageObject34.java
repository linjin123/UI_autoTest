package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject34 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("是否涉及用户基本信息","cssSelector;;[title=是否涉及用户基本信息] [tabindex='-1'] span[class=el-radio__inner]");
POString.put("是否紧急","xpath;;((//*[@class='el-form-item__label'])[10]/parent::div[not(contains(@style,'display: none'))]//input/..)[1]");
POString.put("标题","cssSelector;;[prop='viewData.2.value.NAME']");
}
}
