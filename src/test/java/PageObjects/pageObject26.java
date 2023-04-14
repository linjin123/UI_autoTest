package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject26 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("新增产品_产品名称输入框","cssSelector;;[prop='viewData.0.value.NAME']");
POString.put("新增产品_产品模块信息保存","xpath;;//*[text()='产品模块信息']/following::td[not(contains(@class,'is-hidden'))]//*[text()='保存']");
POString.put("新增产品_产品模块新增","xpath;;//*[text()='产品模块信息']/following::button");
POString.put("新增产品_产品类型输入框","cssSelector;;#create_FLD_S_00006 .el-input__inner");
POString.put("新增产品_产品类型选择1","xpath;;//*[text()='美居产品']");
POString.put("新增产品_产品类型选择2","xpath;;//*[text()='国际美居']");
POString.put("新增产品_产品类型选择3","xpath;;//*[text()='测试账套']");
POString.put("新增产品_产品负责人输入框","xpath;;//*[text()='产品负责人']/../descendant::*[@class='user-picker__input']");
POString.put("新增产品_产品负责人选中","xpath;;//*[contains(@title,'_IoT_')]");
POString.put("新增产品_审核确定","cssSelector;;[aria-label='通过'] .dialog-footer .el-button--primary");
POString.put("新增产品_意见","cssSelector;;textarea[prop='viewData.0.value.FLD_A_00015']");
POString.put("新增产品_描述输入框","cssSelector;;#create_REMARK .el-textarea__inner");
POString.put("新增产品_提交","xpath;;//*[text()=' 提交 ']");
POString.put("新增产品_提交确定","xpath;;//*[@class='el-message-box__btns']/descendant::*[contains(text(),'确定')]");
POString.put("新增产品_校验","cssSelector;;tbody");
POString.put("新增产品_模块名称输入框","xpath;;(//*[text()='产品模块信息']/../descendant::input)[1]");
POString.put("新增产品_模块说明输入框","xpath;;(//*[text()='产品模块信息']/../descendant::textarea)");
POString.put("新增产品_模块负责人输入框","xpath;;//*[text()='产品模块信息']/following::td[not(contains(@class,'is-hidden'))]//input[@class='user-picker__input']");
POString.put("新增产品_设置属性下一步","cssSelector;;[gppm-ui=createFlow__button__C]");
POString.put("新增产品_输入会签人","xpath;;//*[text()=' 会签 ']/../descendant::input[@class='user-picker__input']");
POString.put("新增产品_输入审批人","xpath;;//*[text()=' 审批 ']/../descendant::input[@class='user-picker__input']");
POString.put("新增产品_通过","xpath;;//*[text()=' 通过 ']");
POString.put("新增产品_领域信息保存","xpath;;//*[text()='领域信息']/following::td[not(contains(@class,'is-hidden'))]//*[text()='保存']");
POString.put("新增产品_领域名称输入框","xpath;;(//*[text()='领域信息']/../descendant::input)[1]");
POString.put("新增产品_领域成员输入框","xpath;;//*[text()='领域信息']/following::td[not(contains(@class,'is-hidden'))][3]//input[@class='user-picker__input']");
POString.put("新增产品_领域负责人输入框","xpath;;//*[text()='领域信息']/following::td[not(contains(@class,'is-hidden'))][2]//input[@class='user-picker__input']");
POString.put("新增产品按钮","cssSelector;;[gppm-ui=flowList__create]");
}
}
