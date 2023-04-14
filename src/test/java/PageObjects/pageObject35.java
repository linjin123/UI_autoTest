package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject35 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("已处理","cssSelector;;[aria-controls=pane-processed]");
POString.put("待处理","cssSelector;;[aria-controls=pane-pending]");
POString.put("我创建","cssSelector;;[aria-controls=pane-found]");
POString.put("提数流程_已处理","xpath;;//*[@class='el-tabs__content']//*[@aria-labelledby='tab-processed']//*[contains(text(),'提数流程')]");
POString.put("提数流程_待处理","xpath;;//*[@class='el-tabs__content']//*[@aria-labelledby='tab-pending']//*[contains(text(),'提数流程')]");
}
}
