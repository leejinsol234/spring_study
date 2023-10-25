package exam05.main;

import exam05.config.AppCtx;
import exam05.Message;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex02 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Message m1 = ctx.getBean(Message.class);
        Message m2 = ctx.getBean(Message.class);

        System.out.println(m1 == m2); //false
        //의존성을 주입할 때마다, 또는 조회할 때마다 새로운 객체를 생성하기 때문에
        ctx.close();
    }
}
