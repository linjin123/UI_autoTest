package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject27 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("产品列表_产品","cssSelector;;tbody tr td:nth-child(2) a");
POString.put("产品列表_产品固化","cssSelector;;.el-checkbox__inner");
POString.put("产品列表_产品模块","cssSelector;;#tab-module");
POString.put("产品列表_产品模块流程配置产品经理","cssSelector;;.demand-config>label:nth-child(4)");
POString.put("产品列表_产品模块流程配置产品负责人","cssSelector;;.demand-config>label:nth-child(6)");
POString.put("产品列表_产品模块流程配置会签","cssSelector;;.demand-config>label:nth-child(5)");
POString.put("产品列表_产品流程配置产品经理","cssSelector;;.bgw.tabs-layout__container>label:nth-child(2)");
POString.put("产品列表_产品流程配置产品负责人","cssSelector;;.bgw.tabs-layout__container>label:nth-child(4)");
POString.put("产品列表_产品流程配置会签","cssSelector;;.bgw.tabs-layout__container>label:nth-child(3)");
POString.put("产品列表_产品经理维护成员","xpath;;//*[text()=' 产品经理 ']/ancestor::*[@class='el-table__row']/td[4]//button");
POString.put("产品列表_产品详情","cssSelector;;tbody>tr>td:nth-child(2)");
POString.put("产品列表_产品配置","xpath;;//h2/following-sibling::*[text()='产品配置']");
POString.put("产品列表_人员信息校验","cssSelector;;[title='单击复制;双击复制全部']");
POString.put("产品列表_名称查询","xpath;;(//*[@class='table-list__wrapper']//input)[2]");
POString.put("产品列表_技术负责人维护成员","xpath;;//*[text()=' 技术负责人 ']/ancestor::*[@class='el-table__row']/td[4]//button");
POString.put("产品列表_新建需求","xpath;;//span[contains(text(),'新建需求')]");
POString.put("产品列表_流程类型下拉框","xpath;;//*[@class='el-select el-select--small']//span[1]");
POString.put("产品列表_流程类型下拉框选择","xpath;;//*[@class='el-select-dropdown__wrap el-scrollbar__wrap']//*[text()='需求管理']");
POString.put("产品列表_流程配置","xpath;;//*[text()='流程配置']");
POString.put("产品列表_维护成员删除","xpath;;//*[@aria-label='维护成员']//*[text()='删除']/..");
POString.put("产品列表_维护成员新增","xpath;;//*[text()=' 新增成员 ']/ancestor::button");
POString.put("产品列表_维护成员添加姓名","cssSelector;;.svg-icon.svg-icon--user.el-input__icon");
POString.put("产品列表_维护成员确定","xpath;;//*[@aria-label='维护成员']//*[text()='确定']/..");
POString.put("产品列表_规划专员维护成员","xpath;;//*[text()=' 规划专员 ']/ancestor::*[@class='el-table__row']/td[4]//button");
POString.put("产品列表_角色人员固化","xpath;;//*[text()='角色人员固化']");
POString.put("产品列表_选择模块","xpath;;//div[@aria-hidden='false']//label//span[1]");
POString.put("产品列表_需求","xpath;;//*[text()='需求']");
POString.put("产品列表_需求会签维护成员","xpath;;//*[text()=' 需求会签 ']/ancestor::*[@class='el-table__row']/td[4]//button");
POString.put("产品列表_创建模块","cssSelector;;.el-icon-circle-plus-outline");
POString.put("产品列表_创建模块_模块名称","xpath;;//div[@aria-label='创建模块']//input");
POString.put("产品列表_创建模块_模块说明","xpath;;//div[@aria-label='创建模块']//textarea");
POString.put("产品列表_创建模块_确定","xpath;;//div[@aria-label='创建模块']//span[contains(text(),'确定')]");
POString.put("产品详情_新增模块","xpath;;//span[@title='新增模块']//..");
POString.put("产品详情_新增模块_删除","xpath;;//span[@title='新增模块']//..//i[@class='el-icon-delete cursor']");
}
}
