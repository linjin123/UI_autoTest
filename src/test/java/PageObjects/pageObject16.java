package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject16 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("产品技术负责人","cssSelector;;[for='viewData.1.owners.2.ownerUsers'] +div input.user-picker__input");
POString.put("品质代表","cssSelector;;[for='viewData.1.owners.3.ownerUsers'] +div input.user-picker__input");
POString.put("工艺员","cssSelector;;[for='viewData.1.owners.4.ownerUsers'] +div input.user-picker__input");
POString.put("流程发布后接收者","cssSelector;;[for='viewData.4.owners.0.ownerUsers'] +div input.user-picker__input");
POString.put("生产计划专员","cssSelector;;[for='viewData.2.owners.0.ownerUsers'] +div input.user-picker__input");
POString.put("用户清空按钮","cssSelector;;button[gppm-ui=selectUser__clear]");
POString.put("财务代表","cssSelector;;[for='viewData.1.owners.0.ownerUsers'] +div input.user-picker__input");
POString.put("部门或模块负责人财务代表","cssSelector;;[for='viewData.1.owners.1.ownerUsers'] +div input.user-picker__input");
POString.put("项目经理","cssSelector;;[for='viewData.3.owners.0.ownerUsers'] +div input.user-picker__input");
}
}
