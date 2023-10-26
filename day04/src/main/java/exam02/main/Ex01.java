package exam02.main;

import exam02.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import exam02.aopex.*;

public class Ex01 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator cal = ctx.getBean(RecCalculator.class);
        //AppCtx에서 @EnableAspectJAutoProxy(proxyTargetClass = true)설정을 true로 바꿈으로써 가능
        long result = cal.factorial(10);
        System.out.printf("cal: %d%n", result);

        ctx.close();
    }
}
