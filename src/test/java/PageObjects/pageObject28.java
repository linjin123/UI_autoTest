package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject28 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("列表标题","cssSelector;;.gantt_grid_scale>div");
POString.put("发布","cssSelector;;.flowtab.tabs-layout__container .flowForm__head>.el-button--primary");
POString.put("发布2","xpath;;//*[text()='发布']");
POString.put("提交","xpath;;//*[text()='提交']");
POString.put("提交2","xpath;;//*[text()=' 提交 ']");
POString.put("收件人","xpath;;//*[contains(text(),'收件人')]/following::input[1]");
POString.put("正文编辑","xpath;;//label[contains(text(),'正文')]/following-sibling::div//textarea");
POString.put("确定","xpath;;//*[@class='flowtab']//*[text()=' 确定 ']");
POString.put("立项任务_资源匹配成员页确定","cssSelector;;[aria-label=资源匹配成员] .el-dialog__footer .el-button.el-button--primary");
POString.put("编辑","cssSelector;;.flowtab.tabs-layout__container .flowForm__head>[class=el-button]");
POString.put("转办","xpath;;//*[text()=' 转办 ']");
POString.put("需求","cssSelector;;.gantt_grid_data [aria-label~=确定需求] [data-column-name='GANTT_CTRL']");
POString.put("项目计划名称","cssSelector;;tbody>tr>td div[class='ellipsis'] a");
POString.put("复制历史成员","xpath;;//span[text()='复制历史成员']");
POString.put("删除默认责任人","xpath;;//*[@for='viewData.11.value']/parent::div[not(contains(@style,'display: none'))]//span//i");
POString.put("保存","xpath;;//*[text()=' 保存 ']");
POString.put("发布3","xpath;;//*[text()=' 发布 ']");
}
}
