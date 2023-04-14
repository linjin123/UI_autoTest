package PageObjects;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
public class pageObject11 {
public static Map<String,By > PO= new HashMap<String,By>();
public static Map<String,String > POString= new HashMap<String,String>();
static{
POString.put("中试代表","cssSelector;;[for='viewData.1.owners.2.ownerUsers'] +div input.user-picker__input");
POString.put("人员搜索框","cssSelector;;[placeholder=请输入搜索人员]");
POString.put("人员搜索框放大镜","cssSelector;;.el-icon-circle-close+i");
POString.put("其他(部门或模块负责人)","cssSelector;;[for='viewData.1.owners.3.ownerUsers'] +div input.user-picker__input");
POString.put("提交按钮","cssSelector;;[gppm-ui^=createFlow__button__]");
POString.put("提示确认按钮","cssSelector;;div.el-message-box button.el-button--primary");
POString.put("流程发布后接收者","cssSelector;;[for='viewData.3.owners.0.ownerUsers'] +div input.user-picker__input");
POString.put("清除用户按钮","cssSelector;;button[gppm-ui=selectUser__clear]");
POString.put("生产计划专员","cssSelector;;[for='viewData.2.owners.0.ownerUsers'] +div input.user-picker__input");
POString.put("确定","cssSelector;;[gppm-ui=selectUser__ok]");
POString.put("财务代表","cssSelector;;[for='viewData.1.owners.3.ownerUsers'] +div input.user-picker__input");
POString.put("选择人员","cssSelector;;.center__content .el-table__body .el-checkbox__input");
POString.put("项目管理","cssSelector;;[for='viewData.1.owners.1.ownerUsers'] +div input.user-picker__input");
POString.put("项目经理","cssSelector;;[for='viewData.1.owners.0.ownerUsers'] +div input.user-picker__input");
}
}
