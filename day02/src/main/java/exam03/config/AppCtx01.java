package exam03.config;

import exam03.models.member.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppCtx02.class)
public class AppCtx01 {
    @Bean
    //@Qualifier("mDao") 이름을 따로 설정할 수도 있음
    public MemberDao memberDao(){
        return new MemberDao();
    }

    /*
    @Bean
    public MemberDao memberDao2(){
        return new MemberDao();
    }

     */
}
