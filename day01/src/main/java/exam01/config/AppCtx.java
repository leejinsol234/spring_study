package exam01.config;

import exam01.Greet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정 클래스임을 spring container에게 알려주는 어노테이션
public class AppCtx {
    @Bean //스프링이 관리할 객체임을 알려주는 어노테이션
    public Greet greet(){
        return new Greet();
    }
}
