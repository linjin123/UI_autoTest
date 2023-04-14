package Framework;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import static Framework.GlobalVariable.*;

public class MyRetryAnalyzer implements IRetryAnalyzer {
    private int count = 1;
    private int max_count = maxRetry;

    @Override
    public boolean retry(ITestResult result) {
        System.out.println("步骤："+outputtextList.get(result.getTestContext().getSuite().getName()).getMethodName()+"，第"+count+"次失败");
        max_count=maxRetry;
        if (count <= max_count && stepRerun.equalsIgnoreCase("true")) {
            count++;
            result.setAttribute("skipReport","true");
            return true;
        }
        count=1;
        return false;
    }
}
