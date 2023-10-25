package exam05.main;

import exam05.config.*;
import exam05.Message;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
    public static void main(String[] args) {
        //객체 생성(Bean) -> 의존 설정(Autowired,의존성 주입) -> 초기화(초기 설정)
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        //모든 객체의 참조가 해제되며 소멸
        ctx.close();

    }
}
