package Framework;

import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyRetryListener implements IAnnotationTransformer {

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

        IRetryAnalyzer retry = annotation.getRetryAnalyzer();
        if (retry == null) {
            annotation.setRetryAnalyzer(MyRetryAnalyzer.class);  // 这个类名一定要和上方的对上

        }
    }
}
