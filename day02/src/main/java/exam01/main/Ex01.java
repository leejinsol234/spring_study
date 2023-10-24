package exam01.main;

import exam01.Greet;
import exam01.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        Greet g1 = ctx.getBean("greet", Greet.class);
        //name은 Bean객체의 메서드명, Bean객체가 반환하는 클래스
        g1.hello("이이름");

        Greet g2 = ctx.getBean("greet", Greet.class);
        System.out.println(g1 == g2); //true -> singletone으로 객체를 관리하므로 같은 객체를 주입했기 때문에 주소값도 같다.

        ctx.close();
    }
}
