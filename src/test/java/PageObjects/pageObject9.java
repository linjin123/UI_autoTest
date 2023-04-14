package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject9 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("下拉菜单输入框","cssSelector;;div[aria-hidden=false].popper-select-tree  input[type=text]");
POString.put("下拉菜单选择第一项","cssSelector;;[aria-hidden=false] .el-tree-node");
POString.put("单选择第一个用户","cssSelector;;div.center__content div.el-table__body-wrapper tr:nth-child(1)  label>span>span");
POString.put("多选择第一个用户","cssSelector;;div.center__content div.el-table__body-wrapper tr:nth-child(1)  label>span>span");
POString.put("日期下一个月","cssSelector;;div[x-placement*=-start].el-date-picker div.el-date-picker__header button[aria-label=下个月]");
POString.put("日期具体日期","cssSelector;;div[x-placement*=-start].el-date-picker div.el-picker-panel__content tbody tr:nth-child(5) td:nth-child(4)");
POString.put("确认按钮","cssSelector;;button[gppm-ui=selectUser__ok]");
POString.put("选择日期弹框","cssSelector;;div[x-placement*=-start].el-date-picker");
POString.put("选择用户搜索按钮","cssSelector;;div[aria-label=选择用户] i.el-icon-search");
POString.put("选择用户输入框","cssSelector;;div[aria-label=选择用户] input.el-input__inner");
}
}
