package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject24 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("缺陷列表_产品经理","xpath;;//div[@class='processConfig__node-wrap']//label[contains(text(),'产品经理')]");
POString.put("缺陷列表_新建","xpath;;//*[contains(text(),'新建')]/ancestor::button");
POString.put("缺陷列表_解决人","xpath;;//div[@class='processConfig__node-wrap']//label[contains(text(),'产品经理')]");
POString.put("缺陷列表_项目经理","xpath;;//div[@class='processConfig__node-wrap']//label[contains(text(),'产品经理')]");
POString.put("废弃原因第一条","xpath;;(//div[@class='select-tree__body select-tree']//span[@class='el-checkbox__inner'])[1]");
}
}
