package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject22 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("产品管理","cssSelector;;.svg-icon--app");
POString.put("产品管理_产品列表","cssSelector;;li [title=产品列表]");
POString.put("产品管理_产品新建","cssSelector;;li [title=产品新建]");
POString.put("支撑管理","cssSelector;;span[title=支撑管理]");
POString.put("支撑管理_任务审核","cssSelector;;.navigation-sub__item span[title=任务审核]");
POString.put("模拟登录","cssSelector;;[gppm-ui=simulation--login]>.user-options");
POString.put("模拟登录_搜索人员","cssSelector;;[placeholder=请输入搜索人员]");
POString.put("模拟登录_选中人员","cssSelector;;[aria-label=选择用户] .el-table__body label");
POString.put("测试管理","cssSelector;;.svg-icon--code-filled");
POString.put("测试管理_缺陷管理","cssSelector;;li [title=缺陷管理]");
POString.put("确定","cssSelector;;[gppm-ui=selectUser__ok]");
POString.put("管理员","cssSelector;;.header-user__content");
POString.put("组织切换","cssSelector;;[gppm-ui=switch-account]>.user-options");
POString.put("需求管理","cssSelector;;.svg-icon--document-filled");
POString.put("需求管理_需求池","xpath;;//li/*[text()='需求池']");
POString.put("项目管理","cssSelector;;span[title=项目管理]");
POString.put("项目管理_立项管理","cssSelector;;.navigation-sub__item span[title=立项管理]");
POString.put("项目管理_项目列表","cssSelector;;.navigation-sub__item span[title=项目列表]");
POString.put("选择组织_家用空调","xpath;;//span[text()='家用空调事业部']");
POString.put("退出登录","xpath;;//*[text()='退出']");
POString.put("测试管理_公共用例流程列表","cssSelector;;li [title=公共用例流程列表]");
POString.put("测试管理_用例库","cssSelector;;li [title=用例库]");
POString.put("通知预览","cssSelector;;[aria-label=通知预览] [aria-label=Close]");
POString.put("输入组织","xpath;;//div[@aria-label='选择组织']//div[@class='switchOrg__search mb']//input");
POString.put("选择组织_确定","xpath;;//div[@aria-label='选择组织']//div[@class='dialog-footer']//span[contains(text(),'确定')]");
POString.put("选择组织_IOT","xpath;;//span[text()='IoT']");
}
}
