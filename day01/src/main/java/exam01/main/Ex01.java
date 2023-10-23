package exam01.main;

import exam01.Greet;
import exam01.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        //AnnotationConfigApplicationContext -> spring container
        //매개변수가 AppCtx.class인 이유는 대상 객체를 담아서 관리하기 때문에 설정 클래스 정보를 확인해야 하므로
        Greet g1 = ctx.getBean("greet", Greet.class); //getBean: 컨테이너에 담긴 객체를 꺼내옴. name은 AppCtx에서 Bean이 되는 메서드명
        g1.hello("이이름");

        ctx.close();
    }
}
