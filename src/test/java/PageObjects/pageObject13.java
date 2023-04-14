package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject13 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("下一步按钮","cssSelector;;button[gppm-ui=createFlow__next]");
POString.put("产品弹框搜索按钮","cssSelector;;div[aria-label=产品搜索].el-dialog  div.dialog-head__right  button");
POString.put("产品弹框确认按钮","cssSelector;;div[aria-label=产品搜索].el-dialog  button[gppm-ui=materialProducts__dialog__ok]");
POString.put("产品搜索选择第一行","cssSelector;;div#__MaterialProducts__ div.el-table__body-wrapper tr:nth-child(1)  label>span>span");
POString.put("产品编号","cssSelector;;div[gppm-ui=materialProducts__dialog__code] input[type=text]");
POString.put("作业需求链接","cssSelector;;div.full-screen__wrap button[gppm-ui=workRequireLabel__button-headLinkFirst]");
POString.put("其他需求","cssSelector;;div.inputNumberItem  input.is-required");
POString.put("其他需求样机用途","cssSelector;;[gppm-ui=workRequireLabel__otherPrototypeFunction0] input");
POString.put("刷新按钮","cssSelector;;button[gppm-ui=flowList__refresh]");
POString.put("工厂组织","cssSelector;;div[gppm-ui=materialProducts__dialog__organization] input[readonly=readonly]");
POString.put("广东顺德工厂","cssSelector;;span[title=INV_M01_广东美的制冷设备-顺德工厂_制造库存组织]");
POString.put("所属项目","cssSelector;;#_PROJECTID i");
POString.put("技术和工艺准备-选择第一个名称","cssSelector;;[aria-hidden=false] .filter-tree>div:first-child");
POString.put("技术和工艺准备名称","cssSelector;;[gppm-ui=craftsRequireLabel__name0]");
POString.put("技术和工艺准备新建按钮","cssSelector;;button[gppm-ui=craftsRequireLabel__button-create]");
POString.put("技术和工艺准备责任人","cssSelector;;div[gppm-ui=craftsRequireLabel__user0]  i");
POString.put("技术要求及其他","cssSelector;;div#_FLD_A_00005  textarea");
POString.put("提交按钮","cssSelector;;button[gppm-ui^=createFlow__button__]");
POString.put("提示确认按钮","cssSelector;;div.el-message-box button.el-button--primary");
POString.put("新建按钮","cssSelector;;button[gppm-ui=flowList__create]");
POString.put("测试需求","cssSelector;;td.__testCount_selected__ input.is-required");
POString.put("要求完成日期","cssSelector;;div[gppm-ui=workRequireLabel__planEndDate0] input");
POString.put("计划开始日期","cssSelector;;div[gppm-ui=workRequireLabel__planStartDate0] input");
POString.put("试产标题","cssSelector;;div#_NAME input[type=text]");
POString.put("选择项目单选项目第一行","cssSelector;;div.left__content  tbody tr:nth-child(1) label>span>span");
POString.put("选择项目弹框搜索按钮","cssSelector;;div.el-form-item__content  button.el-button--primary");
POString.put("选择项目弹框确认按钮","cssSelector;;button[gppm-ui=selectProject__ok]");
POString.put("附件","cssSelector;;div.gppm-upload-wrap__upload");
POString.put("项目名称输入框","cssSelector;;input[placeholder=项目名称]");
POString.put("项目编号输入框","cssSelector;;input[placeholder=项目编号]");
POString.put("顺德工厂","cssSelector;;span[title=INV_M01_广东美的制冷设备-顺德工厂_制造库存组织]");
POString.put("库存组织","xpath;;//div[@class='el-table__body-wrapper is-scrolling-left']//tr//td[2]//div[@class='el-form-item__content']");
POString.put("库存组织选择","xpath;;//span[@title='INV_M12_邯郸美的制冷-邯郸工厂_制造库存组织']");
}
}
