package exam03.config;

import exam03.models.member.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx02 {
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
