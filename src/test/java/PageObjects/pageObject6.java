package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject6 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("加载页","cssSelector;;[class='el-loading-mask is-fullscreen']");
POString.put("上传文件","xpath;;//div[@class='el-upload el-upload--text']");
POString.put("下拉点击","cssSelector;;[x-placement='bottom-start'] .el-tree-node.is-expanded.is-focusable:not([style='display: none;'])");
POString.put("下拉输入","cssSelector;;[x-placement][role] input:first-child");
POString.put("列表标题","cssSelector;;thead.has-gutter th");
POString.put("列表第一条","cssSelector;;tbody tr:nth-child(1)");
POString.put("加载框","cssSelector;;[class='el-loading-mask is-fullscreen']");
POString.put("审核通过按钮","cssSelector;;.flowTabs__button-box button");
POString.put("审核通过确定按钮","cssSelector;;.el-dialog__wrapper:not([style='display: none;']) .el-button.el-button--primary");
POString.put("富文本编辑1","xpath;;(//textarea)[1]");
POString.put("富文本编辑2","xpath;;(//textarea)[2]");
POString.put("弹出选中","cssSelector;;[x-placement][role] li:first-child");
POString.put("提交按钮","xpath;;//*[contains(text(),'提交')]/parent::button");
POString.put("提交确定按钮","xpath;;//*[@class='el-message-box']//*[contains(text(),'确定')]/parent::button");
POString.put("新建提交","xpath;;//*[contains(text(),'提交')]");
POString.put("日期选择_天","cssSelector;;body>div:last-child .el-picker-panel__content tr:nth-child(4)>td:nth-child(4)");
POString.put("日期选择_年","cssSelector;;body>div:last-child .el-picker-panel__body [aria-label=后一年]");
POString.put("日期选择_月","cssSelector;;body>div:last-child .el-picker-panel__body [aria-label=下个月]");
POString.put("流程节点审批1","xpath;;(//*[@class='processConfig__node-wrap']//label/following-sibling::div//input[not(contains(@readonly,'readonly'))])[1]");
POString.put("流程节点审批2","xpath;;(//*[@class='processConfig__node-wrap']//label/following-sibling::div//input[not(contains(@readonly,'readonly'))])[2]");
POString.put("流程节点审批3","xpath;;(//*[@class='processConfig__node-wrap']//label/following-sibling::div//input[not(contains(@readonly,'readonly'))])[3]");
POString.put("流程节点审批4","xpath;;(//*[@class='processConfig__node-wrap']//label/following-sibling::div//input[not(contains(@readonly,'readonly'))])[4]");
POString.put("流程节点审批5","xpath;;(//*[@class='processConfig__node-wrap']//label/following-sibling::div//input[not(contains(@readonly,'readonly'))])[5]");
POString.put("确定","xpath;;//*[text()=' 确定 ']");
POString.put("编辑页标题0","cssSelector;;[prop='viewData.0.value.NAME']");
POString.put("编辑页标题1","cssSelector;;[prop='viewData.1.value.NAME']");
POString.put("编辑页点击空10","xpath;;//*[@for='viewData.10.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空11","xpath;;//*[@for='viewData.11.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空12","xpath;;//*[@for='viewData.12.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空13","xpath;;//*[@for='viewData.13.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空14","xpath;;//*[@for='viewData.14.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空15","xpath;;//*[@for='viewData.15.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空16","xpath;;//*[@for='viewData.16.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空17","xpath;;//*[@for='viewData.17.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空18","xpath;;//*[@for='viewData.18.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空19","xpath;;//*[@for='viewData.19.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空2","xpath;;//*[@for='viewData.2.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空20","xpath;;//*[@for='viewData.20.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空21","xpath;;//*[@for='viewData.21.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空22","xpath;;//*[@for='viewData.22.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空23","xpath;;//*[@for='viewData.23.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空24","xpath;;//*[@for='viewData.24.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空25","xpath;;//*[@for='viewData.25.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空26","xpath;;//*[@for='viewData.26.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空27","xpath;;//*[@for='viewData.27.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空28","xpath;;//*[@for='viewData.28.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空29","xpath;;//*[@for='viewData.29.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空3","xpath;;//*[@for='viewData.3.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空4","xpath;;//*[@for='viewData.4.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空5","xpath;;//*[@for='viewData.5.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空6","xpath;;//*[@for='viewData.6.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空7","xpath;;//*[@for='viewData.7.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空8","xpath;;//*[@for='viewData.8.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页点击空9","xpath;;//*[@for='viewData.9.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页空0","xpath;;//*[@for='viewData.0.value']/parent::div[not(contains(@style,'display: none'))]//input[@readonly='readonly']");
POString.put("编辑页空22","xpath;;//*[@for='viewData.2.value']/parent::div[not(contains(@style,'display: none'))]//input[1]");
POString.put("编辑页输入空10","xpath;;//*[@for='viewData.10.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空11","xpath;;//*[@for='viewData.11.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空12","xpath;;//*[@for='viewData.12.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空13","xpath;;//*[@for='viewData.13.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空14","xpath;;//*[@for='viewData.14.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空15","xpath;;//*[@for='viewData.15.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空16","xpath;;//*[@for='viewData.16.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空17","xpath;;//*[@for='viewData.17.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空18","xpath;;//*[@for='viewData.18.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空19","xpath;;//*[@for='viewData.19.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空2","xpath;;//*[@for='viewData.2.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空20","xpath;;//*[@for='viewData.20.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空21","xpath;;//*[@for='viewData.21.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空22","xpath;;//*[@for='viewData.22.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空23","xpath;;//*[@for='viewData.23.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空24","xpath;;//*[@for='viewData.24.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空25","xpath;;//*[@for='viewData.25.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空26","xpath;;//*[@for='viewData.26.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空27","xpath;;//*[@for='viewData.27.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空28","xpath;;//*[@for='viewData.28.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空29","xpath;;//*[@for='viewData.29.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空3","xpath;;//*[@for='viewData.3.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空4","xpath;;//*[@for='viewData.4.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空5","xpath;;//*[@for='viewData.5.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空6","xpath;;//*[@for='viewData.6.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空7","xpath;;//*[@for='viewData.7.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空8","xpath;;//*[@for='viewData.8.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("编辑页输入空9","xpath;;//*[@for='viewData.9.value']/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("会签通过按钮","xpath;;//span[text()=' 会签通过 ']");
POString.put("流程节点审批6","xpath;;(//*[@class='processConfig__node-wrap']//label/following-sibling::div//input[not(contains(@readonly,'readonly'))])[6]");
POString.put("流程节点审批7","xpath;;(//*[@class='processConfig__node-wrap']//label/following-sibling::div//input[not(contains(@readonly,'readonly'))])[7]");
POString.put("编辑页输入空24_1","xpath;;//*[@for='viewData.24.value']/parent::div[not(contains(@style,'display: none'))]//input[not(contains(@readonly,'readonly'))]");
POString.put("编辑页输入空11_1","xpath;;//*[@for='viewData.11.value']/parent::div[not(contains(@style,'display: none'))]//input[not(contains(@readonly,'readonly'))]");
POString.put("下拉点击1","cssSelector;;[x-placement='bottom-start'] li:nth-child(1)");
POString.put("下拉点击2","cssSelector;;[x-placement='bottom-start'] li:nth-child(2)");
POString.put("列表","cssSelector;;div.el-table__body-wrapper");
POString.put("列表_名称","cssSelector;;.el-table__header-wrapper>.el-table__header span[title='名称']");
POString.put("列表行数","cssSelector;;div.el-table__body-wrapper tbody tr");
POString.put("弹框列表第一条","cssSelector;;div[class='el-table el-table--fit el-table--striped el-table--fluid-height el-table--scrollable-y el-table--enable-row-hover el-table--enable-row-transition'] tbody  tr:nth-child(1)");
POString.put("通知预览_关闭","xpath;;//span[contains(text(),'通知预览')]//..//i[@class='el-dialog__close el-icon el-icon-close']");
POString.put("列表第一条1","cssSelector;;tbody tr:nth-child(1)");
POString.put("新编辑页输入空1","xpath;;(//*[@class='el-form-item__label'])[1]/parent::div[not(contains(@style,'display: none'))]//input");
POString.put("新编辑页输入空2","xpath;;((//*[@class='el-form-item__label'])[2]/parent::div[not(contains(@style,'display: none'))]//input)[1]");
POString.put("新编辑页输入空3","xpath;;((//*[@class='el-form-item__label'])[3]/parent::div[not(contains(@style,'display: none'))]//input)[1]");
POString.put("新编辑页输入空5","xpath;;((//*[@class='el-form-item__label'])[5]/parent::div[not(contains(@style,'display: none'))]//input)[1]");
POString.put("上拉点击","cssSelector;;[x-placement='top-start'] .el-tree-node.is-expanded.is-focusable:not([style='display: none;'])");
POString.put("提交审核确定","xpath;;//div[@aria-label='提交审核']//span[contains(text(),'确定')]");
POString.put("滚动到是否出厂","cssSelector;;.is-scrolling-middle");
POString.put("今天","xpath;;//div[@class='el-picker-panel el-date-picker el-popper' and not(contains(@style,'display: none'))]//button[contains(text(),'今天')]");
POString.put("下拉点击22","xpath;;(//div[@x-placement='bottom-start']//div[@role='treeitem'])[2]");
POString.put("列表第一行第一列","cssSelector;;tbody tr:nth-child(1)>td:nth-child(1)");
POString.put("下拉点击21","xpath;;//*[@x-placement='bottom-start']//*[@role='treeitem' and not(contains(@style,'display: none'))][1]");
POString.put("新编辑页输入空6","xpath;;((//*[@class='el-form-item__label'])[6]/parent::div[not(contains(@style,'display: none'))]//input)[1]");
POString.put("列表刷新","cssSelector;;[gppm-ui=flowList__refresh]");
POString.put("审核意见","cssSelector;;div[aria-label=通过] .is-required textarea");
POString.put("富文本编辑3","xpath;;(//textarea)[3]");
POString.put("导出全选按钮","xpath;;//div[not(contains(@style,'display: none'))]/*[@aria-label='导出字段']//*[contains(text(),'全选')]/preceding-sibling::span");
POString.put("导出按钮","xpath;;//*[contains(text(),'导出')]/ancestor::button");
POString.put("导出确定按钮","xpath;;//div[not(contains(@style,'display: none'))]/*[@aria-label='导出字段']//*[text()=' 确定 ']");
POString.put("必填项校验","xpath;;//*[contains(text(),'必填项不能为空')]");
POString.put("新建按钮","xpath;;//*[contains(text(),'新建')]/ancestor::button");
POString.put("新编辑页输入空15","xpath;;((//*[@class='el-form-item__label'])[15]/parent::div[not(contains(@style,'display: none'))]//input)[1]");
POString.put("新编辑页输入空16","xpath;;((//*[@class='el-form-item__label'])[16]/parent::div[not(contains(@style,'display: none'))]//input)[1]");
POString.put("新编辑页输入空17","xpath;;((//*[@class='el-form-item__label'])[17]/parent::div[not(contains(@style,'display: none'))]//input)[1]");
POString.put("新编辑页输入空18","xpath;;((//*[@class='el-form-item__label'])[18]/parent::div[not(contains(@style,'display: none'))]//input)[1]");
POString.put("新编辑页输入空7","xpath;;((//*[@class='el-form-item__label'])[7]/parent::div[not(contains(@style,'display: none'))]//input)[1]");
POString.put("编辑按钮","xpath;;//*[contains(text(),'编辑')]/ancestor::button");
}
}