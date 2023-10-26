package exam02.main;

import exam02.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import exam02.aopex.*;

public class Ex02 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator cal = ctx.getBean(Calculator.class);
        long result = cal.factorial(10);
        System.out.printf("cal=%d%n", result);

        result = cal.factorial(10);
        System.out.printf("cal=%d%n", result);

        result = cal.factorial(10);
        System.out.printf("cal=%d%n", result);

        result = cal.factorial(10);
        System.out.printf("cal=%d%n", result);

        result = cal.factorial(10);
        System.out.printf("cal=%d%n", result);

        result = cal.factorial(10);
        System.out.printf("cal=%d%n", result);

        result = cal.factorial(10);
        System.out.printf("cal=%d%n", result);
        ctx.close();
    }
}
