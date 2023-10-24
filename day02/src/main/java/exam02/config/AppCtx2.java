package exam02.config;

import exam02.models.member.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx2 {
    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }
    @Bean
    public JoinValidator joinValidator(){
        return new JoinValidator();
    }

    @Bean
    public JoinService joinService(){
        return new JoinService();
    }

    @Bean
    public InfoService infoService(){
        return new InfoService();
    }




}
