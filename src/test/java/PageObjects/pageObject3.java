package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject3 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("下一步按钮","cssSelector;;button[gppm-ui=createFlow__next]");
POString.put("刷新按钮","cssSelector;;button[gppm-ui=flowList__refresh]");
POString.put("日本向产品经理项目组","cssSelector;;div[aria-hidden=false] span[title=洗衣机事业部_研发中心_制程技术_不分明细]");
POString.put("领用原因1","xpath;;//*[text()='领用原因']/following-sibling::div//textarea");
POString.put("广东顺德工厂","cssSelector;;div[aria-hidden=false] span[title=INV_ME5_无锡小天鹅电器-无锡工厂_制造组织]");
POString.put("成本中心","cssSelector;;#_FLD_S_00012 i.el-select__caret");
POString.put("所属项目","cssSelector;;#_PROJECTID i");
POString.put("提交按钮","cssSelector;;button[gppm-ui^=createFlow__button__]");
POString.put("提示确认按钮","cssSelector;;div.el-message-box button.el-button--primary");
POString.put("新建按钮","cssSelector;;button[gppm-ui=flowList__create].el-button--primary");
POString.put("新建需求按钮","cssSelector;;button[gppm-ui=productWholeRequiesLabel__create__whole]");
POString.put("标题","cssSelector;;#_NAME input[type=text]");
POString.put("样机接收人","cssSelector;;#_FLD_T_00040 input[type=text]");
POString.put("样机接收人电话","cssSelector;;#_FLD_T_00041 input[type=text]");
POString.put("产品弹框搜索按钮","cssSelector;;div[aria-label=产品搜索].el-dialog  div.dialog-head__right button");
POString.put("产品弹框确认按钮","cssSelector;;div[aria-label=产品搜索].el-dialog  button[gppm-ui=materialProducts__dialog__ok]");
POString.put("物料搜索选择第一行","cssSelector;;div#__MaterialProducts__ div.el-table__body-wrapper tr:nth-child(1)  label>span>span");
POString.put("物料编号","cssSelector;;div[gppm-ui=materialProducts__dialog__code] input[type=text]");
POString.put("生产计划采购审核人","cssSelector;;[for='viewData.1.owners.0.ownerUsers'] +div input.user-picker__input");
POString.put("研发中心主任","cssSelector;;#_FLD_U_00006 i.iconfont");
POString.put("研发项目物料消耗","xpath;;//span[@title='研发项目样机领用(研发支出)']");
POString.put("财务审核人","cssSelector;;[for='viewData.2.owners.0.ownerUsers'] +div input.user-picker__input");
POString.put("账户别名","cssSelector;;#_FLD_S_00015 i.el-select__caret");
POString.put("选择项目单选项目第一行","cssSelector;;div.left__content  tbody tr:nth-child(1) label>span>span");
POString.put("选择项目弹框搜索按钮","cssSelector;;div.el-form-item__content  button.el-button--primary");
POString.put("选择项目弹框确认按钮","cssSelector;;button[gppm-ui=selectProject__ok]");
POString.put("成品","cssSelector;;span[title=成品]");
POString.put("部门主管","cssSelector;;[for='viewData.1.owners.1.ownerUsers']  +div input.user-picker__input");
POString.put("需求数量","cssSelector;;td.__testCount_selected__ input.is-required");
POString.put("项目名称输入框","cssSelector;;input[placeholder=项目名称]");
POString.put("项目编号输入框","cssSelector;;input[placeholder=项目编号]");
POString.put("领入组织","cssSelector;;#_FLD_S_00010 i.el-select__caret");
POString.put("领出组织","cssSelector;;#_FLD_S_00014 i.el-select__caret");
POString.put("领用原因","cssSelector;;#_FLD_A_00002 textarea");
POString.put("领用类型","cssSelector;;#_FLD_S_00032 input[type=text][readonly=readonly]");
POString.put("需求日期","xpath;;//tr[@class='el-table__row']/td[not(contains(@class,'is-hidden'))]//div[@required='required']//i[@class='el-input__icon']");
POString.put("选择需求日期","xpath;;//button[contains(text(),'今天')]");
}
}
