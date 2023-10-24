package exam03.config;

import exam03.models.member.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppCtx02.class)
public class AppCtx01 {
    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }

    @Bean
    public MemberDao memberDao2(){
        return new MemberDao();
    }
}
